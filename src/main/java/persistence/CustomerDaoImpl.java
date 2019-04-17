package persistence;

import domain.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDaoImpl extends PostgresBaseDao implements CustomerDao {
    private AddressDao addDao = new AddressDaoImpl();
    private AccountDao accountDao = new AccountDaoImpl();


    @Override
    public ArrayList<Customer> allCustomer() {
        ArrayList<Customer> results = new ArrayList<Customer>();
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("Select * FROM customers ORDER BY id DESC");
            ResultSet dbResultSet = pstmt.executeQuery();
            while (dbResultSet.next()) {
                results.add(resultCustomer(dbResultSet));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return results;
    }

    @Override
    public Customer customerById(int id) {
        Customer result = new Customer();
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("Select * FROM Customers WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet dbResultSet = pstmt.executeQuery();
            while (dbResultSet.next()) {
                result = resultCustomer(dbResultSet);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteCustomer(int customerId) {
        boolean result = false;
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("DELETE from Customers where id = ? ");
            pstmt.setInt(1, customerId);
            int res = pstmt.executeUpdate();
            if (res == 1) {
                result = true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean saveCustomer(Customer customer) {
        boolean result = false;
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Customers(name, picture, addressid)values(?,?,?) ");
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getPicture());
            pstmt.setInt(3, customer.getAddress().getId());

            int res = pstmt.executeUpdate();
            if (res == 1) {
                result = true;
            }

            pstmt = con.prepareStatement("select currval('customers_id_seq'::regclass) curval");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                customer.setId(rs.getInt("curval"));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        boolean result = false;
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("UPDATE Customers SET name = ?, picture = ? WHERE id = ?");
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getPicture());
            pstmt.setInt(3, customer.getId());
            int res = pstmt.executeUpdate();
            if (res == 1) {
                result = true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateAddressCustomer(int customerId, int addressId) {
        boolean result = false;
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("UPDATE Customers SET addressid = ? WHERE id = ?");
            pstmt.setInt(1, addressId);
            pstmt.setInt(2, customerId);
            int res = pstmt.executeUpdate();
            if (res == 1) {
                result = true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return result;
    }

    public Customer resultCustomer(ResultSet rs) {
        Customer customer = new Customer();
        try {
            int idResult = rs.getInt("id");
            String name = rs.getString("name");
            String picture = rs.getString("picture");
            int addressID = rs.getInt("addressid");

            customer = new Customer(idResult, name, picture);
            customer.setAddress(addDao.adressById(addressID));
            customer.setAccount(accountDao.accountById(idResult));

        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return customer;
    }
}

