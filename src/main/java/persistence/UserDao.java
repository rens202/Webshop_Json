package persistence;

import domain.User;

import java.util.List;

public interface UserDao {

    public List<User> findAll();
    public User findByUsername(String username);
    public String findRole(String username, String password);
    public boolean updateUser(User account);
    public boolean saveUser(User user);
    public boolean deleteUser(String username);

}
