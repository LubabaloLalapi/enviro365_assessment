package com.enviro.assessment.grad001.lubabalolalapi.model;

import com.enviro.assessment.grad001.lubabalolalapi.controller.ResourceNotFoundException;
import com.enviro.assessment.grad001.lubabalolalapi.repository.investorRepository;
import jakarta.persistence.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.Optional;

@Entity
public class withdrawalNotice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal withdrawalAmount;

    @ManyToOne
    private product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(BigDecimal withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public product getProduct() {
        return product;
    }

    public void setProduct(product product) {
        this.product = product;
    }

    public ResponseEntity<investor> getInvestor(@PathVariable Long id) {
        investor investor = investor.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Investor not found with id: " + id));
            return ResponseEntity.ok(investor);

    }
}



