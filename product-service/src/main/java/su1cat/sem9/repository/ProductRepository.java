package su1cat.sem9.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import su1cat.sem9.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    @Override
    List<Product> findAll();
}
