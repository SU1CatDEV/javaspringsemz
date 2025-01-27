package su1cat.sem9.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su1cat.sem9.model.Product;
import su1cat.sem9.repository.Cart;

import java.util.HashMap;

// this feels. highly redundant but whatever
@Service
public class CartService {
    @Autowired
    private Cart cartRepository;

    public void addToCart(Product product) {
        cartRepository.addToCart(product);
    }

    public void removeFromCart(Long id) {
        cartRepository.removeFromCart(id);
    }

    public HashMap<Long, Product> getCart() {
        return cartRepository.getProductList();
    }
}
