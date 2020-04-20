package security.contrparties.investigations.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import security.contrparties.investigations.stage.soap.RawWsDataEntity;

import javax.persistence.EntityManager;

@Repository
public class SoapMessageStageRepositoryImpl {

    private EntityManager entityManager;

    public SoapMessageStageRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveOrUpdate(RawWsDataEntity rawWsDataEntity) {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // save employee
        currentSession.saveOrUpdate(rawWsDataEntity);

    }
}