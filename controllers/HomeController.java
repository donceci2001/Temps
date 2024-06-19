package bg.temps.jobportal.controllers;

import bg.temps.jobportal.models.FindJobModel;
import bg.temps.jobportal.models.JobModel;
import bg.temps.jobportal.models.JobResponseModel;
import bg.temps.jobportal.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/home")
public class HomeController {
  @Autowired
  private HomeService homeService;
    @GetMapping(path = "/job")
    public ResponseEntity <JobResponseModel> showAllJobs(@RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
                                                         @RequestParam(value = "pageSize",defaultValue ="8",required = false)Integer pageSize) throws RuntimeException {
        return ResponseEntity
                .ok()
                .body(homeService.showAllJobs(pageNumber,pageSize));
    }
    @PostMapping(path = "job/{jobId}")
    public ResponseEntity<JobModel>showJob(@PathVariable("jobId") long id){
        return ResponseEntity
                .ok()
                .body(homeService.showJob(id));

    }

    @PostMapping(path = "findjob")
    public ResponseEntity<List<JobModel>> findJobByTitle(@RequestBody FindJobModel findJobModel)throws RuntimeException {
        return ResponseEntity
                .ok()
                .body(homeService.showAllJobsByTitle(findJobModel));
    }





}