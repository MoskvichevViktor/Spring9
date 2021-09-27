package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.entity.Product;

import java.util.Optional;
import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Long> {

    Product save(Product p);
    void deleteById(long id);

    Optional<Product> findById(long id);
    List<Product> findAll();

}
