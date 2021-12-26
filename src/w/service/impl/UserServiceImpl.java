package w.service.impl;

import java.sql.SQLException;

import w.dao.UserDao;
import w.dao.impl.UserDaoImpl;

import w.bean.User;
import w.service.UserService;
import w.utils.DruidUtils;

public class UserServiceImpl implements UserService {
	private UserDao userDao = new UserDaoImpl();
	@Override
	public User selectUser(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		User user = userDao.selectUserByUsername(username);
		if (user == null) {
            return null;
        }
		if (user.getPassword().equals(password)) {
            return user;
        }
		return null;
	}

	@Override
	public int insertUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		DruidUtils.beginTx();
		int result=0;
		try {
			result=userDao.insertUser(user);
		}catch(SQLException throwables) {
			throwables.printStackTrace();
			DruidUtils.rollbackTx();
			throw throwables;
		}
		DruidUtils.endTx();
		return result;
	}

	@Override
	public User selectUserByUsername(String username) throws SQLException {
		// TODO Auto-generated method stub
		return userDao.selectUserByUsername(username);
	}

}
