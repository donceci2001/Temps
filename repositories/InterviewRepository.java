package bg.temps.jobportal.repositories;

import bg.temps.jobportal.entities.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepository extends JpaRepository<Interview,Long> {
    public Interview findInterviewByApplyJob_IdAndInterviewStatus_id(long ApplyJobId,long InterviewStatusId);

}
