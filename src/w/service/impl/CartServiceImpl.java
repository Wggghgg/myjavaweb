package w.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import w.bean.Goods;

import w.utils.DruidUtils;

import w.dao.CartDao;
import w.dao.GoodsDao;
import w.dao.impl.CartDaoImpl;
import w.dao.impl.GoodsDaoImpl;

import w.bean.Cart;
import w.service.CartService;

public class CartServiceImpl implements CartService{
	 private CartDao  cartDao  = new CartDaoImpl();
	 private GoodsDao goodsDao = new GoodsDaoImpl();
	@Override
	public int selectCartNum(Cart cart) throws SQLException {
		DruidUtils.beginTx();

        int result = 0;

        try {
            Cart c = cartDao.selectCart(cart);

            // 更新数量
            if (c != null) {

                // 获取数据库中的商品数量
                int num = c.getNum();

                // 预留获取jsp中的传入商品增量
                int num1 = cart.getNum();

                // 计算商品 +1 或 -1
                num = num + num1;
                cart.setNum(num);
                result = cartDao.updateCartNum(cart);
            } else {    // 添加数据
                result = cartDao.insertCart(cart);
            }

            if (result <= 0) {
                DruidUtils.rollbackTx();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            DruidUtils.rollbackTx();

            throw throwables;
        }

        DruidUtils.endTx();

        return result;
	}

	@Override
	public List<Cart> showCart(String username) throws SQLException, InvocationTargetException, IllegalAccessException {
		// TODO Auto-generated method stub
		System.out.println("showCart");
		List<Cart> cartList = cartDao.selectCartList(username);
        Goods      goods    = null;

        for (Cart cart : cartList) {
            goods = goodsDao.selectgoodsbyid(cart.getPid());

            double totalPrice = cart.getNum() * goods.getPrice();

            cart.setMoney(totalPrice);
            cart.setGoods(goods);
        }

        Map<String, Object> map = new HashMap<>();

        map.put("goods", goods);
        map.put("cartList", cartList);
        BeanUtils.populate(goods, map);
        BeanUtils.populate(cartList, map);

        return cartList;
	}

	@Override
	public String updateCartNum(Cart cart) throws SQLException {
		// TODO Auto-generated method stub
		DruidUtils.beginTx();

        int result = 0;

        try {
            result = cartDao.updateCartNum(cart);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            DruidUtils.rollbackTx();

            throw throwables;
        }

        if (result <= 0) {
            return "修改失败";
        }

        DruidUtils.endTx();

        return "修改成功";
    }
	@Override
	public String clearCart(String username) throws SQLException {
		// TODO Auto-generated method stub
		DruidUtils.beginTx();

        int result = 0;

        try {
            result = cartDao.clearCartNum(username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            DruidUtils.rollbackTx();

            throw throwables;
        }

        if (result <= 0) {
            return "清空失败";
        }

        DruidUtils.endTx();

        return "清空成功";
	}

	@Override
	public List<Cart> selectCartListByun(String username) throws SQLException {
		// TODO Auto-generated method stub
		return cartDao.selectCartList(username);
	}

	@Override
	public String deleteCart(String username, int pid) throws SQLException {
		// TODO Auto-generated method stub
		DruidUtils.beginTx();

        int result = 0;

        try {
            result = cartDao.deleteCart(username,pid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            DruidUtils.rollbackTx();

            throw throwables;
        }

        if (result <= 0) {
            return "删除失败";
        }

        DruidUtils.endTx();

        return "删除成功";
	}

}
