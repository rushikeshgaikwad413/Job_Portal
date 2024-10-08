package com.jobportal.serviceimpl;

import com.jobportal.dto.PlanDto;
import com.jobportal.entity.Plan;
import com.jobportal.entity.User;
import com.jobportal.exception.PlanNotFoundByIdException;
import com.jobportal.exception.UserNotFoundException;
import com.jobportal.repository.PlanRepository;
import com.jobportal.repository.UserRepository;
import com.jobportal.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
  public class PlanServiceImpl implements PlanService {

      @Autowired
      private PlanRepository planRepository;

      @Autowired
      private UserRepository userRepository;


      @Override
      public PlanDto createPlan(PlanDto planDto, Long userId) {

          User user=userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException("user not found with id"+ userId));

          Plan plan = new Plan(planDto);
          plan.setUser(user);

          Plan savedPlan = planRepository.save(plan);
          return new PlanDto(savedPlan);
      }



      @Override
    public PlanDto updatePlan(Integer id, PlanDto planDto) {
          Plan plan = planRepository.findById(id).orElseThrow(() -> new PlanNotFoundByIdException("Plan Not Found By Id" + id));

          plan.setPlanName(planDto.getPlanName());
          plan.setPostsPerMonth(planDto.getPostsPerMonth());
          plan.setPrice(planDto.getPrice());
          plan.setDuration(planDto.getDuration());
          plan.setCreatedAt(planDto.getCreatedAt());
          plan.setUpdatedAt(planDto.getUpdatedAt());
          plan.setId(plan.getId());
          planRepository.save(plan);

          return new PlanDto(plan);
    }

    @Override
    public PlanDto getPlanById(Integer id) {
        Optional<Plan> byId = planRepository.findById(id);
        if (byId.isPresent())
        {
            Plan plan=byId.get();
            return new  PlanDto(plan);
        }
        else {
            throw new  RuntimeException("Plan Not Found By Id Exception");
        }

    }

    @Override
    public String deletePlan(Integer id) {
     if (!planRepository.existsById(id))
     {
        throw new RuntimeException("plan Not Found");
     }else {
         planRepository.deleteById(id);
         return "Plan Deleted Sucessfully";
     }
    }

    @Override
    public List<PlanDto> getAllPlans() {
        List<Plan> allPlans = planRepository.findAll();
        return allPlans.stream()
                .map(plan -> new PlanDto(
                        plan.getId(),
                        plan.getPlanName(),
                        plan.getPostsPerMonth(),
                        plan.getPrice(),
                        plan.getDuration(),
                        plan.getCreatedAt(),
                        plan.getUpdatedAt(),
                        plan.getUser().getId()
                ))
                .collect(Collectors.toUnmodifiableList());
    }






}

