package w.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import w.utils.DruidUtils;

import w.bean.Cart;
import w.dao.CartDao;

public class CartDaoImpl implements CartDao {
	private QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
	@Override
	public Cart selectCart(Cart cart) throws SQLException {
		// TODO Auto-generated method stub
		return qr.query("select username,pid, num from shop_cart where username=? and pid=?",
                new BeanHandler<Cart>(Cart.class),cart.getUsername(),cart.getPid());
	}

	@Override
	public int updateCartNum(Cart cart) throws SQLException {
		// TODO Auto-generated method stub
		return qr.update(DruidUtils.getConnection(),
                "update shop_cart set num=? where username=? and pid=?",
                cart.getNum(),
                cart.getUsername(),
                cart.getPid());
	}

	@Override
	public int insertCart(Cart cart) throws SQLException {
		// TODO Auto-generated method stub
		return qr.update(DruidUtils.getConnection(),
                "insert into shop_cart(username, pid, num) values (?, ?, ?)",
                cart.getUsername(),
                cart.getPid(),
                cart.getNum());
	}

	@Override
	public List<Cart> selectCartList(String username) throws SQLException {
		// TODO Auto-generated method stub
		return qr.query("select * from shop_cart where username=?",
                new BeanListHandler<Cart>(Cart.class),username);
	}


	@Override
	public int clearCartNum(String username) throws SQLException {
		// TODO Auto-generated method stub
		return qr.update("delete from shop_cart where username = ?", username);
	}

	@Override
	public int deleteCart(String username, int pid) throws SQLException {
		// TODO Auto-generated method stub
		return qr.update("delete from shop_cart where username = ? and pid=?", username,pid);
	}

}
