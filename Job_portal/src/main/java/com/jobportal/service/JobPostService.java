package com.jobportal.service;

import com.jobportal.dto.JobPostDto;

import java.util.List;

public interface JobPostService {

       // post JobPost details
    JobPostDto createJobPost(JobPostDto jobPostDto, Long userId);

    // get by id
    JobPostDto getJobPostById(Long id);

    // Get all JobPosts
    List<JobPostDto> getAllJobPosts();

    // Update a JobPost by its id
    JobPostDto updateJobPost(Long id, JobPostDto jobPostDto);
}
