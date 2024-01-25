package JpMorganApi.JpMorganBankApi.repository;

import JpMorganApi.JpMorganBankApi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
