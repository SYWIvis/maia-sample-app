package co.ivis.infrastructure.repository.mybatis.generated.mapper;

import co.ivis.infrastructure.repository.mybatis.generated.entity.ProductEntity;
import co.ivis.infrastructure.repository.mybatis.generated.entity.ProductEntityExample;
import jakarta.annotation.Generated;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductMapper {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    long countByExample(ProductEntityExample example);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    int deleteByExample(ProductEntityExample example);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    int deleteByPrimaryKey(byte[] id);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    int insert(ProductEntity row);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    int insertSelective(ProductEntity row);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    List<ProductEntity> selectByExample(ProductEntityExample example);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    ProductEntity selectByPrimaryKey(byte[] id);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    int updateByExampleSelective(@Param("row") ProductEntity row, @Param("example") ProductEntityExample example);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    int updateByExample(@Param("row") ProductEntity row, @Param("example") ProductEntityExample example);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    int updateByPrimaryKeySelective(ProductEntity row);

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    int updateByPrimaryKey(ProductEntity row);
}