/*
 * Copyright (c) 2019 Kirig Technologies. All rights reserved.
 *
 * This document is considered proprietary and confidential. It may not be stored, reproduced,
 * or transmitted by any means without express written permission from Kirig Technologies.
 */

package ph.kirig.budgetapp.models;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by Gene on 15/03/2019.
 * Kirig Technologies
 * gene(at)kirig.ph
 */


public class TransactionRecord {
    private String txUuid;
    private String ownerAccount;
    private String txDescription;

    private BigDecimal txAmount;
    private long timeMillis;

    public TransactionRecord() {
        txUuid = UUID.randomUUID().toString();
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
        return txUuid;
    }

    public TransactionRecord getValidatedTxRecord() throws Exception {
        if (txAmount.equals(new BigDecimal(0))) {
            throw new Exception();
        }

        return this;
    }
}

