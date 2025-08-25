package Dhiraj.sPortfolio.My_Portfolio.mapper;

import Dhiraj.sPortfolio.My_Portfolio.dto.ProjectDto;
import Dhiraj.sPortfolio.My_Portfolio.entity.Project;

public class ProjectMapper {

    public static ProjectDto toDTO(Project project) {
        ProjectDto dto = new ProjectDto();
        dto.setId(project.getId());
        dto.setTitle(project.getTitle());
        dto.setDescription(project.getDescription());
        dto.setGithubLink(project.getGithubLink());
        dto.setLiveDemoLink(project.getLiveLink()); // Corrected line
        return dto;
    }

    public static Project toEntity(ProjectDto dto) {
        Project project = new Project();
        project.setId(dto.getId());
        project.setTitle(dto.getTitle());
        project.setDescription(dto.getDescription());
        project.setGithubLink(dto.getGithubLink());
        project.setLiveLink(dto.getLiveDemoLink()); // Corrected line
        return project;
    }
}