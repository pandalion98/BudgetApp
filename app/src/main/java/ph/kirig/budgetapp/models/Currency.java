package ph.kirig.budgetapp.models;

import java.util.UUID;

/**
 * Created by Gene on 01/04/2019.
 * Kirig Technologies
 * gene(at)kirig.ph
 */

public class Currency {

    public String currencyName;
    public String currencyAbbrev;
    public String currencySymbol;
    public int numericScale;
    private String currencyUuid;

    public Currency() {
        currencyUuid = UUID.randomUUID().toString();
    }

    public String getCurrencyUuid() {
        return currencyUuid;
    }
}
