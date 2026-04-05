package com.miki.jobboard.repository;

import com.miki.jobboard.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
