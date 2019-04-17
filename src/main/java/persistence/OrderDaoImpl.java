package persistence;

import domain.Account;
import domain.Order;
import domain.OrderLine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl extends PostgresBaseDao implements OrderDao{
    @Override
    public List<Order> allOrders() {
        List<Order> results = new ArrayList<Order>();
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("Select * FROM Orders ORDER BY id DESC");
            ResultSet dbResultSet = pstmt.executeQuery();
            while (dbResultSet.next()) {
                results.add(resultOrder(dbResultSet));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return results;
    }

    @Override
    public Order orderById(int orderId) {
        Order result = new Order();
        try (Connection con = super.getConnection()) {
            PreparedStatement ptmt = con.prepareStatement("Select * FROM Orders WHERE id = ?");
            ptmt.setInt(1, orderId);
            ResultSet dbResultSet = ptmt.executeQuery();
            while (dbResultSet.next()) {
                result = resultOrder(dbResultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean saveOrder(Order order){
        boolean result = false;
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Orders(accountid, addressid)values(?,?) ");
            pstmt.setInt(1, order.getAccount().getId());
            pstmt.setInt(2, order.getAddress().getId());
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
    public List<Order> ordersByAccount(Account account) {
        List<Order> results = new ArrayList<>();
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("Select * FROM Orders where accountid = ?");
            pstmt.setInt(1, account.getId());
            ResultSet dbResultSet = pstmt.executeQuery();
            while (dbResultSet.next()) {
                results.add(resultOrder(dbResultSet, account));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return results;
    }

    private Order resultOrder(ResultSet rs) {
        Order order = new Order();
        AccountDao accDao = new AccountDaoImpl();
        try {
            int accountId = rs.getInt("accountid");
            Account account = accDao.accountById(accountId);
            order = resultOrder(rs, account);
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return order;
    }

    private Order resultOrder(ResultSet rs, Account account) {
        Order order = new Order();
        AddressDao addressDao = new AddressDaoImpl();
        OrderLineDao orderLineDao = new OrderLineDaoImpl();
        try {
            int idResult = rs.getInt("id");
            int addressId = rs.getInt("addressid");
            //get  account
            order = new Order(idResult);
            order.setAddress(addressDao.adressById(addressId));
            order.setAccount(account);
            order.setOrderLines(orderLineDao.orderLinesByOrder(order));

        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return order;
    }



}