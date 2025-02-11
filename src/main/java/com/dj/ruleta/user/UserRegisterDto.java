package com.dj.ruleta.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRegisterDto(
        @NotBlank @Size(min = 8, max = 8) @Pattern(regexp = "^[a-zA-Z0-9]+$")
        String username,
        @NotBlank @Pattern(regexp = "^[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+$") @Size(min = 4, max = 100)
        String fullName,
        @NotBlank @Size(min = 8, max = 255) @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")
        String password
) {
}
