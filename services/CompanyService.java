package bg.temps.jobportal.services;

import bg.temps.jobportal.entities.*;
import bg.temps.jobportal.models.*;
import bg.temps.jobportal.repositories.*;
import bg.temps.jobportal.repositories.InterviewstatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private PostJobRepository postJobRepository;
    @Autowired
    private ApplyJobRepository applyJobRepository;
    @Autowired
    private InterviewRepository interviewRepository;
    @Autowired
    private InterviewstatusRepository interviewstatusRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    public String addCompanyUser(CompanyUserModel companyUserModel) {
        User checkDetail = userRepository.findUserById(companyUserModel.getUserId());
        String findUser = companyUserModel.getEmail();
        User isUserExist = userRepository.findUserByEmail(findUser);
        if (isUserExist != null) {
            return "User Already Exist";
        } else {
            Role role = roleRepository.findByRoleNameIgnoreCase(companyUserModel.getRole());
            role.setId(role.getId());
            Company company = new Company();
            company.setId(checkDetail.getCompany().getId());
            User user = companyUserModel.dissemble();
            user.setRole(role);
            user.setCompany(company);
            userRepository.save(user);
            return "User add";
        }
    }

    public String postNewJob(PostJobModel postJobModel) throws Exception {
        User checkUserDetail = userRepository.findUserById(postJobModel.getUserId());
        Company getCompanyName = companyRepository.findCompanyById(checkUserDetail.getCompany().getId());
        if (getCompanyName != null) {
            Job job = postJobModel.dissemble();
            job.setCompany(getCompanyName);
            jobRepository.save(job);
            PostJob postJob = new PostJob();
            postJob.setCompany(getCompanyName);
            postJob.setJob(job);
            postJobRepository.save(postJob);
            return "Job Post successfully";
        } else {
            return "User Permission not allowed";
        }
    }

    public List<ViewPostedJobmodel> showPostedJob(ParseObjectModel parseObjectModel) throws Exception {
        User user = userRepository.findUserById(parseObjectModel.getId());
        List<PostJob> listOfPostJob = postJobRepository.findPostJobByCompany_Id(user.getCompany().getId());
        List<ViewPostedJobmodel> listOfPostedJobs = new ArrayList<>();
        if (listOfPostJob != null) {
            for (PostJob postJob : listOfPostJob) {
                listOfPostedJobs.add(new ViewPostedJobmodel().assemble(postJob));
            }
            return listOfPostedJobs;
        } else {
            throw new Exception("No Job Found");
        }
    }

    public List<ListOfUsersApplyJobs> showUsersApplyJobs(ParseObjectModel parseObjectModel) throws Exception {
        List<ApplyJob> listOfApplyJobs = applyJobRepository.findApplyJobByPostJob_job_Id(parseObjectModel.getId());
        List<ListOfUsersApplyJobs> listOfUsersApplyJobs = new ArrayList<>();
        if (listOfApplyJobs != null) {
            for (ApplyJob applyJob : listOfApplyJobs) {
                listOfUsersApplyJobs.add(new ListOfUsersApplyJobs().assemble(applyJob));
            }
            return listOfUsersApplyJobs;
        } else {
            throw new Exception("Something went wrong");
        }
    }

    public String SelectApplication(InterviewModel interviewModel) throws IOException {
        ApplyJob applyJob = applyJobRepository.findApplyJobById(interviewModel.getApplyJobId());
        InterviewStatus isInterviewStatusExist = interviewstatusRepository.findInterviewStatusById(interviewModel.getInterviewStatusId());
        Interview isInterviewExist = interviewRepository.findInterviewByApplyJob_IdAndInterviewStatus_id(interviewModel.getApplyJobId(), interviewModel.getInterviewStatusId());
        if (isInterviewExist != null) {
            return "Status Exist";
        } else {
            Interview interview = new Interview();
            interview.setApplyJob(applyJob);
            interview.setInterviewStatus(isInterviewStatusExist);
            interviewRepository.save(interview);
            return "Successful";
        }
    }

    public Object SendReview(CreateReviewModel createReviewModel) throws IOException {
        PostJob postJob=postJobRepository.findPostJobByJob_Id(createReviewModel.getJobId());
        ApplyJob applyJob=applyJobRepository.findApplyJobByUser_IdAndPostJob_Id(createReviewModel.getUserId(),postJob.getId());
        Review CheckReviewExist=reviewRepository.findByPostJob_Id(postJob.getId());
        if (CheckReviewExist!=null && applyJob!=null){
            return "Feedback Already Send";
        }
        else if (postJob!=null && applyJob !=null ){
                Review review=new Review();
                review.setRating(createReviewModel.getRating());
                review.setFeedback(createReviewModel.getFeedback());
                review.setPostJob(postJob);
                reviewRepository.save(review);
                return "Feedback Send";
            }
        return "something went wrong";
        }
}
