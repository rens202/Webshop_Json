package persistence;

import domain.Customer;

import java.util.ArrayList;

public interface CustomerDao {
    public ArrayList<Customer> allCustomer();
    public Customer customerById(int id);
    public boolean updateAddressCustomer(int customerId, int addressId);
    public boolean updateCustomer(Customer customer);
    public boolean saveCustomer(Customer customer);
    public boolean deleteCustomer(int customerId);
}
