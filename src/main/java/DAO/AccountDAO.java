package DAO;

import Model.Account;
import java.util.List;

public interface AccountDAO {
    Account createAccount(Account account);
    Account getAccountById(int id);
    Account getAccountByUsername(String username);
    List<Account> getAllAccounts();
    Account updateAccount(Account account);
    boolean deleteAccount(int id);
}
