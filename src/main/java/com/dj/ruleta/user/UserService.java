package com.dj.ruleta.user;

import com.dj.ruleta.infra.jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public String loginUser(UserLoginDto userLoginDto) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(userLoginDto.username(), userLoginDto.password());
        Authentication authentication = authenticationManager.authenticate(authToken);
        User user = (User) authentication.getPrincipal();
        return jwtService.generateToken(user);
    }

    public User getUser(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        return user;
    }
}
