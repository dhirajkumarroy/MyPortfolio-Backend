// File: src/main/java/Dhiraj/sPortfolio/My_Portfolio/controller/CertificationController.java
package Dhiraj.sPortfolio.My_Portfolio.controller;

import Dhiraj.sPortfolio.My_Portfolio.dto.CertificationDto;
import Dhiraj.sPortfolio.My_Portfolio.service.CertificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/certifications")
@RequiredArgsConstructor
public class CertificationController {

    private final CertificationService certificationService;

    // ✅ Admin Only: Add certification
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public CertificationDto addCertification(@RequestBody CertificationDto certificationDto) {
        return certificationService.addCertification(certificationDto);
    }

    // ✅ Public/User: View all certifications (with pagination)
    @GetMapping
    public Page<CertificationDto> getAllCertifications(Pageable pageable) {
        return certificationService.getAllCertifications(pageable);
    }

    // ✅ Public/User: View by ID
    @GetMapping("/{id}")
    public CertificationDto getCertificationById(@PathVariable Long id) {
        return certificationService.getCertificationById(id);
    }

    // ✅ Admin Only: Update certification
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public CertificationDto updateCertification(@PathVariable Long id, @RequestBody CertificationDto certificationDto) {
        return certificationService.updateCertification(id, certificationDto);
    }

    // ✅ Admin Only: Delete certification
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteCertification(@PathVariable Long id) {
        certificationService.deleteCertification(id);
    }
}
