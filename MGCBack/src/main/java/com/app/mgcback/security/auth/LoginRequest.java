package com.app.mgcback.security.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @NotBlank(message = "nombre de usuario/email obligatorio")
    private String username;
    @NotBlank(message = "contraseña obligatoria")
    private String password;

}
