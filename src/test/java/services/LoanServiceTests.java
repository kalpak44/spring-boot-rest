package services;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pavel.usanli.domain.Loan;
import pavel.usanli.domain.User;
import pavel.usanli.service.LoanService;

/**
 * Created by Pavel Usanli on 27.11.2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootApplication(scanBasePackages = "pavel.usanli")
public class LoanServiceTests {
    @Autowired
    private LoanService loanService;

    @Test
    public void testSimpleAddingLoan() {
        loanService.create(new Loan("term desc", 5.0, new User("Ivan", "Ivanov")));
        Assert.assertFalse(loanService.getAll().isEmpty());
    }

    @Test
    public void testFindingLoansByExistingUserId() {
        User user1 = new User("Ivan", "Ivanov");
        loanService.create(new Loan("term desc", 5.0, user1));
        Assert.assertEquals(0, Double.compare(loanService.getByUserId(1).getAmount(), 5.0));
    }
}
