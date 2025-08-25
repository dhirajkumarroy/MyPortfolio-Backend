package Dhiraj.sPortfolio.My_Portfolio.mapper;

import Dhiraj.sPortfolio.My_Portfolio.dto.SkillDto; // Correct import
import Dhiraj.sPortfolio.My_Portfolio.entity.Skill;
import Dhiraj.sPortfolio.My_Portfolio.entity.SkillCategory; // Import the enum

public class SkillMapper {

    public static SkillDto toDTO(Skill skill) {
        SkillDto dto = new SkillDto();
        dto.setId(skill.getId());
        dto.setName(skill.getName());
        dto.setCategory(skill.getCategory().name()); // Corrected field and mapping
        return dto;
    }

    public static Skill toEntity(SkillDto dto) {
        Skill skill = new Skill();
        skill.setId(dto.getId());
        skill.setName(dto.getName());
        skill.setCategory(SkillCategory.valueOf(dto.getCategory())); // Corrected field and mapping
        return skill;
    }
}