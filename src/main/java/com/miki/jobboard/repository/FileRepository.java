package com.miki.jobboard.repository;

import com.miki.jobboard.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {
    Optional<File> findByApplicationId(Long applicationId);
}
