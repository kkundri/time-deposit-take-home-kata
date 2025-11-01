package org.ikigaidigital.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class WithdrawalResponse implements Serializable {
    private Integer id;
    private BigDecimal amount;
    private LocalDate date;
}
