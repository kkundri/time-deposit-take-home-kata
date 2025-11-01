package org.ikigaidigital.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "timeDeposits")
@Data
public class TimeDepositEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;
    private String planType;
    private BigDecimal balance;
    private Integer days;

    @OneToMany(mappedBy = "timeDeposit", cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<WithdrawalEntity> withdrawalEntities;

    @Version
    private Long version;

}
