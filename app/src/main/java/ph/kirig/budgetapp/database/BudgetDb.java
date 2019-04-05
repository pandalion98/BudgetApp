package ph.kirig.budgetapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


/**
 * Created by Gene on 04/04/2019.
 * Kirig Technologies
 * gene(at)kirig.ph
 */
public class BudgetDb extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "BudgetDB.db";

    private static BudgetDb dbSingleton;

    private BudgetDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Singleton, so things flow smoothly instead of encountering db locks
    public static BudgetDb getInstance(Context ctx) {
        if (dbSingleton == null) {
            dbSingleton = new BudgetDb(ctx);
        }

        return dbSingleton;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create account table
        String create_account_table =
                "CREATE TABLE " + DbContract.TABLE_ACCT + " (" +
                        DbContract.ACCT_COLUMN_UUID + " TEXT PRIMARY KEY," +
                        DbContract.ACCT_COLUMN_NAME + " TEXT," +
                        DbContract.ACCT_COLUMN_CURRENCY_UUID + " TEXT," +
                        DbContract.ACCT_COLUMN_ACCOUNT_METADATA + " TEXT)";
        db.execSQL(create_account_table);

        // Create currency table
        String create_currency_table =
                "CREATE TABLE " + DbContract.TABLE_CURRENCY + " (" +
                        DbContract.CURR_COLUMN_UUID + " TEXT PRIMARY KEY," +
                        DbContract.CURR_COLUMN_NUMERIC_SCALE + " INTEGER," +
                        DbContract.CURR_COLUMN_NAME + " TEXT," +
                        DbContract.CURR_COLUMN_ABBREV + " TEXT," +
                        DbContract.CURR_COLUMN_SYMBOL + " TEXT)";
        db.execSQL(create_currency_table);

        // Create record table
        String create_record_table =
                "CREATE TABLE " + DbContract.TABLE_RECORDS + " (" +
                        DbContract.REC_COLUMN_UUID + " TEXT PRIMARY KEY," +
                        DbContract.REC_COLUMN_AMOUNT_SIGNED + " INTEGER," +
                        DbContract.ACCT_COLUMN_CURRENCY_UUID + " TEXT," +
                        DbContract.ACCT_COLUMN_ACCOUNT_METADATA + " TEXT)";
        db.execSQL(create_record_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Fires if current DB version < DATABASE_VERSION
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Direct opposite of onUpgrade
    }

    public static class DbContract implements BaseColumns {
        static final String TABLE_ACCT = "tbl_account";
        static final String ACCT_COLUMN_NAME = "account_visible_name";
        static final String ACCT_COLUMN_UUID = "account_uuid";
        static final String ACCT_COLUMN_CURRENCY_UUID = "currency_uuid";
        static final String ACCT_COLUMN_ACCOUNT_METADATA = "account_metadata";

        static final String TABLE_CURRENCY = "tbl_currency";
        static final String CURR_COLUMN_UUID = "currency_uuid";
        static final String CURR_COLUMN_NUMERIC_SCALE = "numeric_scale";
        static final String CURR_COLUMN_NAME = "currency_visible_name";
        static final String CURR_COLUMN_ABBREV = "currency_abbrev";
        static final String CURR_COLUMN_SYMBOL = "currency_symbol";

        static final String TABLE_RECORDS = "tbl_record";
        static final String REC_COLUMN_UUID = "record_uuid";
        static final String REC_COLUMN_AMOUNT_SIGNED = "title";
        static final String COLUMN_NAME_SUBTITLE = "subtitle";
    }

}
