package com.example.demo.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

        private UserRepository userRepository;
        private BCryptPasswordEncoder bCryptPasswordEncoder;

        public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
                this.userRepository = userRepository;
                this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        }

        @PostMapping("/register")
        public void signUp(@RequestBody ApplicationUser user) {
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                userRepository.save(user);
        }
}
