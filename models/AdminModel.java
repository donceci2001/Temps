package bg.temps.jobportal.models;

import bg.temps.jobportal.entities.User;
import lombok.Data;

@Data
public class AdminModel {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String companyName;
    public AdminModel assemble(User user){
        AdminModel adminModel=new AdminModel();
        adminModel.setFirstName(user.getFirstName());
        adminModel.setLastName(user.getLastName());
        adminModel.setEmail(user.getEmail());
        adminModel.setCompanyName(user.getCompany().getName());
        return adminModel;
    }
}
