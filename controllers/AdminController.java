package bg.temps.jobportal.controllers;

import bg.temps.jobportal.models.AdminModel;
import bg.temps.jobportal.models.CompanyAdminModel;
import bg.temps.jobportal.models.CompanyModel;
import bg.temps.jobportal.models.ParseObjectModel;
import bg.temps.jobportal.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @PostMapping(path = "/add/company")
    public  ResponseEntity<String> addCompany(@RequestBody CompanyModel companyModel){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(adminService.addCompany(companyModel));
    }
    @GetMapping(path = "/view/company")
    public ResponseEntity<List<CompanyModel>> viewCompany(){
        return ResponseEntity.ok().body(adminService.showAllCompany());
    }
    @PostMapping(path = "/add/administration")
    public ResponseEntity<String> addCompanyAdmin(@RequestBody CompanyAdminModel companyAdminModel){
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(adminService.addCompanyAdmin(companyAdminModel));
    }
    @GetMapping(path = "/view/administration")
    public ResponseEntity<List<AdminModel>> viewUser(){
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(adminService.viewCompanyUser());
    }
    @DeleteMapping(path = "/remove/{companyId}")
    public ResponseEntity<String>removeCompany(@PathVariable("companyId") long id){
        return ResponseEntity
                .ok()
                .body(adminService.removeCompany(id));

    }
    @PutMapping(path = "/update/company")
    public ResponseEntity<String >updateCompany(@RequestBody CompanyModel companyModel){
        return ResponseEntity
                .ok()
                .body(adminService.updateCompanyDetails(companyModel));

    }
    @DeleteMapping(path = "/remove/user")
    public ResponseEntity<String>removeUser(@RequestBody ParseObjectModel parseObjectModel){
        return ResponseEntity
                .ok()
                .body(adminService.removeUser(parseObjectModel));

    }
    @PutMapping(path = "/update/user")
    public ResponseEntity<String>updateUser(@RequestBody AdminModel adminModel){
        return ResponseEntity
                .ok()
                .body(adminService.updateUserDetails(adminModel));

    }
}
