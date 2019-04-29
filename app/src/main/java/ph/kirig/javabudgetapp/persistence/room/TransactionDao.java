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

import ph.kirig.javabudgetapp.models.TransactionRecord;

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

    @Query("SELECT * FROM TransactionRecord")
    List<TransactionRecord> getAll();

    @Query("SELECT * FROM TransactionRecord WHERE time_epoch BETWEEN :from AND :to")
    List<TransactionRecord> getAllBetweenDates(long from, long to);

    @Query("SELECT * FROM TransactionRecord WHERE time_epoch BETWEEN :timeFrom AND :timeTo " +
            "AND owner_account_uuid = :accountUuid")
    List<TransactionRecord> getByAccount(long timeFrom, long timeTo, String accountUuid);

    @Query("SELECT * FROM TransactionRecord WHERE time_epoch BETWEEN :timeFrom AND :timeTo " +
            "AND description LIKE :queryString")
    List<TransactionRecord> searchByString(long timeFrom, long timeTo, String queryString);
}
