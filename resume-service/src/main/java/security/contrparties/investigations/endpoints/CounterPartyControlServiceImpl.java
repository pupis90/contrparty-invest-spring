package security.contrparties.investigations.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import security.contrparties.investigations.domain.*;
import security.contrparties.investigations.services.CheckAndSaveRegistrCounterpartyService;
import security.contrparties.investigations.services.SoapMessageStageHandleServiceImpl;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBElement;
import java.math.BigDecimal;
import java.util.List;

@Endpoint
public class CounterPartyControlServiceImpl {

    /** Скрипт генерации wsdl с включенными xsd (inline)
     * Версия кода не позднее java 1.8
     * C:\Program Files\Java\jdk1.8.0_181\bin>wsgen -wsdl -inlineSchemas -r C:\\JPA -verbose -keep -d C:\\JPA -s C:\\JPA
     * -cp C:\\CompanyResumeProducer\\out\\production\\CompanyResumeProducer security.contrparties.investigations.CounterPartyControlServiceImpl
     * D:\jaxws-ri\bin\wsgen.bat -wsdl -inlineSchemas -r C:\\Wsdls -verbose -keep -d C:\\Wsdls -s C:\\Wsdls
     * -cp D:\\ProgrammVictors\\CompanyResumeProducer_jdk14\\out\\production\\CompanyResumeProducer_jdk14 security.contrparties.investigations.endpoints.CounterPartyControlServiceImpl
     */
    @Autowired
    ApplicationContext applicationContext;

    public CounterPartyControlServiceImpl(CheckAndSaveRegistrCounterpartyService checkAndSaveRegistrCounterpartyService, SoapMessageStageHandleServiceImpl soapMessageStageProcessService) {
        this.checkAndSaveRegistrCounterpartyService = checkAndSaveRegistrCounterpartyService;
        this.soapMessageStageProcessService = soapMessageStageProcessService;
    }

    CheckAndSaveRegistrCounterpartyService checkAndSaveRegistrCounterpartyService;
    SoapMessageStageHandleServiceImpl soapMessageStageProcessService;


    @PostConstruct
    private void debu(){
        int br=1;
    }

    //<!-- Сообщения КИС. Инициализация БД ИАС историческими данными о Договорах  и согласовании Договоров -->

    /**
     * Метод, принимающий исторические данные о  Договоре с контрагентом и не требующий Заключений по договорам -
     * только учет в ИАС "ПроверКА"
     * @return
     */
   // @WebMethod(operationName = "PutHistoricalContracts")
   // @WebResult(name = "ResponseSync", targetNamespace = "http://spi2.ru/jaxws/datatypes", partName = "response")
    // Можно было бы использовать @InstanceResolverAnnotation и не переписывать на Spring
    @PayloadRoot(localPart = "PutHistoricalContracts", namespace = "http://spi2.ru/jaxws/datatypes")
    @ResponsePayload
    public SyncResponse putHistoricalContracts(@RequestPayload Header header, @RequestPayload List<Contract> contracts) {


        SyncResponse handlerStatus = new SyncResponse();
        return handlerStatus;

    }

    /**
     * Метод, принимающий запрос на асинхронную выдачу Заключения СБ при согласовании Договора с контрагентом
     * @param cisTask - задание в КИС по согласованию Договора
     * @return
     */
  //  @WebMethod(operationName = "ContractConclusionRequestAsync")
  //  @WebResult(name = "ResponseSync", targetNamespace = "http://spi2.ru/jaxws/datatypes", partName = "response")
    @PayloadRoot(localPart = "ContractConclusionRequestAsync", namespace = "http://spi2.ru/jaxws/datatypes")
    @ResponsePayload
    public SyncResponse contractConclusionRequestAsync(@RequestPayload Header header, @RequestPayload CISTask cisTask) {

        SyncResponse handlerStatus = new SyncResponse();
        return handlerStatus;
    }

    /**
     * Метод, принимающий статус Договора с контрагентом для его обновления в системе
     * @param
     * @return
     */
   // @WebMethod(operationName = "PutContractStatus")
   // @WebResult(name = "ResponseSync", targetNamespace = "http://spi2.ru/jaxws/datatypes", partName = "response")
    @PayloadRoot(localPart = "PutContractStatus", namespace = "http://spi2.ru/jaxws/datatypes")
    @ResponsePayload
    //   public PutContractStatusResponse putContractStatus(@RequestPayload HeaderEntity headerEntity, @RequestPayload String contractId , @RequestPayload String approvalStage) {
    public JAXBElement<PutContractStatusResponse> putContractStatus(@RequestPayload JAXBElement<PutContractStatus> putContractStatusJAXBElement, MessageContext messageContext) {

        ObjectFactory objectFactory = new ObjectFactory();
        soapMessageStageProcessService.saveBeforeHandle(messageContext);
        PutContractStatusResponse handlerStatus = objectFactory.createPutContractStatusResponse();
        SyncResponse syncResponse = objectFactory.createSyncResponse();


        //псевдокод
        if ("exist select * from ContractEntity  where contract_id = contractId" == "true")
            // 	update ContractEntity set  approval_stage = 	approvalStage where contract_id = contractId; commit;
            syncResponse.setResultCode("success");
        else {
            syncResponse.setResultCode("test");
            String mess = "Договор с идентификатором " + putContractStatusJAXBElement.getValue().getContractId() + " не найден";
            mess += " Запрос " + putContractStatusJAXBElement.getValue().getHeader().getRequestid() + " от " +
                    putContractStatusJAXBElement.getValue().getHeader().getRequestdate();
            syncResponse.setResultMessage(mess);

        }
        handlerStatus.setResponseSync(syncResponse);

        return objectFactory.createPutContractStatusResponse(handlerStatus);
    }

/**************************************************************************************************************/
    //******<!-- Сообщения SAP SRM. Регистрация участника и cогласование участника на этапах конкуррентной процедуры -->

    /**
     * Метод, принимающий сообщение о том , что контрагент подал заявку на регистрацию (в SAP SRM)
     * @param  - реквизиты контрагента и др. информация
     *
     * @return
     */
 //   @WebMethod(operationName = "CheckAndSaveRegistrCounterpartySRM_Async")
 //   @WebResult(name = "ResponseSync", targetNamespace = "http://spi2.ru/jaxws/datatypes", partName = "response")
    @PayloadRoot(localPart = "CheckAndSaveRegistrCounterpartySRM_Async", namespace = "http://spi2.ru/jaxws/datatypes")
    @ResponsePayload
    public JAXBElement<CheckAndSaveRegistrCounterpartySRMAsyncResponse> checkAndSaveRegistrCounterpartySRM_Async(@RequestPayload JAXBElement<CheckAndSaveRegistrCounterpartySRMAsync> checkAndSaveRegistrCounterpartySRMAsyncJAXBElement
            , MessageContext messageContext) {
        ObjectFactory objectFactory = new ObjectFactory();

        soapMessageStageProcessService.saveBeforeHandle(messageContext);
        CheckAndSaveRegistrCounterpartySRMAsyncResponse checkAndSaveRegistrCounterpartySRMAsyncResponse = objectFactory.createCheckAndSaveRegistrCounterpartySRMAsyncResponse();
        SyncResponse syncResponse = objectFactory.createSyncResponse();

        Header header = checkAndSaveRegistrCounterpartySRMAsyncJAXBElement.getValue().getHeader();
        Counterparty counterparty = checkAndSaveRegistrCounterpartySRMAsyncJAXBElement.getValue().getCounterparty();

        String mess = " Запрос " + header.getRequestid() + " от " + header.getRequestdate();

        syncResponse.setResultMessage(mess);
        syncResponse = checkAndSaveRegistrCounterpartyService.simpleCheckToQueueForCheckAndAsyncResponse(header, counterparty, syncResponse);
        syncResponse = checkAndSaveRegistrCounterpartyService.saveToQueueForFullCheckAndAsyncResponseCmpRus(header, counterparty, syncResponse);
        soapMessageStageProcessService.saveAfterHandle(messageContext, syncResponse);
        checkAndSaveRegistrCounterpartySRMAsyncResponse.setResponseSync(syncResponse);

        return objectFactory.createCheckAndSaveRegistrCounterpartySRMAsyncResponse(checkAndSaveRegistrCounterpartySRMAsyncResponse);
    }


    /**
     * Метод, принимающий сообщение с данными (первый раз и обновления) о КА ,
     * в том числе о регистрации КА в SAP SRM (Дата рег. в SAP, Рег. Номер в SAP)
     * Метод должен быть вызван после того как реквизиты, предоставленные контрагентом прошли проверку
     * @param header -
     * @param registrationRegrequestId  - Ид. успешного запроса о регистрации КА
     * @param counterparty -
     * @param counterpartySapIdentities - Данные  о регистрации КА в SAP SRM (Дата рег. в SAP, Рег. Номер в SAP)
     * @return
     */
  //  @WebMethod(operationName = "PutCounterpartyInfo")
  //  @WebResult(name = "ResponseSync", targetNamespace = "http://spi2.ru/jaxws/datatypes", partName = "response")
    @PayloadRoot(localPart = "PutCounterpartyInfo", namespace = "http://spi2.ru/jaxws/datatypes")
    @ResponsePayload
    public SyncResponse putCounterpartyInfo(@RequestPayload Header header, @RequestPayload BigDecimal registrationRegrequestId,
                                            @RequestPayload  Counterparty counterparty, @RequestPayload CounterpartySapIdentities counterpartySapIdentities ) {
        SyncResponse handlerStatus = new SyncResponse();

        //1. Найти сущность

        return handlerStatus;
    }


    //----------------------------- Этапы конкурентной процедуры ----------------------

    /**
     * Метод, который может быть вызван несколько раз для одной или разных групп участников
     * во время проведения конкурентной процедуры.  По каждому участнику
     * формируется отчет нового типа, формируется отсылается асинхронно ответ с Заключением,
     * а сотрудник может его изменить
     * В Заклюении указывается
     * 1. Виза
     * 2. Комментарии: 2.1. Наличие связи с участниками КП 2.2. Перечень связанных  sap_srm_id
     * @param concurrentProcess
     * @return
     */
   // @WebMethod(operationName = "ConcurrentProcedureConclusionRequestAsync")
   // @WebResult(name = "ResponseSync", targetNamespace = "http://spi2.ru/jaxws/datatypes", partName = "response")
    @PayloadRoot(localPart = "ConcurrentProcedureConclusionRequestAsync", namespace = "http://spi2.ru/jaxws/datatypes")
    @ResponsePayload
    public SyncResponse concurrentProcedureConclusionRequestAsync(@RequestPayload Header header,
                                                                  @RequestPayload ConcurrentProcedure concurrentProcess) {
        SyncResponse handlerStatus = new SyncResponse();


        return handlerStatus;
    }














}
