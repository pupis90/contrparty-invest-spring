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
        return syncResponse;
    }


}
