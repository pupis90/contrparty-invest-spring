package security.contrparties.investigations.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import security.contrparties.investigations.dao.CouterpartyRepository;
import security.contrparties.investigations.domain.Counterparty;
import security.contrparties.investigations.domain.Header;
import security.contrparties.investigations.domain.SyncResponse;
import security.contrparties.investigations.entities.CounterpartyEntity;

@Service
public class CheckAndSaveRegistrCounterpartyServiceImpl implements CheckAndSaveRegistrCounterpartyService {


    private CouterpartyRepository counterpartyRepository;

    @Autowired
    public CheckAndSaveRegistrCounterpartyServiceImpl(CouterpartyRepository counterpartyRepository) {
        this.counterpartyRepository = counterpartyRepository;
    }


    @Override
    @Transactional
    public SyncResponse simpleCheckToQueueForCheckAndAsyncResponse(Header header, Counterparty counterparty, SyncResponse syncResponse) {

        //Вообще регулярка будет в xsd , но и тут нужно проверять для надежности

        // Контроль ИНН

        // Контроль ОКПО

        // Контроль ОГРН

        // Контроль КПП

        return syncResponse;

    }

    @Override
    @Transactional
    public SyncResponse saveToQueueForFullCheckAndAsyncResponseCmpRus(Header header, Counterparty counterparty, SyncResponse syncResponse) {

        CounterpartyEntity ContractPersonEntity = new CounterpartyEntity(counterparty);
        syncResponse.setResultCode("test");
        counterpartyRepository.save(ContractPersonEntity);
        syncResponse.setResultMessage(syncResponse.getResultMessage() + " Тест. Результат принятия (сохранения) реквизитов контрагента. Например, Код success Рекивизиты сохранены. ");
        return syncResponse;


    }

}
