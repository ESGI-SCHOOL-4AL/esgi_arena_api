package com.example.demo.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
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

        @GetMapping(value = "/")
        public ApplicationUser getUser() {

                Authentication auth = SecurityContextHolder.getContext().getAuthentication();

                ApplicationUser user = userRepository.findByUsername(auth.getName());
                if (user == null) {
                        throw new UsernameNotFoundException(auth.getName());
                }
                return user;
        }

        @PostMapping("/register")
        public void signUp(@RequestBody ApplicationUser user) {
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                userRepository.save(user);
        }
}
