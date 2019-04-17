package persistence;

import domain.Account;
import domain.Order;
import domain.OrderLine;

import java.util.List;

public interface OrderDao {
    List<Order> allOrders();
    Order orderById(int orderId);
    boolean saveOrder(Order order);
    List<Order> ordersByAccount(Account account);
}
