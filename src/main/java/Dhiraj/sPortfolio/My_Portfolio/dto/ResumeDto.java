// File: src/main/java/Dhiraj/sPortfolio/My_Portfolio/dto/ResumeDto.java
package Dhiraj.sPortfolio.My_Portfolio.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeDto {
    private Long id;
    private String fileName;
    private String fileUrl;
    private String description;
    private LocalDateTime uploadedAt;
}
