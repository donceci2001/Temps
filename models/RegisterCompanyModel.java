package bg.temps.jobportal.models;

import bg.temps.jobportal.entities.Company;
import bg.temps.jobportal.entities.Role;
import bg.temps.jobportal.entities.User;
import lombok.Data;

@Data
public class RegisterCompanyModel {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean status;
    private long roleid;
    private String companyName;

    public User dissemble() {
        Company company=new Company();
        Role role=new Role();
        User user = new User();
        user.setEmail(this.email);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setPassword(this.password);
        user.setCompany(company);
        user.setRole(role);
        return user;
    }
}
