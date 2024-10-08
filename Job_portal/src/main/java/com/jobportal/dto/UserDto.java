package com.jobportal.dto;

import com.jobportal.entity.User;

import javax.management.relation.Role;

public class UserDto {

    private Long id;
    private String email;
    private String password; // Consider not exposing the password in DTOs
    private String name;


    public UserDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword(); // Handle this with care
        this.name = user.getName();
    }
}
