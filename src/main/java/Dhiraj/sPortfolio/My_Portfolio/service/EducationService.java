// File: src/main/java/Dhiraj/sPortfolio/My_Portfolio/service/EducationService.java
package Dhiraj.sPortfolio.My_Portfolio.service;

import Dhiraj.sPortfolio.My_Portfolio.dto.EducationDto;
import Dhiraj.sPortfolio.My_Portfolio.entity.Education;
import Dhiraj.sPortfolio.My_Portfolio.repository.EducationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationRepository educationRepository;

    @Transactional
    public EducationDto addEducation(EducationDto dto) {
        Education education = mapToEntity(dto);
        return mapToDto(educationRepository.save(education));
    }

    public Page<EducationDto> getAllEducations(Pageable pageable) {
        return educationRepository.findAll(pageable).map(this::mapToDto);
    }

    public EducationDto getEducationById(Long id) {
        return educationRepository.findById(id)
                .map(this::mapToDto) // Use Optional.map for cleaner conversion
                .orElseThrow(() -> new RuntimeException("Education not found"));
    }

    @Transactional
    public EducationDto updateEducation(Long id, EducationDto dto) {
        Education existing = educationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Education not found"));

        existing.setInstitution(dto.getInstitution());
        existing.setDegree(dto.getDegree());
        existing.setFieldOfStudy(dto.getFieldOfStudy());
        existing.setStartDate(dto.getStartDate());
        existing.setEndDate(dto.getEndDate());
        existing.setGrade(dto.getGrade());
        existing.setDescription(dto.getDescription());

        return mapToDto(educationRepository.save(existing));
    }

    @Transactional
    public void deleteEducation(Long id) {
        if (!educationRepository.existsById(id)) {
            throw new RuntimeException("Education not found");
        }
        educationRepository.deleteById(id);
    }

    // --- Mapping Helpers ---
    private EducationDto mapToDto(Education education) {
        EducationDto dto = new EducationDto();
        dto.setId(education.getId());
        dto.setInstitution(education.getInstitution());
        dto.setDegree(education.getDegree());
        dto.setFieldOfStudy(education.getFieldOfStudy());
        dto.setStartDate(education.getStartDate());
        dto.setEndDate(education.getEndDate());
        dto.setGrade(education.getGrade());
        dto.setDescription(education.getDescription());
        return dto;
    }

    private Education mapToEntity(EducationDto dto) {
        Education education = new Education();
        education.setInstitution(dto.getInstitution());
        education.setDegree(dto.getDegree());
        education.setFieldOfStudy(dto.getFieldOfStudy());
        education.setStartDate(dto.getStartDate());
        education.setEndDate(dto.getEndDate());
        education.setGrade(dto.getGrade());
        education.setDescription(dto.getDescription());
        return education;
    }
}
