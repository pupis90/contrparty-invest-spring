//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.04.17 at 02:39:49 PM MSK 
//


package security.contrparties.investigations.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "STG_Passport")
public class PassportEntity extends BaseEntity {


    protected String series;

    protected String number;

    /**
     * Gets the value of the series property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSeries() {
        return series;
    }

    /**
     * Sets the value of the series property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSeries(String value) {
        this.series = value;
    }

    /**
     * Gets the value of the number property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getNumber() {
        return number;
    }

    /**
     * Sets the value of the number property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setNumber(String value) {
        this.number = value;
    }

}
