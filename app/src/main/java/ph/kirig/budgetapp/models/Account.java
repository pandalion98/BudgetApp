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

    public String getAccountUuid() {
        return accountUuid;
    }
}
