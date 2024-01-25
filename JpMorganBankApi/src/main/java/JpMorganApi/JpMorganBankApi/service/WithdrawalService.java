package JpMorganApi.JpMorganBankApi.service;



import JpMorganApi.JpMorganBankApi.model.Account;
import JpMorganApi.JpMorganBankApi.model.Withdrawal;
import JpMorganApi.JpMorganBankApi.repository.WithdrawalRepository;
import JpMorganApi.JpMorganBankApi.repository.AccountRepository;
import JpMorganApi.JpMorganBankApi.handler.exceptions.WithdrawalNotFoundException;
import JpMorganApi.JpMorganBankApi.handler.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WithdrawalService {
    @Autowired
    private WithdrawalRepository withdrawalRepository;
    @Autowired
    private AccountRepository accountRepository;

    private final Logger logger = LoggerFactory.getLogger(WithdrawalService.class);

    public Withdrawal createWithdrawal(Withdrawal withdrawal, Long accountId) throws Exception {
        var account = accountRepository.findById(accountId);
        if (account.isEmpty()) {
            throw new ResourceNotFoundException("Account not found");
        }
        Account existingAccount = account.get();
        if (existingAccount.getBalance() < withdrawal.getAmount()) {
            throw new Exception("Insufficient funds");
        }
        existingAccount.setBalance(existingAccount.getBalance() - withdrawal.getAmount());
        accountRepository.save(existingAccount);
        withdrawal.setAccount(existingAccount);
        return withdrawalRepository.save(withdrawal);
    }
    public Iterable<Withdrawal> getWithdrawals() throws Exception {
        logger.info("Successfully retrieved withdrawals");
        return withdrawalRepository.findAll();
    }

    public Optional<Withdrawal> getWithdrawalById(Long withdrawalId) throws Exception, WithdrawalNotFoundException {
        logger.info("Trying to retrieve withdrawal by Withdrawal ID");

        Optional<Withdrawal> optionalWithdrawal = withdrawalRepository.findById(withdrawalId);

        if (optionalWithdrawal.isEmpty()) {
            throw new WithdrawalNotFoundException("Error fetching withdrawal with id: " + withdrawalId);
        }

        return optionalWithdrawal;
    }

    public Withdrawal editWithdrawal(Long withdrawalId, Withdrawal withdrawal) throws Exception, WithdrawalNotFoundException {
        Optional<Withdrawal> existingWithdrawalOptional = withdrawalRepository.findById(withdrawalId);
        if (existingWithdrawalOptional.isEmpty()) {
            throw new WithdrawalNotFoundException("Withdrawal ID does not exist");
        }
        Withdrawal existingWithdrawal = existingWithdrawalOptional.get();
        existingWithdrawal.setId(withdrawal.getId());
        existingWithdrawal.setTransaction_date(withdrawal.getTransaction_date());
        existingWithdrawal.setAmount(withdrawal.getAmount());
        existingWithdrawal.setDescription(withdrawal.getDescription());
        existingWithdrawal.setAccount(withdrawal.getAccount());

        logger.info("Successfully updated Withdrawal");
        withdrawalRepository.save(existingWithdrawal);
        return existingWithdrawal;
    }

    public void removeWithdrawal(Long id) throws Exception, WithdrawalNotFoundException {

        if (!withdrawalRepository.existsById(id)) {
            throw new WithdrawalNotFoundException("This id does not exist in withdrawals");
        }
        logger.info("Withdrawal was Successfully deleted");
        withdrawalRepository.deleteById(id);
    }
}