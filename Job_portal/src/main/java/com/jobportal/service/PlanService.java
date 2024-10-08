package com.jobportal.service;

import com.jobportal.dto.PlanDto;

import java.util.List;

public interface PlanService {

    //post
    PlanDto createPlan(PlanDto planDto,Long userId);

    //update
    PlanDto updatePlan(Integer id, PlanDto planDto);

     //get by id
    PlanDto getPlanById(Integer id);

  // delete
    String deletePlan(Integer id);

    // get all
    List<PlanDto> getAllPlans();
}
