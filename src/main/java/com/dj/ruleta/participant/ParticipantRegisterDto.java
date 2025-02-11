package com.dj.ruleta.participant;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ParticipantRegisterDto(
        @NotBlank @Size(min = 8, max = 8) @Pattern(regexp = "^[0-9]*$")
        String username
) {
}
