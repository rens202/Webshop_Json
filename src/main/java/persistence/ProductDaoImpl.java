package persistence;

import domain.Category;
import domain.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDaoImpl extends PostgresBaseDao implements ProductDao {
    private CategoryDao catDao = new CategoryDaoImpl();

    @Override
    public boolean deleteProduct(int productId){
        boolean result = false;
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("DELETE from products where id = ? ");
            pstmt.setInt(1, productId);
            int res = pstmt.executeUpdate();
            if(res == 1){
                result = true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean saveProduct(Product product){
        boolean result = false;
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO products(name, price, description, picture)values(?,?,?,?) ");
            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.setString(3, product.getDescription());
            pstmt.setString(4, product.getPicture());
            int res = pstmt.executeUpdate();
            if(res == 1){
                result = true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateProduct(Product product){
        boolean result = false;
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("UPDATE products SET name = ?, price = ?, description = ?, picture = ? WHERE id = ? ");
            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.setString(3, product.getDescription());
            pstmt.setString(4, product.getPicture());
            pstmt.setInt(5, product.getId());
            int res = pstmt.executeUpdate();
            if(res == 1){
                result = true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Product> allProducts() {
        ArrayList<Product> results = new ArrayList<Product>();
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("Select * FROM Products ORDER BY id DESC");
            ResultSet dbResultSet = pstmt.executeQuery();
            while (dbResultSet.next()) {
                results.add(resultProduct(dbResultSet));
                //ArrayList<Category> categorys =
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return results;
    }

    @Override
    public Product productById(int id) {
        Product result= new Product();
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("Select * FROM Products WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet dbResultSet = pstmt.executeQuery();
            while (dbResultSet.next()) {
                result = resultProduct(dbResultSet);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Product> productByCategory(int catId) {
        ArrayList<Product> results = new ArrayList<Product>();
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("select * from products where id IN (Select productid FROM category_Product WHERE categoryid = ?)");
            pstmt.setInt(1, catId);
            ResultSet dbResultSet = pstmt.executeQuery();
            while (dbResultSet.next()) {
                results.add(resultProduct(dbResultSet));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return results;
    }


    public Product resultProduct(ResultSet rs) {
        Product product = new Product();
        try {
            int idResult = rs.getInt("id");
            String name = rs.getString("name");
            double price = rs.getDouble("price");
            String description = rs.getString("description");
            String picture = rs.getString("picture");

            product = new Product(idResult, name, price, description, picture);
            ArrayList<Category> categorys = catDao.categoryByProduct(idResult);
            product.setCategories(categorys);
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return product;
    }

}
