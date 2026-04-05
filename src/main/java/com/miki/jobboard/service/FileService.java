package com.miki.jobboard.service;

import com.miki.jobboard.entity.File;

public interface FileService {
    File uploadFile(File file);

    File getFileById(Long id);

    File getFileByApplicationId(Long application_id);

    void deleteFileById(Long id);
}
