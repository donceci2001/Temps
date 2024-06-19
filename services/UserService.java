package bg.temps.jobportal.services;
import bg.temps.jobportal.entities.*;
import bg.temps.jobportal.models.ApplyJobModel;
import bg.temps.jobportal.models.Feedbackmodel;
import bg.temps.jobportal.models.ReviewModel;
import bg.temps.jobportal.models.ViewApplyJobModel;
import bg.temps.jobportal.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ApplyJobRepository applyJobRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private PostJobRepository postJobRepository;

    public List<ViewApplyJobModel> showAppliedJob(Long id) throws ConfigDataResourceNotFoundException {
        List<ViewApplyJobModel> viewApplyJobModels = new ArrayList<>();
        List<ApplyJob> applyJobList = applyJobRepository.findApplyJobByUser_Id(id);
        for (ApplyJob applyJob : applyJobList) {
            viewApplyJobModels.add(new ViewApplyJobModel().assemble(applyJob));
        }
        return viewApplyJobModels;
    }

    public String applyForJob(long id, ApplyJobModel applyJobModel) throws IOException {

        String incomingEmail=applyJobModel.getEmail();
        User user=userRepository.findUserByEmail(incomingEmail);
        Role role=roleRepository.findRoleById(user.getRole().getId());
        PostJob isPostJobExist=postJobRepository.findPostJobByJob_Id(id);
        ApplyJob checkApplyAlready= applyJobRepository.findApplyJobByUser_EmailAndPostJob_Id(incomingEmail,isPostJobExist.getId());
        if (checkApplyAlready!=null){

            return "Already Applied";
        }
        else {
            ApplyJob applyJob=new ApplyJob();
            applyJob.setUser(user);
            applyJob.setPostJob(isPostJobExist);
            applyJob.setDate(LocalDateTime.parse(LocalDateTime.now().toString()));
            applyJobRepository.save(applyJob);
            return "Apply Successfully";
        }

    }
    public Object ViewFeedback(Feedbackmodel feedbackmodel)throws IOException{
        PostJob postJob=postJobRepository.findPostJobByJob_Id(feedbackmodel.getJobId());
        ApplyJob applyJob=applyJobRepository.findApplyJobByUser_IdAndPostJob_Id(feedbackmodel.getUserId(),postJob.getId());
        if (postJob!=null && applyJob !=null ){
            Review review=reviewRepository.findByPostJob_Id(applyJob.getPostJob().getId());
            ReviewModel reviewModel=new ReviewModel();
            reviewModel.setId(review.getId());
            reviewModel.setRating(review.getRating());
            reviewModel.setFeedback(review.getFeedback());
            return reviewModel;
        }

        return "Something Went Wrong";
    }

}


