// File: src/main/java/Dhiraj/sPortfolio/My_Portfolio/entity/Resume.java
package Dhiraj.sPortfolio.My_Portfolio.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;      // original file name
    private String fileUrl;       // storage path
    private String description;   // optional notes
    private LocalDateTime uploadedAt;
}
