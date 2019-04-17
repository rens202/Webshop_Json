package persistence;

import domain.Product;

import java.util.ArrayList;

public interface ProductDao {

    public ArrayList<Product> allProducts();

    public Product productById(int id);

    public ArrayList<Product> productByCategory(int catId);

    public boolean deleteProduct(int Productid);

    public boolean saveProduct(Product product);

    public boolean updateProduct(Product product);


}
