//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.05.03 at 05:37:35 PM MSK 
//


package security.contrparties.investigations.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ContractConclusionRequestAsync complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ContractConclusionRequestAsync"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="header" type="{http://spi2.ru/jaxws/datatypes}Header"/&gt;
 *         &lt;element name="cisTask" type="{http://spi2.ru/jaxws/datatypes}CISTask"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContractConclusionRequestAsync", propOrder = {
    "header",
    "cisTask"
})
public class ContractConclusionRequestAsync {

    @XmlElement(required = true)
    protected Header header;
    @XmlElement(required = true)
    protected CISTask cisTask;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link Header }
     *     
     */
    public Header getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link Header }
     *     
     */
    public void setHeader(Header value) {
        this.header = value;
    }

    /**
     * Gets the value of the cisTask property.
     * 
     * @return
     *     possible object is
     *     {@link CISTask }
     *     
     */
    public CISTask getCisTask() {
        return cisTask;
    }

    /**
     * Sets the value of the cisTask property.
     * 
     * @param value
     *     allowed object is
     *     {@link CISTask }
     *     
     */
    public void setCisTask(CISTask value) {
        this.cisTask = value;
    }

}
