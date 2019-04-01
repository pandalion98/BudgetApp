package ph.kirig.budgetapp.models;

import java.util.UUID;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by Gene on 01/04/2019.
 * Kirig Technologies
 * gene(at)kirig.ph
 */
@Entity
public class Currency {
    @Id
    public long id;
    public String currencyAbbrev;
    public String currencySymbol;
    public int numericPrecision;
    public int numericScale;
    private String currencyUuid;

    public Currency() {
        currencyUuid = UUID.randomUUID().toString();
    }

    public String getCurrencyUuid() {
        return currencyUuid;
    }
}
