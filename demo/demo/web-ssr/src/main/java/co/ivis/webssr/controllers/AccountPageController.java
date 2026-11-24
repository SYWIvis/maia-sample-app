package co.ivis.webssr.controllers;

import co.ivis.application_core.user.UserApplicationService;
import co.ivis.webssr.models.PasswordChangeForm;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountPageController {

  private final UserApplicationService userService;

  public AccountPageController(UserApplicationService userService) {
    this.userService = userService;
  }

  @GetMapping("/profile")
  public String profile(Authentication authentication, Model model) {
    model.addAttribute("username", authentication.getName());
    return "profile";
  }

  @GetMapping("/change-password")
  public String changePasswordForm(Model model) {
    model.addAttribute("passwordChangeForm", new PasswordChangeForm());
    return "change-password";
  }

  @PostMapping("/change-password")
  public String changePassword(
      Authentication authentication,
      @Valid @ModelAttribute("passwordChangeForm") PasswordChangeForm form,
      BindingResult bindingResult,
      Model model) {
    if (bindingResult.hasErrors()) {
      return "change-password";
    }
    userService.changePassword(authentication.getName(), form.getNewPassword());
    model.addAttribute("successMessage", "Password changed.");
    model.addAttribute("passwordChangeForm", new PasswordChangeForm());
    return "change-password";
  }
}
