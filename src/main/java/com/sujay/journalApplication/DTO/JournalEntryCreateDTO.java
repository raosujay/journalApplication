package com.sujay.journalApplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class JournalEntryCreateDTO {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
