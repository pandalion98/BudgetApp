/*
 * Copyright (c) 2019 Kirig Technologies. All rights reserved.
 *
 * This document is considered proprietary and confidential. It may not be stored, reproduced,
 * or transmitted by any means without express written permission from Kirig Technologies.
 */

package ph.kirig.budgetapp.persistence.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import ph.kirig.budgetapp.models.Currency;
import ph.kirig.budgetapp.persistence.Repository;
import ph.kirig.budgetapp.persistence.SqlPreparedQuery;

import static ph.kirig.budgetapp.persistence.local.LocalSQLite.DbContract.CURR_COLUMN_ABBREV;
import static ph.kirig.budgetapp.persistence.local.LocalSQLite.DbContract.CURR_COLUMN_NAME;
import static ph.kirig.budgetapp.persistence.local.LocalSQLite.DbContract.CURR_COLUMN_NUMERIC_SCALE;
import static ph.kirig.budgetapp.persistence.local.LocalSQLite.DbContract.CURR_COLUMN_SYMBOL;
import static ph.kirig.budgetapp.persistence.local.LocalSQLite.DbContract.CURR_COLUMN_UUID;
import static ph.kirig.budgetapp.persistence.local.LocalSQLite.DbContract.TABLE_CURRENCY;

/**
 * Created by Gene on 09/04/2019.
 * Kirig Technologies
 * gene(at)kirig.ph
 */
public class CurrencyLocalRepo implements Repository<Currency> {
    private SQLiteDatabase db;

    public CurrencyLocalRepo(Context ctx) {
        db = LocalSQLite.getInstance(ctx.getApplicationContext()).getWritableDatabase();
    }

    @Override
    public void add(Currency item) {
        ContentValues insertValues = new ContentValues();
        insertValues.put(CURR_COLUMN_UUID, item.getUuid());
        insertValues.put(CURR_COLUMN_NUMERIC_SCALE, item.numeric_scale);
        insertValues.put(CURR_COLUMN_NAME, item.full_name);
        insertValues.put(CURR_COLUMN_ABBREV, item.abbreviation);
        insertValues.put(CURR_COLUMN_SYMBOL, item.symbol);
        db.insert(TABLE_CURRENCY, null, insertValues);
    }

    @Override
    public void update(Currency item) {
        ContentValues values = new ContentValues();
        values.put(CURR_COLUMN_NUMERIC_SCALE, item.numeric_scale);
        values.put(CURR_COLUMN_NAME, item.full_name);
        values.put(CURR_COLUMN_ABBREV, item.abbreviation);
        values.put(CURR_COLUMN_SYMBOL, item.symbol);

        db.update(TABLE_CURRENCY, values,
                CURR_COLUMN_UUID + " = ?", new String[]{item.getUuid()});
    }

    @Override
    public void remove(Currency item) {
        db.delete(TABLE_CURRENCY,
                CURR_COLUMN_UUID + " = ?", new String[]{item.getUuid()});
    }

    @Override
    public List<Currency> query(SqlPreparedQuery q) {
        Cursor cur = db.rawQuery(q.generateSqlPreparedStatement(), q.generateSelectionArgs());

        ArrayList<Currency> buffer = new ArrayList<>();

        while (cur.moveToNext()) {
            Currency e = new Currency(cur.getString(cur.getColumnIndex(CURR_COLUMN_UUID)));
            e.full_name = cur.getString(cur.getColumnIndex(CURR_COLUMN_NAME));
            e.abbreviation = cur.getString(cur.getColumnIndex(CURR_COLUMN_ABBREV));
            e.symbol = cur.getString(cur.getColumnIndex(CURR_COLUMN_SYMBOL));
            e.numeric_scale = cur.getInt(cur.getColumnIndex(CURR_COLUMN_NUMERIC_SCALE));

            buffer.add(e);
        }

        cur.close();
        return buffer;
    }
}
