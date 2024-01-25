package JpMorganApi.JpMorganBankApi.model;

import javax.validation.constraints.NotEmpty;
import jakarta.persistence.*;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Set;


@Entity
public class Withdrawal {
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotEmpty
    @Column(name = "withdrawalId")
    private Long id;

    @Column(name = "transactionDate")
    @NotEmpty
    private String transaction_date;



    @Column(name = "amount")
    @NotEmpty
    private Double amount;
    @Column(name = "description")
    @NotEmpty
    private String description;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(String transaction_date) {
        this.transaction_date = transaction_date;
    }





    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }






}
