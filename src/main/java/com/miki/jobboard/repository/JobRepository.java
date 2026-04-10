package com.miki.jobboard.repository;

import com.miki.jobboard.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    @Query(value = "SELECT * FROM job WHERE to_tsvector('english', title || ' ' || coalesce(description, '')) @@ to_tsquery('english', :query)", nativeQuery = true)
    List<Job> searchJobs(@Param("query") String query);
}
