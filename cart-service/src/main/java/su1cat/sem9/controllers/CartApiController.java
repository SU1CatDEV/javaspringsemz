package su1cat.sem9.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import su1cat.sem9.model.Product;
import su1cat.sem9.service.CartService;

@RestController
@RequestMapping("/cart/api")
public class CartApiController {
    @Autowired
    private CartService cartService;

    @PutMapping("/add")
    public Product add(@RequestBody Product product) {
        cartService.addToCart(product);
        return product;
    }

    // yes, get instead of delete. im being serious. this is for the links to work directly without having this microservice interface with itself. i forgot everything from the architecture course
    @GetMapping("/remove/{id}")
    public ResponseEntity<Object> remove(@PathVariable(name="id") Long id) {
        cartService.removeFromCart(id);
        String redirectUrl = "/cart/";
        return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirectUrl).build();
    }
}
