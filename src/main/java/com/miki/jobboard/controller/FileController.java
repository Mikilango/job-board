package com.miki.jobboard.controller;

import com.miki.jobboard.entity.File;
import com.miki.jobboard.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<File> getFileById(@PathVariable Long id) {
        return ResponseEntity.ok(fileService.getFileById(id));
    }

    @GetMapping("/application/{applicationId}")
    public ResponseEntity<File> getApplicationById(@PathVariable Long applicationId) {
        return ResponseEntity.ok(fileService.getFileByApplicationId(applicationId));
    }

    @PostMapping
    public ResponseEntity<File> createApplication(@RequestBody File file) {
        return ResponseEntity.ok(fileService.uploadFile(file));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFile(@PathVariable Long id) {
        fileService.deleteFileById(id);
        return ResponseEntity.noContent().build();
    }

}
