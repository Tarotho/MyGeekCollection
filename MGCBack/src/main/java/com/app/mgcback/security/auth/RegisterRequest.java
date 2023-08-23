package com.app.mgcback.security.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "nombre obligatorio")
    private String name;
    @NotBlank(message = "nombre de usuario obligatorio")
    private String username;
    @Email(message = "dirección de email no válida")
    @NotBlank(message = "email obligatorio")
    private String email;
    @NotBlank
    private String password;
    private Set<String> roles = new HashSet<>();

}
