package co.ivis.application_core.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserApplicationService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserApplicationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public void registerUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.create(user);
  }

  public void changePassword(String username, String newPassword) {
  userRepository.updatePassword(username, passwordEncoder.encode(newPassword));
}
}