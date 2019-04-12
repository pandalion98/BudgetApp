/*
 * Copyright (c) 2019 Kirig Technologies. All rights reserved.
 *
 * This document is considered proprietary and confidential. It may not be stored, reproduced,
 * or transmitted by any means without express written permission from Kirig Technologies.
 */

package ph.kirig.budgetapp.persistence.local;

import java.util.List;

import ph.kirig.budgetapp.models.TransactionRecord;
import ph.kirig.budgetapp.persistence.Repository;
import ph.kirig.budgetapp.persistence.SqlPreparedQuery;


/**
 * Created by Gene on 12/04/2019.
 * Kirig Technologies
 * gene(at)kirig.ph
 */
public class TransactionLocalRepo implements Repository<TransactionRecord> {
    @Override
    public void add(TransactionRecord item) {

    }

    @Override
    public void update(TransactionRecord item) {

    }

    @Override
    public void remove(TransactionRecord item) {

    }

    @Override
    public List<TransactionRecord> query(SqlPreparedQuery q) {
        return null;
    }
}
