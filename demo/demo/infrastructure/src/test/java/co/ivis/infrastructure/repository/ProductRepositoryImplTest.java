package co.ivis.infrastructure.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import co.ivis.application_core.product.Product;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = co.ivis.infrastructure.TestApplication.class)
class ProductRepositoryImplTest {

  @Autowired
  private ProductRepositoryImpl productRepository;

  @Test
  void create_thenFindAll_returnsTheCreatedProduct() {
    Product newProduct = new Product(null, "Test Product", new BigDecimal("999"));
    productRepository.create(newProduct);

    List<Product> all = productRepository.findAll();

    assertTrue(all.stream().anyMatch(p -> p.getName().equals("Test Product")));
  }
}

