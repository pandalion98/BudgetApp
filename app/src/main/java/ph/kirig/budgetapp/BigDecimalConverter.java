package ph.kirig.budgetapp;

import java.math.BigDecimal;

import io.objectbox.converter.PropertyConverter;

/**
 * Created by Gene on 31/03/2019.
 * Kirig Technologies
 * gene(at)kirig.ph
 */

// Converts the BigDecimal into a string for storage into the DB.
public class BigDecimalConverter implements PropertyConverter<BigDecimal, String> {
    @Override
    public BigDecimal convertToEntityProperty(String databaseValue) {
        return new BigDecimal(databaseValue);
    }

    @Override
    public String convertToDatabaseValue(BigDecimal entityProperty) {
        return entityProperty.toString();
    }
}
