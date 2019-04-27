/*
 * Copyright (c) 2019 Kirig Technologies. All rights reserved.
 *
 * This document is considered proprietary and confidential. It may not be stored, reproduced,
 * or transmitted by any means without express written permission from Kirig Technologies.
 */

package ph.kirig.budgetapp.home_ui.transactionlist;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import java.util.List;

import ph.kirig.budgetapp.models.TransactionRecord;
import ph.kirig.budgetapp.persistence.room.BudgetDb;
import ph.kirig.budgetapp.persistence.room.TransactionDao;


public class TransactionListViewModel extends ViewModel {
    private Context ctx;
    private TransactionDao txlr;
    private List<TransactionRecord> recordList;

    public TransactionListViewModel(Context contextArg) {
        ctx = contextArg;

        BudgetDb db = BudgetDb.getInstance(contextArg);
        txlr = db.transactionDao();
    }
}
