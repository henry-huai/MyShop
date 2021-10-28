package dev.huai.daos;

import dev.huai.models.Product;

import java.math.BigDecimal;

public interface ProductDao {

    public boolean addProduct(Product product);

    public boolean updateProductPrice(Integer product_id, BigDecimal newPrice);

    public boolean deleteProductById(Integer product_id);

}
