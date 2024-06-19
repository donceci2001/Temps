package bg.temps.jobportal.repositories;


import bg.temps.jobportal.entities.InterviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewstatusRepository extends JpaRepository<InterviewStatus,Long> {
    public InterviewStatus findInterviewStatusById(long id);
}
