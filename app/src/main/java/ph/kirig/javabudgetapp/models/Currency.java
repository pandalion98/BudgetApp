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
 * Created by Gene on 01/04/2019.
 * Kirig Technologies
 * gene(at)kirig.ph
 */

@Entity
public class Currency {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "currency_uuid", typeAffinity = ColumnInfo.TEXT)
    private String uuid;

    @ColumnInfo(name = "full_name", typeAffinity = ColumnInfo.TEXT)
    private String fullName;

    @ColumnInfo(name = "abbreviation", typeAffinity = ColumnInfo.TEXT)
    private String abbreviation;

    @ColumnInfo(name = "symbol", typeAffinity = ColumnInfo.TEXT)
    private String symbol;

    @ColumnInfo(name = "numeric_scale", typeAffinity = ColumnInfo.INTEGER)
    private int numericScale;


    // Default ctor
    public Currency() {
    }

    // Fresh, new currency
    @Ignore
    public Currency(String fullName, String abbreviation, String symbol, int numericScale) {
        uuid = UUID.randomUUID().toString();
        this.fullName = fullName;
        this.abbreviation = abbreviation;
        this.symbol = symbol;
        this.numericScale = numericScale;
    }

    // Instantiating from persistence
    @Ignore
    public Currency(@NonNull String uuid,
                    String fullName, String abbreviation, String symbol, int numericScale) {
        this.uuid = uuid;
        this.fullName = fullName;
        this.abbreviation = abbreviation;
        this.symbol = symbol;
        this.numericScale = numericScale;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getNumericScale() {
        return numericScale;
    }

    public void setNumericScale(int numericScale) {
        this.numericScale = numericScale;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(@NonNull String uuid) {
        this.uuid = uuid;
    }
}
