package bg.temps.jobportal.models;

import bg.temps.jobportal.entities.Role;
import lombok.Data;

@Data
public class RoleModel {
    private long id;
    private String roleName;
    public Role dissemble()
    {
        Role role=new Role();
        role.setId(this.id);
        role.setRoleName(this.roleName);
        return role;
    }


}
