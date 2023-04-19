package lk.ijse.hibernate.dao.custom;

import lk.ijse.hibernate.dao.SuperDAO;
import lk.ijse.hibernate.entity.User;

import java.util.List;

public interface UserDAO extends SuperDAO {
    List<User> getUserDetails(String userName, String pwd) throws Exception;

}
