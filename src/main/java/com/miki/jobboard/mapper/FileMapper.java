package com.miki.jobboard.mapper;

import com.miki.jobboard.dto.FileResponseDTO;
import com.miki.jobboard.entity.File;
import org.springframework.stereotype.Component;

@Component
public class FileMapper {
    public FileResponseDTO toFileResponseDTO(File file) {
        FileResponseDTO dto = new FileResponseDTO();
        dto.setId(file.getId());
        dto.setFileName(file.getFileName());
        dto.setFilePath(file.getFilePath());
        dto.setFileType(file.getFileType());
        dto.setApplicationId(file.getApplication().getId());
        return dto;
    }
}
