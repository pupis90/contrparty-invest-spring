package security.contrparties.investigations.domain_etalon;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;

@XmlRootElement(name = "task", namespace = "http://spi2.ru/jaxws/datatypes")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CounterpartySapIdentities",  namespace = "http://spi2.ru/jaxws/datatypes",
		propOrder = {"sap_srm_id","regDate"})
public class CounterpartySapIdentities {

	/** Данные регистрации КА в SAP SRM */

	/** Для надежности идентификации КА - резидентов*/
	/** Уникальный идентификатор КА, присвоенный в SAP SRM */
	@XmlElement(required = true)
    public BigDecimal sap_srm_id;

    /** Дата и время регистрации КА в SAP SRM (по Москве),
	 *  т.е. когда в SAP SRM был присвоен уникальный идентификатор КА*/
	@XmlElement(required = true)
	@XmlSchemaType(name = "date")
	public javax.xml.datatype.XMLGregorianCalendar 	regDate;


}
