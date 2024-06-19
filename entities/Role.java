package bg.temps.jobportal.entities;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "role_name")
    private String roleName;
    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL)
    private List<User> user;

}
