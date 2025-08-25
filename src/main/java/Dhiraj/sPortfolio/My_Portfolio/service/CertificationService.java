// File: src/main/java/Dhiraj/sPortfolio/My_Portfolio/service/CertificationService.java
package Dhiraj.sPortfolio.My_Portfolio.service;

import Dhiraj.sPortfolio.My_Portfolio.dto.CertificationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CertificationService {

    CertificationDto addCertification(CertificationDto certificationDto);

    Page<CertificationDto> getAllCertifications(Pageable pageable);

    CertificationDto getCertificationById(Long id);

    CertificationDto updateCertification(Long id, CertificationDto certificationDto);

    void deleteCertification(Long id);
}
