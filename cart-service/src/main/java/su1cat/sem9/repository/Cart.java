package su1cat.sem9.repository;

import org.springframework.stereotype.Repository;
import su1cat.sem9.model.Product;

import java.util.HashMap;

@Repository
public class Cart {
    private HashMap<Long, Product> productList = new HashMap<>();

    public Product addToCart(Product product) {
        productList.put(product.getId(), product);
        return product;
    }

    public void removeFromCart(Long productId) {
        productList.remove(productId);
    }

    public HashMap<Long, Product> getProductList() {
        return productList;
    }
}
