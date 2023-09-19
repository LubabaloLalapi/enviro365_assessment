package com.enviro.assessment.grad001.lubabalolalapi.controller;

import com.enviro.assessment.grad001.lubabalolalapi.model.product;
import com.enviro.assessment.grad001.lubabalolalapi.model.withdrawalNotice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/withdrawals")
public class withdrawalNoticeController {
    @PostMapping
    public ResponseEntity<String> createWithdrawalNotice(@RequestBody withdrawalNotice withdrawalNotice) {
        // Validate withdrawal based on product type and balance
        product product = withdrawalNotice.getProduct();
        if ("RETIREMENT".equals(product.getType())) {
            if (withdrawalNotice.getWithdrawalAmount().compareTo(BigDecimal.ZERO) > 0
                    && withdrawalNotice.getInvestor().getAge() <= 65) {
                return ResponseEntity.badRequest().body("Investor must be older than 65 for RETIREMENT withdrawal.");
            }
        }

        BigDecimal maxWithdrawal = product.getCurrentBalance().multiply(BigDecimal.valueOf(0.9));
        if (withdrawalNotice.getWithdrawalAmount().compareTo(maxWithdrawal) > 0) {
            return ResponseEntity.badRequest().body("Withdrawal amount exceeds the limit.");
        }

        // Save withdrawal notice if validation passes
        // withdrawalNoticeRepository.save(withdrawalNotice);

        return ResponseEntity.ok("Withdrawal notice created successfully.");
    }
}

