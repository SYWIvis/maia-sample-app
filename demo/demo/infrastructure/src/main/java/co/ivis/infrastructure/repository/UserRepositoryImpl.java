package co.ivis.infrastructure.repository;

import co.ivis.application_core.user.User;
import co.ivis.application_core.user.UserRepository;
import co.ivis.infrastructure.repository.mybatis.generated.entity.UserEntity;
import co.ivis.infrastructure.repository.mybatis.generated.entity.UserEntityExample;
import co.ivis.infrastructure.repository.mybatis.generated.mapper.UserMapper;
import java.nio.ByteBuffer;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

  private final UserMapper userMapper;

  public UserRepositoryImpl(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Override
  public void create(User user) {
    UserEntity entity = new UserEntity();
    entity.setId(uuidToBytes(UUID.randomUUID()));
    entity.setUsername(user.getUsername());
    entity.setPassword(user.getPassword()); // already hashed by the Service layer before reaching here
    userMapper.insert(entity);
  }

  private byte[] uuidToBytes(UUID uuid) {
    ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
    bb.putLong(uuid.getMostSignificantBits());
    bb.putLong(uuid.getLeastSignificantBits());
    return bb.array();
  }

  @Override
  public void updatePassword(String username, String hashedPassword) {
    UserEntityExample example = new UserEntityExample();
    example.createCriteria().andUsernameEqualTo(username);
    UserEntity entity = new UserEntity();
    entity.setPassword(hashedPassword);
    userMapper.updateByExampleSelective(entity, example);
}
}