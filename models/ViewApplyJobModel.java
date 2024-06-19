package bg.temps.jobportal.models;

import bg.temps.jobportal.entities.ApplyJob;
import lombok.Data;

@Data
public class ViewApplyJobModel {
    private String title;
    private java.time.LocalDateTime date;
    private String company;
    public ViewApplyJobModel assemble(ApplyJob applyJob){
        ViewApplyJobModel viewApplyJobModel=new ViewApplyJobModel();
        viewApplyJobModel.setTitle(applyJob.getPostJob().getJob().getTitle());
        viewApplyJobModel.setDate(applyJob.getPostJob().getJob().getDate());
        viewApplyJobModel.setCompany(applyJob.getPostJob().getCompany().getName());
        return viewApplyJobModel;
    }
}
