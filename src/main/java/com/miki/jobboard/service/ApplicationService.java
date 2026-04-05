package com.miki.jobboard.service;

import com.miki.jobboard.entity.Application;
import com.miki.jobboard.entity.ApplicationStatus;

import java.util.List;

public interface ApplicationService {
    Application createApplication(Application application);

    Application getApplicationById(Long id);

    List<Application> getApplicationsByJobId(Long jobId);

    List<Application> getApplicationsByUserId(Long userId);

    Application updateApplicationStatus(Long id, ApplicationStatus status);

    void deleteApplication(Long id);
}
