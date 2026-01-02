package com.jsfcourse.bb;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

import com.jsfcourse.dao.ProductsDAO;
import com.jsfcourse.entities.Products;

@Named
@RequestScoped

/**
 *
 * @author vladm
 */
public class HomePageBB {

    @Inject
    private ProductsDAO productsDAO;

    private List<Products> products;

    @PostConstruct
    public void init() {
    try {
        products = productsDAO.findActiveProducts();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    public List<Products> getProducts() {
        return products;
    }

}
