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
import java.util.List;

@Endpoint
public class CounterPartyControlServiceImpl {

    /**
     * Скрипт генерации wsdl с включенными xsd (inline)
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
    private void debu() {
        int br = 1;
    }

    //<!-- Сообщения КИС. Инициализация БД ИАС историческими данными о Договорах  и согласовании Договоров -->

    /**
     * ================= К И С ====================================
     * /**********************************************************************************************************
     * Метод, принимающий исторические данные о  Договоре с контрагентом и не требующий Заключений по договорам -
     * только учет в ИАС "ПроверКА"
     *
     * @param putHistoricalContractsJAXBElement содержит
     * @return
     * @RequestPayload Header header,
     * *                                            @RequestPayload Lis<Contract> - пакет историч. Договоров
     */
    // @WebMethod(operationName = "PutHistoricalContracts")
    // @WebResult(name = "ResponseSync", targetNamespace = "http://spi2.ru/jaxws/datatypes", partName = "response")
    // Можно было бы использовать @InstanceResolverAnnotation и не переписывать на Spring
    @PayloadRoot(localPart = "PutHistoricalContracts", namespace = "http://spi2.ru/jaxws/datatypes")
    @ResponsePayload
    public JAXBElement<PutHistoricalContractsResponse> putHistoricalContracts(@RequestPayload JAXBElement<PutHistoricalContracts> putHistoricalContractsJAXBElement
            , MessageContext messageContext) {

        ObjectFactory objectFactory = new ObjectFactory();
        PutHistoricalContractsResponse handlerStatus = objectFactory.createPutHistoricalContractsResponse();
        String mess = "";
        SyncResponse syncResponse = objectFactory.createSyncResponse();
        syncResponse.setResultCode(ResultCodesEnum.success.name());

        // @ToDo  1. Сохранение по приему со статусом PERSIST_RAW_DATA_DONE

        soapMessageStageProcessService.saveBeforeHandle(messageContext);

        if (putHistoricalContractsJAXBElement != null &&
                putHistoricalContractsJAXBElement.getValue() != null) {

            //@ToDo  2. Валидация на not null и обязательные поля - сервис не сделан
            // Внимание. Это список, в xsd он не ограничен. Но нужно как то его size () ограничить величиноой пакета, в настройках
            // Иначе ограмное поле LOB будет/ Не забыть в ТЗ включить это ограничение


            //@ToDo 3.  ДЛЯ ИСТОРИЧ. ДАННЫХ НЕ НУЖНО !! Обновление записи телом почтового сообщения
            // ****


            //@ToDo 4. Сообщение для синхронного ответа
            Header header = putHistoricalContractsJAXBElement.getValue().getHeader();
            if (header != null) {
                mess += "Принято в обработку.  ";
                mess += " Запрос " + header.getRequestid() + " от " + header.getRequestdate();
                List<Contract> contracts = putHistoricalContractsJAXBElement.getValue().getContracts();
                if (contracts != null) {
                    mess += " В пакете договоров  " + contracts.size();
                } else {
                    mess += " Пустой пакет договоров";
                }
                handlerStatus.setResponseSync(syncResponse);
            } else {
                mess += " Запрос содержит пустой заголовок. ";
                syncResponse.setResultCode(ResultCodesEnum.failure.name());
            }

        } else {
            mess = " Пустой запрос";
            syncResponse = objectFactory.createSyncResponse();
            syncResponse.setResultCode(ResultCodesEnum.failure.name());

        }

        syncResponse.setResultMessage(mess);
        //@ToDo  5. Обновление записи по результатам валидации задачи
        // КИС , ее Контракта, лиц Контракта на not null и обязательные поля, прочий ФЛК
        //статус         VALIDATION_SUCCESS или VALIDATION_FAULT
        soapMessageStageProcessService.updateAfterHandle(messageContext, syncResponse);
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

        ObjectFactory objectFactory = new ObjectFactory();
        ContractConclusionRequestAsyncResponse handlerStatus = objectFactory.createContractConclusionRequestAsyncResponse();
        String mess = "";
        SyncResponse syncResponse = objectFactory.createSyncResponse();
        syncResponse.setResultCode(ResultCodesEnum.success.name());

        // @ToDo  1. Сохранение по приему со статусом PERSIST_RAW_DATA_DONE
        soapMessageStageProcessService.saveBeforeHandle(messageContext);

        if (contractConclusionRequestAsyncJAXBElement != null &&
                contractConclusionRequestAsyncJAXBElement.getValue() != null) {
            CISTask cisTask = contractConclusionRequestAsyncJAXBElement.getValue().getCisTask();

            //@ToDo  2. Валидация задаич КИС , ее Контракта, лиц Контракта на not null и обязательные поля, прочий ФЛК
            syncResponse = validationCisTaskService.validate(cisTask); //
            handlerStatus.setResponseSync(syncResponse);

            //@ToDo 3.  Обновление записи телом почтового сообщения
            // ****


            //@ToDo 4. Сообщение для синхронного ответа
            mess = syncResponse.getResultMessage();
            Header header = contractConclusionRequestAsyncJAXBElement.getValue().getHeader();
            if (header != null) {
                mess += " Запрос " + header.getRequestid() + " от " + header.getRequestdate() + ".";

            } else {
                mess += " Запрос содержит пустой заголовок. ";
                syncResponse.setResultCode(ResultCodesEnum.failure.name());
            }

        } else {

            mess = " Пустой запрос";
            syncResponse = objectFactory.createSyncResponse();
            syncResponse.setResultCode(ResultCodesEnum.failure.name());

        }

        syncResponse.setResultMessage(mess);

        //@ToDo  5. Обновление записи по результатам валидации задачи
        // КИС , ее Контракта, лиц Контракта на not null и обязательные поля, прочий ФЛК
        //статус         VALIDATION_SUCCESS или VALIDATION_FAULT
        soapMessageStageProcessService.updateAfterHandle(messageContext, syncResponse);

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
        PutContractStatusResponse handlerStatus = objectFactory.createPutContractStatusResponse();
        String mess = " ";
        SyncResponse syncResponse = objectFactory.createSyncResponse();
        syncResponse.setResultCode(ResultCodesEnum.success.name());

        // @ToDo  1. Сохранение по приему со статусом PERSIST_RAW_DATA_DONE
        soapMessageStageProcessService.saveBeforeHandle(messageContext);

        if (putContractStatusJAXBElement != null &&
                putContractStatusJAXBElement.getValue() != null) {

            // @ToDo 2. Валидация  на not null и обязательные поля, прочий ФЛК - тут всего 3 поля
            String contractId = putContractStatusJAXBElement.getValue().getContractId();
            String status = putContractStatusJAXBElement.getValue().getApprovalStage();
            if (contractId == null) {
                mess += " Получен пустой идентификатор контракта ";
                syncResponse.setResultCode(ResultCodesEnum.failure.name());
            }
            if (status == null) {
                mess += " Получен пустой статус ";
                syncResponse.setResultCode(ResultCodesEnum.failure.name());
            }

            Header header = putContractStatusJAXBElement.getValue().getHeader();
            if (header != null) {

                //@ToDo 3.  Обновление записи телом почтового сообщения
                // ****

                //@ToDo 4. Подготовка сообщения для синхронного ответа

                mess += " Получен новый статус " + status + " по  договору с идентификатором " + contractId + ". ";
                mess += " Запрос " + header.getRequestid() + " от " + header.getRequestdate() + ".";
                syncResponse.setResultMessage(mess);

            } else {
                mess += " Запрос содержит пустой заголовок. ";
                syncResponse.setResultCode(ResultCodesEnum.failure.name());
            }

        } else {
            mess = " Пустой запрос";
            syncResponse = objectFactory.createSyncResponse();
            syncResponse.setResultCode(ResultCodesEnum.failure.name());

        }

        syncResponse.setResultMessage(mess);
        handlerStatus.setResponseSync(syncResponse);

        //@ToDo  5. Обновление записи по результатам валидации задачи
        // КИС , ее Контракта, лиц Контракта на not null и обязательные поля, прочий ФЛК
        //статус         VALIDATION_SUCCESS или VALIDATION_FAULT
        soapMessageStageProcessService.updateAfterHandle(messageContext, syncResponse);

        return objectFactory.createPutContractStatusResponse(handlerStatus);
    }

    /**
     * ================= Сообщения SAP SRM. Регистрация участника и cогласование участника на этапах конкуррентной процедуры  ========================
     * //******<!--  -->
     * <p>
     * /*******************************************************************************************************
     * Метод, принимающий сообщение о том , что контрагент подал заявку на регистрацию (в SAP SRM)
     * с целью проведения проверки основных данных, предоставленных контрагентом
     *
     * @param checkAndSaveRegistrCounterpartySRMAsyncJAXBElement содержит Header,
     *                                                           registrReqGuid - GUID заявки, его нужно вернуть в асинхр. ответе
     *                                                           counterparty - контрагент (ИНН, ОКПО ..) эти реквизиты нужно проверит по простому тут же
     *                                                           а по СПАРК в iRule и вернуть результат проверки в асинхронном ответе
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
        CheckAndSaveRegistrCounterpartySRMAsyncResponse handlerStatus =
                objectFactory.createCheckAndSaveRegistrCounterpartySRMAsyncResponse();
        String mess = "";
        SyncResponse syncResponse = objectFactory.createSyncResponse();
        syncResponse.setResultCode(ResultCodesEnum.success.name());

        // @ToDo  1. Сохранение по приему со статусом PERSIST_RAW_DATA_DONE
        soapMessageStageProcessService.saveBeforeHandle(messageContext);

        if (checkAndSaveRegistrCounterpartySRMAsyncJAXBElement != null &&
                checkAndSaveRegistrCounterpartySRMAsyncJAXBElement.getValue() != null) {
            Counterparty counterparty = checkAndSaveRegistrCounterpartySRMAsyncJAXBElement.getValue().getCounterparty();

            //@ToDo  2. Валидация задаич КИС , ее Контракта, лиц Контракта на not null и обязательные поля, прочий ФЛК
            syncResponse = validationCntrService.validate(counterparty); //
            handlerStatus.setResponseSync(syncResponse);

            //@ToDo 3.  Обновление записи телом почтового сообщения
            // ****


            //@ToDo 4. Сообщение для синхронного ответа
            mess = syncResponse.getResultMessage();
            Header header = checkAndSaveRegistrCounterpartySRMAsyncJAXBElement.getValue().getHeader();


            mess += " Запрос " + header.getRequestid() + " от " + header.getRequestdate();

        } else {

            mess = " Пустой запрос";
            syncResponse = objectFactory.createSyncResponse();
            syncResponse.setResultCode(ResultCodesEnum.failure.name());

        }

        syncResponse.setResultMessage(mess);

        //@ToDo  5. Обновление записи по результатам валидации задачи
        soapMessageStageProcessService.updateAfterHandle(messageContext, syncResponse);

        return objectFactory.createCheckAndSaveRegistrCounterpartySRMAsyncResponse(handlerStatus);
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
        PutCounterpartyInfoResponse handlerStatus = objectFactory.createPutCounterpartyInfoResponse();
        String mess = "";
        SyncResponse syncResponse = objectFactory.createSyncResponse();
        syncResponse.setResultCode(ResultCodesEnum.success.name());

        // @ToDo  1. Сохранение по приему со статусом PERSIST_RAW_DATA_DONE
        soapMessageStageProcessService.saveBeforeHandle(messageContext);

        Counterparty counterparty = putCounterpartyInfoJAXBElement.getValue().getCounterparty();

        //@ToDo  2. Валидация задаич КИС , ее Контракта, лиц Контракта на not null и обязательные поля, прочий ФЛК
        syncResponse = validationCntrService.validate(counterparty); //


        //@ToDo 3.  Обновление записи телом почтового сообщения
        // ****


        //@ToDo 4. Сообщение для синхронного ответа
        mess += " Получен контрагент с идентификатором " + putCounterpartyInfoJAXBElement.getValue().getCounterpartySapIdentities().getSapSrmId();
        mess += " Запрос " + putCounterpartyInfoJAXBElement.getValue().getHeader().getRequestid() + " от " +
                putCounterpartyInfoJAXBElement.getValue().getHeader().getRequestdate();
        syncResponse.setResultMessage(mess);

        handlerStatus.setResponseSync(syncResponse);

        //@ToDo  5. Обновление записи по результатам валидации задачи
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
        ConcurrentProcedureConclusionRequestAsyncResponse handlerStatus =
                objectFactory.createConcurrentProcedureConclusionRequestAsyncResponse();
        String mess = "";
        SyncResponse syncResponse = objectFactory.createSyncResponse();
        syncResponse.setResultCode(ResultCodesEnum.success.name());

        // @ToDo  1. Сохранение по приему со статусом PERSIST_RAW_DATA_DONE
        soapMessageStageProcessService.saveBeforeHandle(messageContext);
        //@ToDo 3.  Обновление записи телом почтового сообщения
        // ****


        //@ToDo 4. Сообщение для синхронного ответа

        mess += " Получен запрос на проверку группы участников конкуррентной процедуры с идентификатором " +
                concurrentProcedureConclusionRequestAsyncJAXBElement.getValue().getConcurrentProcedure().getProcedureNumber();
        mess += " Запрос " + concurrentProcedureConclusionRequestAsyncJAXBElement.getValue().getHeader().getRequestid() + " от " +
                concurrentProcedureConclusionRequestAsyncJAXBElement.getValue().getHeader().getRequestdate();
        syncResponse.setResultMessage(mess);

        //@ToDo  5. Обновление записи по результатам валидации задачи
        handlerStatus.setResponseSync(syncResponse);

        return objectFactory.createConcurrentProcedureConclusionRequestAsyncResponse(handlerStatus);
    }


}
