package spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import spring.apierror.ApiError;
import spring.service.ProductService;
import spring.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService service;

    @PostMapping("app/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        return ResponseEntity.created(URI.create("app/products/")).body(service.saveOrUpdate(product));
    }

    @GetMapping("app/products")
    public ResponseEntity<List<Product>> showAll() {
        return  ResponseEntity.ok(service.findAll());
    }

    @GetMapping("app/products/{id}")
    public ResponseEntity<Product> showById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("app/products/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") Long id){
       service.deleteById(id);
       return ResponseEntity.ok(String.format("Product with id: %d deleted", id)) ;

    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handleException(RuntimeException ex){
        return ResponseEntity.internalServerError()
                .body(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()));
    }
}
