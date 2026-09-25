package co.ivis.web.controller;

import co.ivis.application_core.user.User;
import co.ivis.application_core.user.UserApplicationService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserApplicationService userService;

  public UserController(UserApplicationService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public void registerUser(@RequestBody User user) {
    userService.registerUser(user);
  }

  @GetMapping("/me")
  public String getCurrentUser(java.security.Principal principal) {
  return principal.getName();
}
  @PostMapping("/change-password")
  public void changePassword(java.security.Principal principal, @RequestBody java.util.Map<String, String> body) {
  userService.changePassword(principal.getName(), body.get("newPassword"));
}

}
