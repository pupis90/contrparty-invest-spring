//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.04.17 at 02:39:49 PM MSK 
//


package security.contrparties.investigations.entities;


import javax.persistence.Entity;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class CounterpartySapIdentitiesEntity extends BaseEntity {


    protected BigDecimal sapSrmId;

    protected Date regDate;

    /**
     * Gets the value of the sapSrmId property.
     *
     * @return possible object is
     * {@link BigDecimal }
     */
    public BigDecimal getSapSrmId() {
        return sapSrmId;
    }

    /**
     * Sets the value of the sapSrmId property.
     *
     * @param value allowed object is
     *              {@link BigDecimal }
     */
    public void setSapSrmId(BigDecimal value) {
        this.sapSrmId = value;
    }

    /**
     * Gets the value of the regDate property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public Date getRegDate() {
        return regDate;
    }

    /**
     * Sets the value of the regDate property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setRegDate(Date value) {
        this.regDate = value;
    }

}
