//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.04.17 at 02:39:49 PM MSK 
//


package security.contrparties.investigations.entities;

import javax.persistence.Entity;

@Entity
public class EmployeeEntity extends BaseEntity {

    protected String personnelNumber;

    /**
     * Gets the value of the personnelNumber property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getPersonnelNumber() {
        return personnelNumber;
    }

    /**
     * Sets the value of the personnelNumber property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPersonnelNumber(String value) {
        this.personnelNumber = value;
    }

}
