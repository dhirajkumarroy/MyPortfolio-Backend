// File: src/main/java/Dhiraj/sPortfolio/My_Portfolio/service/ResumeService.java
package Dhiraj.sPortfolio.My_Portfolio.service;

import Dhiraj.sPortfolio.My_Portfolio.dto.ResumeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.io.IOException;

public interface ResumeService {
    ResumeDto uploadResume(MultipartFile file, String description) throws IOException;
    Page<ResumeDto> getAllResumes(Pageable pageable);
    ResumeDto getResumeById(Long id);
    Resource downloadResume(Long id);
    ResumeDto updateResume(Long id, MultipartFile file, String description) throws IOException;
    void deleteResume(Long id) throws IOException;
}
