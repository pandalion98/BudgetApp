/*
 * Copyright (c) 2019 Kirig Technologies. All rights reserved.
 *
 * This document is considered proprietary and confidential. It may not be stored, reproduced,
 * or transmitted by any means without express written permission from Kirig Technologies.
 */

package ph.kirig.budgetapp.persistence;

import java.util.List;

/**
 * Created by Gene on 09/04/2019.
 * Kirig Technologies
 * gene(at)kirig.ph
 */
public interface Repository<T> {
    void add(T item);

    void update(T item);

    void remove(T item);

    List<T> query(SqlPreparedQuery q);
}
