package org.ikigaidigital.controller;



import org.ikigaidigital.entity.TimeDepositEntity;
import org.ikigaidigital.service.TimeDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/time-deposits")
public class TimeDepositController {

    @Autowired
    private TimeDepositService timeDepositService;

    @GetMapping
    public List<TimeDepositEntity> getAll() {
        return timeDepositService.getAllTimeDeposits();
    }

    @PostMapping("/update-balances")
    public String updateBalances() {
        timeDepositService.updateAllBalances();
        return "Balances updated successfully";
    }
}
