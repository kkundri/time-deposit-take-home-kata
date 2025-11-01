package org.ikigaidigital.mapper;

import org.ikigaidigital.dto.TimeDepositResponse;
import org.ikigaidigital.dto.WithdrawalResponse;
import org.ikigaidigital.entity.TimeDepositEntity;
import org.ikigaidigital.entity.WithdrawalEntity;

import java.util.List;
import java.util.stream.Collectors;

public final class TimeDepositMapper {

    public static TimeDepositResponse toResponse(TimeDepositEntity deposit) {
        TimeDepositResponse response = new TimeDepositResponse();
        response.setId(deposit.getId());
        response.setPlanType(deposit.getPlanType());
        response.setDays(deposit.getDays());
        response.setBalance(deposit.getBalance());

        if (deposit.getWithdrawalEntities() != null) {
            List<WithdrawalResponse> withdrawalResponses =
                    deposit.getWithdrawalEntities().stream().map(TimeDepositMapper::toWithdrawalResponse)
                            .collect(Collectors.toList());
            response.setWithdrawals(withdrawalResponses);
        }

        return response;
    }

    private static WithdrawalResponse toWithdrawalResponse(WithdrawalEntity withdrawalEntity) {
        WithdrawalResponse wr = new WithdrawalResponse();
        wr.setId(withdrawalEntity.getId());
        wr.setAmount(withdrawalEntity.getAmount());
        wr.setDate(withdrawalEntity.getDate() != null ? withdrawalEntity.getDate() : null);
        return wr;
    }
}
