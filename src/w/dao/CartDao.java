package w.dao;

import java.sql.SQLException;
import java.util.List;

import w.bean.Cart;

public interface CartDao {
	Cart selectCart(Cart cart) throws SQLException;	//查找一条购物车记录

    int updateCartNum(Cart cart) throws SQLException;	//更新购物车商品数量

    int insertCart(Cart cart) throws SQLException;	//插入购物车记录

	List<Cart> selectCartList(String username) throws SQLException;	//按用户名查找购物车所有记录

	int clearCartNum(String username) throws SQLException;	//清空购物车
	
	int deleteCart(String username,int pid) throws SQLException;	//删除购物车一条记录
}
