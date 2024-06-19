package bg.temps.jobportal.models;

import bg.temps.jobportal.entities.User;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
public class UserRegistrationModel {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean status;
    BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder();
    public User dissemble() {
        User user = new User();
        user.setEmail(this.email);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        return user;
    }
    public UserRegistrationModel assemble(User user) {
        UserRegistrationModel userRegistrationModel = new UserRegistrationModel();
        userRegistrationModel.setEmail(user.getEmail());
        userRegistrationModel.setFirstName(user.getFirstName());
        userRegistrationModel.setLastName(user.getLastName());
        userRegistrationModel.setPassword(user.getPassword());
        return userRegistrationModel;
    }
}

