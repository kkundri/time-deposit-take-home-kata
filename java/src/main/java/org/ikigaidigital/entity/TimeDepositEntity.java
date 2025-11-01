package org.ikigaidigital.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "time_deposits")
@Data
@ToString
public class TimeDepositEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;

    @Column(name ="plan_type",nullable = false)
    private String planType;

    @Column(name ="balance",nullable = false)
    private BigDecimal balance;

    @Column(name ="days",nullable = false)
    private Integer days;

    @OneToMany(mappedBy = "timeDeposit", cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<WithdrawalEntity> withdrawalEntities;

    @Version
    @Column(nullable = false)
    @ColumnDefault("0")
    private Long version;

}
