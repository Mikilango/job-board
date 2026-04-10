package com.miki.jobboard.service;

import com.miki.jobboard.entity.Job;

import java.util.List;

public interface JobService {
    Job createJob(Job job);

    Job getJobById(Long id);

    List<Job> getAllJobs();

    Job updateJob(Job job);

    void deleteJob(Long id);

    List<Job> searchJobs(String query);
}
