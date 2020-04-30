package web.application.mazsu.ppmtool.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import web.application.mazsu.ppmtool.domain.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long>{

	public Project findByProjectIdentifier(String projectId);

	@Override
	public Iterable<Project> findAll();
	
	
}
