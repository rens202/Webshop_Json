package persistence;

import domain.Address;

import java.util.List;

public interface AddressDao {

    public List<Address> allAdress();
    public Address adressById(int id);
    public boolean deleteAddress(int addressId);
    public boolean saveAddress(Address address);
    public boolean updateAddress(Address address);
}
