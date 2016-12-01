package pavel.usanli.repository;

import org.springframework.data.repository.CrudRepository;
import pavel.usanli.domain.Loan;
import pavel.usanli.domain.User;

/**
 * Created by Pavel Usanli on 26.11.2016.
 */
public interface LoanRepository extends CrudRepository<Loan, Long> {
    Loan findByUser(final User user);
}