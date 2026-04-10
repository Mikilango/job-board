package com.miki.jobboard.dto;

import com.miki.jobboard.entity.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationResponseDTO {
    private Long id;
    private ApplicationStatus status;
    private String coverLetter;
    private LocalDateTime createdAt;
    private String jobTitle;
    private String applicantEmail;
}
