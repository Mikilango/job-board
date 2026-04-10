package com.miki.jobboard.controller;

import com.miki.jobboard.dto.ApplicationRequestDTO;
import com.miki.jobboard.dto.ApplicationResponseDTO;
import com.miki.jobboard.entity.Application;
import com.miki.jobboard.entity.ApplicationStatus;
import com.miki.jobboard.entity.Job;
import com.miki.jobboard.entity.User;
import com.miki.jobboard.exception.ResourceNotFoundException;
import com.miki.jobboard.mapper.ApplicationMapper;
import com.miki.jobboard.repository.JobRepository;
import com.miki.jobboard.repository.UserRepository;
import com.miki.jobboard.service.ApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final ApplicationMapper applicationMapper;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public ApplicationController(ApplicationService applicationService, ApplicationMapper applicationMapper, JobRepository jobRepository, UserRepository userRepository) {
        this.applicationService = applicationService;
        this.applicationMapper = applicationMapper;
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponseDTO> getApplicationById(@PathVariable Long id) {
        return ResponseEntity.ok(applicationMapper.toApplicationResponseDTO(applicationService.getApplicationById(id)));
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<ApplicationResponseDTO>> getApplicationsByJobId(@PathVariable Long jobId) {
        return ResponseEntity.ok(applicationService.getApplicationsByJobId(jobId)
                .stream()
                .map(applicationMapper::toApplicationResponseDTO)
                .toList());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ApplicationResponseDTO>> getApplicationsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(applicationService.getApplicationsByUserId(userId)
                .stream()
                .map(applicationMapper::toApplicationResponseDTO)
                .toList());
    }

    @PostMapping
    public ResponseEntity<ApplicationResponseDTO> createApplication(@RequestBody ApplicationRequestDTO dto, Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Job job = jobRepository.findById(dto.getJobId())
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        Application application = applicationMapper.toEntity(dto);

        application.setUser(user);
        application.setJob(job);
        application.setStatus(ApplicationStatus.PENDING);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(applicationMapper.toApplicationResponseDTO(
                        applicationService.createApplication(application)
                ));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApplicationResponseDTO> updateApplicationStatus(
            @PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(applicationMapper.toApplicationResponseDTO(
                applicationService.updateApplicationStatus(id, ApplicationStatus.valueOf(status))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }
}
