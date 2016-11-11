package de.ustutt.iaas.lcm.labs.shop.web;

import de.ustutt.iaas.lcm.labs.shop.model.Product;
import de.ustutt.iaas.lcm.labs.shop.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Get all products
     *
     * @return a list of all products
     */
    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Product> getProducts() {
        return productRepository.findAll();
    }

    /**
     * Create a new product
     *
     * @param product that should be created
     */
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    /**
     * Method to get a product by it's id.
     *
     * @param id of the product
     * @return the product
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable("id") UUID id) {
        return productRepository.findOne(id);
    }
}
