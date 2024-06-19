package bg.temps.jobportal.repositories;

import bg.temps.jobportal.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
    public Company findCompanyByNameIgnoreCase(String name);
    public Company findCompanyById(long id);

}
