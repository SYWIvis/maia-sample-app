package co.ivis.webssr.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PasswordChangeForm {

  @NotBlank
  @Size(min = 8)
  private String newPassword;
}
