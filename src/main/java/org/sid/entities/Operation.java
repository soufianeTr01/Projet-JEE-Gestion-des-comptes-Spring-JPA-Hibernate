package org.sid.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_OP",discriminatorType = DiscriminatorType.STRING
,length = 1)
public abstract class Operation implements Serializable {
    @Id @GeneratedValue
    private  Long numOperation;
    private Date dateOperation;
    private  double montant;
    // une operation appartient a un seul compte
    @ManyToOne()
    @JoinColumn(name = "CODE_CPTE")
    private Compte compte;

    public Operation() {
    }

    public Operation(  Date dateOperation, double montant,Compte compte) {
        this.dateOperation = dateOperation;
        this.montant = montant;
        this.compte=compte;
    }

    public Long getNumOperation() {
        return numOperation;
    }

    public void setNumOperation(Long numOperation) {
        this.numOperation = numOperation;
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
}
