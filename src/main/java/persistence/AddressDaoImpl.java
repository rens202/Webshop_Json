package persistence;


import domain.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoImpl extends PostgresBaseDao implements AddressDao {

    @Override
    public List<Address> allAdress() {
        List<Address> results = new ArrayList<Address>();
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("Select * FROM Address ORDER BY id DESC");
            ResultSet dbResultSet = pstmt.executeQuery();
            while (dbResultSet.next()) {
                results.add(resultAdress(dbResultSet));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return results;
    }

    @Override
    public Address adressById(int id) {
        Address result = new Address();
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("Select * FROM Address WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet dbResultSet = pstmt.executeQuery();
            while (dbResultSet.next()) {
                result = resultAdress(dbResultSet);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean deleteAddress(int addressId){
        boolean result = false;
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("DELETE from Address where id = ? ");
            pstmt.setInt(1, addressId);
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
    public boolean saveAddress(Address address){
        boolean result = false;
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Address(street, number)values(?,?) ");
            pstmt.setString(1, address.getStreet());
            pstmt.setInt(2, address.getNumber());
            int res = pstmt.executeUpdate();
            if(res == 1){
                result = true;
            }
            pstmt = con.prepareStatement("select currval('address_id_seq'::regclass) curval");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                address.setId(rs.getInt("curval"));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateAddress(Address address){
        boolean result = false;
        try (Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("UPDATE address SET street = ?, number = ? WHERE id = ?");
            pstmt.setString(1, address.getStreet());
            pstmt.setInt(2, address.getNumber());
            pstmt.setInt(3, address.getId());
            int res = pstmt.executeUpdate();
            if(res == 1){
                result = true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return result;
    }

    public Address resultAdress(ResultSet rs) {
        Address address = new Address();
        try {
            int idResult = rs.getInt("id");
            String street = rs.getString("street");
            int number = rs.getInt("number");

            address = new Address(street, idResult, number);
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return address;
    }
}
