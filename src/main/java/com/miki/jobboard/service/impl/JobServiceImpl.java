package com.miki.jobboard.service.impl;

import com.miki.jobboard.entity.Job;
import com.miki.jobboard.exception.ResourceNotFoundException;
import com.miki.jobboard.mapper.JobMapper;
import com.miki.jobboard.repository.JobRepository;
import com.miki.jobboard.service.JobService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    public JobServiceImpl(JobRepository jobRepository, JobMapper jobMapper) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
    }

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job updateJob(Job job) {
        if (!jobRepository.existsById(job.getId())) {
            throw new ResourceNotFoundException("Job does not exist");
        }
        return jobRepository.save(job);
    }

    @Override
    public void deleteJob(Long id) {
        if (!jobRepository.existsById(id)) {
            throw new ResourceNotFoundException("Job does not exist");
        }
        jobRepository.deleteById(id);
    }

    @Override
    public List<Job> searchJobs(String query) {
        return jobRepository.searchJobs(query);
    }

    @Override
    public Page<Job> getAllJobsPaged(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return jobRepository.findAll(pageable);
    }

}
