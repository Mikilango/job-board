package com.miki.jobboard.dto;

import com.miki.jobboard.entity.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

    @NotBlank(message = "Email не должен быть пустым")
    @Email(message = "Email должен быть корректным и содержать '@'")
    private String email;

    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 8, message = "Пароль должен быть не менее 8 символов")
    @Pattern(regexp = ".*[A-Z].*", message = "Пароль должен содержать хотя бы одну заглавную букву")
    private String password;

    @NotNull(message = "Роль не должна быть пустой")
    private Role role;
}
