package bg.temps.jobportal.repositories;

import bg.temps.jobportal.entities.ApplyJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ApplyJobRepository extends JpaRepository<ApplyJob,Long> {
    public List<ApplyJob> findAllJobByUser_Id(long userId);
    public List<ApplyJob> findAllApplyJobByUser_Email(String email);
    public List<ApplyJob> findApplyJobByUser_Id(long id);
    public List<ApplyJob> findApplyJobByPostJob_job_Id(long id);
    public ApplyJob findApplyJobById(long id);
    public ApplyJob findApplyJobByUser_IdAndPostJob_Id(long UserId,long PostJobId);

    public ApplyJob findApplyJobByUser_EmailAndPostJob_Id(String email,long id);
}
