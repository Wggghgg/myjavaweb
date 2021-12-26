package w.service;

import java.sql.SQLException;

import w.bean.User;


public interface UserService {
	 User selectUser(String username, String password) throws SQLException;

	 int insertUser(User user) throws SQLException;

	 User selectUserByUsername(String username) throws SQLException;
}
