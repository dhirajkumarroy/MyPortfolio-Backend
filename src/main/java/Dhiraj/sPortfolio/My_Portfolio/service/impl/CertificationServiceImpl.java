// File: src/main/java/Dhiraj/sPortfolio/My_Portfolio/service/impl/CertificationServiceImpl.java
package Dhiraj.sPortfolio.My_Portfolio.service.impl;

import Dhiraj.sPortfolio.My_Portfolio.dto.CertificationDto;
import Dhiraj.sPortfolio.My_Portfolio.entity.Certification;
import Dhiraj.sPortfolio.My_Portfolio.repository.CertificationRepository;
import Dhiraj.sPortfolio.My_Portfolio.service.CertificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CertificationServiceImpl implements CertificationService {

    private final CertificationRepository certificationRepository;

    @Override
    @Transactional
    public CertificationDto addCertification(CertificationDto certificationDto) {
        Certification cert = mapToEntity(certificationDto);
        Certification saved = certificationRepository.save(cert);
        return mapToDto(saved);
    }

    @Override
    public Page<CertificationDto> getAllCertifications(Pageable pageable) {
        return certificationRepository.findAll(pageable).map(this::mapToDto);
    }

    @Override
    public CertificationDto getCertificationById(Long id) {
        Certification cert = certificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certification not found with id: " + id));
        return mapToDto(cert);
    }

    @Override
    @Transactional
    public CertificationDto updateCertification(Long id, CertificationDto certificationDto) {
        Certification existing = certificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certification not found with id: " + id));

        existing.setName(certificationDto.getName());
        existing.setIssuingOrganization(certificationDto.getIssuingOrganization());
        existing.setIssueDate(certificationDto.getIssueDate());
        existing.setExpiryDate(certificationDto.getExpiryDate());
        existing.setUrl(certificationDto.getUrl());

        Certification updated = certificationRepository.save(existing);
        return mapToDto(updated);
    }

    @Override
    @Transactional
    public void deleteCertification(Long id) {
        if (!certificationRepository.existsById(id)) {
            throw new RuntimeException("Certification not found with id: " + id);
        }
        certificationRepository.deleteById(id);
    }

    // --- Mapping Helpers ---
    private CertificationDto mapToDto(Certification cert) {
        return CertificationDto.builder()
                .id(cert.getId())
                .name(cert.getName())
                .issuingOrganization(cert.getIssuingOrganization())
                .issueDate(cert.getIssueDate())
                .expiryDate(cert.getExpiryDate())
                .url(cert.getUrl())
                .build();
    }

    private Certification mapToEntity(CertificationDto dto) {
        return Certification.builder()
                .id(dto.getId())
                .name(dto.getName())
                .issuingOrganization(dto.getIssuingOrganization())
                .issueDate(dto.getIssueDate())
                .expiryDate(dto.getExpiryDate())
                .url(dto.getUrl())
                .build();
    }
}
