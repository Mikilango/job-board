package com.miki.jobboard.controller;

import com.miki.jobboard.dto.FileResponseDTO;
import com.miki.jobboard.entity.Application;
import com.miki.jobboard.entity.File;
import com.miki.jobboard.exception.ResourceNotFoundException;
import com.miki.jobboard.mapper.FileMapper;
import com.miki.jobboard.repository.ApplicationRepository;
import com.miki.jobboard.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final FileService fileService;
    private final ApplicationRepository applicationRepository;
    private final FileMapper fileMapper;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public FileController(FileService fileService, ApplicationRepository applicationRepository, FileMapper fileMapper) {
        this.fileService = fileService;
        this.applicationRepository = applicationRepository;
        this.fileMapper = fileMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FileResponseDTO> getFileById(@PathVariable Long id) {
        return ResponseEntity.ok(fileMapper.toFileResponseDTO(fileService.getFileById(id)));
    }

    @GetMapping("/application/{applicationId}")
    public ResponseEntity<FileResponseDTO> getFileByApplicationId(@PathVariable Long applicationId) {
        return ResponseEntity.ok(fileMapper.toFileResponseDTO(fileService.getFileByApplicationId(applicationId)));
    }

    @PostMapping
    public ResponseEntity<FileResponseDTO> uploadFile(
            @RequestParam("file") MultipartFile multipartFile,
            @RequestParam Long applicationId) throws IOException {

        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found"));

        String fileName = UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(multipartFile.getInputStream(), filePath);

        File file = new File();
        file.setFileName(multipartFile.getOriginalFilename());
        file.setFilePath(filePath.toString());
        file.setFileType(multipartFile.getContentType());
        file.setApplication(application);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(fileMapper.toFileResponseDTO(fileService.uploadFile(file)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable Long id) {
        fileService.deleteFileById(id);
        return ResponseEntity.noContent().build();
    }
}