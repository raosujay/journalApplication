package com.sujay.journalApplication.DTO;

import com.sujay.journalApplication.Enums.Sentiment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class JournalEntryCreateDTO {
    @NotBlank
    @Schema(description = "Journal Title")
    private String title;
    @NotBlank
    @Schema(description = "Journal Content")
    private String content;
    @Schema(description = "Sentiment while entering the Journal")
    private Sentiment sentiment;
}
