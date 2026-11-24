package co.ivis.webssr.controllers;

import co.ivis.application_core.product.Product;
import co.ivis.application_core.product.ProductApplicationService;
import co.ivis.webssr.models.ProductForm;
import jakarta.validation.Valid;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class ProductPageController {

  private final ProductApplicationService productService;

  public ProductPageController(ProductApplicationService productService) {
    this.productService = productService;
  }

  @GetMapping("/products")
  public String listProducts(Model model) {
    model.addAttribute("products", productService.getAllProducts());
    return "products/list";
  }

  @GetMapping("/products/create")
  public String createForm(Model model) {
    model.addAttribute("productForm", new ProductForm());
    model.addAttribute("pageTitle", "Create Product");
    model.addAttribute("formAction", "/products/create");
    return "products/form";
  }

  @PostMapping("/products/create")
  public String createProduct(
      @Valid @ModelAttribute("productForm") ProductForm form,
      BindingResult bindingResult,
      Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("pageTitle", "Create Product");
      model.addAttribute("formAction", "/products/create");
      return "products/form";
    }
    productService.createProduct(new Product(null, form.getName(), form.getPrice()));
    return "redirect:/products";
  }

  @GetMapping("/products/{id}/edit")
  public String editForm(@PathVariable String id, Model model) {
    Product product = findProduct(id);
    ProductForm form = new ProductForm();
    form.setName(product.getName());
    form.setPrice(product.getPrice());
    model.addAttribute("productForm", form);
    model.addAttribute("pageTitle", "Edit Product");
    model.addAttribute("formAction", "/products/" + id + "/edit");
    model.addAttribute("productId", id);
    return "products/form";
  }

  @PostMapping("/products/{id}/edit")
  public String updateProduct(
      @PathVariable String id,
      @Valid @ModelAttribute("productForm") ProductForm form,
      BindingResult bindingResult,
      Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("pageTitle", "Edit Product");
      model.addAttribute("formAction", "/products/" + id + "/edit");
      model.addAttribute("productId", id);
      return "products/form";
    }
    findProduct(id);
    productService.updateProduct(new Product(id, form.getName(), form.getPrice()));
    return "redirect:/products";
  }

  @PostMapping("/products/{id}/delete")
  public String deleteProduct(@PathVariable String id) {
    findProduct(id);
    productService.deleteProduct(id);
    return "redirect:/products";
  }

  private Product findProduct(String id) {
    Product product;
    try {
      product = productService.getProduct(id);
    } catch (IllegalArgumentException exception) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found", exception);
    }
    if (Objects.isNull(product)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
    }
    return product;
  }
}
