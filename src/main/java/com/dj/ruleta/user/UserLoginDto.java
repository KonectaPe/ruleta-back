package com.dj.ruleta.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserLoginDto(
        @NotBlank @Size(min = 8, max = 8) @Pattern(regexp = "^[a-zA-Z0-9]+$")
        String username,
        @NotBlank @Size(min = 8, max = 255) @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")
        String password
) {
}
