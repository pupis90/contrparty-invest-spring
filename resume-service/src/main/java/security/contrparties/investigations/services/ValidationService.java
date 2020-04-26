package security.contrparties.investigations.services;

import security.contrparties.investigations.domain.SyncResponse;

public interface ValidationService<T> {

    public SyncResponse validate(T payloadObj);
}
