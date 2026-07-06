package com.jin.init;

import com.jin.entity.User;
import com.jin.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DemoDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public DemoDataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // create demo accounts if not exists
        if (userRepository.findByUsername("admin").isEmpty()) {
            User u = new User();
            u.setUsername("admin");
            u.setPassword(passwordEncoder.encode("admin123"));
            u.setRole("ROLE_ADMIN");
            userRepository.save(u);
        }
        if (userRepository.findByUsername("merchant").isEmpty()) {
            User u = new User();
            u.setUsername("merchant");
            u.setPassword(passwordEncoder.encode("merchant123"));
            u.setRole("ROLE_MERCHANT");
            userRepository.save(u);
        }
        if (userRepository.findByUsername("user").isEmpty()) {
            User u = new User();
            u.setUsername("user");
            u.setPassword(passwordEncoder.encode("user123"));
            u.setRole("ROLE_USER");
            userRepository.save(u);
        }
    }
}
