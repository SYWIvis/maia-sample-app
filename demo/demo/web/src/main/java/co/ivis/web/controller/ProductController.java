package co.ivis.web.controller;

import co.ivis.application_core.product.Product;
import co.ivis.application_core.product.ProductApplicationService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductApplicationService productService;

  public ProductController(ProductApplicationService productService) {
    this.productService = productService;
  }

  @GetMapping
  public List<Product> getAllProducts() {
    return productService.getAllProducts();
  }

  @PutMapping("/{id}")
  public void updateProduct(@PathVariable String id, @RequestBody Product product) {
    product.setId(id); //assign the path variable ID to the product's ID field because by default product doesnt have its ID set
    productService.updateProduct(product);
}
  @PostMapping
  public void createProduct(@RequestBody Product product) {
    productService.createProduct(product);
  }
  @DeleteMapping("/{id}")
  public void deleteProduct(@PathVariable String id) {
    productService.deleteProduct(id);
  }
}