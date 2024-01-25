package JpMorganApi.JpMorganBankApi.repository;

import JpMorganApi.JpMorganBankApi.model.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithdrawalRepository extends JpaRepository<Withdrawal, Long> {
}
