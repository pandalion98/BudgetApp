package ph.kirig.budgetapp.models;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by Gene on 15/03/2019.
 * Kirig Technologies
 * gene(at)kirig.ph
 */

@Entity
public class TxRecord {
    @Id public long tx_id;

    public TxRecord() {

    }
}
