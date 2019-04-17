package persistence;

import domain.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl extends PostgresBaseDao implements AccountDao {
    @Override
    public List<Account> allAccounts() {
        List<Account> results = new ArrayList<Account>();
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("Select * FROM Accounts ORDER BY id DESC");
            ResultSet dbResultSet = pstmt.executeQuery();
            while (dbResultSet.next()) {
                results.add(resultAccount(dbResultSet));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return results;
    }

    private Account resultAccount(ResultSet rs) {
        Account account = new Account();
        OrderDao orderDao = new OrderDaoImpl();

        try {
            int idResult = rs.getInt("id");
            Date openDate = rs.getDate("openDate");
            int isActive = rs.getInt("isActive");
            account = new Account(idResult, openDate, isActive);
            account.setOrders(orderDao.ordersByAccount(account));
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return account;
    }

    @Override
    public Account accountById(int id) {
        Account result= new Account();
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("Select * FROM Accounts WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet dbResultSet = pstmt.executeQuery();
            while (dbResultSet.next()) {
                result = resultAccount(dbResultSet);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return result;
    }


    @Override
    public boolean deleteAccount(int accountId){
        boolean result = false;
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("DELETE from Accounts where id = ? ");
            pstmt.setInt(1, accountId);
            int res = pstmt.executeUpdate();
            if(res == 1){
                result = true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean saveAccount(Account account){
        boolean result = false;
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Accounts(openDate, isactive, addressid, customerid)values(?,?,?,?) ");
            pstmt.setDate(1, account.getOpenDate());
            pstmt.setInt(2, account.getIsActive());
            pstmt.setInt(3, account.getAddress().getId());
            pstmt.setInt(4, account.getCustomer().getId());

            int res = pstmt.executeUpdate();
            if(res == 1){
                result = true;
            }

            pstmt = con.prepareStatement("select currval('accounts_id_seq'::regclass) curval");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                account.setId(rs.getInt("curval"));
            }


        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateAccount(Account account){
        boolean result = false;
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("UPDATE Accounts SET opendate = ?, isActive = ? WHERE id = ?");
            pstmt.setDate(1, account.getOpenDate());
            pstmt.setInt(2, account.getIsActive());
            pstmt.setInt(3, account.getId());
            int res = pstmt.executeUpdate();
            if(res == 1){
                result = true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return result;
    }


    @Override
    public boolean setAccountsCustomer(int accountId, int customerId){
        boolean result = false;
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("UPDATE Accounts SET customerid = ? WHERE id = ?");
            pstmt.setInt(1, customerId);
            pstmt.setInt(2, accountId);
            int res = pstmt.executeUpdate();
            if(res == 1){
                result = true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return result;
    }

}
