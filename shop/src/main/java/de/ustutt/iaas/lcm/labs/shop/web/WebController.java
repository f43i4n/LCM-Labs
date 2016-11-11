package de.ustutt.iaas.lcm.labs.shop.web;

import de.ustutt.iaas.lcm.labs.shop.model.InventoryEntry;
import de.ustutt.iaas.lcm.labs.shop.persistence.InventoryRepository;
import de.ustutt.iaas.lcm.labs.shop.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @RequestMapping("/")
    public String index() {

        // create html output
        StringBuilder html = new StringBuilder();

        // header and product amount
        html.append("<h1>Welcome to the shop!</h1>");
        html.append("Currently there ");

        if (productRepository.count() > 1){
            html.append("are ");
            html.append(productRepository.count());
            html.append(" products available:");
        } else {
            html.append("is ");
            html.append(productRepository.count());
            html.append(" product available:");
        }

        // product list
        html.append("<ul>");
        for (InventoryEntry i : inventoryRepository.findAll()){
            html.append("<li>");
            html.append(i.getProduct().getName());
            html.append(" (");
            html.append(i.getAmount());
            html.append("x)");
            html.append("</li>");
        }

        // product details link
        html.append("</ul>");
        html.append("<a href='/products'>See all products in detail</a>");

        return html.toString();
    }
}
