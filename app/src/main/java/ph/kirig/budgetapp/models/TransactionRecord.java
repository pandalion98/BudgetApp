package ph.kirig.budgetapp.models;

import java.math.BigDecimal;
import java.util.UUID;

import io.objectbox.annotation.Convert;
import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import ph.kirig.budgetapp.BigDecimalConverter;

/**
 * Created by Gene on 15/03/2019.
 * Kirig Technologies
 * gene(at)kirig.ph
 */

@Entity
public class TransactionRecord {
    @Id
    public long id; // On-device use only

    public String txUUID;
    public String ownerAccount;
    public String txDescription;

    @Convert(dbType = String.class, converter = BigDecimalConverter.class)
    public BigDecimal txAmount;

    public TransactionRecord() {
        txUUID = UUID.randomUUID().toString();
    }


    // Getters and setters. TODO: Set up validation in the future, especially with API stuff.
    public String getOwnerAccount() {
        return ownerAccount;
    }

    public void setOwnerAccount(String ownerAccount) {
        this.ownerAccount = ownerAccount;
    }

    public String getTxDescription() {
        return txDescription;
    }

    public void setTxDescription(String txDescription) {
        this.txDescription = txDescription;
    }


    public BigDecimal getTxAmount() {
        return txAmount;
    }

    public void setTxAmount(BigDecimal txAmount) {
        this.txAmount = txAmount;
    }


    public String getUUID() {
        return txUUID;
    }

    public TransactionRecord getValidatedTxRecord() throws Exception {
        if (txAmount.equals(new BigDecimal(0))) {
            throw new Exception();
        }

        return this;
    }
}

