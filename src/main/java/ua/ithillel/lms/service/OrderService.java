package ua.ithillel.lms.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.ithillel.lms.dto.OrderDto;
import ua.ithillel.lms.dto.ProductDto;
import ua.ithillel.lms.exception.OrderNotFoundException;
import ua.ithillel.lms.model.Order;
import ua.ithillel.lms.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final ObjectMapper objectMapper;

  /**
   * Returns Order object by id of searching Order
   *
   * @param id of Order to search for
   * @return OrderDto object
   * @throws OrderNotFoundException if Order has not been found
   */
  public OrderDto getOrderById(long id) throws OrderNotFoundException {
    Order order = orderRepository.getOrderById(id);
    return objectMapper.convertValue(order, OrderDto.class);
  }

  /**
   * Returns list of OrderDto objects
   *
   * @return ArrayList<OrderDto>
   */
  public List<OrderDto> getOrders() {
    List<Order> orders = orderRepository.getOrders();
    List<OrderDto> result = new ArrayList<>(orders.size());
    orders.forEach(order -> {
      result.add(objectMapper.convertValue(order, OrderDto.class));
    });
    return result;
  }

  /**
   * Returns OrderDto object for Order added
   *
   * @param orderDto object to add
   * @return OrderDto object added
   */
  public OrderDto addOrder(OrderDto orderDto) {

    orderDto.setCost(orderDto.getProducts().stream().mapToDouble(ProductDto::getCost).sum());
    Order order = objectMapper.convertValue(orderDto, Order.class);

    Order addedOrder = orderRepository.addOrder(order);
    orderDto.setId(addedOrder.getId());
    return orderDto;
  }
}
