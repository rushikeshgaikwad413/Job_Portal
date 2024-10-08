package com.jobportal.dto;

import com.jobportal.entity.Plan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlanDto {

    private Integer id;
    private String planName;
    private Integer postsPerMonth;
    private String price;
    private Integer duration;
    private Date createdAt;
    private Date updatedAt;
    private Long userId;



    public PlanDto(Plan plan)
    {
        this.id=plan.getId();
         this.planName=plan.getPlanName();
         this.postsPerMonth=plan.getPostsPerMonth();
         this.price=plan.getPrice();
         this.duration=plan.getDuration();
         this.createdAt=plan.getCreatedAt();
         this.updatedAt=plan.getUpdatedAt();
         this.userId=plan.getUser().getId();
    }

    public PlanDto(Integer id, String planName, Integer postsPerMonth,
                   String price, Integer duration, Date createdAt, Date updatedAt)
    {
        this.id = id;
        this.planName = planName;
        this.postsPerMonth = postsPerMonth;
        this.price = price;
        this.duration = duration;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userId = userId;

    }
}
