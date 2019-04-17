package persistence;

import domain.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDaoImpl extends PostgresBaseDao implements CategoryDao {


    //Returns a list filled with Category's corresponding the catid given by the product. This category does not have a product
    @Override
    public ArrayList<Category> categoryByProduct(int protId) {
        ArrayList<Category> results = new ArrayList<Category>();
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("select * from categories where id IN (Select categoryid FROM category_product WHERE productid = ?)"); //fix this, supposed to be "koppeltabel"
            pstmt.setInt(1, protId);
            ResultSet dbResultSet = pstmt.executeQuery();
            while (dbResultSet.next()) {
                results.add(resultCategory(dbResultSet));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return results;
    }

    @Override
    public Category categoryByCatId(int catId) {
        Category result = new Category();
        try (Connection con = super.getConnection()) {
            PreparedStatement ptmt = con.prepareStatement("Select * FROM categories WHERE id = ?");
            ptmt.setInt(1, catId);
            ResultSet dbResultSet = ptmt.executeQuery();
            while (dbResultSet.next()) {
                result = resultCategory(dbResultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean saveCategory(Category category, int productid){
        boolean result = false;
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO categories(name, description, picture)values(?, ?, ?) ");
            pstmt.setString(1, category.getName());
            pstmt.setString(2, category.getDescription());
            pstmt.setString(3, category.getPicture());

            int categoryid = getCurrentValCategory();
            int res = pstmt.executeUpdate();
            if(res == 1){
                result = true;
                saveProductCategory(categoryid, productid);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean saveProductCategory(int categoryId, int productId){
        boolean result = false;
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO category_product(categoryid, productid)values(?, ?) ");
            pstmt.setInt(1, categoryId);
            pstmt.setInt(2, productId);

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
    public int getCurrentValCategory(){
        int result = 0;
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("select nextval('categories_id_seq')");

            ResultSet dbResultSet = pstmt.executeQuery();
            while (dbResultSet.next()) {
                result = dbResultSet.getInt("nextval");
                result = result - 1;
            }
            } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return result;
    }



    @Override
    public ArrayList<Category> findAll() {
        ArrayList<Category> results = new ArrayList<Category>();
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("Select * FROM categories");
            ResultSet dbResultSet = pstmt.executeQuery();
            while (dbResultSet.next()) {
                results.add(resultCategory(dbResultSet));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return results;
    }

    public Category resultCategory(ResultSet rs) {
        Category category = new Category();
        try {
            int idResult = rs.getInt("id");
            String name = rs.getString("name");
            String picture = rs.getString("picture");
            String description = rs.getString("description");

            category = new Category(idResult, name, description, picture);
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return category;
    }

}

