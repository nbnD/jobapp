package com.flutterjunction.jobms.job;

import com.flutterjunction.jobms.job.dto.JobWithCompanyDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;


    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<JobWithCompanyDTO>> findAll() {
        return ResponseEntity.ok(jobService.findAll());

    }

//    @PostMapping
//    public ResponseEntity<String> createJob(@RequestBody Job job) {
//        Company c=job.getCompany();
//        if(c!=null){
//            jobService.createJob(job);
//            return new ResponseEntity<String>("Job added Successfully", HttpStatus.CREATED);
//        }else {
//            return new ResponseEntity<String>("Company doesnot exist", HttpStatus.BAD_REQUEST);
//        }

//    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        jobService.createJob(job);
        return new ResponseEntity<String>("Job added Successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        if (job != null)
            return new ResponseEntity<>(job, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id) {
        boolean isDeleted = jobService.deleteJobById(id);
        return isDeleted
                ? ResponseEntity.ok("Deleted Successfully")
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
        boolean isUpdated = jobService.updateJob(id, updatedJob);
        if (isUpdated)
            return new ResponseEntity<>("Job updated  SuccessFully", HttpStatus.OK);
        else
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }
}
