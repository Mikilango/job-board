package com.miki.jobboard.dto;

import com.miki.jobboard.entity.JobStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobRequestDTO {
    private String title;
    private String description;
    private double salary;
    private String location;
    private JobStatus status;
}
