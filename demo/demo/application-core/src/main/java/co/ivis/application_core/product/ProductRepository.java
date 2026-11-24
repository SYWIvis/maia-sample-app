package co.ivis.application_core.product;

import java.util.List;

public interface ProductRepository {
  List<Product> findAll();
  void update(Product product);
  void create(Product product);
  void delete(String id);
  Product findById(String id);
}