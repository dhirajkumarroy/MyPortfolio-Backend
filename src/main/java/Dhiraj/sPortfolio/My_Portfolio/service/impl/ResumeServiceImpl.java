// File: src/main/java/Dhiraj/sPortfolio/My_Portfolio/service/impl/ResumeServiceImpl.java
package Dhiraj.sPortfolio.My_Portfolio.service.impl;

import Dhiraj.sPortfolio.My_Portfolio.dto.ResumeDto;
import Dhiraj.sPortfolio.My_Portfolio.entity.Resume;
import Dhiraj.sPortfolio.My_Portfolio.repository.ResumeRepository;
import Dhiraj.sPortfolio.My_Portfolio.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    @Transactional
    public ResumeDto uploadResume(MultipartFile file, String description) throws IOException {
        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
        Files.createDirectories(uploadPath);

        String originalFileName = file.getOriginalFilename();
        String fileName = System.currentTimeMillis() + "_" + originalFileName.replaceAll("[^a-zA-Z0-9.\\-]", "_");

        Path targetLocation = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        Resume resume = Resume.builder()
                .fileName(originalFileName)
                .fileUrl(targetLocation.toString())
                .description(description)
                .uploadedAt(LocalDateTime.now())
                .build();

        Resume saved = resumeRepository.save(resume);
        return mapToDto(saved);
    }

    @Override
    public Page<ResumeDto> getAllResumes(Pageable pageable) {
        return resumeRepository.findAll(pageable).map(this::mapToDto);
    }

    @Override
    public ResumeDto getResumeById(Long id) {
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resume not found with id: " + id));
        return mapToDto(resume);
    }

    @Override
    public Resource downloadResume(Long id) {
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resume not found with id: " + id));

        Path filePath = Paths.get(resume.getFileUrl());
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File not found at path: " + filePath);
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("Error reading file path", ex);
        }
    }

    @Override
    @Transactional
    public ResumeDto updateResume(Long id, MultipartFile file, String description) throws IOException {
        Resume existing = resumeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resume not found with id: " + id));

        if (file != null && !file.isEmpty()) {
            Path oldFilePath = Paths.get(existing.getFileUrl());
            if (Files.exists(oldFilePath)) {
                Files.delete(oldFilePath);
            }

            Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
            Files.createDirectories(uploadPath);
            String originalFileName = file.getOriginalFilename();
            String fileName = System.currentTimeMillis() + "_" + originalFileName.replaceAll("[^a-zA-Z0-9.\\-]", "_");
            Path targetLocation = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            existing.setFileName(originalFileName);
            existing.setFileUrl(targetLocation.toString());
        }

        if (description != null) {
            existing.setDescription(description);
        }

        Resume updated = resumeRepository.save(existing);
        return mapToDto(updated);
    }

    @Override
    @Transactional
    public void deleteResume(Long id) throws IOException {
        Resume resume = resumeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resume not found with id: " + id));

        Path filePath = Paths.get(resume.getFileUrl());
        if (Files.exists(filePath)) {
            Files.delete(filePath);
        }

        resumeRepository.deleteById(id);
    }

    private ResumeDto mapToDto(Resume resume) {
        return ResumeDto.builder()
                .id(resume.getId())
                .fileName(resume.getFileName())
                .fileUrl(resume.getFileUrl())
                .description(resume.getDescription())
                .uploadedAt(resume.getUploadedAt())
                .build();
    }
}
