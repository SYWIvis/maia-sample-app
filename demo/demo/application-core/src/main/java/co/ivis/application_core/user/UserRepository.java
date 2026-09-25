package co.ivis.application_core.user;
import java.util.List;
public interface UserRepository {
  void create(User user);
  void updatePassword(String username, String hashedPassword);
}