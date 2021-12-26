package w.dao;

import java.sql.SQLException;

import w.bean.User;

public interface UserDao {
	User selectUserByUsername(String username) throws SQLException;

    int insertUser(User user) throws SQLException;

}
