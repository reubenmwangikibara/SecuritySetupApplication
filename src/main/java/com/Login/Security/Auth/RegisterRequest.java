package com.Login.Security.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {
    //is a dto
    //private Integer ID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
