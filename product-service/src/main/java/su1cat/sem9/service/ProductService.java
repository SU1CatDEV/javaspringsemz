package su1cat.sem9.service;

//import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import su1cat.sem9.model.Product;
import su1cat.sem9.model.Review;
import su1cat.sem9.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product moddedProduct) {
        return productRepository.save(moddedProduct);
    }

    public Product findProductById(Long id) throws ClassNotFoundException {
        return productRepository.findById(id).orElseThrow(ClassNotFoundException::new);
    }

    public void deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        }
    }

    public List<Review> getProductReviews(Long id) {
        String url = "http://localhost:8675/reviews/byproduct/" + id.toString();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Review>> response = restTemplate.exchange(
                url,
                org.springframework.http.HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        return response.getBody();
    }

    public void sendAddToCartRequest(Product product) {
        String url = "http://localhost:8675/cart/api/add";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Product> requestEntity = new HttpEntity<>(product, headers);
        ResponseEntity<Product> response = restTemplate.exchange(
                url,
                org.springframework.http.HttpMethod.PUT,
                requestEntity,
                new ParameterizedTypeReference<>() {}
        );
    }
}