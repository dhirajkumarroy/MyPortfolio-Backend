// File: src/main/java/Dhiraj/sPortfolio/My_Portfolio/entity/Certification.java
package Dhiraj.sPortfolio.My_Portfolio.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String issuingOrganization;

    private LocalDate issueDate;

    private LocalDate expiryDate;

    private String url; // Added to match DTO
}
