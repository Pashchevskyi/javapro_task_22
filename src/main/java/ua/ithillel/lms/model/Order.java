package ua.ithillel.lms.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Order {

  private long id;

  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  @Setter
  private LocalDateTime date;

  private double cost;

  @Setter
  private List<Product> products;

  public Order(long id, List<Product> products) {
    this.id = id;
    this.products = products;
    date = LocalDateTime.now();
    this.cost = products.stream().mapToDouble(Product::getCost).sum();
  }

  public Order() {
    products = new ArrayList<>();
    date = LocalDateTime.now();
    cost = products.stream().mapToDouble(Product::getCost).sum();
  }
}