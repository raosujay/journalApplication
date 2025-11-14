package com.sujay.journalApplication.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDTO {
    @Schema(description = "New username (optional)", example = "user_07")
    private String userName;
    @Schema(description = "New password (optional)", example = "Password@123")
    private String password;
    @Schema(description = "Email address (optional)", example = "user@gmail.com")
    private String email;
}
