package security.contrparties.investigations.dao;

import org.springframework.data.repository.CrudRepository;
import security.contrparties.investigations.stage.soap.ProcessingStatus;

import java.util.List;

public interface ProcessingStatusRepo extends CrudRepository<ProcessingStatus, Long> {
    List<ProcessingStatus> findByName(String name);
}
