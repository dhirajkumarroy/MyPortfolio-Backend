// File: src/main/java/Dhiraj/sPortfolio/My_Portfolio/controller/ResumeController.java
package Dhiraj.sPortfolio.My_Portfolio.controller;

import Dhiraj.sPortfolio.My_Portfolio.dto.ResumeDto;
import Dhiraj.sPortfolio.My_Portfolio.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import java.io.IOException;

@RestController
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    // ✅ Admin Only: Upload a new resume
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ResumeDto> uploadResume(
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "description", required = false) String description
    ) throws IOException {
        return ResponseEntity.ok(resumeService.uploadResume(file, description));
    }

    // ✅ Public/User: View all resumes (with pagination)
    @PreAuthorize("permitAll()")
    @GetMapping
    public ResponseEntity<?> getAllResumes(Pageable pageable) {
        return ResponseEntity.ok(resumeService.getAllResumes(pageable));
    }

    // ✅ Public/User: View by ID
    @PreAuthorize("permitAll()")
    @GetMapping("/{id}")
    public ResponseEntity<ResumeDto> getResumeById(@PathVariable Long id) {
        return ResponseEntity.ok(resumeService.getResumeById(id));
    }

    // ✅ Public: Download resume by ID
    @PreAuthorize("permitAll()")
    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadResume(@PathVariable Long id) {
        Resource file = resumeService.downloadResume(id);

        // This is a simplified approach, you would want to get filename/type from the service
        // and set headers more accurately in a real app.
        String filename = "resume.pdf";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(file);
    }

    // ✅ Admin Only: Update an existing resume
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ResumeDto> updateResume(
            @PathVariable Long id,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "description", required = false) String description
    ) throws IOException {
        return ResponseEntity.ok(resumeService.updateResume(id, file, description));
    }

    // ✅ Admin Only: Delete a resume
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResume(@PathVariable Long id) throws IOException {
        resumeService.deleteResume(id);
        return ResponseEntity.noContent().build();
    }
}
