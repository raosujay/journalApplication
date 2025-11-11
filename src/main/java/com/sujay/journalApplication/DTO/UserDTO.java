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
    @Schema(description = "The User's username")
    private String userName;
    @NotEmpty
    private String password;
    private String email;

    private boolean sentimentAnalysis;
}
