package com.jobportal.controller;

import com.jobportal.dto.PlanDto;
import com.jobportal.exception.PlanNotFoundByIdException;
import com.jobportal.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PlanController {

    @Autowired
    private PlanService planService;

    @PostMapping("/add")
    public ResponseEntity<PlanDto>createPlan(@RequestBody PlanDto planDto, @RequestParam Long userId)
    {
        PlanDto plan = planService.createPlan(planDto,userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(plan);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PlanDto>getPlanById(@PathVariable Integer id)
    {
        try{
            PlanDto planById = planService.getPlanById(id);
             return ResponseEntity.status(HttpStatus.FOUND).body(planById);

        }catch (RuntimeException exception)
        {
            throw new PlanNotFoundByIdException("Plan Not Found By This Id"+id);
        }
    }



    @GetMapping("/all")
    public ResponseEntity<List<PlanDto>> getAllPlans() {
        try {
            List<PlanDto> allPlans = planService.getAllPlans();
            if (allPlans.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.ok(allPlans);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<PlanDto>updatePlan(@RequestBody PlanDto planDto,@PathVariable Integer id)
    {
       try {
           PlanDto planDto1 = planService.updatePlan(id, planDto);
           return ResponseEntity.ok(planDto1);
       }catch (RuntimeException e)
       {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String>deletePlan(@PathVariable Integer id)
    {
       try {
            planService.deletePlan(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted Sucessfully");

       }catch (RuntimeException e)
       {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Uncessfully");
       }
    }



}
