package JpMorganApi.JpMorganBankApi.repository;

import JpMorganApi.JpMorganBankApi.model.Account;
import JpMorganApi.JpMorganBankApi.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
    List<Deposit> findByAccount(Account account);

}
