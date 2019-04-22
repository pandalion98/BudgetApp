/*
 * Copyright (c) 2019 Kirig Technologies. All rights reserved.
 *
 * This document is considered proprietary and confidential. It may not be stored, reproduced,
 * or transmitted by any means without express written permission from Kirig Technologies.
 */

package ph.kirig.budgetapp.models;

import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

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
    private final String uuid;

    @ColumnInfo(name = "full_name", typeAffinity = ColumnInfo.TEXT)
    private final String fullName;

    @ColumnInfo(name = "abbreviation", typeAffinity = ColumnInfo.TEXT)
    private final String abbreviation;

    @ColumnInfo(name = "symbol", typeAffinity = ColumnInfo.TEXT)
    private final String symbol;

    @ColumnInfo(name = "numeric_scale", typeAffinity = ColumnInfo.INTEGER)
    private final int numericScale;

    // Fresh, new currency
    public Currency(String fullName, String abbreviation, String symbol, int numericScale) {
        uuid = UUID.randomUUID().toString();
        this.fullName = fullName;
        this.abbreviation = abbreviation;
        this.symbol = symbol;
        this.numericScale = numericScale;
    }

    // Instantiating from persistence
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

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getNumericScale() {
        return numericScale;
    }

    public String getUuid() {
        return uuid;
    }
}
