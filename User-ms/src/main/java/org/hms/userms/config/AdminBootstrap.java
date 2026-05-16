package org.hms.userms.config;

import org.hms.userms.dto.Roles;
import org.hms.userms.entity.Users;
import org.hms.userms.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminBootstrap implements CommandLineRunner {

    public static final String ADMIN_EMAIL = "admin@hms.local";
    public static final String ADMIN_PASSWORD = "Admin@123";
    public static final String ADMIN_NAME = "System Admin";

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminBootstrap(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.findByEmail(ADMIN_EMAIL).isPresent()) {
            return;
        }
        Users admin = new Users();
        admin.setName(ADMIN_NAME);
        admin.setEmail(ADMIN_EMAIL);
        admin.setPassword(passwordEncoder.encode(ADMIN_PASSWORD));
        admin.setRole(Roles.ADMIN);
        userRepository.save(admin);
    }
}
