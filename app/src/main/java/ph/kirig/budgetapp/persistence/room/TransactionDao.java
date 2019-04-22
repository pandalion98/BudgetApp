/*
 * Copyright (c) 2019 Kirig Technologies. All rights reserved.
 *
 * This document is considered proprietary and confidential. It may not be stored, reproduced,
 * or transmitted by any means without express written permission from Kirig Technologies.
 */

package ph.kirig.budgetapp.persistence.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;
import ph.kirig.budgetapp.models.TransactionRecord;

/**
 * Created by Gene on 15/04/2019.
 * Kirig Technologies
 * gene(at)kirig.ph
 */

@Dao
public interface TransactionDao {
    @Insert
    void add(TransactionRecord account);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(TransactionRecord account);

    @Delete
    void delete(TransactionRecord account);
}
