/*
 * Copyright (c) 2019 Kirig Technologies. All rights reserved.
 *
 * This document is considered proprietary and confidential. It may not be stored, reproduced,
 * or transmitted by any means without express written permission from Kirig Technologies.
 */

package ph.kirig.javabudgetapp.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.UUID;

/**
 * Created by Gene on 31/03/2019.
 * Kirig Technologies
 * gene(at)kirig.ph
 */

@Entity
public class Account {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "account_uuid", typeAffinity = ColumnInfo.TEXT)
    private String uuid;

    @ColumnInfo(name = "account_name", typeAffinity = ColumnInfo.TEXT)
    private String name;

    @ColumnInfo(name = "currency_uuid", typeAffinity = ColumnInfo.TEXT)
    private String currencyUuid;

    @ColumnInfo(name = "metadata", typeAffinity = ColumnInfo.TEXT)
    private String metadata;

    // ctors
    // Fresh new account
    public Account() {
        uuid = UUID.randomUUID().toString();
    }

    // Account from persistence
    @Ignore
    public Account(@NonNull String uuid, String name, String currencyUuid, String metadata) {
        this.uuid = uuid;
        this.name = name;
        this.currencyUuid = currencyUuid;
        this.metadata = metadata;
    }

    @NonNull
    public String getUuid() {
        return uuid;
    }

    public void setUuid(@NonNull String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrencyUuid() {
        return currencyUuid;
    }

    public void setCurrencyUuid(String currencyUuid) {
        this.currencyUuid = currencyUuid;
    }

    public String getMetadata() {
        return metadata;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }
}
