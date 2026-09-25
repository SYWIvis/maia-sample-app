package co.ivis.application_core.product;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductApplicationService {
    private final ProductRepository productRepository;

    public ProductApplicationService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public void updateProduct(Product product){
        productRepository.update(product);
    }
    public void createProduct(Product product){
        productRepository.create(product);
    }
    public void deleteProduct(String id){
        productRepository.delete(id);
    }
}
