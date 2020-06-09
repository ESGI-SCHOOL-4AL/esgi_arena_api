package com.example.demo.user;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/users")
public class UserManagementController {

    private static final List<User> USERS = Arrays.asList(
            new User(1, "Jean Valjean"),
            new User(2, "Bernard Lhermite"),
            new User(3, "Truc Muche")
    );

//    hasRole('ROLE_') hasAnyRole('ROLE_') hasAuthority('permission') hasAnyAuthority('permission')

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public List<User> getAllUsers() {
        System.out.println("getAllUsers");
        return USERS;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user:write')")
    public void registerNewUser(@RequestBody User user) {
        System.out.println("registerNewUser");
        System.out.println(user);
    }

    @DeleteMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('user:write')")
    public void deleteUser(@PathVariable("userId") Integer userId) {
        System.out.println("deleteUser");
        System.out.println(userId);
    }

    @PutMapping(path = "{userId}")
    @PreAuthorize("hasAuthority('user:write')")
    public void updateUser(@PathVariable("userId") Integer userId, @RequestBody User user) {
        System.out.println("updateUser");
        System.out.println(String.format("%s %s", userId, user));
    }
}
