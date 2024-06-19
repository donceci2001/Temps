package bg.temps.jobportal.models;

import bg.temps.jobportal.entities.ApplyJob;
import lombok.Data;

@Data
public class ListOfUsersApplyJobs {
    private long userId;
    private String email;
    private long applyJobId;
    public ListOfUsersApplyJobs assemble(ApplyJob applyJob){
        ListOfUsersApplyJobs listOfUsersApplyJobs=new ListOfUsersApplyJobs();
        listOfUsersApplyJobs.setUserId(applyJob.getUser().getId());
        listOfUsersApplyJobs.setEmail(applyJob.getUser().getEmail());
        listOfUsersApplyJobs.setApplyJobId(applyJob.getId());
        return listOfUsersApplyJobs;
    }
}
