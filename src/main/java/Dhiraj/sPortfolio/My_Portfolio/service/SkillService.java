// Corrected SkillService.java
package Dhiraj.sPortfolio.My_Portfolio.service;

import Dhiraj.sPortfolio.My_Portfolio.entity.Skill;
import Dhiraj.sPortfolio.My_Portfolio.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SkillService {
    private final SkillRepository skillRepository;

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Optional<Skill> getSkillById(Long id) {
        return skillRepository.findById(id);
    }

    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    public Skill updateSkill(Long id, Skill skill) {
        return skillRepository.findById(id)
                .map(existing -> {
                    existing.setName(skill.getName());
                    // Removed the problematic line: existing.setLevel(skill.getLevel());
                    return skillRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Skill not found"));
    }

    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }
}