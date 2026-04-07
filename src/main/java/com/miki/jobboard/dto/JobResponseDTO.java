package com.miki.jobboard.dto;

import com.miki.jobboard.entity.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String location;
    private JobStatus status;
    private double salary;
    private LocalDateTime createdAt;
    private String employerEmail;
}
