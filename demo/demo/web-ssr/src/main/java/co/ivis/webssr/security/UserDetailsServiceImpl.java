package co.ivis.webssr.security;

import co.ivis.infrastructure.repository.mybatis.generated.entity.UserEntity;
import co.ivis.infrastructure.repository.mybatis.generated.entity.UserEntityExample;
import co.ivis.infrastructure.repository.mybatis.generated.mapper.UserMapper;
import java.util.List;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserMapper userMapper;

  public UserDetailsServiceImpl(UserMapper userMapper) {
    this.userMapper = userMapper;
  }
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntityExample example = new UserEntityExample();
    example.createCriteria().andUsernameEqualTo(username);
    List<UserEntity> results = userMapper.selectByExample(example);

    if (results.isEmpty()) {
      throw new UsernameNotFoundException("User not found: " + username);
    }

    UserEntity user = results.get(0);
    return User.withUsername(user.getUsername())
        .password(user.getPassword())
        .authorities("USER")
        .build();
  }
}
