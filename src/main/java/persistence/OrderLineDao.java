package persistence;

import domain.Order;
import domain.OrderLine;

import java.util.List;

public interface OrderLineDao {
    public List<OrderLine> allOrderLines();
    public List<OrderLine> orderLinesByOrder(Order order);
    public boolean saveOrderLine(OrderLine orderline);
}
