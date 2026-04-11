package com.miki.jobboard.service;

import com.miki.jobboard.entity.Job;
import com.miki.jobboard.exception.ResourceNotFoundException;
import com.miki.jobboard.repository.JobRepository;
import com.miki.jobboard.service.impl.JobServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JobServiceImplTest {

    @Mock
    private JobRepository jobRepository;

    @InjectMocks
    private JobServiceImpl jobService;

    @Test
    void getJobById_whenJobExists_returnsJob() {
        Job job = new Job();
        job.setId(1L);
        job.setTitle("Java Developer");
        when(jobRepository.findById(1L)).thenReturn(Optional.of(job));

        Job result = jobService.getJobById(1L);

        assertNotNull(result);
        assertEquals("Java Developer", result.getTitle());
        verify(jobRepository).findById(1L);
    }

    @Test
    void getJobById_whenJobNotExists_throwsException() {
        when(jobRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> jobService.getJobById(99L));
        verify(jobRepository).findById(99L);
    }

    @Test
    void createJob_returnsCreatedJob() {
        Job job = new Job();
        job.setTitle("Java Developer");
        when(jobRepository.save(job)).thenReturn(job);

        Job result = jobService.createJob(job);

        assertNotNull(result);
        assertEquals("Java Developer", result.getTitle());
        verify(jobRepository).save(job);
    }

    @Test
    void deleteJob_whenJobNotExists_throwsException() {
        when(jobRepository.existsById(99L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> jobService.deleteJob(99L));
        verify(jobRepository).existsById(99L);
    }
}
