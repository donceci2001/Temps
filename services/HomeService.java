package bg.temps.jobportal.services;
import bg.temps.jobportal.entities.Company;
import bg.temps.jobportal.entities.Job;
import bg.temps.jobportal.models.FindJobModel;
import bg.temps.jobportal.models.JobModel;
import bg.temps.jobportal.models.JobResponseModel;
import bg.temps.jobportal.repositories.ApplyJobRepository;
import bg.temps.jobportal.repositories.CompanyRepository;
import bg.temps.jobportal.repositories.JobRepository;
import bg.temps.jobportal.repositories.PostJobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeService {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplyJobRepository applyJobRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private PostJobRepository postJobRepository;
    public JobResponseModel showAllJobs(Integer pageNumber, Integer pageSize)throws RuntimeException {
        Pageable pageable= PageRequest.of(pageNumber, pageSize);
        Page<Job> pagePost=jobRepository.findAll(pageable);
        List<Job> allJobs=pagePost.getContent();
        List<JobModel> jobModel = new ArrayList<>();
        for (Job job : allJobs) {
            jobModel.add(new JobModel().assemble(job));
        }
        JobResponseModel jobResponseModel=new JobResponseModel();
        jobResponseModel.setContent(jobModel);
        jobResponseModel.setPageNumber(pagePost.getNumber());
        jobResponseModel.setPageSize(pagePost.getSize());
        jobResponseModel.setTotalElements(pagePost.getTotalElements());
        jobResponseModel.setTotalPage(pagePost.getTotalPages());
        jobResponseModel.setLastPage(pagePost.isLast());
        return jobResponseModel;
    }
    public JobModel showJob(long id) {
       Job job=jobRepository.findJobById(id);
       if (job!=null) {
           JobModel jobModel = new JobModel();
           return jobModel.assemble(job);
       }else throw new RuntimeException("No Job Found");
    }
    public List<JobModel> showAllJobsByTitle(FindJobModel findJobModel) {
        Company company=companyRepository.findCompanyByNameIgnoreCase(findJobModel.getTitle());
        List<JobModel> jobModel = new ArrayList<>();
        List<Job> listJobs = jobRepository.findByTitleContainingIgnoreCaseOrCompany_NameContainingIgnoreCaseOrAddressContainingIgnoreCase(findJobModel.getTitle(),findJobModel.getTitle(),findJobModel.getAddress());
        for (Job job : listJobs ) {
            jobModel.add(new JobModel().assemble(job));
        }
        return jobModel;
    }


}

