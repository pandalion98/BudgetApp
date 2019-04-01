package ph.kirig.budgetapp.models;

import java.util.UUID;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by Gene on 31/03/2019.
 * Kirig Technologies
 * gene(at)kirig.ph
 */

@Entity
public class Account {
    @Id
    public long id; // On-device use only
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
