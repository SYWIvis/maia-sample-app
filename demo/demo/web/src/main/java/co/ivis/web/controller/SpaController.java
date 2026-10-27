package co.ivis.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpaController {

  @GetMapping({"/about", "/login", "/profile", "/change-password",
      "/products", "/products/create", "/products/{id}/edit"})
  public String forward() {
    return "forward:/index.html";
  }
}