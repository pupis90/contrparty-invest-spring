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
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for Counterparty complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Counterparty"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="isNew" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="needAtributeUpdate" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="isResident" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="isEntrepreneur" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="companyRus" type="{http://spi2.ru/jaxws/datatypes}CompanyRus" minOccurs="0"/&gt;
 *         &lt;element name="flPrivateEntrepreneur" type="{http://spi2.ru/jaxws/datatypes}PrivateEntrepreneur" minOccurs="0"/&gt;
 *         &lt;element name="companyForeign" type="{http://spi2.ru/jaxws/datatypes}CompanyForeign" minOccurs="0"/&gt;
 *         &lt;element name="bankDetails" type="{http://spi2.ru/jaxws/datatypes}BankDetail" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="contacts" type="{http://spi2.ru/jaxws/datatypes}Contact" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="confirmLocation" type="{http://spi2.ru/jaxws/datatypes}Attachment" minOccurs="0"/&gt;
 *         &lt;element name="confirmAbility" type="{http://spi2.ru/jaxws/datatypes}Attachment" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Counterparty", propOrder = {
        "isNew",
        "needAtributeUpdate",
        "isResident",
        "isEntrepreneur",
    "companyRus",
    "flPrivateEntrepreneur",
    "companyForeign",
    "bankDetails",
    "contacts",
    "confirmLocation",
    "confirmAbility"
})
public class Counterparty {

    protected boolean isNew;
    protected boolean needAtributeUpdate;
    protected boolean isResident;
    protected boolean isEntrepreneur;
    protected CompanyRus companyRus;
    protected PrivateEntrepreneur flPrivateEntrepreneur;
    protected CompanyForeign companyForeign;
    protected List<BankDetail> bankDetails;
    @XmlElement(nillable = true)
    protected List<Contact> contacts;
    protected Attachment confirmLocation;
    protected Attachment confirmAbility;

    /**
     * Gets the value of the isNew property.
     * 
     */
    public boolean isIsNew() {
        return isNew;
    }

    /**
     * Sets the value of the isNew property.
     * 
     */
    public void setIsNew(boolean value) {
        this.isNew = value;
    }

    /**
     * Gets the value of the needAtributeUpdate property.
     * 
     */
    public boolean isNeedAtributeUpdate() {
        return needAtributeUpdate;
    }

    /**
     * Sets the value of the needAtributeUpdate property.
     * 
     */
    public void setNeedAtributeUpdate(boolean value) {
        this.needAtributeUpdate = value;
    }

    /**
     * Gets the value of the isResident property.
     * 
     */
    public boolean isIsResident() {
        return isResident;
    }

    /**
     * Sets the value of the isResident property.
     * 
     */
    public void setIsResident(boolean value) {
        this.isResident = value;
    }

    /**
     * Gets the value of the isEntrepreneur property.
     * 
     */
    public boolean isIsEntrepreneur() {
        return isEntrepreneur;
    }

    /**
     * Sets the value of the isEntrepreneur property.
     * 
     */
    public void setIsEntrepreneur(boolean value) {
        this.isEntrepreneur = value;
    }

    /**
     * Gets the value of the companyRus property.
     * 
     * @return
     *     possible object is
     *     {@link CompanyRus }
     *     
     */
    public CompanyRus getCompanyRus() {
        return companyRus;
    }

    /**
     * Sets the value of the companyRus property.
     * 
     * @param value
     *     allowed object is
     *     {@link CompanyRus }
     *     
     */
    public void setCompanyRus(CompanyRus value) {
        this.companyRus = value;
    }

    /**
     * Gets the value of the flPrivateEntrepreneur property.
     * 
     * @return
     *     possible object is
     *     {@link PrivateEntrepreneur }
     *     
     */
    public PrivateEntrepreneur getFlPrivateEntrepreneur() {
        return flPrivateEntrepreneur;
    }

    /**
     * Sets the value of the flPrivateEntrepreneur property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrivateEntrepreneur }
     *     
     */
    public void setFlPrivateEntrepreneur(PrivateEntrepreneur value) {
        this.flPrivateEntrepreneur = value;
    }

    /**
     * Gets the value of the companyForeign property.
     * 
     * @return
     *     possible object is
     *     {@link CompanyForeign }
     *     
     */
    public CompanyForeign getCompanyForeign() {
        return companyForeign;
    }

    /**
     * Sets the value of the companyForeign property.
     * 
     * @param value
     *     allowed object is
     *     {@link CompanyForeign }
     *     
     */
    public void setCompanyForeign(CompanyForeign value) {
        this.companyForeign = value;
    }

    /**
     * Gets the value of the bankDetails property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bankDetails property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBankDetails().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BankDetail }
     * 
     * 
     */
    public List<BankDetail> getBankDetails() {
        if (bankDetails == null) {
            bankDetails = new ArrayList<BankDetail>();
        }
        return this.bankDetails;
    }

    /**
     * Gets the value of the contacts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contacts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContacts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Contact }
     * 
     * 
     */
    public List<Contact> getContacts() {
        if (contacts == null) {
            contacts = new ArrayList<Contact>();
        }
        return this.contacts;
    }

    /**
     * Gets the value of the confirmLocation property.
     * 
     * @return
     *     possible object is
     *     {@link Attachment }
     *     
     */
    public Attachment getConfirmLocation() {
        return confirmLocation;
    }

    /**
     * Sets the value of the confirmLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Attachment }
     *     
     */
    public void setConfirmLocation(Attachment value) {
        this.confirmLocation = value;
    }

    /**
     * Gets the value of the confirmAbility property.
     * 
     * @return
     *     possible object is
     *     {@link Attachment }
     *     
     */
    public Attachment getConfirmAbility() {
        return confirmAbility;
    }

    /**
     * Sets the value of the confirmAbility property.
     * 
     * @param value
     *     allowed object is
     *     {@link Attachment }
     *     
     */
    public void setConfirmAbility(Attachment value) {
        this.confirmAbility = value;
    }

}
