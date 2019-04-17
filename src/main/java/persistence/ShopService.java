package persistence;


import domain.*;

import java.util.List;

public class ShopService {
    private AccountDao accDao = new AccountDaoImpl();
    private AddressDao addDao = new AddressDaoImpl();
    private AdvertisementDao advDao = new AdvertisementDaoImpl();
    private CategoryDao catDao = new CategoryDaoImpl();
    private CustomerDao cusDao = new CustomerDaoImpl();
    private OrderDao ordDao = new OrderDaoImpl();
    private ProductDao proDao = new ProductDaoImpl();
    private OrderLineDao ordlDao = new OrderLineDaoImpl();
    private UserDao userDao = new UserDaoImpl();


    public List<Category> getAllCategories() {
        return catDao.findAll();
    }

    public List<Product> getAllProducts() {
        return proDao.allProducts();
    }

    public List<Advertisement> getAllAdvertisements() {
        return advDao.allAdvertisement();
    }

    public List<Customer> getAllCustomers() {
        return cusDao.allCustomer();
    }

    public List<Order> getAllOrders() {
        return ordDao.allOrders();
    }

    public Product getProduct(int id) {
        return proDao.productById(id);
    }

    public Category getCategory(int id) {
        return catDao.categoryByCatId(id);
    }

    public Customer getCustomer(int id) {
        return cusDao.customerById(id);
    }

    public Order getOrder(int id) {
        return ordDao.orderById(id);
    }

    public User getUser(String username) {
        return userDao.findByUsername(username);
    }

    public boolean saveProduct(Product product) {
        return proDao.saveProduct(product);
    }

    public boolean updateProduct(Product product) {
        return proDao.updateProduct(product);
    }

    public boolean saveNewUser(User user) {
        Customer customer = user.getCustomer();
        addDao.saveAddress(customer.getAddress());
        cusDao.saveCustomer(customer);
        accDao.saveAccount(customer.getAccount());
        return userDao.saveUser(user);
    }

    public boolean deleteProduct(int id) {
        return proDao.deleteProduct(id);
    }
}
