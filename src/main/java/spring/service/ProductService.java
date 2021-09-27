package spring.service;


import spring.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import spring.repository.ProductRepo;

import java.util.List;

@Component
public class ProductService {
    @Autowired
    private ProductRepo repo;

    public Product saveOrUpdate(Product p){
        Product save = repo.save(p);
        return save;
    }

    public void deleteById(long id){
        repo.deleteById(id);
    }

    public List<Product> findAll(){
        return repo.findAll();
    }

    public Product findById(long id){
        return repo.findById(id).orElseGet(null);
    }


}
