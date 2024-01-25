package JpMorganApi.JpMorganBankApi.repository;

import JpMorganApi.JpMorganBankApi.model.Bill;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    @Query(value = "SELECT b FROM Bill b WHERE b.account_id = :accountId",nativeQuery = true)
    List<Bill> findBillsByAccountId(@Param("accountId") Long accountId);


}
