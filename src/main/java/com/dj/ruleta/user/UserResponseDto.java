package com.dj.ruleta.user;

public record UserResponseDto(
        String id,
        String username,
        String fullName
) {
    public UserResponseDto(User user) {
        this(user.getId(), user.getUsername(), user.getFullName());
    }
}
