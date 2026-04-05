package com.miki.jobboard.service.impl;

import com.miki.jobboard.entity.File;
import com.miki.jobboard.repository.FileRepository;
import com.miki.jobboard.service.FileService;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public File uploadFile(File file) {
        return fileRepository.save(file);
    }

    @Override
    public File getFileById(Long id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));
    }

    @Override
    public File getFileByApplicationId(Long applicationId) {
        return fileRepository.findByApplicationId(applicationId)
                .orElseThrow(() -> new RuntimeException("File not found"));
    }

    @Override
    public void deleteFileById(Long id) {
        if (!fileRepository.existsById(id)) {
            throw new IllegalArgumentException("File with id " + id + " does not exist");
        }
        fileRepository.deleteById(id);
    }
}
