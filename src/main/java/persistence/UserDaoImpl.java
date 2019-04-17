package persistence;

import domain.Customer;
import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends PostgresBaseDao implements UserDao {
    @Override
    public List<User> findAll() {
        List<User> results = new ArrayList<>();
        try {
            Connection con = super.getConnection();
            PreparedStatement pstmt = con.prepareStatement("Select * FROM Users");
            ResultSet dbResultSet = pstmt.executeQuery();
            while (dbResultSet.next()) {
                results.add(toUser(dbResultSet));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return results;
    }

    @Override
    public User findByUsername(String username) {
        User result = null;
        try {
            Connection con = super.getConnection();
            PreparedStatement pstmt = con.prepareStatement("Select * FROM Users WHERE username = ?");
            pstmt.setString(1, username);
            ResultSet dbResultSet = pstmt.executeQuery();
            if (dbResultSet.next()) {
                result = toUser(dbResultSet);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return result;
    }

    @Override
    public String findRole(String username, String password) {
        String result = null;
        try {
            Connection con = super.getConnection();
            PreparedStatement pstmt = con.prepareStatement(
                    "Select role FROM Users WHERE username = ? AND password = ?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet dbResultSet = pstmt.executeQuery();
            if (dbResultSet.next()) {
                result = dbResultSet.getString("role");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateUser(User account) {
        return false;
    }

    @Override
    public boolean saveUser(User user) {
        try {
            Connection con = super.getConnection();
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Users values(?, ?, ?, ?)");
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getRole());
            pstmt.setInt(4, user.getCustomer().getId());

            int res = pstmt.executeUpdate();
            if(res == 1){
                return true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUser(String username) {
        return false;
    }

    private User toUser(ResultSet rs) {
        User user = null;
        CustomerDao customerDao = new CustomerDaoImpl();
        try {
            String username = rs.getString("username");
            String password = rs.getString("password");
            String role = rs.getString("role");
            Customer customer = customerDao.customerById(rs.getInt("customer"));
            user = new User(username, password, role, customer);
        } catch (SQLException sql) {
            sql.printStackTrace();
        }

        return user;
    }
}
