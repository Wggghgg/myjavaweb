package w.service;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import w.bean.Cart;

public interface CartService {
	//查找某一条购物车记录商品数量
	int selectCartNum(Cart cart) throws SQLException;	
	//按用户显示所有购物车记录
    List<Cart> showCart(String username) throws SQLException, InvocationTargetException, IllegalAccessException;
    //更新某一条购物车记录商品数量
    String updateCartNum(Cart cart) throws SQLException;
    //清空购物车
    String clearCart(String username) throws SQLException;
    //按用户查找所有购物车记录
    List<Cart> selectCartListByun(String username) throws SQLException;
    //删除某条购物车记录
    String deleteCart(String username,int pid) throws SQLException;
}
