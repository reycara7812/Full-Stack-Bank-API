package JpMorganApi.JpMorganBankApi.response;



import JpMorganApi.JpMorganBankApi.dto.Body;
import JpMorganApi.JpMorganBankApi.model.Account;
import JpMorganApi.JpMorganBankApi.service.AccountService;
import JpMorganApi.JpMorganBankApi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountResponse {


    @Autowired
    private AccountService accountService;



    @Autowired
    private CustomerService customerService;


    public ResponseEntity<?> createAccount(Long costumerId, Account account) {
        accountService.createAccount(costumerId, account);
        Body body = new Body();
        body.setData(account);
        body.setCode(HttpStatus.CREATED.value());
        body.setMessage("Account created successfully");
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }





    public ResponseEntity<?> getAllAccounts() {
      try {
          Body body = new Body();
          body.setData(accountService.getAllAccounts());
          body.setCode(HttpStatus.OK.value());
          body.setMessage("Success");
          return new ResponseEntity<>(body, HttpStatus.OK);
      }catch(Exception exception){
          Body body = new Body();
          body.setCode(HttpStatus.NOT_FOUND.value());
          body.setMessage("Error Fetching Accounts");
          return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
      }
    }



    public ResponseEntity<?> getAccountById(Long accountId) {
        Optional<Account> account = accountService.getAccountById(accountId);

            Body body = new Body();
            body.setData(account);
            body.setCode(HttpStatus.OK.value());
            body.setMessage("Account retrieval Success");
            return new ResponseEntity<>(body, HttpStatus.OK);
        }




    public ResponseEntity<?> deleteAccount(Long accountId) {

        accountService.deleteAccount(accountId);
        Body body = new Body();

        body.setCode(HttpStatus.NO_CONTENT.value());
        body.setMessage("Account successfully deleted");
        return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
    }






    public ResponseEntity<?> updateAccount(Account updatedAccount, Long accountId) {
        Account account =accountService.updateAccount(updatedAccount, accountId);

        Body body = new Body();
        body.setData(account);
        body.setCode(HttpStatus.OK.value());
        body.setMessage("Customer Account Updated");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }


    public  ResponseEntity<?> getAllAccountsForCostumer(Long customer_id){
        Body body = new Body();
        body.setData(accountService.getAllAccountsForCostumer(customer_id));
        body.setCode(HttpStatus.OK.value());
        body.setMessage("Success");
        return new ResponseEntity<>(body, HttpStatus.OK);
    }





}
