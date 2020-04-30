package web.application.mazsu.ppmtool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import web.application.mazsu.ppmtool.domain.Backlog;
import web.application.mazsu.ppmtool.domain.Project;
import web.application.mazsu.ppmtool.domain.ProjectTask;
import web.application.mazsu.ppmtool.exceptions.ProjectNotFoundException;
import web.application.mazsu.ppmtool.repositories.BacklogRepository;
import web.application.mazsu.ppmtool.repositories.ProjectRepository;
import web.application.mazsu.ppmtool.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask){
        try {
            Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);

            projectTask.setBacklog(backlog);

            Integer backLogSequence = backlog.getPTSequence();
            backLogSequence++;

            backlog.setPTSequence(backLogSequence);

            projectTask.setProjectSequence(projectIdentifier + "-" + backLogSequence);
            projectTask.setProjectIdentifier(projectIdentifier);

            if (projectTask.getPriority() == null) {
                projectTask.setPriority(3);
            }
            if (StringUtils.isEmpty(projectTask.getStatus())) {
                projectTask.setStatus("TO_DO");
            }
            return projectTaskRepository.save(projectTask);
        }catch (Exception e){
            throw new ProjectNotFoundException("Project not found");
        }
    }

    public Iterable<ProjectTask> findBacklogById(String id){

        Project project = projectRepository.findByProjectIdentifier(id);

        if(project == null){
            throw new ProjectNotFoundException("Project with ID: '" + id +"' does not exist");
        }

        return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
    }

    public ProjectTask findPTByProjectSequence(String backlog_id,String pt_id){
        return projectTaskRepository.findbyProjectSequence(pt_id);
    }
}
