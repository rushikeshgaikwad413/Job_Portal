package com.jobportal.dto;

import com.jobportal.entity.JobPost;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JobPostDto {

    private Long id;
    private String organizationName;
    private String position;
    private int vacancy;
    private double salary;
    private String jobDescription;
    private String workShift;
    private String education;
    private long contact;
    private String address;
    private Long userId;

    public JobPostDto(JobPost jobPost) {
        this.id = jobPost.getId();
        this.organizationName = jobPost.getOrganizationName();
        this.position = jobPost.getPosition();
        this.vacancy = jobPost.getVacancy();
        this.salary = jobPost.getSalary();
        this.jobDescription = jobPost.getJobDescription();
        this.workShift = jobPost.getWorkShift();
        this.education = jobPost.getEducation();
        this.contact = jobPost.getContact();
        this.address = jobPost.getAddress();
        this.userId = jobPost.getUser().getId();
    }
}
