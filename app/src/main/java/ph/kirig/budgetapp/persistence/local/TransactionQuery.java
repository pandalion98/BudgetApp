/*
 * Copyright (c) 2019 Kirig Technologies. All rights reserved.
 *
 * This document is considered proprietary and confidential. It may not be stored, reproduced,
 * or transmitted by any means without express written permission from Kirig Technologies.
 */

package ph.kirig.budgetapp.persistence.local;

import ph.kirig.budgetapp.persistence.SqlPreparedQuery;


/**
 * Created by Gene on 12/04/2019.
 * Kirig Technologies
 * gene(at)kirig.ph
 */
public class TransactionQuery implements SqlPreparedQuery {
    public TransactionQuery() {

    }

    @Override
    public String generateSqlPreparedStatement() {
        return null;
    }

    @Override
    public String[] generateSelectionArgs() {
        return new String[0];
    }
}
