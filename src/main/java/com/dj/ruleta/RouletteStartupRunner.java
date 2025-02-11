package com.dj.ruleta;

import com.dj.ruleta.user.User;
import com.dj.ruleta.user.UserRegisterDto;
import com.dj.ruleta.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Component
public class RouletteStartupRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RouletteStartupRunner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        String username = "74549054";
        String fullName = "admin";
        String password = "Admin123";

        if (userRepository.findByUsername(username) == null) {
            UserRegisterDto adminUser = new UserRegisterDto(username, fullName, passwordEncoder.encode(password));
            User user = new User(adminUser);
            userRepository.save(user);
        }
    }
}
