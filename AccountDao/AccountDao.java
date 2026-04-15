package com.cwnu.dao;
import com.cwnu.pojo.Account;
import java.util.List;
import java.util.Map;

public interface AccountDao {
    void addAccount(Account var1);
    Account queryAccountById(int var1);
    void addAccountList(List<Account> var1);
    Map<String, Object> queryAccountForMap(int var1);
    List<Account> queryAccountList();
    void editAccount(Account var1);
    void delAccount(int var1);
    void transfer(String outUser,String inUser,Double money);
}
