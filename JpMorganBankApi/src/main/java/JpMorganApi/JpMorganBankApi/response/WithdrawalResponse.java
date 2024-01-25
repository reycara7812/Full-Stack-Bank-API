package JpMorganApi.JpMorganBankApi.response;

import JpMorganApi.JpMorganBankApi.dto.Body;
import JpMorganApi.JpMorganBankApi.handler.exceptions.WithdrawalNotFoundException;
import JpMorganApi.JpMorganBankApi.model.Withdrawal;
import JpMorganApi.JpMorganBankApi.service.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WithdrawalResponse {

    @Autowired
    private WithdrawalService withdrawalService;

    public ResponseEntity<?> createWithdrawal(Long accountId, Withdrawal withdrawal) throws Exception {
        withdrawalService.createWithdrawal(withdrawal, accountId);
        Body body = new Body();
        body.setData(withdrawal);
        body.setCode(HttpStatus.CREATED.value());
        body.setMessage("Withdrawal created successfully");
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    public ResponseEntity<?> getAllWithdrawals() {
        try {
            Body body = new Body();
            body.setData(withdrawalService.getWithdrawals());
            body.setCode(HttpStatus.OK.value());
            body.setMessage("Success");
            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch(Exception exception) {
            Body body = new Body();
            body.setCode(HttpStatus.NOT_FOUND.value());
            body.setMessage("Error Fetching Withdrawals");
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> getWithdrawalById(Long withdrawalId) throws Exception, WithdrawalNotFoundException {
        Optional<Withdrawal> withdrawal = withdrawalService.getWithdrawalById(withdrawalId);
        Body body = new Body();
        body.setData(withdrawal);
        body.setCode(HttpStatus.OK.value());
        body.setMessage("Withdrawal retrieval Success");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteWithdrawal(Long withdrawalId) throws Exception, WithdrawalNotFoundException {
        withdrawalService.removeWithdrawal(withdrawalId);
        Body body = new Body();
        body.setCode(HttpStatus.NO_CONTENT.value());
        body.setMessage("Withdrawal successfully deleted");
        return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> updateWithdrawal(Withdrawal updatedWithdrawal, Long withdrawalId) throws Exception, WithdrawalNotFoundException {
        Withdrawal withdrawal = withdrawalService.editWithdrawal(withdrawalId, updatedWithdrawal);
        Body body = new Body();
        body.setData(withdrawal);
        body.setCode(HttpStatus.OK.value());
        body.setMessage("Withdrawal Updated");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}