package persistence;

import domain.Order;
import domain.OrderLine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderLineDaoImpl extends PostgresBaseDao implements OrderLineDao {

    @Override
    public List<OrderLine> allOrderLines() {
        List<OrderLine> results = new ArrayList<OrderLine>();
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("Select * FROM OrderLines ORDER BY id DESC");
            ResultSet dbResultSet = pstmt.executeQuery();
            while (dbResultSet.next()) {
                results.add(resultOrderLine(dbResultSet));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return results;
    }

    @Override
    public List<OrderLine> orderLinesByOrder(Order order) {
        List<OrderLine> results = new ArrayList<OrderLine>();
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("Select * FROM OrderLines where orderid = ?");
            pstmt.setInt(1, order.getId());
            ResultSet dbResultSet = pstmt.executeQuery();
            while (dbResultSet.next()) {
                results.add(resultOrderLine(dbResultSet, order));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return results;
    }

    @Override
    public boolean saveOrderLine(OrderLine orderline){
        boolean result = false;

        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Orderlines(quantity, price, orderid, productid)values(?,?,?,?)");
            pstmt.setInt(1, orderline.getQuantity());
            pstmt.setDouble(2, orderline.getPrice());
            pstmt.setInt(3, orderline.getOrder().getId());
            pstmt.setInt(4, orderline.getProduct().getId());
            int res = pstmt.executeUpdate();
            if(res == 1){
                result = true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return result;
    }

    private OrderLine resultOrderLine(ResultSet rs, Order order) {
        OrderLine orderLine = new OrderLine();
        ProductDao proDao = new ProductDaoImpl();

        try {
            int idResult = rs.getInt("id");
            int quantity = rs.getInt("quantity");
            double price = rs.getDouble("price");
            int productId = rs.getInt("productid");

            orderLine = new OrderLine(idResult, quantity, price);
            orderLine.setProduct(proDao.productById(productId));
            orderLine.setOrder(order);
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return orderLine;
    }

    private OrderLine resultOrderLine(ResultSet rs) {
        OrderLine orderLine = new OrderLine();
        OrderDao orderDao = new OrderDaoImpl();
        try {
            int orderId = rs.getInt("orderid");
            orderLine = resultOrderLine(rs, orderDao.orderById(orderId));
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return orderLine;
    }
}
