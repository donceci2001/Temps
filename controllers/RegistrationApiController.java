package bg.temps.jobportal.controllers;

import bg.temps.jobportal.models.LoginModel;
import bg.temps.jobportal.models.UserLoginModel;
import bg.temps.jobportal.models.UserRegistrationModel;
import bg.temps.jobportal.services.RegistrationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationApiController {
    @Autowired
    public RegistrationServiceImp registrationServiceImp;

    @PostMapping(path = "/signup")
    public ResponseEntity<String> signup(@RequestBody UserRegistrationModel userRegistrationModel)
    {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registrationServiceImp.saveuser(userRegistrationModel));
    }
    @PostMapping(path = "/login")
    public ResponseEntity<UserLoginModel> UserLogin(@RequestBody LoginModel request) throws Exception {

        return ResponseEntity.ok()
                .body(registrationServiceImp.isUserExist(request));

    }
    @PostMapping(path = "admin/signup")
    public ResponseEntity<String> registerAdmin(@RequestBody UserRegistrationModel userRegistrationModel)
    {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(registrationServiceImp.adminRegister(userRegistrationModel));
    }

}

  /*   UserLoginModel loggedInUser = registrationServiceImp.isUserExist(request);
        if (loggedInUser != null) {
            return ResponseEntity.ok(loggedInUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }*/