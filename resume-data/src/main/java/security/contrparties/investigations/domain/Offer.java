//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.04.17 at 02:39:49 PM MSK 
//


package security.contrparties.investigations.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;


/**
 * <p>Java class for Offer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Offer"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="offertaAmmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0" form="qualified"/&gt;
 *         &lt;element name="sap_srm_id" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0" form="qualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Offer", propOrder = {
    "offertaAmmount",
    "sapSrmId"
})
public class Offer {

    @XmlElement(namespace = "http://spi2.ru/jaxws/datatypes")
    protected BigDecimal offertaAmmount;
    @XmlElement(name = "sap_srm_id", namespace = "http://spi2.ru/jaxws/datatypes")
    protected BigDecimal sapSrmId;

    /**
     * Gets the value of the offertaAmmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOffertaAmmount() {
        return offertaAmmount;
    }

    /**
     * Sets the value of the offertaAmmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOffertaAmmount(BigDecimal value) {
        this.offertaAmmount = value;
    }

    /**
     * Gets the value of the sapSrmId property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSapSrmId() {
        return sapSrmId;
    }

    /**
     * Sets the value of the sapSrmId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSapSrmId(BigDecimal value) {
        this.sapSrmId = value;
    }

}
