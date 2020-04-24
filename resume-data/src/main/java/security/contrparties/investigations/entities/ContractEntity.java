
package security.contrparties.investigations.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.CascadeType.PERSIST;

@Entity
@Table(name = "STG_Contract")
public class ContractEntity extends BaseEntity {

    @Size(max = 50)
    protected String contractId;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date docDate;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date cardDate;

    @Size(max = 300)
    protected String userConractId;

    @ManyToOne(cascade = PERSIST)
    @JoinColumn(name = "SIBUR_ORG_ID")
    protected DictionaryEntity siburOrg;

    @Size(max = 300)
    protected String regNum;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date regDate;

    @ManyToOne(cascade = PERSIST)
    @JoinColumn(name = "CNTGRAG_INF_ID")
    protected CounterpartyInfoEntity counterpartyInfoEntity;

    @Size(max = 500)
    protected String contractUrl;

    @Size(max = 100)
    protected String procedureNumber;


    @ManyToOne(cascade = PERSIST)
    @JoinColumn(name = "SUBJ_ID")
    protected DictionaryEntity subject;

    @ManyToOne(cascade = PERSIST)
    @JoinColumn(name = "SUB_CATEG_ID")
    protected DictionaryEntity subCategory;

    @ManyToOne(cascade = PERSIST)
    @JoinColumn(name = "KIND_ID")
    protected DictionaryEntity kind;

    @ManyToOne(cascade = PERSIST)
    @JoinColumn(name = "KIND_DD_ID")
    protected DictionaryEntity kindDd;

    protected BigDecimal amountVv;

    protected BigDecimal amountTransaction;

    protected BigDecimal amountTotal;

    protected boolean framework;

    @ManyToOne(cascade = PERSIST)
    @JoinColumn(name = "AVANCE_ID")
    protected DictionaryEntity advancePaymentType;

    protected BigDecimal advancePaymentAmount;

    protected boolean postponement;

    @ManyToOne(cascade = PERSIST)
    @JoinColumn(name = "KIND_VZ__ID")
    protected DictionaryEntity kindVz;

    protected boolean safeList;

    @Size(max = 40)
    protected String isDefault;

    @Size(max = 40)
    protected String urgent;

    /**
     * Статус
     */
    protected String approvalStage;

    @Size(max = 100)
    protected String version;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date receiveDate;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date completeonDate;

    @ManyToOne(cascade = PERSIST)
    @JoinColumn(name = "VISA_ID")
    protected DictionaryEntity visa;

    @Size(max = 2000)
    protected String note;

    @ManyToOne(cascade = PERSIST)
    @JoinColumn(name = "BE_ID")
    protected DictionaryEntity be;

    @OneToMany
    protected List<ContractPersonEntity> personList;

    /**
     * Gets the value of the contractId property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getContractId() {
        return contractId;
    }

    /**
     * Sets the value of the contractId property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setContractId(String value) {
        this.contractId = value;
    }

    /**
     * Gets the value of the docDate property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public Date getDocDate() {
        return docDate;
    }

    /**
     * Sets the value of the docDate property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setDocDate(Date value) {
        this.docDate = value;
    }

    /**
     * Gets the value of the cardDate property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public Date getCardDate() {
        return cardDate;
    }

    /**
     * Sets the value of the cardDate property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setCardDate(Date value) {
        this.cardDate = value;
    }

    /**
     * Gets the value of the userConractId property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getUserConractId() {
        return userConractId;
    }

    /**
     * Sets the value of the userConractId property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setUserConractId(String value) {
        this.userConractId = value;
    }

    /**
     * Gets the value of the siburOrg property.
     *
     * @return possible object is
     * {@link DictionaryEntity }
     */
    public DictionaryEntity getSiburOrg() {
        return siburOrg;
    }

    /**
     * Sets the value of the siburOrg property.
     *
     * @param value allowed object is
     *              {@link DictionaryEntity }
     */
    public void setSiburOrg(DictionaryEntity value) {
        this.siburOrg = value;
    }

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
     * Gets the value of the counterpartyInfoEntity property.
     *
     * @return possible object is
     * {@link CounterpartyInfoEntity }
     */
    public CounterpartyInfoEntity getCounterpartyInfoEntity() {
        return counterpartyInfoEntity;
    }

    /**
     * Sets the value of the counterpartyInfoEntity property.
     *
     * @param value allowed object is
     *              {@link CounterpartyInfoEntity }
     */
    public void setCounterpartyInfoEntity(CounterpartyInfoEntity value) {
        this.counterpartyInfoEntity = value;
    }

    /**
     * Gets the value of the contractUrl property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getContractUrl() {
        return contractUrl;
    }

    /**
     * Sets the value of the contractUrl property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setContractUrl(String value) {
        this.contractUrl = value;
    }

    /**
     * Gets the value of the procedureNumber property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getProcedureNumber() {
        return procedureNumber;
    }

    /**
     * Sets the value of the procedureNumber property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setProcedureNumber(String value) {
        this.procedureNumber = value;
    }

    /**
     * Gets the value of the subject property.
     *
     * @return possible object is
     * {@link String }
     */
    public DictionaryEntity getSubject() {
        return subject;
    }

    /**
     * Sets the value of the subject property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSubject(DictionaryEntity value) {
        this.subject = value;
    }

    /**
     * Gets the value of the subCategory property.
     *
     * @return possible object is
     * {@link DictionaryEntity }
     */
    public DictionaryEntity getSubCategory() {
        return subCategory;
    }

    /**
     * Sets the value of the subCategory property.
     *
     * @param value allowed object is
     *              {@link DictionaryEntity }
     */
    public void setSubCategory(DictionaryEntity value) {
        this.subCategory = value;
    }

    /**
     * Gets the value of the kind property.
     *
     * @return possible object is
     * {@link DictionaryEntity }
     */
    public DictionaryEntity getKind() {
        return kind;
    }

    /**
     * Sets the value of the kind property.
     *
     * @param value allowed object is
     *              {@link DictionaryEntity }
     */
    public void setKind(DictionaryEntity value) {
        this.kind = value;
    }

    /**
     * Gets the value of the kindDd property.
     *
     * @return possible object is
     * {@link DictionaryEntity }
     */
    public DictionaryEntity getKindDd() {
        return kindDd;
    }

    /**
     * Sets the value of the kindDd property.
     *
     * @param value allowed object is
     *              {@link DictionaryEntity }
     */
    public void setKindDd(DictionaryEntity value) {
        this.kindDd = value;
    }

    /**
     * Gets the value of the amountVv property.
     *
     * @return possible object is
     * {@link BigDecimal }
     */
    public BigDecimal getAmountVv() {
        return amountVv;
    }

    /**
     * Sets the value of the amountVv property.
     *
     * @param value allowed object is
     *              {@link BigDecimal }
     */
    public void setAmountVv(BigDecimal value) {
        this.amountVv = value;
    }

    /**
     * Gets the value of the amountTransaction property.
     *
     * @return possible object is
     * {@link BigDecimal }
     */
    public BigDecimal getAmountTransaction() {
        return amountTransaction;
    }

    /**
     * Sets the value of the amountTransaction property.
     *
     * @param value allowed object is
     *              {@link BigDecimal }
     */
    public void setAmountTransaction(BigDecimal value) {
        this.amountTransaction = value;
    }

    /**
     * Gets the value of the amountTotal property.
     *
     * @return possible object is
     * {@link BigDecimal }
     */
    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    /**
     * Sets the value of the amountTotal property.
     *
     * @param value allowed object is
     *              {@link BigDecimal }
     */
    public void setAmountTotal(BigDecimal value) {
        this.amountTotal = value;
    }

    /**
     * Gets the value of the framework property.
     */
    public boolean isFramework() {
        return framework;
    }

    /**
     * Sets the value of the framework property.
     */
    public void setFramework(boolean value) {
        this.framework = value;
    }

    /**
     * Gets the value of the advancePaymentType property.
     *
     * @return possible object is
     * {@link DictionaryEntity }
     */
    public DictionaryEntity getAdvancePaymentType() {
        return advancePaymentType;
    }

    /**
     * Sets the value of the advancePaymentType property.
     *
     * @param value allowed object is
     *              {@link DictionaryEntity }
     */
    public void setAdvancePaymentType(DictionaryEntity value) {
        this.advancePaymentType = value;
    }

    /**
     * Gets the value of the advancePaymentAmount property.
     *
     * @return possible object is
     * {@link BigDecimal }
     */
    public BigDecimal getAdvancePaymentAmount() {
        return advancePaymentAmount;
    }

    /**
     * Sets the value of the advancePaymentAmount property.
     *
     * @param value allowed object is
     *              {@link BigDecimal }
     */
    public void setAdvancePaymentAmount(BigDecimal value) {
        this.advancePaymentAmount = value;
    }

    /**
     * Gets the value of the postponement property.
     */
    public boolean isPostponement() {
        return postponement;
    }

    /**
     * Sets the value of the postponement property.
     */
    public void setPostponement(boolean value) {
        this.postponement = value;
    }

    /**
     * Gets the value of the kindVz property.
     *
     * @return possible object is
     * {@link DictionaryEntity }
     */
    public DictionaryEntity getKindVz() {
        return kindVz;
    }

    /**
     * Sets the value of the kindVz property.
     *
     * @param value allowed object is
     *              {@link DictionaryEntity }
     */
    public void setKindVz(DictionaryEntity value) {
        this.kindVz = value;
    }

    /**
     * Gets the value of the safeList property.
     */
    public boolean isSafeList() {
        return safeList;
    }

    /**
     * Sets the value of the safeList property.
     */
    public void setSafeList(boolean value) {
        this.safeList = value;
    }

    /**
     * Gets the value of the isDefault property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getIsDefault() {
        return isDefault;
    }

    /**
     * Sets the value of the isDefault property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIsDefault(String value) {
        this.isDefault = value;
    }

    /**
     * Gets the value of the urgent property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getUrgent() {
        return urgent;
    }

    /**
     * Sets the value of the urgent property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setUrgent(String value) {
        this.urgent = value;
    }

    /**
     * Gets the value of the approvalStage property.
     *
     * @return possible object is
     * {@link DictionaryEntity }
     */
    public String getApprovalStage() {
        return approvalStage;
    }

    /**
     * Sets the value of the approvalStage property.
     *
     * @param value allowed object is
     *              {@link DictionaryEntity }
     */
    public void setApprovalStage(String value) {
        this.approvalStage = value;
    }

    /**
     * Gets the value of the version property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Gets the value of the receiveDate property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public Date getReceiveDate() {
        return receiveDate;
    }

    /**
     * Sets the value of the receiveDate property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setReceiveDate(Date value) {
        this.receiveDate = value;
    }

    /**
     * Gets the value of the completeonDate property.
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public Date getCompleteonDate() {
        return completeonDate;
    }

    /**
     * Sets the value of the completeonDate property.
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setCompleteonDate(Date value) {
        this.completeonDate = value;
    }

    /**
     * Gets the value of the visa property.
     *
     * @return possible object is
     * {@link DictionaryEntity }
     */
    public DictionaryEntity getVisa() {
        return visa;
    }

    /**
     * Sets the value of the visa property.
     *
     * @param value allowed object is
     *              {@link DictionaryEntity }
     */
    public void setVisa(DictionaryEntity value) {
        this.visa = value;
    }

    /**
     * Gets the value of the note property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the value of the note property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setNote(String value) {
        this.note = value;
    }

    /**
     * Gets the value of the be property.
     *
     * @return possible object is
     * {@link DictionaryEntity }
     */
    public DictionaryEntity getBe() {
        return be;
    }

    /**
     * Sets the value of the be property.
     *
     * @param value allowed object is
     *              {@link DictionaryEntity }
     */
    public void setBe(DictionaryEntity value) {
        this.be = value;
    }

    /**
     * Gets the value of the personList property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the personList property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPersonList().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContractPersonEntity }
     */
    public List<ContractPersonEntity> getPersonList() {
        if (personList == null) {
            personList = new ArrayList<ContractPersonEntity>();
        }
        return this.personList;
    }

}
