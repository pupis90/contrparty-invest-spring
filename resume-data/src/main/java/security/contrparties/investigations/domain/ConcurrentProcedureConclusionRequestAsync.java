//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.04.20 at 12:12:53 AM MSK 
//


package security.contrparties.investigations.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConcurrentProcedureConclusionRequestAsync complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConcurrentProcedureConclusionRequestAsync"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="header" type="{http://spi2.ru/jaxws/datatypes}Header" minOccurs="0"/&gt;
 *         &lt;element name="concurrentProcedure" type="{http://spi2.ru/jaxws/datatypes}ConcurrentProcedure" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConcurrentProcedureConclusionRequestAsync", propOrder = {
    "header",
    "concurrentProcedure"
})
public class ConcurrentProcedureConclusionRequestAsync {

    protected Header header;
    protected ConcurrentProcedure concurrentProcedure;

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
     * Gets the value of the concurrentProcedure property.
     * 
     * @return
     *     possible object is
     *     {@link ConcurrentProcedure }
     *     
     */
    public ConcurrentProcedure getConcurrentProcedure() {
        return concurrentProcedure;
    }

    /**
     * Sets the value of the concurrentProcedure property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConcurrentProcedure }
     *     
     */
    public void setConcurrentProcedure(ConcurrentProcedure value) {
        this.concurrentProcedure = value;
    }

}
