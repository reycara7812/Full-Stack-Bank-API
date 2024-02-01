package JpMorganApi.JpMorganBankApi.controller;

import JpMorganApi.JpMorganBankApi.model.Deposit;
import JpMorganApi.JpMorganBankApi.response.DepositResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts/{accountId}/deposits")
@CrossOrigin("http://localhost:4200/")
public class DepositController {

    @Autowired
    private DepositResponse depositResponse;

    @PostMapping
    public ResponseEntity<?> createDeposit(@PathVariable Long accountId, @RequestBody Deposit deposit) {
        return depositResponse.createDeposit(accountId, deposit);
    }

    @GetMapping
    public ResponseEntity<?> getAllDeposits() {
        return depositResponse.getAllDeposits();
    }

    @GetMapping("/{depositId}")
    public ResponseEntity<?> getDeposit(@PathVariable Long accountId, @PathVariable Long depositId) {
        return depositResponse.getDeposit(depositId);
    }

    @PutMapping("/{depositId}")
    public ResponseEntity<?> editDeposit(@PathVariable Long accountId, @PathVariable Long depositId, @RequestBody Deposit deposit) {
        return depositResponse.editDeposit(depositId, deposit);
    }

    @DeleteMapping("/{depositId}")
    public ResponseEntity<?> deleteDeposit(@PathVariable Long accountId, @PathVariable Long depositId) {
        return depositResponse.deleteDeposit(depositId);
    }



}
