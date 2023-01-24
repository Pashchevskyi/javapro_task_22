package ua.ithillel.lms.repository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ua.ithillel.lms.exception.OrderNotFoundException;
import ua.ithillel.lms.model.Order;
import ua.ithillel.lms.model.OrderList;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

  private final OrderList orderList;

  public Order getOrderById(long id) throws OrderNotFoundException {
    return orderList.getOrders().stream().filter((order) -> order.getId() == id).findFirst()
        .orElseThrow(() -> new OrderNotFoundException(id));
  }

  public List<Order> getOrders() {
    return orderList.getOrders();
  }

  public Order addOrder(Order order) {
    orderList.getOrders().add(order);
    int latestAddedOrderNumber = orderList.getOrders().size() - 1;
    return orderList.getOrders().get(latestAddedOrderNumber);
  }
}
