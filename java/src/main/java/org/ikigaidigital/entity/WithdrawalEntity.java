package org.ikigaidigital.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "withdrawals")
@Data
public class WithdrawalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal amount;
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "timeDepositId", nullable = false)
    private TimeDepositEntity timeDepositEntity;

}