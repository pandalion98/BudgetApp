package ph.kirig.budgetapp.database;

import android.content.Context;

import ph.kirig.budgetapp.models.Currency;

/**
 * Created by Gene on 04/04/2019.
 * Kirig Technologies
 * gene(at)kirig.ph
 */
public class BudgetDao {
    private BudgetDb db;

    public BudgetDao(Context ctx) {
        db = BudgetDb.getInstance(ctx);
    }

    public void addCurrency(Currency currency) {

    }
}
