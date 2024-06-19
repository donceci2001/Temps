package bg.temps.jobportal.models;

import bg.temps.jobportal.entities.User;
import lombok.Data;

@Data
public class UserLoginModel {
    private long id;
    private String email;
    private String RoleName;
    private long RoleId;
    private JwtAuthResponse jwtAuthResponse;

    public UserLoginModel assemble(User user){
        UserLoginModel userLoginModel=new UserLoginModel();
        userLoginModel.setId(user.getId());
        userLoginModel.setEmail(user.getEmail());
        userLoginModel.setRoleName(user.getRole().getRoleName());
        userLoginModel.setRoleId(user.getRole().getId());
        userLoginModel.setJwtAuthResponse(this.jwtAuthResponse);
        return userLoginModel;
    }

}
