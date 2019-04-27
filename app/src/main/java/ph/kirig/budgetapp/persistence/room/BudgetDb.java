/*
 * Copyright (c) 2019 Kirig Technologies. All rights reserved.
 *
 * This document is considered proprietary and confidential. It may not be stored, reproduced,
 * or transmitted by any means without express written permission from Kirig Technologies.
 */

package ph.kirig.budgetapp.persistence.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ph.kirig.budgetapp.models.Account;
import ph.kirig.budgetapp.models.Currency;
import ph.kirig.budgetapp.models.TransactionRecord;

/**
 * Created by Gene on 22/04/2019.
 * Kirig Technologies
 * gene(at)kirig.ph
 */
@Database(entities = {Account.class, Currency.class, TransactionRecord.class}, version = 2)
public abstract class BudgetDb extends RoomDatabase {

    private static BudgetDb dbInstance;

    public static BudgetDb getInstance(Context ctx) {
        if (dbInstance == null) {
            dbInstance = Room.databaseBuilder(ctx.getApplicationContext(),
                    BudgetDb.class, "BudgetDatabase").build();
        }

        return dbInstance;
    }

    public abstract AccountDao accountDao();

    public abstract CurrencyDao currencyDao();

    public abstract TransactionDao transactionDao();
}