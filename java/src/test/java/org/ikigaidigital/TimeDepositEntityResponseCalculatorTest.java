package org.ikigaidigital;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TimeDepositEntityResponseCalculatorTest {
    @Test
    public void updateBalance_Test() {
        TimeDepositCalculator calc = new TimeDepositCalculator();
        List<TimeDeposit> plans = Arrays.asList(
            new TimeDeposit(1,"basic", 1234567.00, 45)
        );
        calc.updateBalance(plans);

        assertThat(1).isEqualTo(1);
    }
}
