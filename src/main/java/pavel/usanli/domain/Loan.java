package pavel.usanli.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Pavel Usanli on 26.11.2016.
 */

@Entity
public class Loan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long loanId;
    private String term;
    private Double amount;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "userId")
    private User user;


    public Loan() {
    }

    public Loan(final String term, final Double amount, final User user) {
        this.term = term;
        this.amount = amount;
        this.user = user;
    }

    public String getTerm() {
        return term;
    }

    public Loan setTerm(final String term) {
        this.term = term;
        return this;
    }

    public double getAmount() {
        return amount;
    }

    public Loan setAmount(final Double amount) {
        this.amount = amount;
        return this;
    }

    public long getLoanId() {
        return loanId;
    }

    public User getUser() {
        return user;
    }

    public Loan setUser(final User user) {
        this.user = user;
        return this;
    }
}
