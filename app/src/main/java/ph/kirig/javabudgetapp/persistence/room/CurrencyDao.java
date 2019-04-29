/*
 * Copyright (c) 2019 Kirig Technologies. All rights reserved.
 *
 * This document is considered proprietary and confidential. It may not be stored, reproduced,
 * or transmitted by any means without express written permission from Kirig Technologies.
 */

package ph.kirig.javabudgetapp.persistence.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import ph.kirig.javabudgetapp.models.Currency;

/**
 * Created by Gene on 12/04/2019.
 * Kirig Technologies
 * gene(at)kirig.ph
 */

@Dao
public interface CurrencyDao {
    @Insert
    void add(Currency currency);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Currency currency);

    @Delete
    void delete(Currency currency);

    @Query("SELECT * FROM Currency")
    List<Currency> getAll();

    @Query("SELECT * FROM Currency WHERE currency_uuid = (:uuid)")
    List<Currency> get(String uuid);

    @Query("SELECT * FROM Currency WHERE currency_uuid LIKE (:queryString)")
    List<Currency> search(String queryString);
}
