/*
 * Copyright (c) 2019 Kirig Technologies. All rights reserved.
 *
 * This document is considered proprietary and confidential. It may not be stored, reproduced,
 * or transmitted by any means without express written permission from Kirig Technologies.
 */

package ph.kirig.budgetapp.persistence.local;

import java.util.ArrayList;

import ph.kirig.budgetapp.persistence.SqlPreparedQuery;

import static ph.kirig.budgetapp.persistence.local.LocalSQLite.DbContract.ACCT_COLUMN_CURRENCY_UUID;
import static ph.kirig.budgetapp.persistence.local.LocalSQLite.DbContract.ACCT_COLUMN_NAME;
import static ph.kirig.budgetapp.persistence.local.LocalSQLite.DbContract.ACCT_COLUMN_UUID;
import static ph.kirig.budgetapp.persistence.local.LocalSQLite.DbContract.TABLE_ACCT;


/**
 * Created by Gene on 12/04/2019.
 * Kirig Technologies
 * gene(at)kirig.ph
 */
public class AccountQuery implements SqlPreparedQuery {
    private String uuid, currencyuuid, name;

    public AccountQuery() {

    }

    public AccountQuery addUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public AccountQuery addCurrencyUuid(String uuid) {
        this.currencyuuid = uuid;
        return this;
    }

    public AccountQuery addName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String generateSqlPreparedStatement() {
        String query = "SELECT * FROM " + TABLE_ACCT + " ";
        boolean appendedWhere = false;

        if (uuid != null) {
            query += "WHERE ";
            appendedWhere = true;
            query += ACCT_COLUMN_UUID + " = ?";
        }

        if (currencyuuid != null) {
            query += appendedWhere ? "OR " : "WHERE ";
            if (!appendedWhere) {
                appendedWhere = true;
            }

            query += ACCT_COLUMN_CURRENCY_UUID + " = ?";
        }

        if (name != null) {
            query += appendedWhere ? "OR " : "WHERE ";
//            if (!appendedWhere) {
//                // TODO: Uncomment and follow pattern if more of these clauses exist
//                appendedWhere = true;
//            }

            query += ACCT_COLUMN_NAME + " LIKE ?";
        }

        return query.trim();
    }

    @Override
    public String[] generateSelectionArgs() {
        ArrayList<String> buffer = new ArrayList<>();
        if (uuid != null) {
            buffer.add(uuid);
        }

        if (currencyuuid != null) {
            buffer.add(currencyuuid);
        }

        if (name != null) {
            buffer.add(name);
        }

        // Why 0? https://shipilev.net/blog/2016/arrays-wisdom-ancients/
        return buffer.toArray(new String[0]);
    }
}
