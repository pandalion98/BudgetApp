/*
 * Copyright (c) 2019 Kirig Technologies. All rights reserved.
 *
 * This document is considered proprietary and confidential. It may not be stored, reproduced,
 * or transmitted by any means without express written permission from Kirig Technologies.
 */

package ph.kirig.budgetapp.persistence.local;

import ph.kirig.budgetapp.persistence.Query;

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


public class CurrencyQuery implements Query {
    private boolean fullStringSearch;
    private String uuid, name, abbrev, symbol, queryString;

    public CurrencyQuery() {
        this.fullStringSearch = false;
    }

    public CurrencyQuery(boolean fullStringSearch) {
        this.fullStringSearch = fullStringSearch;
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

    public CurrencyQuery addQueryString(String queryString) {
        this.queryString = queryString;
        return this;
    }

    @Override
    public String generate() {
        String query = "SELECT * FROM " + TABLE_CURRENCY + " ";

        boolean appendedWhere = false;

        if (uuid != null) {
            query += "WHERE ";
            appendedWhere = true;
            query += CURR_COLUMN_UUID + " = " + "\'" + uuid + "\'";
        }

        if (name != null) {
            if (!appendedWhere) {
                query += "WHERE ";
                appendedWhere = true;
            } else {
                query += "OR ";
            }

            query += CURR_COLUMN_NAME + " LIKE " + "\'" + name + "%\'";
        }

        if (abbrev != null) {
            if (!appendedWhere) {
                query += "WHERE ";
                appendedWhere = true;
            } else {
                query += "OR ";
            }

            query += CURR_COLUMN_ABBREV + " LIKE " + "\'" + abbrev + "%\'";
        }

        if (symbol != null) {
            if (!appendedWhere) {
                query += "WHERE ";
                // TODO: Uncomment and follow pattern if more of these clauses exist
                // appendedWhere = true;
            } else {
                query += "OR ";
            }

            query += CURR_COLUMN_SYMBOL + " LIKE " + "\'" + symbol + "%\'";
        }

        return query;
    }
}

