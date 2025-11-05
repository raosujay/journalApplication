package com.sujay.journalApplication.Controller;

import com.sujay.journalApplication.DTO.AdminUserDTO;
import com.sujay.journalApplication.Entity.User;
import com.sujay.journalApplication.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        List<User> all = userService.getAll();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "Create a new admin user",
            description = "Allows the creation of a new admin user by providing user details in the request body."
    )
    @PostMapping("create-admin-user")
    public ResponseEntity<?> createUser(@RequestBody AdminUserDTO adminUserDTO) {
        User newAdminUser = new User();
        newAdminUser.setUserName(adminUserDTO.getUserName());
        newAdminUser.setPassword(adminUserDTO.getPassword());
        newAdminUser.setEmail(adminUserDTO.getEmail());
        userService.saveAdmin(newAdminUser);
        return new ResponseEntity<>("Admin user created", HttpStatus.CREATED);
    }
}