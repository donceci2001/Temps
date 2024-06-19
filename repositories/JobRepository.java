package bg.temps.jobportal.repositories;

import bg.temps.jobportal.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface JobRepository extends JpaRepository<Job,Long> {
    public List<Job> findByTitleContainingIgnoreCaseOrCompany_NameContainingIgnoreCaseOrAddressContainingIgnoreCase(String title,String company,String Address);
    public List<Job> findByAddressContainingIgnoreCase(String address);

    public Job findJobById(Long IncomingJobId);
    public  List<Job> findJobByCompany_Id(long id);

}
