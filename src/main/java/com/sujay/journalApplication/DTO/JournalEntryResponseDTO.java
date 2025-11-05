package com.sujay.journalApplication.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class JournalEntryResponseDTO {
    @Schema(description = "Entry ID", example = "507f1f77bcf86cd799439011")
    private String id;

    @Schema(description = "Title of the entry")
    private String title;

    @Schema(description = "Content of the entry")
    private String content;

    @Schema(description = "Timestamp of last update")
    private String updatedAt;

}
