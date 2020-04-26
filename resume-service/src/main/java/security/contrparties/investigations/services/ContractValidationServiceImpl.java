package security.contrparties.investigations.services;

import org.springframework.stereotype.Service;
import security.contrparties.investigations.ResultCodesEnum;
import security.contrparties.investigations.domain.Contract;
import security.contrparties.investigations.domain.ObjectFactory;
import security.contrparties.investigations.domain.SyncResponse;

@Service
public class ContractValidationServiceImpl implements ValidationService<Contract> {

    @Override
    public SyncResponse validate(Contract payloadObj) {
        ObjectFactory objectFactory = new ObjectFactory();
        SyncResponse syncResponse = objectFactory.createSyncResponse();
        syncResponse.setResultCode(ResultCodesEnum.success.name());
        return syncResponse;
    }
}

