package com.dj.ruleta.user;

import com.dj.ruleta.infra.jwt.TokenResponseDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<Object> login(@RequestBody(required = false) @Valid UserLoginDto userLoginDto) {

        if (userLoginDto == null) {
            return ResponseEntity.badRequest().body("The request body cannot be empty.");
        }

        String token = userService.loginUser(userLoginDto);
        if (token == null) {
            return ResponseEntity.badRequest().body("Error generating token");
        }
        return ResponseEntity.ok(new TokenResponseDto(token));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable String id) {

        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!authenticatedUser.getId().equals(id)) {
            return ResponseEntity.badRequest().body("User not found");
        }

        User user = userService.getUser(id);
        return ResponseEntity.ok(new UserResponseDto(user));
    }
}
