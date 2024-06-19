package bg.temps.jobportal.configuration;

import bg.temps.jobportal.repositories.RoleRepository;
import bg.temps.jobportal.repositories.UserRepository;
import bg.temps.jobportal.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
        @Autowired
        private UserRepository userRepository;
        @Autowired
        private RoleRepository roleRepository;
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = userRepository.findUserByEmail(username);
            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }
            AppUserDetails appUserDetails=new AppUserDetails(user);
            return appUserDetails;
            /*new org.springframework.security.core.userdetails.User(
                    user.getEmail(), user.getPassword(),getAuthorities());*/
        }

/*        private Collection<? extends GrantedAuthority> getAuthorities() {
            List<Role> roles=roleRepository.findAll();
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for(Role role : roles){
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }
            return authorities;
        }*/
    }



