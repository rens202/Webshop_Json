package persistence;

import domain.Account;

import java.util.List;

public interface AccountDao {
    public List<Account> allAccounts();
    public Account accountById(int id);
    public boolean updateAccount(Account account);
    public boolean saveAccount(Account account);
    public boolean deleteAccount(int accountId);
    public boolean setAccountsCustomer(int accountId, int customerId);
}
