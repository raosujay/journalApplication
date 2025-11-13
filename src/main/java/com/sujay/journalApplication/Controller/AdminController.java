package com.sujay.journalApplication.Controller;

import com.sujay.journalApplication.DTO.AdminUserDTO;
import com.sujay.journalApplication.Entity.User;
import com.sujay.journalApplication.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin APIs", description = "APIs accessible only by administrators. Used to manage users, view all user details, and create admin accounts.")
public class AdminController {

    @Autowired
    private UserService userService;

    @Operation(
            summary = "Get all registered users",
            description = "Fetches a list of all users from the database. Only accessible by admin users."
    )
    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //check if the user is admin
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(role -> role
                        .getAuthority().equals("ROLE_ADMIN")
                );

        if (!isAdmin) {
            return new ResponseEntity<>("You are not a admin user, please ask admin", HttpStatus.FORBIDDEN);
        }
        //only admin can access all user details
        List<User> allDetails = userService.getAll();
        if (allDetails != null && !allDetails.isEmpty()) {

            Map<String, Object> allUsers = new HashMap<>();
            allUsers.put("Message", "All Users Details in Application");
            allUsers.put("all details", allDetails);

            return new ResponseEntity<>(allDetails, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "Create a new admin user",
            description = "Allows the creation of a new admin user by providing user details in the request body."
    )
    @PostMapping("create-admin-user")
    public ResponseEntity<?> createUser(@RequestBody AdminUserDTO adminUserDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null && !authentication.isAuthenticated()) {
            return new ResponseEntity<>("Please login to perform this action", HttpStatus.UNAUTHORIZED);
        }
        //check if the user is admin
        boolean isAdmin = authentication.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            return new ResponseEntity<>("You are not a admin user, please ask admin", HttpStatus.FORBIDDEN);
        }
        // If admin, proceed to create a new admin user
        User newAdminUser = new User();
        newAdminUser.setUserName(adminUserDTO.getUserName());
        newAdminUser.setPassword(adminUserDTO.getPassword());
        newAdminUser.setEmail(adminUserDTO.getEmail());
        userService.saveAdmin(newAdminUser);
        return new ResponseEntity<>("Admin user created successfully", HttpStatus.CREATED);
    }
}