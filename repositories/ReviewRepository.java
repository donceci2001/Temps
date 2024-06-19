package bg.temps.jobportal.repositories;

import bg.temps.jobportal.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    public Review findByPostJob_Id(long id);

}
