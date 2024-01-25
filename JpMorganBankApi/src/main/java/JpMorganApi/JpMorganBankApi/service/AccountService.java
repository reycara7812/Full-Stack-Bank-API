package JpMorganApi.JpMorganBankApi.service;


import JpMorganApi.JpMorganBankApi.handler.exceptions.AccountNotFoundException;
import JpMorganApi.JpMorganBankApi.handler.exceptions.CustomerNotFoundException;
import JpMorganApi.JpMorganBankApi.model.Account;
import JpMorganApi.JpMorganBankApi.model.Customer;
import JpMorganApi.JpMorganBankApi.repository.AccountRepository;
import JpMorganApi.JpMorganBankApi.repository.CustomerRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;




    private final org.slf4j.Logger logger = LoggerFactory.getLogger(AccountService.class);

    public void verifyCostumer(Long CostumerId)  {
        Optional<Customer> costumer = customerService.getCustomerById(CostumerId);
        if (costumer.isEmpty()) {
            logger.error("Customer Not Verified");
            throw new CustomerNotFoundException("Costumer with id " + CostumerId + " not found");
        }

    }
    public Optional<Account> getAccountById (Long accountId) {
        if (accountId == null) {
            logger.error("Account Not Found");
            throw new AccountNotFoundException("Error fetching account");
        }

        logger.info("Retrieved account by Id successfully ");
        return accountRepository.findById(accountId);
    }



    public Iterable<Account> getAllAccounts() throws AccountNotFoundException{
        Iterable<Account> allAccounts = accountRepository.findAll();
        if (!allAccounts.iterator().hasNext()) {
            logger.error("Account Not Found");
            throw new AccountNotFoundException("Error fetching accounts");

        }
        logger.info("All Accounts retrieved");
        return accountRepository.findAll();
    }





    public Account createAccount(Long customerId, Account account)  {
        verifyCostumer(customerId);

        logger.info("Successfully created Account");
        Customer getCustomer = customerRepository.findById(customerId).get();
        account.setCustomer(getCustomer);

        //saving account
        Account savedAccount = accountRepository.save(account);

        logger.info("account and activity were successfully Created");
        return savedAccount;
    }


    public Account updateAccount(Account updatedAccount, Long accountId) throws AccountNotFoundException{
        // Save the entity
        Optional<Account> accountOptional = accountRepository.findById(accountId);
if (accountOptional.isPresent()) {
    Account editThisAccount = accountOptional.get();
    editThisAccount.setBalance(updatedAccount.getBalance());
    editThisAccount.setType(updatedAccount.getType());
    editThisAccount.setNickname(updatedAccount.getNickname());
    editThisAccount.setRewardPoints(updatedAccount.getRewardPoints());



    logger.info("Account was Successfully Updated");
    return accountRepository.save(editThisAccount);
}else {
        logger.error("Unsuccessful Attempt to edit. Account not found");
        throw new AccountNotFoundException("Error updating Account with id " + accountId );
    }
}
    public Iterable<Account> getAllAccountsForCostumer(Long customer_id) throws CustomerNotFoundException {
        verifyCostumer(customer_id);
        Iterable<Account> accounts = accountRepository.findByCustomer_Id(customer_id);
        if (!accounts.iterator().hasNext()) {
            logger.error("No accounts found for customer");
            throw new CustomerNotFoundException("No accounts found for customer with id " + customer_id);
        }
        logger.info("Retrieved all accounts for customer successfully");
        return accounts;
    }

    public void deleteAccount(Long accountId) throws AccountNotFoundException {
        Optional<Account> accountOptional = accountRepository.findById(accountId);
        if (accountOptional.isEmpty()) {
            logger.error("Account not found");
            throw new AccountNotFoundException("Account with id " + accountId + " not found");
        }
        accountRepository.delete(accountOptional.get());
        logger.info("Account deleted successfully");
    }

    }

