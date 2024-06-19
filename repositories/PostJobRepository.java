package bg.temps.jobportal.repositories;

import bg.temps.jobportal.entities.PostJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostJobRepository extends JpaRepository<PostJob, Long> {
 public PostJob findPostJobByJob_Id(long id);
 public List<PostJob> findPostJobByCompany_Id(Long id);

}
