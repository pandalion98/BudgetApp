/*
 * Copyright (c) 2019 Kirig Technologies. All rights reserved.
 *
 * This document is considered proprietary and confidential. It may not be stored, reproduced,
 * or transmitted by any means without express written permission from Kirig Technologies.
 */

package ph.kirig.budgetapp.models;

import java.math.BigDecimal;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Gene on 15/03/2019.
 * Kirig Technologies
 * gene(at)kirig.ph
 */

// TODO: Set up validation in the future, especially with API stuff.

@Entity
public class TransactionRecord {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "record_uuid", typeAffinity = ColumnInfo.TEXT)
    private String uuid;

    // Amount in base units (e.g. PHP centavo, BTC Satoshi, etc.)
    @ColumnInfo(name = "time_epoch", typeAffinity = ColumnInfo.REAL)
    private long timeMillis;

    // Amount in base units (e.g. PHP centavo, BTC Satoshi, etc.)
    @ColumnInfo(name = "amount", typeAffinity = ColumnInfo.REAL)
    private BigDecimal txAmount;

    @ColumnInfo(name = "owner_account_uuid", typeAffinity = ColumnInfo.TEXT)
    private String ownerAccount;

    @ColumnInfo(name = "category", typeAffinity = ColumnInfo.TEXT)
    private String category;

    @ColumnInfo(name = "description", typeAffinity = ColumnInfo.TEXT)
    private String descriptionText;


    // ctors
    // Fresh new tx
    public TransactionRecord() {
        uuid = UUID.randomUUID().toString();
    }

    // Instantiated from persistence
    public TransactionRecord(@NonNull String uuid, long timeMillis, BigDecimal txAmount,
                             String ownerAccount, String category, String descriptionText) {
        this.uuid = uuid;
        this.timeMillis = timeMillis;
        this.txAmount = txAmount;
        this.ownerAccount = ownerAccount;
        this.category = category;
        this.descriptionText = descriptionText;
    }

    @NonNull
    public String getUuid() {
        return uuid;
    }

    public void setUuid(@NonNull String uuid) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String desc) {
        this.descriptionText = desc;
    }

    // toPlainString ONLY
    public BigDecimal getTxAmount() {
        return txAmount;
    }

    public void setTxAmount(BigDecimal txAmount) {
        this.txAmount = txAmount;
    }
}

