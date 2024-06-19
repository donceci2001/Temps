package bg.temps.jobportal.services;

import bg.temps.jobportal.repositories.RoleRepository;
import bg.temps.jobportal.repositories.UserRepository;
import bg.temps.jobportal.security.JwtTokenHelper;
import bg.temps.jobportal.entities.Role;
import bg.temps.jobportal.entities.User;
import bg.temps.jobportal.models.JwtAuthResponse;
import bg.temps.jobportal.models.LoginModel;
import bg.temps.jobportal.models.UserLoginModel;
import bg.temps.jobportal.models.UserRegistrationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImp {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public String saveuser(UserRegistrationModel userRegistrationModel) {
        String inputRole = "User";
        String incomingEmail = userRegistrationModel.getEmail();
        User userExist = userRepository.findUserByEmail(incomingEmail);
        if (userExist != null) {
            return "User already exist with same email id!";
        } else {
            Role role = new Role();
            role = (roleRepository.findByRoleNameIgnoreCase(inputRole));
            User user = new User();
            user = userRegistrationModel.dissemble();
            user.setRole(role);
            userRepository.save(user);
            return "User saved successfully!";
        }
    }

    public UserLoginModel isUserExist(LoginModel request) throws Exception {
        this.authenticate(request.getEmail(), request.getPassword());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getEmail());
        JwtAuthResponse token = new JwtAuthResponse();
        token.setToken(this.jwtTokenHelper.generateToken(userDetails));
        User user = userRepository.findUserByEmail(request.getEmail());
        UserLoginModel response = new UserLoginModel();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setRoleName(user.getRole().getRoleName());
        response.setRoleId(user.getRole().getId());
        response.setJwtAuthResponse(token);
        return response;
    }

    private void authenticate(String email, String password) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        try {
            this.authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {

            throw new Exception("Invalid Username & Password");
        }
    }

    public String adminRegister(UserRegistrationModel userRegistrationModel) {
        String inputRole = "Admin";
        String incomingEmail = userRegistrationModel.getEmail();
        User userExist = userRepository.findUserByEmail(incomingEmail);
        if (userExist != null) {
            return "Admin already exist with same email id!";
        } else {
            Role role = new Role();
            role = (roleRepository.findByRoleNameIgnoreCase(inputRole));
            User user = new User();
            user = userRegistrationModel.dissemble();
            user.setRole(role);
            userRepository.save(user);
            return "Admin Register Successfully!";
        }
    }
}



