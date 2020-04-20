package security.contrparties.investigations.dao;

import org.springframework.data.repository.CrudRepository;
import security.contrparties.investigations.stage.soap.WebServiceMethodEntity;

import java.util.List;

public interface WebServiceMethodRepo extends CrudRepository<WebServiceMethodEntity, Long> {
    List<WebServiceMethodEntity> findByWebServiceNameAndWebMethodName(String webServiceName, String webMethodName);
}

