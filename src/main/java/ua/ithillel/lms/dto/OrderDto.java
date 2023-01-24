package ua.ithillel.lms.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class OrderDto {

  private long id;

  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonDeserialize(using = LocalDateTimeDeserializer.class)
  private LocalDateTime date;
  private double cost;
  private List<ProductDto> products;

  public OrderDto(long id, List<ProductDto> products) {
    this.id = id;
    this.products = products;
    date = LocalDateTime.now();
    this.cost = products.stream().mapToDouble(ProductDto::getCost).sum();
  }

  public OrderDto() {
    products = new ArrayList<>();
    date = LocalDateTime.now();
    cost = products.stream().mapToDouble(ProductDto::getCost).sum();
  }
}
