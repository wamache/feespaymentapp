package com.schoolapp.controller;

import com.schoolapp.entity.Account;
import com.schoolapp.entity.User;
import com.schoolapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping()
public class DashboardController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/dashboard")
    public String getDashboard(HttpSession session) {
        ModelAndView getDashboardPage = new ModelAndView ( "dashboard" );

        User user = (User) session.getAttribute ("user");

        List<Account> getUserAccounts = accountRepository.getUserAccountsById(user.getUser_id ());

        BigDecimal totalAccountsBalance = accountRepository.getTotalBalance(user.getUser_id());

        getDashboardPage.addObject ("userAccounts", getUserAccounts);
        getDashboardPage.addObject ("totalBalance", totalAccountsBalance);


        return "getDashboardPage";
    }

}
