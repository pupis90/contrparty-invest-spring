package security.contrparties.investigations.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.soap.SoapMessage;
import security.contrparties.investigations.dao.ProcessingStatusRepo;
import security.contrparties.investigations.dao.SoapMessageStageRepositoryImpl;
import security.contrparties.investigations.dao.WebServiceMethodRepo;
import security.contrparties.investigations.domain.SyncResponse;
import security.contrparties.investigations.stage.soap.ProcessingStatus;
import security.contrparties.investigations.stage.soap.RawWsDataEntity;
import security.contrparties.investigations.stage.soap.WebServiceMethodEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static security.contrparties.investigations.stage.soap.ProcessingStatus.PERSIST_RAW_DATA_DONE;

@Service
public class SoapMessageStageHandleServiceImpl {
    Logger logger = LogManager.getLogger(SoapMessageStageHandleServiceImpl.class);

    @Autowired
    SoapMessageStageRepositoryImpl soapMessageStageRepository;
    @Autowired
    WebServiceMethodRepo webServiceMethodRepo;

    @Autowired
    ProcessingStatusRepo procesingStatusRepo;


    @Transactional
    public void saveBeforeHandle(MessageContext messageContext) {

        try {
            RawWsDataEntity rawWsDataEntity = new RawWsDataEntity();
            WebServiceMessage webServiceMessage = messageContext.getRequest();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            webServiceMessage.writeTo(outputStream);

            if (webServiceMessage instanceof SoapMessage) {
                SoapMessage soapMessage = (SoapMessage) webServiceMessage;
                logger.debug("*********** Это SOAP Message ******");
            }

            String soapContent = outputStream.toString("UTF-8");
            /**@ToDo !!!!!!!!!!!!!! Это обрезание исключительно при прототипировании для поля character varing Postgres. Нужно для Oracle
            !!!!!!!!!!!  раскомментировать @Lob  */
            logger.info(soapContent);
            String soapContentFit = "";
            soapContentFit = (soapContent.length() < 1500) ? soapContent : soapContent.substring(0, 1499);
            //*************************
            rawWsDataEntity.soapData = soapContentFit;
            rawWsDataEntity.creationTimestamp = new Date();


            String service = "CounterPartyControlService";
            String method = webServiceMessage.getPayloadSource().getSystemId();

            List<WebServiceMethodEntity> result = webServiceMethodRepo.findByWebServiceNameAndWebMethodName(service, method);
            if (result.size() == 0) {
                rawWsDataEntity.webServiceEntity = new WebServiceMethodEntity();
                rawWsDataEntity.webServiceEntity.webServiceName = method;
                rawWsDataEntity.webServiceEntity.webServiceName = service;
            } else {
                rawWsDataEntity.webServiceEntity = result.get(0);
            }

            List<ProcessingStatus> status = procesingStatusRepo.findByName(PERSIST_RAW_DATA_DONE.name);
            if (status.size() == 0) {
                rawWsDataEntity.processingStatus = PERSIST_RAW_DATA_DONE;
            } else {

                rawWsDataEntity.processingStatus = status.get(0);
            }

            soapMessageStageRepository.save(rawWsDataEntity);

        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e);
        }
    }


    @Transactional
    public void updateAfterHandle(MessageContext messageContext, SyncResponse syncResponse) {


    }
}
