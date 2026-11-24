package co.ivis.infrastructure.repository;

import co.ivis.application_core.product.Product;
import co.ivis.application_core.product.ProductRepository;
import co.ivis.infrastructure.repository.mybatis.generated.entity.ProductEntity;
import co.ivis.infrastructure.repository.mybatis.generated.entity.ProductEntityExample;
import co.ivis.infrastructure.repository.mybatis.generated.mapper.ProductMapper;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

  private final ProductMapper productMapper;

  public ProductRepositoryImpl(ProductMapper productMapper) {
    this.productMapper = productMapper;
  }

  @Override
  public List<Product> findAll() {
    ProductEntityExample example = new ProductEntityExample();
    return productMapper.selectByExample(example).stream()
        .map(e -> new Product(bytesToUuidString(e.getId()), e.getName(), e.getPrice()))
        .toList();
  }

  @Override
  public void update(Product product) {
    ProductEntity entity = new ProductEntity();
    entity.setId(uuidStringToBytes(product.getId()));
    entity.setName(product.getName());
    entity.setPrice(product.getPrice());
    productMapper.updateByPrimaryKey(entity);
  }

  @Override
  public void create(Product product) {
    ProductEntity entity = new ProductEntity();
    entity.setId(uuidToBytes(UUID.randomUUID()));
    entity.setName(product.getName());
    entity.setPrice(product.getPrice());
    productMapper.insert(entity);
  }

  @Override
  public void delete(String id) {
    ProductEntityExample example = new ProductEntityExample();
    example.createCriteria().andIdEqualTo(uuidStringToBytes(id));
    productMapper.deleteByExample(example);
  }
  @Override
  public Product findById(String id) {
    ProductEntity entity = productMapper.selectByPrimaryKey(uuidStringToBytes(id));
    if (entity == null) {
      return null;
    }
    return new Product(bytesToUuidString(entity.getId()), entity.getName(), entity.getPrice());
  }

  private String bytesToUuidString(byte[] bytes) {
    ByteBuffer bb = ByteBuffer.wrap(bytes);
    long high = bb.getLong();
    long low = bb.getLong();
    return new UUID(high, low).toString();
  }

  private byte[] uuidStringToBytes(String uuidStr) {
    return uuidToBytes(UUID.fromString(uuidStr));
  }

  private byte[] uuidToBytes(UUID uuid) {
    ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
    bb.putLong(uuid.getMostSignificantBits());
    bb.putLong(uuid.getLeastSignificantBits());
    return bb.array();
  }
}