/*
 * Copyright (c) 2019 Kirig Technologies. All rights reserved.
 *
 * This document is considered proprietary and confidential. It may not be stored, reproduced,
 * or transmitted by any means without express written permission from Kirig Technologies.
 */

package ph.kirig.budgetapp.models;

import java.util.UUID;

/**
 * Created by Gene on 31/03/2019.
 * Kirig Technologies
 * gene(at)kirig.ph
 */


public class Account {
    public String accountName;
    public String currencyUuid;
    public String accountMetadata;
    private String accountUuid;

    public Account() {
        accountUuid = UUID.randomUUID().toString();
    }

    public Account(String uuid) {
        accountUuid = uuid;
    }

    public String getUuid() {
        return accountUuid;
    }
}
