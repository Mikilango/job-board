package com.miki.jobboard.mapper;

import com.miki.jobboard.dto.JobRequestDTO;
import com.miki.jobboard.dto.JobResponseDTO;
import com.miki.jobboard.dto.JobUpdateDTO;
import com.miki.jobboard.entity.Job;
import org.springframework.stereotype.Component;

@Component
public class JobMapper {

    public JobResponseDTO toJobResponseDTO(Job job) {
        JobResponseDTO dto = new JobResponseDTO();
        dto.setId(job.getId());
        dto.setTitle(job.getTitle());
        dto.setDescription(job.getDescription());
        dto.setLocation(job.getLocation());
        dto.setStatus(job.getStatus());
        dto.setSalary(job.getSalary());
        dto.setCreatedAt(job.getCreatedAt());
        dto.setEmployerEmail(job.getUser().getEmail());
        return dto;
    }

    public Job toEntity(JobRequestDTO dto) {
        Job job = new Job();
        job.setTitle(dto.getTitle());
        job.setDescription(dto.getDescription());
        job.setLocation(dto.getLocation());
        job.setSalary(dto.getSalary());
        return job;
    }

    public Job toEntity(JobUpdateDTO dto) {
        Job job = new Job();
        job.setTitle(dto.getTitle());
        job.setDescription(dto.getDescription());
        job.setLocation(dto.getLocation());
        job.setStatus(dto.getStatus());
        job.setSalary(dto.getSalary());
        return job;
    }
}
