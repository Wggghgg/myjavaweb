package w.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import w.utils.DruidUtils;

import w.bean.User;
import w.dao.UserDao;
public class UserDaoImpl implements UserDao {
	private QueryRunner qr=new QueryRunner(DruidUtils.getDataSource());
	@Override
	public User selectUserByUsername(String username) throws SQLException {
		// TODO Auto-generated method stub
		return qr.query("select username,nickname,password,email from shop_user where username=?",
				new BeanHandler<User>(User.class),
				username);
	}

	@Override
	public int insertUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		return qr.update(DruidUtils.getConnection(),
				"insert into shop_user(username,nickname, password, email) values (?,?, ?, ?)",
				user.getUsername(),
				user.getNickusername(),
				user.getPassword(),
				user.getEmail());
	}

}
