package ph.kirig.budgetapp.models;

import android.content.Context;
import android.util.Log;

import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;
import ph.kirig.budgetapp.BuildConfig;
import ph.kirig.budgetapp.KirigApp;

/**
 * Created by Gene on 15/03/2019.
 * Kirig Technologies
 * gene(at)kirig.ph
 */


public class TransactionBox {
    private static BoxStore boxStore;

    public static void init(Context context) {
        boxStore = MyObjectBox.builder()
                .androidContext(context.getApplicationContext())
                .build();

        if (BuildConfig.DEBUG) {
            new AndroidObjectBrowser(boxStore).start(context.getApplicationContext());
            Log.d(KirigApp.TAG, String.format("Using ObjectBox %s (%s)",
                    BoxStore.getVersion(), BoxStore.getVersionNative()));
        }
    }

    public static BoxStore get() {
        return boxStore;
    }

}
