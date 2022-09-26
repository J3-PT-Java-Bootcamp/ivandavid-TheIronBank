package com.ironhack.ivandavidtheironbank.user.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
    String username;
    String password;
    String email;
    String firstname;
    String lastname;
}
