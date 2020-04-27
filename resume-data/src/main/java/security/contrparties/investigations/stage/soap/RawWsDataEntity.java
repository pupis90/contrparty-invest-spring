package security.contrparties.investigations.stage.soap;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "SPI_WS_RAW_DATA_SAP_KIS")
public class RawWsDataEntity {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "F_RAW_DATA_ID")
    public Long id;

    @Lob
    @Column(name = "F_SOAP_DATA")
    // @Size(max = 4000)
    public String soapData;

    @Lob
    @Column(name = "F_XML_DATA")
    // @Size(max = 4000)
    public String xmlData;

    @Column(name = "F_MAIL_SUBJECT")
    @Size(max = 1000)
    public String mailSubject;

    @Lob
    @Column(name = "F_MAIL_BODY")
    //  @Size(max = 4000)
    public String mailBody = "";

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "F_CREATION_TIMESTAMP")
    public Date creationTimestamp;

    @ManyToOne(cascade = PERSIST)
    @JoinColumn(name = "F_PROCESSING_STATUS_ID")
    public ProcessingStatus processingStatus;

    @ManyToOne(cascade = PERSIST)
    @JoinColumn(name = "F_WEB_SERVICE_ID")
    public WebServiceMethodEntity webServiceEntity;

    @Lob
    @Column(name = "F_ERROR_MESSAGE")
    public String errorMessage;

    @Lob
	@Column( name = "F_SYS_INFO" )
    String sysInfo;

    @Column(name = "F_REMOTE_ADDR")
    String remoteAddr;

    String getMailBody() {
        if (mailBody == null) {
            return "";
        } else {
            return mailBody;
        }
    }
}




