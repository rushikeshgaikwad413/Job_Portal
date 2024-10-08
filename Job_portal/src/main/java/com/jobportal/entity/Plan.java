package com.jobportal.entity;

import com.jobportal.dto.PlanDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "plans")
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "plan_name", nullable = false)
    private String planName;

    @Column(name = "posts_per_month", nullable = false)
    private Integer postsPerMonth;

    @Column(nullable = false)
    private String price;

    @Column(nullable = false)
    private Integer duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

      @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

   @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;


    public Plan(PlanDto planDto)
    {
        this.id=planDto.getId();
        this.planName=planDto.getPlanName();
        this.postsPerMonth=planDto.getPostsPerMonth();
        this.price=planDto.getPrice();
        this.createdAt=planDto.getCreatedAt();
        this.updatedAt=planDto.getUpdatedAt();
        this.duration=planDto.getDuration();
    }



}
