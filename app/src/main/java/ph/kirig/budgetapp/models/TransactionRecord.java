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

// TODO: Set up validation in the future, especially with API stuff.
public class TransactionRecord {
    private String uuid;
    private String ownerAccount;
    private String txDescription;
    private String txCategory;

    public TransactionRecord() {
        uuid = UUID.randomUUID().toString();
    }

    public String getTxCategory() {
        return txCategory;
    }

    private BigDecimal txAmount;
    private long timeMillis;

    public void setTxCategory(String txCategory) {
        this.txCategory = txCategory;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getTimeMillis() {
        return timeMillis;
    }

    public void setTimeMillis(long timeMillis) {
        this.timeMillis = timeMillis;
    }

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


    public BigDecimal getTxAmountNum() {
        return txAmount;
    }

    public void setTxAmountNum(BigDecimal txAmount) {
        this.txAmount = txAmount;
    }

    public String getTxAmountString() {
        return txAmount.toPlainString();
    }

    public void setTxAmountString(String txAmount) {
        this.txAmount = new BigDecimal(txAmount);
    }

    public TransactionRecord getValidatedTxRecord() throws Exception {
        if (txAmount.equals(new BigDecimal(0))) {
            throw new Exception();
        }

        return this;
    }
}

