package security.contrparties.investigations.stage.soap;

import javax.persistence.*;

@Entity
@Table(name = "SPI_WS_WEB_SERVICE")
public class WebServiceMethodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "F_WEB_SERVICE_ID")
    public Long id;

    @Basic
    @Column(name = "F_WEB_SERVICE_NAME", length = 4000)
    public String webServiceName;

    @Basic
    @Column(name = "F_WEB_METHOD_NAME", length = 4000)
    public String webMethodName;
}
