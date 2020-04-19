package security.contrparties.investigations.services;

import org.springframework.transaction.annotation.Transactional;
import security.contrparties.investigations.domain.Counterparty;
import security.contrparties.investigations.domain.Header;
import security.contrparties.investigations.domain.SyncResponse;

public interface CheckAndSaveRegistrCounterpartyService {
    @Transactional
    SyncResponse simpleCheckToQueueForCheckAndAsyncResponse(Header header, Counterparty counterparty, SyncResponse syncResponse);

    @Transactional
    SyncResponse saveToQueueForFullCheckAndAsyncResponseCmpRus(Header header, Counterparty counterparty, SyncResponse syncResponse);
}
