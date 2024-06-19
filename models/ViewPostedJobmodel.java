package bg.temps.jobportal.models;

import bg.temps.jobportal.entities.PostJob;
import lombok.Data;

@Data
public class ViewPostedJobmodel {

    private String title;
    private String description;
    private java.time.LocalDateTime date;
    private long salary;
    private String address;
    private String company;
    public ViewPostedJobmodel assemble(PostJob postJob){
        ViewPostedJobmodel viewPostedJobmodel =new ViewPostedJobmodel();
        viewPostedJobmodel.setTitle(postJob.getJob().getTitle());
        viewPostedJobmodel.setDescription(postJob.getJob().getDescription());
        viewPostedJobmodel.setAddress(postJob.getJob().getAddress());
        viewPostedJobmodel.setSalary(postJob.getJob().getSalary());
        viewPostedJobmodel.setDate(postJob.getJob().getDate());
        viewPostedJobmodel.setCompany(postJob.getCompany().getName());
        return viewPostedJobmodel;
    }



}
