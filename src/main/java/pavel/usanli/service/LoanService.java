package pavel.usanli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pavel.usanli.domain.Loan;
import pavel.usanli.domain.User;
import pavel.usanli.repository.LoanRepository;
import pavel.usanli.repository.UserRepository;
import pavel.usanli.service.exceptions.BlackListException;
import pavel.usanli.service.interfaces.LoanServiceInterface;

import java.util.List;

/**
 * Created by Pavel Usanli on 26.11.2016.
 */

@Service
public class LoanService implements LoanServiceInterface {
    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlackListService blackListService;

    @Override
    public List<Loan> getAll() {
        return (List<Loan>) loanRepository.findAll();
    }

    @Override
    public Loan getByUserId(final long userId) {
        return loanRepository.findByUser(userRepository.findOne(userId));
    }

    @Override
    public Loan create(Loan loan) {
        final User user = loan.getUser();
        if(blackListService.hasInBlackList(user.getPersonalId())){
            throw new BlackListException("Can't create loan. User has in black list");
        }
        return loanRepository.save(loan);
    }
}