//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.04.17 at 02:39:49 PM MSK 
//


package security.contrparties.investigations.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CompanyForeign")
public class CompanyForeignEntity extends BaseEntity {

    protected String regNum;

    protected Date regDate;

    protected String issuingAuthority;

    protected String fullName;

    protected String shortName;

    protected String nativeName;

    protected String nativeShortName;

    protected String country;

    protected String phoneNumber;

    protected String email;

    protected String site;

    protected String asvNsiCode;

    @OneToMany
    protected List<AddressEntity> addresses;

    /**
     * Gets the value of the regNum property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getRegNum() {
        return regNum;
    }

    /**
     * Sets the value of the regNum property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRegNum(String value) {
        this.regNum = value;
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

    /**
     * Gets the value of the issuingAuthority property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIssuingAuthority() {
        return issuingAuthority;
    }

    /**
     * Sets the value of the issuingAuthority property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIssuingAuthority(String value) {
        this.issuingAuthority = value;
    }

    /**
     * Gets the value of the fullName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the value of the fullName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFullName(String value) {
        this.fullName = value;
    }

    /**
     * Gets the value of the shortName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Sets the value of the shortName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setShortName(String value) {
        this.shortName = value;
    }

    /**
     * Gets the value of the nativeName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getNativeName() {
        return nativeName;
    }

    /**
     * Sets the value of the nativeName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setNativeName(String value) {
        this.nativeName = value;
    }

    /**
     * Gets the value of the nativeShortName property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getNativeShortName() {
        return nativeShortName;
    }

    /**
     * Sets the value of the nativeShortName property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setNativeShortName(String value) {
        this.nativeShortName = value;
    }

    /**
     * Gets the value of the country property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the phoneNumber property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the value of the phoneNumber property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }

    /**
     * Gets the value of the email property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the site property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSite() {
        return site;
    }

    /**
     * Sets the value of the site property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSite(String value) {
        this.site = value;
    }

    /**
     * Gets the value of the asvNsiCode property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getAsvNsiCode() {
        return asvNsiCode;
    }

    /**
     * Sets the value of the asvNsiCode property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAsvNsiCode(String value) {
        this.asvNsiCode = value;
    }

    /**
     * Gets the value of the addresses property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the addresses property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddresses().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AddressEntity }
     */
    public List<AddressEntity> getAddresses() {
        if (addresses == null) {
            addresses = new ArrayList<AddressEntity>();
        }
        return this.addresses;
    }

}