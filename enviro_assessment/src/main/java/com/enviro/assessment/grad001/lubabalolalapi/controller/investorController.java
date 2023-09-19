package com.enviro.assessment.grad001.lubabalolalapi.controller;

import com.enviro.assessment.grad001.lubabalolalapi.model.investor;
import com.enviro.assessment.grad001.lubabalolalapi.repository.investorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/investors")
public class investorController {
    private final investorRepository investorRepository;

    @Autowired
    public investorController(investorRepository investorRepository) {
        this.investorRepository = investorRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<investor> getInvestorById(@PathVariable Long id) {
        investor investor = investorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Investor not found with id: " + id));
        return ResponseEntity.ok(investor);
    }

    @GetMapping
    public ResponseEntity<List<investor>> getAllInvestors() {
        List<investor> investors = investorRepository.findAll();
        return ResponseEntity.ok(investors);
    }
    @GetMapping("/{id}")
    public ResponseEntity<investor> getInvestor(@PathVariable Long id) {
        Optional<investor> optionalInvestor = investorRepository.findById(id);

        if (optionalInvestor.isPresent()) {
            investor investor = optionalInvestor.get();
            return ResponseEntity.ok(investor);
        } else {
            throw new ResourceNotFoundException("Investor not found with id: " + id);
        }
    }
}


