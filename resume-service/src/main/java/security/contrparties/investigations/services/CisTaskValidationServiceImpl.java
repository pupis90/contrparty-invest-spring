package security.contrparties.investigations.services;

import org.springframework.stereotype.Service;
import security.contrparties.investigations.ResultCodesEnum;
import security.contrparties.investigations.domain.CISTask;
import security.contrparties.investigations.domain.ObjectFactory;
import security.contrparties.investigations.domain.SyncResponse;


@Service
public class CisTaskValidationServiceImpl implements ValidationService<CISTask> {


    @Override
    public SyncResponse validate(CISTask cisTask) {
        ObjectFactory objectFactory = new ObjectFactory();
        SyncResponse syncResponse = objectFactory.createSyncResponse();
        String mess = " Задача КИС " + cisTask.getTaskId();
        String contractId = cisTask.getContract().getContractId();
        mess += " Будет выдано Заключение СБ на Договор с идентификатором " + contractId;
        syncResponse.setResultMessage(mess);

        //Если валидация успешно
        syncResponse.setResultCode(ResultCodesEnum.success.name());
        return syncResponse;
    }
}
