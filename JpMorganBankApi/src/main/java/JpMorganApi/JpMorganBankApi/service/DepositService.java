package JpMorganApi.JpMorganBankApi.service;

import JpMorganApi.JpMorganBankApi.handler.exceptions.AccountNotFoundException;
import JpMorganApi.JpMorganBankApi.handler.exceptions.DepositNotFoundException;
import JpMorganApi.JpMorganBankApi.model.Account;
import JpMorganApi.JpMorganBankApi.model.Deposit;
import JpMorganApi.JpMorganBankApi.repository.AccountRepository;
import JpMorganApi.JpMorganBankApi.repository.DepositRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepositService {

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private AccountRepository accountRepository;

    private Logger logger = LoggerFactory.getLogger(DepositService.class);

    //create deposit
    public Deposit createDeposit (Long accountId, Deposit deposit){
        logger.info("creating deposit...");
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if(accountOptional.isEmpty()){
            logger.error("Error creating deposit: Account not found");
            throw new DepositNotFoundException("Error creating deposit: Account not found");
        }
        Account existingAccount = accountOptional.get();
        existingAccount.setBalance(existingAccount.getBalance() + deposit.getAmount());
        accountRepository.save(existingAccount);

        deposit.setAccount(existingAccount);

        logger.info("successfully created deposit");
        return depositRepository.save(deposit);
    }

    //get deposit by id
    public Optional<Deposit> getDepositById(Long deposit_Id){
       logger.info("successfully retrieved deposit by Id");
        return depositRepository.findById(deposit_Id);
    }
    //delete deposit
    public void deleteDeposit(Long deposit_Id){
        depositRepository.deleteById(deposit_Id);
    }
    //edit a deposit
    public Deposit editDeposit(Long deposit_Id, Deposit updatedDeposit)throws DepositNotFoundException{
        Optional<Deposit> existingDeposit = depositRepository.findById(deposit_Id);
        if(existingDeposit.isPresent()){
            Deposit depositToUpdate = existingDeposit.get();

            depositToUpdate.setAmount((updatedDeposit.getAmount()));
            depositToUpdate.setAccount(updatedDeposit.getAccount());

            logger.info("Deposit was Successfully Updated");
            return depositRepository.save(depositToUpdate);
        }else {
            logger.error("Unsuccessful Attempt to edit. Deposit not found");
            throw new DepositNotFoundException("deposit was not found");
        }
    }

    //get all deposits from an account
    public List<Deposit> getAllDepositsFromAccount(Long accountId){
        Optional<Account> account = accountRepository.findById(accountId);

        if(account.isPresent()){
            List<Deposit>deposits = depositRepository.findByAccount(account.get());
            logger.info("Successfully retrieved all deposits from account");
            return deposits;
        }else{
            logger.error("Unsuccessful attempt to get deposits. Account not found");
            throw new AccountNotFoundException("Account not found");
        }
    }



}
