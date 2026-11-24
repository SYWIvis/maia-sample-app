package co.ivis.application_core.product;

import java.util.ArrayList;
import java.util.List;

public class FakeProductRepository implements ProductRepository {
  private final List<Product> data = new ArrayList<>();

  @Override
  public List<Product> findAll() {
    return data;
  }

  @Override
  public void update(Product product) {
    data.removeIf(p -> p.getId().equals(product.getId()));
    data.add(product);
  }

  @Override
  public void create(Product product) {
    data.add(product);
  }

  @Override
  public void delete(String id) {
    data.removeIf(p -> p.getId().equals(id));
  }
  //@Override
  public Product findById(String id) {
  return data.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
}
}