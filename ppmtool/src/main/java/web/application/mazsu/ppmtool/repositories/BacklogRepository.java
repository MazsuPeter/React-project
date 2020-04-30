package web.application.mazsu.ppmtool.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import web.application.mazsu.ppmtool.domain.Backlog;

@Repository
public interface BacklogRepository  extends CrudRepository<Backlog,Long> {

    Backlog findByProjectIdentifier(String identifier);
}
