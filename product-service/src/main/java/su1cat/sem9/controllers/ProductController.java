package su1cat.sem9.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import su1cat.sem9.model.Product;
import su1cat.sem9.model.Review;
import su1cat.sem9.model.exceptions.ResourceNotFoundException;
import su1cat.sem9.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String productsListing(Model model) {
        System.out.println("products.");
        model.addAttribute("products", productService.findAllProducts());
        return "products";
    }

    @GetMapping("/{productId}")
    public String productPage(@PathVariable(name="productId") Long productId, Model model) {
        model.addAttribute("reviews", productService.getProductReviews(productId));
        Review review = new Review();
        review.setProduct(productId);
        model.addAttribute("review", review);
        try {
            Product product = productService.findProductById(productId);
            System.out.println(product.getId());
            model.addAttribute("product", product);
        } catch (ClassNotFoundException e) {
            throw new ResourceNotFoundException();
        }
        return "product";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "create-product";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute Product product, Model model) {
        productService.createProduct(product);
        return "redirect:/products/"; // potential source of bug, marking as TODO
    }

    @GetMapping("/update/{productId}")
    public String updateForm(@PathVariable(name="productId") Long productId, Model model) {
        try {
            Product editing = productService.findProductById(productId);
            model.addAttribute("editing", editing);
        } catch (ClassNotFoundException e) {
            throw new ResourceNotFoundException();
        }
        return "update-product";

    }

    @PostMapping("/update")
    public String updateProduct(@ModelAttribute Product product, Model model) {
        productService.updateProduct(product);
        return "redirect:/products/";
    }

    @GetMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable(name="productId") Long productId, Model model) {
        productService.deleteProduct(productId);
        model.addAttribute("id", productId);
        return "deleted";
    }

    @GetMapping("/addtocart/{productId}")
    public String addToCartRequest(@PathVariable(name="productId") Long productId, Model model) {
        Product product;
        try {
            product = productService.findProductById(productId);
            model.addAttribute("product", product);
        } catch (ClassNotFoundException e) {
            throw new ResourceNotFoundException();
        }
        productService.sendAddToCartRequest(product);
        return "redirect:/cart/";
    }
}

