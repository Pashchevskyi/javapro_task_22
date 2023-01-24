package ua.ithillel.lms.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ua.ithillel.lms.model.Order;
import ua.ithillel.lms.model.OrderList;
import ua.ithillel.lms.model.Product;

@Configuration
public class AppConfiguration {

  @Bean
  ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

  @Bean
  public OrderList orderList() {
    List<Order> orders = new ArrayList<>();
    List<Product> products1 = new ArrayList<>();
    products1.add(new Product(1, "Samsung air condition", 31999.98));
    products1.add(new Product(2, "Samsung refrigerator", 28000.00));
    orders.add(new Order(1, products1));
    List<Product> products2 = new ArrayList<>();
    products2.add(new Product(3, "Asus notebook battery", 16999.99));
    products2.add(new Product(4, "Luxeon UPS", 10000));
    products2.add(new Product(5, "Luxeon Voltage corrector", 8900.01));
    orders.add(new Order(2, products2));
    return new OrderList(orders);
  }
}
