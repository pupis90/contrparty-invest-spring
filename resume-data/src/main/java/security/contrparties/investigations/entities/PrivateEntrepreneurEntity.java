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
@Table(name = "STG_PrivateEntrepreneur")
public class PrivateEntrepreneurEntity extends BaseEntity {

    protected String inn;

    protected String okpo;

    protected String ogrnip;

    protected String fio;

    protected String asvNsiCode;

    protected String sparkid;

    /**
     * Gets the value of the inn property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getInn() {
        return inn;
    }

    /**
     * Sets the value of the inn property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setInn(String value) {
        this.inn = value;
    }

    /**
     * Gets the value of the ogrnip property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getOgrnip() {
        return ogrnip;
    }

    /**
     * Sets the value of the ogrnip property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setOgrnip(String value) {
        this.ogrnip = value;
    }

    /**
     * Gets the value of the fio property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getFio() {
        return fio;
    }

    /**
     * Sets the value of the fio property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setFio(String value) {
        this.fio = value;
    }

    /**
     * Gets the value of the okpo property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getOkpo() {
        return okpo;
    }

    /**
     * Sets the value of the okpo property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setOkpo(String value) {
        this.okpo = value;
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
     * Gets the value of the sparkid property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getSparkid() {
        return sparkid;
    }

    /**
     * Sets the value of the sparkid property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSparkid(String value) {
        this.sparkid = value;
    }

}
