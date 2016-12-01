package services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pavel.usanli.domain.Ban;
import pavel.usanli.domain.Loan;
import pavel.usanli.domain.User;
import pavel.usanli.repository.BlackListRepository;
import pavel.usanli.repository.UserRepository;
import pavel.usanli.service.BlackListService;
import pavel.usanli.service.LoanService;
import pavel.usanli.service.exceptions.BlackListException;

/**
 * Created by Pavel Usanli on 27.11.2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlackListServiceTest {
    @Autowired
    private BlackListService blackListService;

    @Autowired
    private BlackListRepository blackListRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoanService loanService;

    @Before
    public void setUp(){
        //create users in the repo
        userRepository.save(new User("Ivan", "Ivanov"));
        userRepository.save(new User("Vasil", "Petrov"));
        userRepository.save(new User("Dmitry", "Balbesov"));

        // Vasil is bad boy. Ban him
        blackListRepository.save(new Ban(userRepository.findOne(2L)));
    }

    @Test
    public void checkUserExistingInBlackList() {
        Assert.assertTrue(blackListService.hasInBlackList(2L));
    }

    @Test(expected = BlackListException.class)
    public void checkLoanWithUserExistingInBlackList() {
        loanService.create(new Loan("Term desc", 2.0, userRepository.findOne(2L)));
    }
}
