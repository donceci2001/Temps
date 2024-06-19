package bg.temps.jobportal.models;

import bg.temps.jobportal.entities.Job;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostJobModel {
    private  long userId;
    private String title;
    private String description;
    private java.time.LocalDateTime date;
    private long salary;
    private String address;
    public PostJobModel assemble(Job job){
        PostJobModel postJobModel =new PostJobModel();
        postJobModel.setTitle(job.getTitle());
        postJobModel.setDescription(job.getDescription());
        postJobModel.setAddress(job.getAddress());
        postJobModel.setDate(job.getDate());
        postJobModel.setSalary(job.getSalary());
        return postJobModel;
    }

    public Job dissemble(){
        Job job=new Job();
        job.setTitle(this.title);
        job.setDescription(this.description);
        job.setAddress(this.address);
        job.setDate(LocalDateTime.parse(LocalDateTime.now().toString()));
        job.setSalary(this.salary);
        return job;
    }


}
