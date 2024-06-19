package bg.temps.jobportal.controllers;

import bg.temps.jobportal.models.ApplyJobModel;
import bg.temps.jobportal.models.Feedbackmodel;
import bg.temps.jobportal.models.ViewApplyJobModel;
import bg.temps.jobportal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    public UserService userService;
    @PostMapping(path = "job/apply/{jobId}")
    public ResponseEntity<String> applyForJob(@PathVariable Long jobId,@RequestBody ApplyJobModel applyJobModel) throws IOException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.applyForJob(jobId, applyJobModel));
    }

    @PostMapping(path = "view/applied/{userId}")
    public ResponseEntity<List<ViewApplyJobModel>> showApplyJob(@PathVariable("userId")Long id ){
        return ResponseEntity
                .ok()
                .body(userService.showAppliedJob(id));
    }
    @PostMapping(path = "view/job/feedback")
    public ResponseEntity<Object> sendFeedback(@RequestBody Feedbackmodel feedbackmodel) throws IOException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.ViewFeedback(feedbackmodel));
    }


}
