package bg.temps.jobportal.repositories;

import bg.temps.jobportal.entities.Company;
import bg.temps.jobportal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findUserByEmail(String incomingEmail);
    public User findUserById(long id);
    public User findByEmailIgnoreCaseAndPassword (String incomingEmail, String incomingPassword);

    public void deleteByCompany(Company company);



}
