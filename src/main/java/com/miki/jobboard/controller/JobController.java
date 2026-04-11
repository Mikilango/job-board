package com.miki.jobboard.controller;

import com.miki.jobboard.dto.JobRequestDTO;
import com.miki.jobboard.dto.JobResponseDTO;
import com.miki.jobboard.dto.JobUpdateDTO;
import com.miki.jobboard.entity.Job;
import com.miki.jobboard.entity.User;
import com.miki.jobboard.mapper.JobMapper;
import com.miki.jobboard.repository.UserRepository;
import com.miki.jobboard.service.JobService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/jobs")
@RestController
public class JobController {

    private final JobService jobService;
    private final UserRepository userRepository;
    private final JobMapper jobMapper;

    public JobController(JobService jobService, UserRepository userRepository, JobMapper jobMapper) {
        this.jobService = jobService;
        this.userRepository = userRepository;
        this.jobMapper = jobMapper;
    }

    @GetMapping
    public ResponseEntity<List<JobResponseDTO>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs()
                .stream()
                .map(jobMapper::toJobResponseDTO)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponseDTO> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(jobMapper.toJobResponseDTO(jobService.getJobById(id)));
    }

    @PostMapping
    public ResponseEntity<JobResponseDTO> createJob(@Valid @RequestBody JobRequestDTO jobRequestDTO, Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Job job = jobMapper.toEntity(jobRequestDTO);
        job.setUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(jobMapper.toJobResponseDTO(jobService.createJob(job)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobResponseDTO> updateJob(@PathVariable Long id, @Valid @RequestBody JobUpdateDTO jobUpdateDTO) {
        Job job = jobMapper.toEntity(jobUpdateDTO);
        job.setId(id);
        return ResponseEntity.ok(jobMapper.toJobResponseDTO(jobService.updateJob(job)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<JobResponseDTO>> searchJobs(@RequestParam String query) {
        return ResponseEntity.ok(jobService.searchJobs(query)
                .stream()
                .map(jobMapper::toJobResponseDTO)
                .toList());
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<JobResponseDTO>> getAllJobsPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy) {
        return ResponseEntity.ok(jobService.getAllJobsPaged(page, size, sortBy)
                .map(jobMapper::toJobResponseDTO));
    }
}
