package com.sujay.journalApplication.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotEmpty
    @Schema(description = "provide Unique username for user", example = "sujay123")
    private String userName;
    @NotEmpty
    @Schema(description = "User password", example = "Password@123")
    private String password;
    @Schema(description = "User email", example = "user@gmail.com")
    private String email;
    private boolean sentimentAnalysis;
}
