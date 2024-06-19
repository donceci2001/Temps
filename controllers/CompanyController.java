package bg.temps.jobportal.controllers;

import bg.temps.jobportal.models.*;
import bg.temps.jobportal.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/company")
public class CompanyController {
    @Autowired
    public CompanyService companyService;

    @PostMapping(path = "/add/user")
    public ResponseEntity<String> addCompanyUser(@RequestBody CompanyUserModel companyUserModel){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(companyService.addCompanyUser(companyUserModel));
    }
    @PostMapping(path = "/postjob")
    public ResponseEntity<String> postJob(@RequestBody PostJobModel postJobModel) throws Exception {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(companyService.postNewJob(postJobModel));
    }
    @PostMapping(path = "view/postjob")
    public ResponseEntity<List<ViewPostedJobmodel>> viewUserPostJob(@RequestBody ParseObjectModel parseObjectModel) throws Exception {
        return ResponseEntity
                .ok()
                .body(companyService.showPostedJob(parseObjectModel));
    }

    @PostMapping(path = "view/postjob/application")
    public ResponseEntity<List<ListOfUsersApplyJobs>> ListOfUsersApplyJob(@RequestBody ParseObjectModel parseObjectModel) throws Exception {
        return ResponseEntity
                .ok()
                .body(companyService.showUsersApplyJobs(parseObjectModel));
    }
    @PostMapping(path = "view/postjob/select")
    public ResponseEntity<String> applicationStatus(@RequestBody InterviewModel interviewModel) throws IOException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(companyService.SelectApplication(interviewModel));
    }

    @PostMapping(path = "view/postjob/feedback/send")
    public ResponseEntity<Object> sendFeedback(@RequestBody CreateReviewModel createReviewModel) throws IOException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(companyService.SendReview(createReviewModel));
    }



}