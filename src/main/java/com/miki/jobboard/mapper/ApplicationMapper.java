package com.miki.jobboard.mapper;

import com.miki.jobboard.dto.ApplicationRequestDTO;
import com.miki.jobboard.dto.ApplicationResponseDTO;
import com.miki.jobboard.entity.Application;
import org.springframework.stereotype.Component;

@Component
public class ApplicationMapper {

    public ApplicationResponseDTO toApplicationResponseDTO(Application application) {
        ApplicationResponseDTO dto = new ApplicationResponseDTO();
        dto.setId(application.getId());
        dto.setStatus(application.getStatus());
        dto.setCoverLetter(application.getCoverLetter());
        dto.setCreatedAt(application.getCreatedAt());
        dto.setJobTitle(application.getJob().getTitle());
        dto.setApplicantEmail(application.getUser().getEmail());
        return dto;
    }

    public Application toEntity(ApplicationRequestDTO dto) {
        Application application = new Application();
        application.setCoverLetter(dto.getCoverLetter());
        return application;
    }
}