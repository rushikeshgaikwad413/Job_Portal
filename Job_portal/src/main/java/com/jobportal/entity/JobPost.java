package com.jobportal.entity;

import com.jobportal.dto.JobPostDto;
import com.jobportal.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "job_posts")
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public JobPost(JobPostDto jobPostDto) {

            this.organizationName = jobPostDto.getOrganizationName();
            this.position = jobPostDto.getPosition();
            this.vacancy = jobPostDto.getVacancy();
            this.salary = jobPostDto.getSalary();
            this.jobDescription = jobPostDto.getJobDescription();
            this.workShift = jobPostDto.getWorkShift();
            this.education = jobPostDto.getEducation();
            this.contact = jobPostDto.getContact();
            this.address = jobPostDto.getAddress();
        }


    }

