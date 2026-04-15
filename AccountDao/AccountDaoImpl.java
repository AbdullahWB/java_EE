package com.cwnu.dao;

import com.cwnu.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class AccountDaoImpl implements AccountDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public AccountDaoImpl() {
    }

    public void addAccount(Account account) {
        String sql = "INSERT INTO account(username,balance) VALUES(?,?)";
        Object[] obj = new Object[]{account.getUsername(), account.getBalance()};
        this.jdbcTemplate.update(sql, obj);
    }

    public void addAccountList(List<Account> acclist) {
        String sql = "INSERT INTO account(username,balance) VALUES(?,?)";
        List<Object[]> batchArgs = new ArrayList();

        for(int i = 0; i < acclist.size(); ++i) {
            batchArgs.add(new Object[]{((Account)acclist.get(i)).getUsername(), ((Account)acclist.get(i)).getBalance()});
        }

        this.jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    public Account queryAccountById(int id) {
        String sql = "SELECT id,username,balance FROM account WHERE id = ?";
        RowMapper<Account> rowMapper = new BeanPropertyRowMapper(Account.class);
        Account account = (Account)this.jdbcTemplate.queryForObject(sql, rowMapper, new Object[]{id});
        return account;
    }

    public Map<String, Object> queryAccountForMap(int id) {
        String sql = "SELECT * FROM account WHERE id = ?";
        Map<String, Object> map = this.jdbcTemplate.queryForMap(sql, new Object[]{id});
        return map;
    }

    public List<Account> queryAccountList() {
        String sql = "SELECT * FROM account";
        List<Account> list = this.jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Account.class));
        return list;
    }

    public void editAccount(Account acc) {
        String sql = "UPDATE account SET username=?,balance=? WHERE id = ?";
        Object[] obj = new Object[]{acc.getUsername(), acc.getBalance(), acc.getId()};
        this.jdbcTemplate.update(sql, obj);
    }

    public void delAccount(int id) {
        String sql = "DELETE  FROM account WHERE id = ?";
        this.jdbcTemplate.update(sql, new Object[]{id});
    }

    @Transactional
    public void transfer(String outUser, String inUser, Double money) {
        // 汇款时，汇款用户的余额=现有余额-所汇金额
        this.jdbcTemplate.update("UPDATE account SET balance = balance-? WHERE username = ?", new Object[]{money, outUser});
        // 模拟系统运行时的突发性问题
//        int i = 1/0;
        // 收款时，收款用户的余额=现有余额+所汇金额
        this.jdbcTemplate.update("UPDATE account SET balance = balance +? WHERE username = ?", new Object[]{money, inUser});
    }
}
