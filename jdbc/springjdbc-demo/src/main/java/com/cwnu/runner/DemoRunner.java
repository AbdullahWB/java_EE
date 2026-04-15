package com.cwnu.runner;

import com.cwnu.dao.AccountDao;
import com.cwnu.pojo.Account;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DemoRunner implements CommandLineRunner {

    private final AccountDao accountDao;

    public DemoRunner(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void run(String... args) {
        System.out.println("===== ALL ACCOUNTS BEFORE =====");
        System.out.println(accountDao.queryAccountList());

        System.out.println("===== ADD ACCOUNT =====");
        accountDao.addAccount(new Account("Ali", 1000.0));
        System.out.println(accountDao.queryAccountList());

        System.out.println("===== BATCH ADD =====");
        accountDao.addAccountList(Arrays.asList(
                new Account("Sara", 1200.0),
                new Account("Omar", 900.0)));
        System.out.println(accountDao.queryAccountList());

        System.out.println("===== QUERY BY ID =====");
        System.out.println(accountDao.queryAccountById(1));

        System.out.println("===== QUERY FOR MAP =====");
        System.out.println(accountDao.queryAccountForMap(1));

        System.out.println("===== EDIT ACCOUNT =====");
        Account acc = accountDao.queryAccountById(1);
        acc.setUsername("Ali Updated");
        acc.setBalance(2000.0);
        accountDao.editAccount(acc);
        System.out.println(accountDao.queryAccountList());

        System.out.println("===== TRANSFER =====");
        accountDao.transfer("Ali Updated", "Sara", 200.0);
        System.out.println(accountDao.queryAccountList());

        System.out.println("===== DELETE ACCOUNT =====");
        accountDao.delAccount(2);
        System.out.println(accountDao.queryAccountList());
    }
}