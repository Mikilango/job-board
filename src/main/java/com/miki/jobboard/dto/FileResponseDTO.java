package com.miki.jobboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileResponseDTO {
    private Long id;
    private String fileName;
    private String filePath;
    private String fileType;
    private Long applicationId;
}
