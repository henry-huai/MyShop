package dev.huai.daos;

import dev.huai.models.Product;

public interface ProductDao {

    public boolean addProduct(Product product);

    public boolean updateProduct();

    public boolean deleteProduct();

}
