/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sdev425;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kalada Opuiyo
 */
@Entity
@Table(name = "TRANSACTION_LOG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransactionLog.findAll", query = "SELECT t FROM TransactionLog t"),
    @NamedQuery(name = "TransactionLog.findById", query = "SELECT t FROM TransactionLog t WHERE t.id = :id"),
    @NamedQuery(name = "TransactionLog.findByUsername", query = "SELECT t FROM TransactionLog t WHERE t.username = :username"),
    @NamedQuery(name = "TransactionLog.findByTransactionType", query = "SELECT t FROM TransactionLog t WHERE t.transactionType = :transactionType"),
    @NamedQuery(name = "TransactionLog.findByDateCreated", query = "SELECT t FROM TransactionLog t WHERE t.dateCreated = :dateCreated")})
public class TransactionLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id

    @Basic(optional = false)
    @SequenceGenerator(name = "logSeq", sequenceName = "TRANSACTIONS_LOG_ID_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "logSeq")
    @Column(name = "ID")
    private String id;
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @Column(name = "TRANSACTION_TYPE")
    private String transactionType;
    @Basic(optional = false)
    @Column(name = "DATE_CREATED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    public TransactionLog() {
    }

    public TransactionLog(String id) {
        this.id = id;
    }

    public TransactionLog(String id, String transactionType, Date dateCreated) {
        this.id = id;
        this.transactionType = transactionType;
        this.dateCreated = new Date(dateCreated.getTime());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Date getDateCreated() {
        return new Date(dateCreated.getTime());
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = new Date(dateCreated.getTime());
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransactionLog)) {
            return false;
        }
        TransactionLog other = (TransactionLog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sdev425_2.TransactionLog[ id=" + id + " ]";
    }

}
