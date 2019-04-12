/*
 * Copyright (c) 2019 Kirig Technologies. All rights reserved.
 *
 * This document is considered proprietary and confidential. It may not be stored, reproduced,
 * or transmitted by any means without express written permission from Kirig Technologies.
 */

package ph.kirig.budgetapp.persistence;

/**
 * Created by Gene on 09/04/2019.
 * Kirig Technologies
 * gene(at)kirig.ph
 */
public interface SqlQuery {
//    /**
//     * Generate the full SQL query, with arguments and all.
//     *
//     * @return Escaped SQL query, with question mark (?) placeholders
//     */
//    String generateFull();

    /**
     * @return Escaped SQL query, with question mark (?) placeholders
     * @see android.database.sqlite.SQLiteDatabase#rawQuery(String, String[]) or similar methods.
     */
    String generateSqlPreparedStatement();

    /**
     * @return SQL query arguments, corresponding to {@link #generateSqlPreparedStatement()} placeholders.
     * @see android.database.sqlite.SQLiteDatabase#rawQuery(String, String[]) or similar methods.
     */
    String[] generateSelectionArgs();
}