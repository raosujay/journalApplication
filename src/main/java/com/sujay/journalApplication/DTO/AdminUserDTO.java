package com.sujay.journalApplication.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data required to create an admin user")
public class AdminUserDTO {
    @NotEmpty
    @Schema(description = "provide Unique username for admin login", example = "admin_sujay")
    private String userName;
    @NotEmpty
    @Schema(description = "Admin user's password", example = "Password@123")
    private String password;
    @Schema(description = "Admin email", example = "admin@gmail.com")
    private String email;
}
