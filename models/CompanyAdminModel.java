package bg.temps.jobportal.models;

import bg.temps.jobportal.entities.User;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
public class CompanyAdminModel {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String companyName;
    BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
    public User dissemble(){
        User user=new User();
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setEmail(this.email);
        user.setPassword(bCryptPasswordEncoder.encode(this.password));
        return user;
    }
}
