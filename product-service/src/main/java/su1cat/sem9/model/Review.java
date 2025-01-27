package su1cat.sem9.model;

import org.springframework.data.annotation.Id;

public class Review {
    @Id
    private Long id;

    private Integer rating;
    private String text;
    private Long product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }

    public boolean hasThisNulls() {
        return this.rating == null || this.product == null;
    }
}
