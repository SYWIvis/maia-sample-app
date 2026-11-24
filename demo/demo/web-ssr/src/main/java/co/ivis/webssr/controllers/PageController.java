package co.ivis.webssr.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

  @GetMapping({"/", "/about"})
  public String about() {
    return "about";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }
}
