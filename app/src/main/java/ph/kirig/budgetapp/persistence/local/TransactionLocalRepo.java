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

import ph.kirig.budgetapp.models.TransactionRecord;
import ph.kirig.budgetapp.persistence.Repository;
import ph.kirig.budgetapp.persistence.SqlPreparedQuery;

import static ph.kirig.budgetapp.persistence.local.LocalSQLite.DbContract.REC_COLUMN_AMOUNT;
import static ph.kirig.budgetapp.persistence.local.LocalSQLite.DbContract.REC_COLUMN_CATEGORY;
import static ph.kirig.budgetapp.persistence.local.LocalSQLite.DbContract.REC_COLUMN_TIME;
import static ph.kirig.budgetapp.persistence.local.LocalSQLite.DbContract.REC_COLUMN_UUID;
import static ph.kirig.budgetapp.persistence.local.LocalSQLite.DbContract.TABLE_RECORDS;


/**
 * Created by Gene on 12/04/2019.
 * Kirig Technologies
 * gene(at)kirig.ph
 */
public class TransactionLocalRepo implements Repository<TransactionRecord> {
    private SQLiteDatabase db;

    public TransactionLocalRepo(Context ctx) {
        db = LocalSQLite.getInstance(ctx.getApplicationContext()).getWritableDatabase();
    }

    @Override
    public void add(TransactionRecord item) {
        ContentValues insertValues = new ContentValues();
        insertValues.put(REC_COLUMN_UUID, item.getUuid());
        insertValues.put(REC_COLUMN_AMOUNT, item.getTxAmount().toPlainString());
        insertValues.put(REC_COLUMN_CATEGORY, item.getCategory());
        insertValues.put(REC_COLUMN_TIME, item.getTimeMillis());
        db.insert(TABLE_RECORDS, null, insertValues);

    }

    @Override
    public void update(TransactionRecord item) {

    }

    @Override
    public void remove(TransactionRecord item) {

    }

    @Override
    public List<TransactionRecord> query(SqlPreparedQuery q) {
        return null;
    }
}
