package com.sujay.journalApplication.Controller;

import com.sujay.journalApplication.DTO.LoginDTO;
import com.sujay.journalApplication.DTO.UserDTO;
import com.sujay.journalApplication.Entity.User;
import com.sujay.journalApplication.Service.UserDetailsServiceImpl;
import com.sujay.journalApplication.Service.UserService;
import com.sujay.journalApplication.Utils.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/public")
@Tag(
        name = "Public APIs",
        description = "Open and unauthenticated endpoints for all users. Includes user signup, login, and basic health check for the Journal Application."
)
public class PublicController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Operation(
            summary = "Application Health Check",
            description = "Returns a simple message confirming that the Journal Application backend is running and accessible."
    )
    @GetMapping("/health-check")
    public String healthCheck() {
        return "Health check passed – Journal Application is working.";
    }

    @Operation(
            summary = "User Signup",
            description = "Registers a new user account by accepting username and password in the request body. This endpoint does not require authentication."
    )
    @PostMapping("/signup")
    public void signup(@RequestBody UserDTO user) {
        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setPassword(user.getPassword());
        newUser.setEmail(user.getEmail());
        newUser.setSentimentAnalysis(user.isSentimentAnalysis());
        userService.saveNewUser(newUser);
    }

    @Operation(
            summary = "User Login",
            description = "Authenticates a user with username and password. If valid, returns a JWT token which must be used for accessing secured endpoints."
    )
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getUserName(), loginDTO.getPassword())
            );
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getUserName());
            String jwtToken = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwtToken, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occurred while creating Authentication Token ", e);
            return new ResponseEntity<>("Incorrect userName or Password", HttpStatus.BAD_REQUEST);
        }
    }
}