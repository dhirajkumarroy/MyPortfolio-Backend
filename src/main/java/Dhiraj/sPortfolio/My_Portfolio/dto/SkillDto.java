package Dhiraj.sPortfolio.My_Portfolio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SkillDto {
    private Long id;

    @NotBlank @Size(min = 2, max = 50)
    private String name;

    @NotBlank @Size(min = 2, max = 20)
    private String category; // LANGUAGE/FRAMEWORK/DATABASE/TOOL/CLOUD/OTHER (as String for API)

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
