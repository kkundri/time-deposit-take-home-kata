package org.ikigaidigital.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class TimeDepositResponse implements Serializable {
    private Integer  id;
    private String planType;
    private BigDecimal balance;
    private Integer days;
    private List<WithdrawalResponse> withdrawals;
}
