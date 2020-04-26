package security.contrparties.investigations.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import security.contrparties.investigations.ResultCodesEnum;
import security.contrparties.investigations.domain.*;
import security.contrparties.investigations.services.CheckAndSaveRegistrCounterpartyService;
import security.contrparties.investigations.services.SoapMessageStageHandleServiceImpl;
import security.contrparties.investigations.services.ValidationService;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBElement;

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

    public CounterPartyControlServiceImpl(CheckAndSaveRegistrCounterpartyService checkAndSaveRegistrCounterpartyService,
                                          SoapMessageStageHandleServiceImpl soapMessageStageProcessService,
                                          ValidationService<Counterparty> validationCntrService,
                                          ValidationService<CISTask> validationCisTaskService,
                                          ValidationService<Contract> validationContractService
    ) {
        this.checkAndSaveRegistrCounterpartyService = checkAndSaveRegistrCounterpartyService;
        this.soapMessageStageProcessService = soapMessageStageProcessService;
        this.validationCntrService = validationCntrService;
        this.validationCisTaskService = validationCisTaskService;
        this.validationContractService = validationContractService;

    }

    CheckAndSaveRegistrCounterpartyService checkAndSaveRegistrCounterpartyService;
    SoapMessageStageHandleServiceImpl soapMessageStageProcessService;
    ValidationService validationCntrService;
    ValidationService validationCisTaskService;
    ValidationService validationContractService;


    @PostConstruct
    private void debu(){
        int br=1;
    }

    //<!-- Сообщения КИС. Инициализация БД ИАС историческими данными о Договорах  и согласовании Договоров -->

    /** ================= К И С ====================================
     /**********************************************************************************************************
     * Метод, принимающий исторические данные о  Договоре с контрагентом и не требующий Заключений по договорам -
     * только учет в ИАС "ПроверКА"
     * @param putHistoricalContractsJAXBElement     содержит
     *                                                   @RequestPayload Header header,
     *      *                                            @RequestPayload Lis<Contract> - пакет историч. Договоров
     * @return
     */
   // @WebMethod(operationName = "PutHistoricalContracts")
   // @WebResult(name = "ResponseSync", targetNamespace = "http://spi2.ru/jaxws/datatypes", partName = "response")
    // Можно было бы использовать @InstanceResolverAnnotation и не переписывать на Spring
    @PayloadRoot(localPart = "PutHistoricalContracts", namespace = "http://spi2.ru/jaxws/datatypes")
    @ResponsePayload
    public JAXBElement<PutHistoricalContractsResponse> putHistoricalContracts(@RequestPayload JAXBElement<PutHistoricalContracts> putHistoricalContractsJAXBElement
            , MessageContext messageContext) {
        //@ToDo Везде проверки на null или использовать Optional класс

        ObjectFactory objectFactory = new ObjectFactory();
        soapMessageStageProcessService.saveBeforeHandle(messageContext);
        PutHistoricalContractsResponse handlerStatus = objectFactory.createPutHistoricalContractsResponse();
        SyncResponse syncResponse = objectFactory.createSyncResponse();

        syncResponse.setResultCode(ResultCodesEnum.success.name());
        String mess = "Принято в обработку.  ";
        mess += " Запрос " + putHistoricalContractsJAXBElement.getValue().getHeader().getRequestid() + " от " +
                putHistoricalContractsJAXBElement.getValue().getHeader().getRequestdate();
        mess += " В пакете договоров  " + putHistoricalContractsJAXBElement.getValue().getContracts().size();
        syncResponse.setResultMessage(mess);
        handlerStatus.setResponseSync(syncResponse);

        return objectFactory.createPutHistoricalContractsResponse(handlerStatus);

    }

    /************************************************************************************************************
     * Метод, принимающий запрос на асинхронную выдачу Заключения СБ при согласовании Договора с контрагентом
     * @param contractConclusionRequestAsyncJAXBElement содержит
     *                                                  @RequestPayload Header header,
     *                                                  @RequestPayload CISTask cisTask - задание в КИС по согласованию Договора
     * @return
     */
  //  @WebMethod(operationName = "ContractConclusionRequestAsync")
  //  @WebResult(name = "ResponseSync", targetNamespace = "http://spi2.ru/jaxws/datatypes", partName = "response")
    @PayloadRoot(localPart = "ContractConclusionRequestAsync", namespace = "http://spi2.ru/jaxws/datatypes")
    @ResponsePayload
    public JAXBElement<ContractConclusionRequestAsyncResponse> contractConclusionRequestAsync(@RequestPayload JAXBElement<ContractConclusionRequestAsync> contractConclusionRequestAsyncJAXBElement
            , MessageContext messageContext) {

        String mess;
        ObjectFactory objectFactory = new ObjectFactory();
        soapMessageStageProcessService.saveBeforeHandle(messageContext);
        ContractConclusionRequestAsyncResponse handlerStatus = objectFactory.createContractConclusionRequestAsyncResponse();
        CISTask cisTask = contractConclusionRequestAsyncJAXBElement.getValue().getCisTask();
        SyncResponse syncResponse = validationCisTaskService.validate(cisTask);
        handlerStatus.setResponseSync(syncResponse);
        soapMessageStageProcessService.updateAfterHandle(messageContext, syncResponse);

        mess = syncResponse.getResultMessage();
        mess += " Запрос " + contractConclusionRequestAsyncJAXBElement.getValue().getHeader().getRequestid() + " от " +
                contractConclusionRequestAsyncJAXBElement.getValue().getHeader().getRequestdate();
        syncResponse.setResultMessage(mess);

        return objectFactory.createContractConclusionRequestAsyncResponse(handlerStatus);
    }

    /***************************************************************************************************************
     * Метод, принимающий статус Договора с контрагентом для его обновления в системе
     * @param
     * @return
     */
   // @WebMethod(operationName = "PutContractStatus")
   // @WebResult(name = "ResponseSync", targetNamespace = "http://spi2.ru/jaxws/datatypes", partName = "response")
    @PayloadRoot(localPart = "PutContractStatus", namespace = "http://spi2.ru/jaxws/datatypes")
    @ResponsePayload
    //   public PutContractStatusResponse putContractStatus(@RequestPayload HeaderEntity headerEntity, @RequestPayload String contractId , @RequestPayload String approvalStage) {
    public JAXBElement<PutContractStatusResponse> putContractStatus(@RequestPayload JAXBElement<PutContractStatus> putContractStatusJAXBElement
            , MessageContext messageContext) {

        ObjectFactory objectFactory = new ObjectFactory();
        soapMessageStageProcessService.saveBeforeHandle(messageContext);

        PutContractStatusResponse handlerStatus = objectFactory.createPutContractStatusResponse();
        SyncResponse syncResponse = objectFactory.createSyncResponse();


            syncResponse.setResultCode("success");
        String mess = " Получен договор с идентификатором " + putContractStatusJAXBElement.getValue().getContractId();
            mess += " Запрос " + putContractStatusJAXBElement.getValue().getHeader().getRequestid() + " от " +
                    putContractStatusJAXBElement.getValue().getHeader().getRequestdate();
            syncResponse.setResultMessage(mess);

        handlerStatus.setResponseSync(syncResponse);

        return objectFactory.createPutContractStatusResponse(handlerStatus);
    }

    /** ================= Сообщения SAP SRM. Регистрация участника и cогласование участника на этапах конкуррентной процедуры  ========================
     //******<!--  -->

     /*******************************************************************************************************
     *  Метод, принимающий сообщение о том , что контрагент подал заявку на регистрацию (в SAP SRM)
     *  с целью проведения проверки основных данных, предоставленных контрагентом
     * @param checkAndSaveRegistrCounterpartySRMAsyncJAXBElement  содержит Header,
     *                                                            registrReqGuid - GUID заявки, его нужно вернуть в асинхр. ответе
     *                                                            counterparty - контрагент (ИНН, ОКПО ..) эти реквизиты нужно проверит по простому тут же
     *                                                            а по СПАРК в iRule и вернуть результат проверки в асинхронном ответе
     *
     *
     * @return
     */
//    @WebMethod(operationName = "CheckAndSaveRegistrCounterpartySRM_Async")
//    @WebResult(name = "ResponseSync", targetNamespace = "http://spi2.ru/jaxws/datatypes", partName = "response")
//    public SyncResponse checkAndSaveRegistrCounterpartySRM_Async(@WebParam(name = "header") Header header,
//                                                                 @WebParam(name = "registrreq_guid") String registrReqGuid,
//                                                                 @WebParam(name = "counterparty") Counterparty counterparty) {

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
        //  syncResponse = checkAndSaveRegistrCounterpartyService.saveToQueueForFullCheckAndAsyncResponseCmpRus(header, counterparty, syncResponse);

        soapMessageStageProcessService.updateAfterHandle(messageContext, syncResponse);
        checkAndSaveRegistrCounterpartySRMAsyncResponse.setResponseSync(syncResponse);

        return objectFactory.createCheckAndSaveRegistrCounterpartySRMAsyncResponse(checkAndSaveRegistrCounterpartySRMAsyncResponse);
    }


    /***************************************************************************************************************
     * Метод, принимающий сообщение с данными (первый раз и обновления) о КА ,
     * в том числе о регистрации КА в SAP SRM (Дата рег. в SAP, Рег. Номер в SAP)
     * Метод должен быть вызван после того как реквизиты, предоставленные контрагентом прошли проверку
     * @param putCounterpartyInfoJAXBElement содержит  header -
     *                                       registrationRegrequestId  - Ид. успешного запроса о регистрации КА (по идее нужно только для первого сообщения
     *                                       counterparty -
     *                                       counterpartySapIdentities - Данные  о регистрации КА в SAP SRM (Дата рег. в SAP, Рег. Номер в SAP)
     * @return
     */
  //  @WebMethod(operationName = "PutCounterpartyInfo")
  //  @WebResult(name = "ResponseSync", targetNamespace = "http://spi2.ru/jaxws/datatypes", partName = "response")
    @PayloadRoot(localPart = "PutCounterpartyInfo", namespace = "http://spi2.ru/jaxws/datatypes")
    @ResponsePayload
    public JAXBElement<PutCounterpartyInfoResponse> putCounterpartyInfo(@RequestPayload JAXBElement<PutCounterpartyInfo> putCounterpartyInfoJAXBElement
            , MessageContext messageContext) {

        ObjectFactory objectFactory = new ObjectFactory();
        soapMessageStageProcessService.saveBeforeHandle(messageContext);
        PutCounterpartyInfoResponse handlerStatus = objectFactory.createPutCounterpartyInfoResponse();
        SyncResponse syncResponse = objectFactory.createSyncResponse();
        syncResponse.setResultCode("success");
        String mess = " Получен контрагент с идентификатором " + putCounterpartyInfoJAXBElement.getValue().getCounterpartySapIdentities().getSapSrmId();
        mess += " Запрос " + putCounterpartyInfoJAXBElement.getValue().getHeader().getRequestid() + " от " +
                putCounterpartyInfoJAXBElement.getValue().getHeader().getRequestdate();
        syncResponse.setResultMessage(mess);

        handlerStatus.setResponseSync(syncResponse);

        return objectFactory.createPutCounterpartyInfoResponse(handlerStatus);
    }

    //----------------------------- Этапы конкурентной процедуры ----------------------

    /*****************************************************************************
     * Метод, который может быть вызван несколько раз для одной или разных групп участников
     * во время проведения конкурентной процедуры.  По каждому участнику
     * формируется отчет нового типа, формируется отсылается асинхронно ответ с Заключением,
     * а сотрудник может его изменить
     * В Заклюении указывается
     * 1. Виза
     * 2. Комментарии: 2.1. Наличие связи с участниками КП 2.2. Перечень связанных  sap_srm_id
     * @param  concurrentProcedureConclusionRequestAsyncJAXBElement
     *                                                  содержит header
     *                                                  concurrentProcedure
     * @return
     */
   // @WebMethod(operationName = "ConcurrentProcedureConclusionRequestAsync")
   // @WebResult(name = "ResponseSync", targetNamespace = "http://spi2.ru/jaxws/datatypes", partName = "response")
    @PayloadRoot(localPart = "ConcurrentProcedureConclusionRequestAsync", namespace = "http://spi2.ru/jaxws/datatypes")
    @ResponsePayload
    public JAXBElement<ConcurrentProcedureConclusionRequestAsyncResponse> concurrentProcedureConclusionRequestAsync(
            @RequestPayload JAXBElement<ConcurrentProcedureConclusionRequestAsync> concurrentProcedureConclusionRequestAsyncJAXBElement
            , MessageContext messageContext) {
        ObjectFactory objectFactory = new ObjectFactory();
        soapMessageStageProcessService.saveBeforeHandle(messageContext);
        ConcurrentProcedureConclusionRequestAsyncResponse handlerStatus = objectFactory.createConcurrentProcedureConclusionRequestAsyncResponse();
        SyncResponse syncResponse = objectFactory.createSyncResponse();
        syncResponse.setResultCode("success");
        String mess = " Получен запрос на проверку группы участников конкуррентной процедуры с идентификатором " +
                concurrentProcedureConclusionRequestAsyncJAXBElement.getValue().getConcurrentProcedure().getProcedureNumber();
        mess += " Запрос " + concurrentProcedureConclusionRequestAsyncJAXBElement.getValue().getHeader().getRequestid() + " от " +
                concurrentProcedureConclusionRequestAsyncJAXBElement.getValue().getHeader().getRequestdate();
        syncResponse.setResultMessage(mess);

        handlerStatus.setResponseSync(syncResponse);

        return objectFactory.createConcurrentProcedureConclusionRequestAsyncResponse(handlerStatus);
    }














}
