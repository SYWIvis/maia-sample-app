package co.ivis.infrastructure.repository.mybatis.generated.mapper;

import co.ivis.infrastructure.repository.mybatis.generated.entity.UserEntity;
import co.ivis.infrastructure.repository.mybatis.generated.entity.UserEntityExample;
import jakarta.annotation.Generated;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    long countByExample(UserEntityExample example);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    int deleteByExample(UserEntityExample example);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    int deleteByPrimaryKey(byte[] id);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    int insert(UserEntity row);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    int insertSelective(UserEntity row);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    List<UserEntity> selectByExample(UserEntityExample example);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    UserEntity selectByPrimaryKey(byte[] id);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    int updateByExampleSelective(@Param("row") UserEntity row, @Param("example") UserEntityExample example);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    int updateByExample(@Param("row") UserEntity row, @Param("example") UserEntityExample example);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    int updateByPrimaryKeySelective(UserEntity row);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    int updateByPrimaryKey(UserEntity row);
}