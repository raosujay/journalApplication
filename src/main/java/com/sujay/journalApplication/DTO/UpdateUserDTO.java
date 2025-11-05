package com.sujay.journalApplication.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDTO {
    @Schema(description = "New username (optional)", example = "sujay_07")
    private String userName;
    @Schema(description = "New password (optional)", example = "StrongerPass@123")
    private String password;
    @Schema(description = "Email address (optional)", example = "sujay@example.com")
    private String email;
}
