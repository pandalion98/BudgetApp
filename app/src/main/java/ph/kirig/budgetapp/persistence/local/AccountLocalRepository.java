/*
 * Copyright (c) 2019 Kirig Technologies. All rights reserved.
 *
 * This document is considered proprietary and confidential. It may not be stored, reproduced,
 * or transmitted by any means without express written permission from Kirig Technologies.
 */

package ph.kirig.budgetapp.persistence.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import ph.kirig.budgetapp.models.Account;
import ph.kirig.budgetapp.persistence.Repository;
import ph.kirig.budgetapp.persistence.SqlPreparedQuery;

import static ph.kirig.budgetapp.persistence.local.LocalSQLite.DbContract.ACCT_COLUMN_ACCOUNT_METADATA;
import static ph.kirig.budgetapp.persistence.local.LocalSQLite.DbContract.ACCT_COLUMN_CURRENCY_UUID;
import static ph.kirig.budgetapp.persistence.local.LocalSQLite.DbContract.ACCT_COLUMN_NAME;
import static ph.kirig.budgetapp.persistence.local.LocalSQLite.DbContract.ACCT_COLUMN_UUID;
import static ph.kirig.budgetapp.persistence.local.LocalSQLite.DbContract.TABLE_ACCT;


/**
 * Created by Gene on 12/04/2019.
 * Kirig Technologies
 * gene(at)kirig.ph
 */
public class AccountLocalRepository implements Repository<Account> {
    private SQLiteDatabase db;

    public AccountLocalRepository(Context ctx) {
        db = LocalSQLite.getInstance(ctx.getApplicationContext()).getWritableDatabase();
    }

    @Override
    public void add(Account item) {
        ContentValues insertValues = new ContentValues();
        insertValues.put(ACCT_COLUMN_UUID, item.getUuid());
        insertValues.put(ACCT_COLUMN_CURRENCY_UUID, item.currencyUuid);
        insertValues.put(ACCT_COLUMN_NAME, item.accountName);
        insertValues.put(ACCT_COLUMN_ACCOUNT_METADATA, item.accountMetadata);
        db.insert(TABLE_ACCT, null, insertValues);
    }

    @Override
    public void update(Account item) {
        ContentValues values = new ContentValues();
        values.put(ACCT_COLUMN_UUID, item.getUuid());
        values.put(ACCT_COLUMN_CURRENCY_UUID, item.currencyUuid);
        values.put(ACCT_COLUMN_NAME, item.accountName);
        values.put(ACCT_COLUMN_ACCOUNT_METADATA, item.accountMetadata);

        db.update(TABLE_ACCT, values,
                ACCT_COLUMN_UUID + " = ?", new String[]{item.getUuid()});
    }

    @Override
    public void remove(Account item) {
        db.delete(TABLE_ACCT,
                ACCT_COLUMN_UUID + " = ?", new String[]{item.getUuid()});
    }

    @Override
    public List<Account> query(SqlPreparedQuery q) {
        return null;
    }
}
