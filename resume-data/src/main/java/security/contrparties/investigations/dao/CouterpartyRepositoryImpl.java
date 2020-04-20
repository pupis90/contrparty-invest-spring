package security.contrparties.investigations.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import security.contrparties.investigations.entities.CounterpartyEntity;

import javax.persistence.EntityManager;
import javax.persistence.Table;
import java.util.List;


@Repository
public class CouterpartyRepositoryImpl implements CouterpartyRepository {

    private EntityManager entityManager;

    public CouterpartyRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<CounterpartyEntity> findAll() {
        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // create a query
        Query<CounterpartyEntity> theQuery =
                currentSession.createQuery("from Counterparty", CounterpartyEntity.class);

        // execute query and get result list
        List<CounterpartyEntity> counterpartyEntities = theQuery.getResultList();

        // return the results
        return counterpartyEntities;
    }

    @Override
    public CounterpartyEntity findById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // get the employee
        CounterpartyEntity counterpartyEntity =
                currentSession.get(CounterpartyEntity.class, theId);

        // return the counterpartyEntity
        return counterpartyEntity;
    }


    @Override
    public void save(CounterpartyEntity counterpartyEntity) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // save employee
        currentSession.saveOrUpdate(counterpartyEntity);
    }


    @Override
    public void deleteById(int theId) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // delete object with primary key , а схeма ?
        Query theQuery =
                currentSession.createQuery(
                        "delete from " + CounterpartyEntity.class.getAnnotation(Table.class).name() + " where id=:counterpartyEntityId", CounterpartyEntity.class);
        theQuery.setParameter("counterpartyEntityId", theId);

        theQuery.executeUpdate();
    }


}
