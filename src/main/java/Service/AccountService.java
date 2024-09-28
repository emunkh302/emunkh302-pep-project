package Service;

import DAO.AccountDAO;
import DAO.AccountDAOImpl;
import Model.Account;

import java.util.List;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService() {
        this.accountDAO = new AccountDAOImpl();
    }

    public Account createAccount(Account account) {
        // Validation: Username not blank, Password length >= 4
        if(account.getUsername() == null || account.getUsername().isEmpty()) {
            return null;
        }
        if(account.getPassword() == null || account.getPassword().length() < 4) {
            return null;
        }
        // Check for duplicate username
        if(accountDAO.getAccountByUsername(account.getUsername()) != null) {
            return null;
        }
        return accountDAO.createAccount(account);
    }

    public Account getAccountById(int id) {
        return accountDAO.getAccountById(id);
    }

    public Account getAccountByUsername(String username) {
        return accountDAO.getAccountByUsername(username);
    }

    public List<Account> getAllAccounts() {
        return accountDAO.getAllAccounts();
    }

    public Account updateAccount(Account account) {
        // Implement as needed
        return accountDAO.updateAccount(account);
    }

    public boolean deleteAccount(int id) {
        return accountDAO.deleteAccount(id);
    }

    public Account login(String username, String password) {
        Account account = accountDAO.getAccountByUsername(username);
        if(account != null && account.getPassword().equals(password)) {
            return account;
        }
        return null;
    }
}
