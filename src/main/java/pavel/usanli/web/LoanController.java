package pavel.usanli.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pavel.usanli.domain.Loan;
import pavel.usanli.service.LimitService;
import pavel.usanli.service.LoanService;

import java.util.List;

/**
 * Created by Pavel Usanli on 26.11.2016.
 */

@RestController
class LoanController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private LimitService limitService;

    @GetMapping("/loans")
    public List<Loan> getAll() {
        return loanService.getAll();
    }

    @GetMapping("loan/user/{userId}")
    public Loan getByUserId(@PathVariable long userId) {
        return loanService.getByUserId(userId);
    }

    @PostMapping("/loan/create")
    public List<Loan> createLoan(@RequestBody Loan loan) {
        loanService.create(loan);
        return loanService.getAll();
    }

    @GetMapping("/")
    public String index(){
        return limitService.hasRequestLocked() ? "^^" : "locked";
    }

}