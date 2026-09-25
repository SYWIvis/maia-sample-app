package co.ivis.application_core.product;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class ProductApplicationServiceTest {

  @Test
  void getAllProducts_returnsWhatRepositoryHas() {
    FakeProductRepository fakeRepo = new FakeProductRepository();
    fakeRepo.create(new Product("id-1", "Test Item", new BigDecimal("100")));

    ProductApplicationService service = new ProductApplicationService(fakeRepo);
    var result = service.getAllProducts();

    assertEquals(1, result.size());
    assertEquals("Test Item", result.get(0).getName());
  }
}