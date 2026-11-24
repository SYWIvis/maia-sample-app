package co.ivis.webssr.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductForm {

  @NotBlank
  private String name;

  @NotNull
  @PositiveOrZero
  private BigDecimal price;
}