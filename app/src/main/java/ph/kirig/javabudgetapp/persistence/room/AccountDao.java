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

import ph.kirig.javabudgetapp.models.Account;

/**
 * Created by Gene on 12/04/2019.
 * Kirig Technologies
 * gene(at)kirig.ph
 */

@Dao
public interface AccountDao {
    @Insert
    void add(Account account);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Account account);

    @Delete
    void delete(Account account);

    @Query("SELECT * FROM Account")
    List<Account> getAll();

    @Query("SELECT * FROM Account WHERE account_uuid = :uuid")
    List<Account> get(String uuid);

    @Query("SELECT * FROM Account WHERE account_name LIKE :queryString")
    List<Account> search(String queryString);
}
