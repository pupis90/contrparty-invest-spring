package security.contrparties.investigations.dao;

import security.contrparties.investigations.entities.CounterpartyEntity;

import java.util.List;

public interface CouterpartyRepository {
    List<CounterpartyEntity> findAll();

    CounterpartyEntity findById(int theId);

    void save(CounterpartyEntity counterpartyEntity);

    void deleteById(int theId);
}
