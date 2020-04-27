package security.contrparties.investigations.services;

import org.springframework.stereotype.Service;
import security.contrparties.investigations.ResultCodesEnum;
import security.contrparties.investigations.domain.Counterparty;
import security.contrparties.investigations.domain.ObjectFactory;
import security.contrparties.investigations.domain.SyncResponse;

@Service
public class ContrPartyValidationServiceImpl implements ValidationService<Counterparty> {

    @Override
    public SyncResponse validate(Counterparty counterparty) {
        ObjectFactory objectFactory = new ObjectFactory();
        SyncResponse syncResponse = objectFactory.createSyncResponse();
        syncResponse.setResultCode(ResultCodesEnum.success.name());

        String mess = "";
        //Вообще регулярка будет в xsd , но и тут нужно проверять для надежности

        // Контроль ИНН

        // Контроль ОКПО

        // Контроль ОГРН

        // Контроль КПП

        syncResponse.setResultMessage(syncResponse.getResultMessage() +
                " Тест. Результат ФЛК реквизитов контрагента. Например, Код success  ");
        return syncResponse;

    }


}
