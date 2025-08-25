package Dhiraj.sPortfolio.My_Portfolio.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data; // Import Lombok's Data annotation
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "skills")
@Data // This automatically generates getters and setters
@EqualsAndHashCode(callSuper = true)
public class Skill extends BaseEntity {

    @NotBlank
    @Size(min = 2, max = 50)
    @Column(nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private SkillCategory category = SkillCategory.OTHER;

    // Add this new field
    private String level;
    // You can also add annotations like @NotBlank, @Size, @Column if needed
}