package com.sujay.journalApplication.Controller;

import com.sujay.journalApplication.DTO.UpdateUserDTO;
import com.sujay.journalApplication.Entity.User;
import com.sujay.journalApplication.Repository.UserRepository;
import com.sujay.journalApplication.Service.UserService;
import com.sujay.journalApplication.Service.WeatherService;
import com.sujay.journalApplication.api.response.WeatherResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/user")
@Tag(
        name = "User APIs",
        description = "APIs for logged-in users to read profile details, update account information, delete their account, and get weather-based greetings."
)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

    @Operation(
            summary = "Update User Details",
            description = "Allows an authenticated user to update their username and password. The current username is fetched from the authentication context."
    )
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserDTO updateUserDTO) {
        //get authentication details
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDB = userService.findByUserName(userName);

        if (userInDB != null ) {
            userInDB.setUserName(updateUserDTO.getUserName());
            userInDB.setPassword(updateUserDTO.getPassword());
            userInDB.setEmail(updateUserDTO.getEmail());
            userService.saveNewUser(userInDB);
        }
        return new ResponseEntity<>(" User Details Updated Successfully", HttpStatus.OK);
    }

    @Operation(
            summary = "Delete User Account",
            description = "Deletes the authenticated user's account from the database. This action cannot be undone."
    )
    @DeleteMapping("/delete-user")
    public ResponseEntity<?> deleteUserById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());

        return new ResponseEntity<>("User Deleted Successfully", HttpStatus.NO_CONTENT);
    }

    @Operation(
            summary = "Get Weather-Based Greeting",
            description = "Returns a personalized greeting for the logged-in user along with the current weather 'feels like' temperature for Bangalore."
    )
    @GetMapping("/weather")
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("Bangalore");
        String greeting = "";
        if (weatherResponse != null) {
            greeting = ", Bangalore Weather feels like " + weatherResponse.getCurrent().getFeelslike() + " degree celsius";
        }
        return new ResponseEntity<>("Hi " + authentication.getName().toUpperCase(Locale.ROOT) + greeting, HttpStatus.OK);
    }
}