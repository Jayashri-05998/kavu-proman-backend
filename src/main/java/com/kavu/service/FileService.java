package com.kavu.service;

import com.kavu.model.FileEntity;
import com.kavu.repository.FileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class FileService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public FileEntity uploadFile(MultipartFile file, String uploadedBy) throws IOException {
        // Ensure directory exists
        Files.createDirectories(Paths.get(uploadDir));

        // Define full path
        String filePath = uploadDir + File.separator + file.getOriginalFilename();
        Path path = Paths.get(filePath);
        Files.write(path, file.getBytes());

        // Prepare metadata
        String size = String.format("%.2f MB", file.getSize() / 1024.0 / 1024.0);
        String type = file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf('.') + 1)
                .toUpperCase();

        // Create and save entity
        FileEntity entity = new FileEntity(
                file.getOriginalFilename(),
                type,
                size,
                uploadedBy,
                filePath
        );

        return fileRepository.save(entity);
    }

    public List<FileEntity> getAllFiles() {
        return fileRepository.findAll();
    }

    public File getFile(Long id) {
        return fileRepository.findById(id)
                .map(f -> new File(f.getFilePath()))
                .orElse(null);
    }

    public void deleteFile(Long id) throws IOException {
        FileEntity entity = fileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found"));
        Files.deleteIfExists(Paths.get(entity.getFilePath()));
        fileRepository.delete(entity);
    }
}
