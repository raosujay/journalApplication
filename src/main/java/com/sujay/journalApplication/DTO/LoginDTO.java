package com.sujay.journalApplication.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    @NotEmpty
    @Schema(description = "Username of user trying to log in", example = "user01")
    private String userName;
    @NotEmpty
    @Schema(description = "Password of the user", example = "Password@123")
    private String password;
}