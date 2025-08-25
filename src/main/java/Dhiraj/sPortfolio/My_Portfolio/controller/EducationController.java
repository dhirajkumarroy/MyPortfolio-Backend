package Dhiraj.sPortfolio.My_Portfolio.controller;

import Dhiraj.sPortfolio.My_Portfolio.dto.EducationDto;
import Dhiraj.sPortfolio.My_Portfolio.service.EducationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/education")
@RequiredArgsConstructor
public class EducationController {

    private final EducationService educationService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public EducationDto addEducation(@Valid @RequestBody EducationDto dto) {
        return educationService.addEducation(dto);
    }


    @GetMapping
    public Page<EducationDto> getAllEducations(Pageable pageable) {
        return educationService.getAllEducations(pageable);
    }

//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}")
    public EducationDto getEducationById(@PathVariable Long id) {
        return educationService.getEducationById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public EducationDto updateEducation(@PathVariable Long id,
                                        @Valid @RequestBody EducationDto dto) {
        return educationService.updateEducation(id, dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteEducation(@PathVariable Long id) {
        educationService.deleteEducation(id);
    }
}