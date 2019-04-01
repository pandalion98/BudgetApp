package ph.kirig.budgetapp;

import android.content.Context;
import android.util.Log;

import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;
import ph.kirig.budgetapp.models.MyObjectBox;

/**
 * Created by Gene on 31/03/2019.
 * Kirig Technologies
 * gene(at)kirig.ph
 */
public class ObjectBoxHelper {
    private static BoxStore boxStore;

    public static void init(Context context) {
        boxStore = MyObjectBox.builder()
                .androidContext(context)
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
