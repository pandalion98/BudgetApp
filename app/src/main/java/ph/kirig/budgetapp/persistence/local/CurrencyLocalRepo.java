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
        insertValues.put(CURR_COLUMN_NUMERIC_SCALE, item.getNumericScale());
        insertValues.put(CURR_COLUMN_NAME, item.getFullName());
        insertValues.put(CURR_COLUMN_ABBREV, item.getAbbreviation());
        insertValues.put(CURR_COLUMN_SYMBOL, item.getSymbol());
        db.insert(TABLE_CURRENCY, null, insertValues);
    }

    @Override
    public void update(Currency item) {
        ContentValues values = new ContentValues();
        values.put(CURR_COLUMN_NUMERIC_SCALE, item.getNumericScale());
        values.put(CURR_COLUMN_NAME, item.getFullName());
        values.put(CURR_COLUMN_ABBREV, item.getAbbreviation());
        values.put(CURR_COLUMN_SYMBOL, item.getSymbol());

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
            String uuid = cur.getString(cur.getColumnIndex(CURR_COLUMN_UUID));
            String fullName = cur.getString(cur.getColumnIndex(CURR_COLUMN_NAME));
            String abbrev = cur.getString(cur.getColumnIndex(CURR_COLUMN_ABBREV));
            String symb = cur.getString(cur.getColumnIndex(CURR_COLUMN_SYMBOL));
            int scale = cur.getInt(cur.getColumnIndex(CURR_COLUMN_NUMERIC_SCALE));

            Currency e = new Currency(uuid, fullName, abbrev, symb, scale);
            buffer.add(e);
        }

        cur.close();
        return buffer;
    }
}
