package com.jobportal.controller;

import com.jobportal.dto.JobPostDto;
import com.jobportal.exception.JobPostNotFoundByIdException;
import com.jobportal.service.JobPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JobPostController {

    @Autowired
    private JobPostService jobPostService;


    @PostMapping("/post")
    public ResponseEntity<JobPostDto>createJobPost(@RequestBody JobPostDto jobPostDto, @RequestParam Long userId)
    {
        JobPostDto jobPost = jobPostService.createJobPost(jobPostDto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(jobPost);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<JobPostDto>getJobPostById(@PathVariable Long id)
    {
        try {
            JobPostDto jobPostById = jobPostService.getJobPostById(id);
            return  ResponseEntity.status(HttpStatus.FOUND).body(jobPostById);

        }catch (RuntimeException e)
        {
            throw new JobPostNotFoundByIdException("Job Post Not Found By this id" +id);
        }
    }


    @GetMapping("/getAll")
    public ResponseEntity<List<JobPostDto>>getAllJobPost()
    {
        try {
            List<JobPostDto> allJobPosts = jobPostService.getAllJobPosts();
            if (allJobPosts.isEmpty())
            {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }else {
                return ResponseEntity.ok(allJobPosts);
            }
        }catch (RuntimeException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<JobPostDto> updateJobPost(@RequestBody JobPostDto jobPostDto, @PathVariable Long id)
    {
        try
        {
            JobPostDto jobPostDto1 = jobPostService.updateJobPost(id, jobPostDto);
            return ResponseEntity.ok(jobPostDto1);
        }catch (RuntimeException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


}




