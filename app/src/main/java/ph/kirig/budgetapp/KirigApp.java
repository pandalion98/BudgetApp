package ph.kirig.budgetapp;

import android.app.Application;

import ph.kirig.budgetapp.models.TransactionBox;


/**
 * Created by Gene on 15/03/2019.
 * Kirig Technologies
 * gene(at)kirig.ph
 */
public class KirigApp extends Application {
    public static final String TAG = "ObjectBoxExample";

    @Override
    public void onCreate() {
        super.onCreate();
        TransactionBox.init(this);
    }
}
