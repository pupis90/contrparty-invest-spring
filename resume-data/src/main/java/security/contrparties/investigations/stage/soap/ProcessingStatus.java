package security.contrparties.investigations.stage.soap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SPI_WS_PROCESSING_STATUS")
public class ProcessingStatus {
    static public List<ProcessingStatus> values = new ArrayList<>();

    static public ProcessingStatus PERSIST_RAW_DATA_DONE;

    static public ProcessingStatus VALIDATION_SUCCESS;
    static public ProcessingStatus VALIDATION_FAULT;

    static ProcessingStatus CA_UPDATE_SUCCESS;
    static ProcessingStatus CA_UPDATE_FAULT;

    static public ProcessingStatus WAITING_SPARK_RESPONSE;

    static public ProcessingStatus SPARK_VALIDATION_FAULT;

    static public ProcessingStatus DATA_SAVE_SUCCESS;
    static public ProcessingStatus DATA_SAVE_FAULT;

    static {
        PERSIST_RAW_DATA_DONE = new ProcessingStatus(0L, "PERSIST_RAW_DATA_DONE");
        VALIDATION_SUCCESS = new ProcessingStatus(10L, "VALIDATION_SUCCESS");
        VALIDATION_FAULT = new ProcessingStatus(-10L, "VALIDATION_FAULT");
        CA_UPDATE_SUCCESS = new ProcessingStatus(15L, "CA_UPDATE_SUCCESS");
        CA_UPDATE_FAULT = new ProcessingStatus(-15L, "CA_UPDATE_FAULT");
        WAITING_SPARK_RESPONSE = new ProcessingStatus(20L, "WAITING_SPARK_RESPONSE");
        SPARK_VALIDATION_FAULT = new ProcessingStatus(-25L, "SPARK_VALIDATION_FAULT");
        DATA_SAVE_SUCCESS = new ProcessingStatus(40L, "DATA_SAVE_SUCCESS");
        DATA_SAVE_FAULT = new ProcessingStatus(-40L, "DATA_SAVE_FAULT");
    }

    ProcessingStatus() {
    }

    ProcessingStatus(Long id, String name) {
        this.id = id;
        this.name = name;
        values.add(this);
    }

    @Id
    @Column(name = "F_PROCESSING_STATUS_ID")
    public Long id;

    @Column(name = "F_NAME")
    public String name;
}