package JpMorganApi.JpMorganBankApi.repository;

import JpMorganApi.JpMorganBankApi.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Iterable<Account> findByCustomer_Id(Long customerId);
}
