package bg.temps.jobportal.models;

import bg.temps.jobportal.entities.Job;
import lombok.Data;

@Data
public class JobModel {
    private long jobId;
    private String title;
    private String companyName;
    private String description;
    private java.time.LocalDateTime date;
    private long salary;
    private String address;


    public JobModel assemble(Job job)
    {
      JobModel jobModel=new JobModel();
      jobModel.setJobId(job.getId());
      jobModel.setTitle(job.getTitle());
      jobModel.setCompanyName(job.getCompany().getName());
      jobModel.setDescription(job.getDescription());
      jobModel.setAddress(job.getAddress());
      jobModel.setDate(job.getDate());
      jobModel.setSalary(job.getSalary());
      return jobModel;
    }


}
