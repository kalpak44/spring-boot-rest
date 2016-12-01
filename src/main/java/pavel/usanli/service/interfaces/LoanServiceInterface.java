package pavel.usanli.service.interfaces;

import pavel.usanli.domain.Loan;

import java.util.List;

/**
 * Created by Pavel Usanli on 26.11.2016.
 */
public interface LoanServiceInterface {

    List getAll();

    Loan getByUserId(final long userId);

    Loan create(final Loan loan);
}