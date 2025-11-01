package org.ikigaidigital.controller;


import org.ikigaidigital.dto.TimeDepositResponse;
import org.ikigaidigital.service.TimeDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/time-deposits")
public class TimeDepositController {

    @Autowired
    private TimeDepositService timeDepositService;

    @GetMapping
    public List<TimeDepositResponse> getAll() {
        return timeDepositService.getAllTimeDeposits();
    }

    @PostMapping("/update-balances")
    public String updateBalances() {
        timeDepositService.updateAllBalances();
        return "Balances updated successfully";
    }
}
