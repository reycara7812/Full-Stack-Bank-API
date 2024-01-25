package JpMorganApi.JpMorganBankApi.controller;

import JpMorganApi.JpMorganBankApi.handler.exceptions.WithdrawalNotFoundException;
import JpMorganApi.JpMorganBankApi.model.Withdrawal;
import JpMorganApi.JpMorganBankApi.response.WithdrawalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts/{accountId}/withdrawals")
public class WithdrawalController {

    @Autowired
    private WithdrawalResponse withdrawalResponse;

    @PostMapping
    public ResponseEntity<?> createWithdrawal(@PathVariable Long accountId, @RequestBody Withdrawal withdrawal) throws Exception {
        return withdrawalResponse.createWithdrawal(accountId, withdrawal);
    }

    @GetMapping
    public ResponseEntity<?> getAllWithdrawals(@PathVariable Long accountId) {
        return withdrawalResponse.getAllWithdrawals();
    }

    @GetMapping("/{withdrawalId}")
    public ResponseEntity<?> getWithdrawal(@PathVariable Long accountId, @PathVariable Long withdrawalId) throws Exception, WithdrawalNotFoundException {
        return withdrawalResponse.getWithdrawalById(withdrawalId);
    }

    @PutMapping("/{withdrawalId}")
    public ResponseEntity<?> editWithdrawal(@PathVariable Long accountId, @PathVariable Long withdrawalId, @RequestBody Withdrawal withdrawal) throws Exception, WithdrawalNotFoundException {
        return withdrawalResponse.updateWithdrawal(withdrawal, withdrawalId);
    }

    @DeleteMapping("/{withdrawalId}")
    public ResponseEntity<?> deleteWithdrawal(@PathVariable Long accountId, @PathVariable Long withdrawalId) throws Exception, WithdrawalNotFoundException {
        return withdrawalResponse.deleteWithdrawal(withdrawalId);
    }
}