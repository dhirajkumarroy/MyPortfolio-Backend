package Dhiraj.sPortfolio.My_Portfolio.service;

import Dhiraj.sPortfolio.My_Portfolio.entity.Project;
import Dhiraj.sPortfolio.My_Portfolio.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public Page<Project> getAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(Long id, Project project) {
        return projectRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(project.getTitle());
                    existing.setDescription(project.getDescription());
                    // Corrected method calls to match the Project entity's fields
                    existing.setGithubLink(project.getGithubLink());
                    existing.setLiveLink(project.getLiveLink());
                    return projectRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}