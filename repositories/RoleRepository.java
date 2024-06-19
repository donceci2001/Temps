package bg.temps.jobportal.repositories;

import bg.temps.jobportal.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    public Role findByRoleNameIgnoreCase(String checkRole);
    public Role findRoleById(long id);
    public Role findRoleByUser_EmailIgnoreCase(String email);


}
