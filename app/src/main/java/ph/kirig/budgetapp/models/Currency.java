package ph.kirig.budgetapp.models;

import java.util.UUID;

/**
 * Created by Gene on 01/04/2019.
 * Kirig Technologies
 * gene(at)kirig.ph
 */

public class Currency {

    public String full_name;
    public String abbreviation;
    public String symbol;
    public int numeric_scale;
    private String uuid;

    public Currency() {
        uuid = UUID.randomUUID().toString();
    }

    public Currency(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }
}
