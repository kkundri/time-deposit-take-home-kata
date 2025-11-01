package org.ikigaidigital.controller;


import org.ikigaidigital.dto.ApiResponse;
import org.ikigaidigital.dto.TimeDepositResponse;
import org.ikigaidigital.service.TimeDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/time-deposits")
public class TimeDepositController {

    @Autowired
    private TimeDepositService timeDepositService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TimeDepositResponse>>> getAll() {
        List<TimeDepositResponse> data = timeDepositService.getAllTimeDeposits();
        return ResponseEntity.ok(ApiResponse.success(data,"Fetched time deposits"));
    }

    @PostMapping("/update-balances")
    public ResponseEntity<ApiResponse<String>> updateBalances() {
        timeDepositService.updateAllBalances();
        return ResponseEntity.ok(ApiResponse.success("Balances updated successfully"));
    }
}
