package com.jobportal.serviceimpl;

import com.jobportal.dto.JobPostDto;
import com.jobportal.dto.PlanDto;
import com.jobportal.entity.JobPost;
import com.jobportal.entity.User;
import com.jobportal.exception.JobPostNotFoundByIdException;
import com.jobportal.exception.UserNotFoundException;
import com.jobportal.repository.JobPostRepository;
import com.jobportal.repository.UserRepository;
import com.jobportal.service.JobPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobPostServiceImpl  implements JobPostService {

    @Autowired
    private JobPostRepository jobPostRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public JobPostDto createJobPost(JobPostDto jobPostDto, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new UserNotFoundException("User Not Found By This Id" + userId));

        JobPost jobPost=new JobPost(jobPostDto);
          jobPost.setUser(user);

        JobPost save = jobPostRepository.save(jobPost);
       return new JobPostDto(save);

    }

    @Override
    public JobPostDto getJobPostById(Long id) {
        Optional<JobPost> byId = jobPostRepository.findById(id);
        if (byId.isPresent())
        {
            JobPost jobPost=byId.get();
            return new JobPostDto(jobPost);
        }
        else {
            throw  new RuntimeException("Job Post Not Found By This Id" +id);
        }

    }

    @Override
    public List<JobPostDto> getAllJobPosts() {
        List<JobPost> all = jobPostRepository.findAll();

        return all.stream().map(jobPost -> new JobPostDto(
                jobPost.getId(),
                jobPost.getOrganizationName(),
                jobPost.getPosition(),
                jobPost.getVacancy(),
                jobPost.getSalary(),
                jobPost.getJobDescription(),
                jobPost.getWorkShift(),
                jobPost.getEducation(),
                jobPost.getContact(),
                jobPost.getAddress(),
                jobPost.getUser().getId() // Map userId from User
        )).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public JobPostDto updateJobPost(Long id, JobPostDto jobPostDto) {
        JobPost jobPost = jobPostRepository.findById(id)
                .orElseThrow(() -> new JobPostNotFoundByIdException("Job Post Not Found By id: " + id));


        jobPost.setOrganizationName(jobPostDto.getOrganizationName());
        jobPost.setPosition(jobPostDto.getPosition());
        jobPost.setVacancy(jobPostDto.getVacancy());
        jobPost.setSalary(jobPostDto.getSalary());
        jobPost.setJobDescription(jobPostDto.getJobDescription());
        jobPost.setWorkShift(jobPostDto.getWorkShift());
        jobPost.setEducation(jobPostDto.getEducation());
        jobPost.setContact(jobPostDto.getContact());
        jobPost.setAddress(jobPostDto.getAddress());


        jobPostRepository.save(jobPost);

        return new JobPostDto(jobPost);
    }



}
