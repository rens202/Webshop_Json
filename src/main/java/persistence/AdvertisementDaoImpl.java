package persistence;

import domain.Advertisement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdvertisementDaoImpl extends PostgresBaseDao implements AdvertisementDao {
    ProductDao proDao = new ProductDaoImpl();

    @Override
    public List<Advertisement> allAdvertisement() {
        List<Advertisement> results = new ArrayList<Advertisement>();
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("Select * FROM Advertisements ORDER BY id DESC");
            ResultSet dbResultSet = pstmt.executeQuery();
            while (dbResultSet.next()) {
                results.add(resultAdvertisement(dbResultSet));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return results;
    }

    @Override
    public boolean deleteAdvertisement(int advertisementId){
        boolean result = false;
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("DELETE from Advertisements where id = ? ");
            pstmt.setInt(1, advertisementId);
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
    public boolean saveAdvertisement(Advertisement advertisement, int productid){
        boolean result = false;
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Advertisements(fromDate, untilDate, advertisementsprice, advertisementtext, productid)values(?, ?, ?, ?, ?) ");
            pstmt.setDate(1, advertisement.getFromDate());
            pstmt.setDate(2, advertisement.getUntilDate());
            pstmt.setDouble(3, advertisement.getOfferPrice());
            pstmt.setString(4, advertisement.getAdvertisementText());
            pstmt.setInt(5, productid);
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
    public boolean updateAdvertisement(Advertisement advertisement, int productid){
        boolean result = false;
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("UPDATE Advertisements SET fromDate = ?, untilDate = ?, advertisementsprice = ?, advertisementtext = ?, productid = ? WHERE id = ?");
            pstmt.setDate(1, advertisement.getFromDate());
            pstmt.setDate(2, advertisement.getUntilDate());
            pstmt.setDouble(3, advertisement.getOfferPrice());
            pstmt.setString(4, advertisement.getAdvertisementText());
            pstmt.setInt(5, productid);
            pstmt.setInt(6, advertisement.getId());
            int res = pstmt.executeUpdate();
            if(res == 1){
                result = true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return result;
    }


    public Advertisement resultAdvertisement(ResultSet rs) {
        Advertisement advertisement = new Advertisement();
        try {
            int idResult = rs.getInt("id");
            String advertisementText = rs.getString("advertisementText");
            Date fromDate = rs.getDate("fromDate");
            Date untilDate = rs.getDate("untilDate");
            double offerPrice = rs.getInt("advertisementsPrice");
            int productId = rs.getInt("productid");

            advertisement = new Advertisement(idResult, fromDate, untilDate, offerPrice, advertisementText);
            advertisement.setProduct(proDao.productById(productId));
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return advertisement;
    }
}
