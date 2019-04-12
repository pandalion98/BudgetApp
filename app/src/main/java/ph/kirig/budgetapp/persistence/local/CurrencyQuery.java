/*
 * Copyright (c) 2019 Kirig Technologies. All rights reserved.
 *
 * This document is considered proprietary and confidential. It may not be stored, reproduced,
 * or transmitted by any means without express written permission from Kirig Technologies.
 */

package ph.kirig.budgetapp.persistence.local;

import java.util.ArrayList;

import ph.kirig.budgetapp.persistence.SqlQuery;

import static ph.kirig.budgetapp.persistence.local.LocalSQLite.DbContract.CURR_COLUMN_ABBREV;
import static ph.kirig.budgetapp.persistence.local.LocalSQLite.DbContract.CURR_COLUMN_NAME;
import static ph.kirig.budgetapp.persistence.local.LocalSQLite.DbContract.CURR_COLUMN_SYMBOL;
import static ph.kirig.budgetapp.persistence.local.LocalSQLite.DbContract.CURR_COLUMN_UUID;
import static ph.kirig.budgetapp.persistence.local.LocalSQLite.DbContract.TABLE_CURRENCY;


/**
 * Created by Gene on 09/04/2019.
 * Kirig Technologies
 * gene(at)kirig.ph
 */


public class CurrencyQuery implements SqlQuery {
    private String uuid, name, abbrev, symbol;

    public CurrencyQuery() {
    }


    public CurrencyQuery addUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public CurrencyQuery addName(String name) {
        this.name = name;
        return this;
    }

    public CurrencyQuery addAbbreviation(String abbrev) {
        this.abbrev = abbrev;
        return this;
    }

    public CurrencyQuery addSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    @Override
    public String generateSqlPreparedStatement() {
        String query = "SELECT * FROM " + TABLE_CURRENCY + " ";
        boolean appendedWhere = false;

        if (uuid != null) {
            query += "WHERE ";
            appendedWhere = true;
            query += CURR_COLUMN_UUID + " = ?";
        }

        if (name != null) {
            query += appendedWhere ? "OR " : "WHERE ";
            if (!appendedWhere) {
                appendedWhere = true;
            }

            query += CURR_COLUMN_NAME + " LIKE ?";
        }

        if (abbrev != null) {
            query += appendedWhere ? "OR " : "WHERE ";
            if (!appendedWhere) {
                appendedWhere = true;
            }

            query += CURR_COLUMN_ABBREV + " LIKE ?";
        }

        if (symbol != null) {
            query += appendedWhere ? "OR " : "WHERE ";
//            if (!appendedWhere) {
//                // TODO: Uncomment and follow pattern if more of these clauses exist
//                appendedWhere = true;
//            }

            query += CURR_COLUMN_SYMBOL + " LIKE ?";
        }

        return query.trim();
    }

    @Override
    public String[] generateSelectionArgs() {
        ArrayList<String> buffer = new ArrayList<>();
        if (uuid != null) {
            buffer.add(uuid);
        }

        if (name != null) {
            buffer.add(name);
        }

        if (abbrev != null) {
            buffer.add(abbrev);
        }

        if (symbol != null) {
            buffer.add(symbol);
        }

        // Why 0? https://shipilev.net/blog/2016/arrays-wisdom-ancients/
        return buffer.toArray(new String[0]);
    }
}

