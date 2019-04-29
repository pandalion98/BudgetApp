/*
 * Copyright (c) 2019 Kirig Technologies. All rights reserved.
 *
 * This document is considered proprietary and confidential. It may not be stored, reproduced,
 * or transmitted by any means without express written permission from Kirig Technologies.
 */

package ph.kirig.javabudgetapp.persistence.room.typeconverters;

import androidx.room.TypeConverter;

import java.math.BigDecimal;

/**
 * Created by Gene on 2019-04-27.
 * Kirig Technologies
 * gene(at)kirig.ph
 */
public class BigDecimalConverter {
    @TypeConverter
    public static BigDecimal toBigDecimal(String value) {
        return new BigDecimal(value);
    }

    @TypeConverter
    public static String toPlainString(BigDecimal value) {
        return value.toPlainString();
    }
}
