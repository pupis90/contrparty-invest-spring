//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.04.27 at 06:59:13 AM MSK 
//


package security.contrparties.investigations.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PutCounterpartyInfoResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PutCounterpartyInfoResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ResponseSync" type="{http://spi2.ru/jaxws/datatypes}SyncResponse" minOccurs="0" form="qualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PutCounterpartyInfoResponse", propOrder = {
    "responseSync"
})
public class PutCounterpartyInfoResponse {

    @XmlElement(name = "ResponseSync", namespace = "http://spi2.ru/jaxws/datatypes")
    protected SyncResponse responseSync;

    /**
     * Gets the value of the responseSync property.
     * 
     * @return
     *     possible object is
     *     {@link SyncResponse }
     *     
     */
    public SyncResponse getResponseSync() {
        return responseSync;
    }

    /**
     * Sets the value of the responseSync property.
     * 
     * @param value
     *     allowed object is
     *     {@link SyncResponse }
     *     
     */
    public void setResponseSync(SyncResponse value) {
        this.responseSync = value;
    }

}
