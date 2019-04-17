package persistence;

import domain.Category;

import java.util.ArrayList;

public interface CategoryDao {

    ArrayList<Category> categoryByProduct(int catId);

    ArrayList<Category> findAll();

    Category categoryByCatId(int catId);

    public boolean saveCategory(Category category, int productid);

    public int getCurrentValCategory();

    public boolean saveProductCategory(int categoryId, int productId);
}