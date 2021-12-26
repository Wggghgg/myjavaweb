package w.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;


import w.bean.*;
import w.dao.*;
import w.dao.impl.*;
import w.service.*;
import w.service.impl.*;
import w.utils.*;

public class OrderServiceImpl implements OrderService {

	private OrderDetailDao orderDetailDao = new OrderDetailDaoImpl();
    private CartDao        cartDao        = new CartDaoImpl();
    private GoodsDao       goodsDao       = new GoodsDaoImpl();
    private OrderDao       orderDao       = new OrderDaoImpl();
    
	@Override
    public List<Order> selectOrderAll() throws SQLException
    {
    	return orderDao.selectOrderAll();
    }
    
    @Override
	public Order addOrder(String username, String address) throws SQLException {
        Order      order      = new Order();
        String     id         = UUID.randomUUID().toString().replaceAll("-", "");
        Date       time       = new Date();
        List<Cart> cartList   = cartDao.selectCartList(username);
        double     totalPrice = 0;
        String status="1";

        for (Cart cart : cartList) {
            Goods goods = goodsDao.selectgoodsbyid(cart.getPid());

            totalPrice = totalPrice + cart.getNum() * goods.getPrice();
        }

        order.setMoney(totalPrice);
        order.setId(id);
        order.setStatus(status);
        order.setTime(time.toString());

        return order;
    }

    @Override
    public void inserOrderDetail(OrderDetail orderDetail) throws SQLException {
        orderDetailDao.insertOrderDetail(orderDetail);
    }

	@Override
    public void updateOrderStatus(String oid) throws SQLException {
        orderDao.updateOrder(oid);
    }

    @Override
    public List<Order> selectOrderList(String username) throws SQLException {
        return orderDao.selectOrderByUser(username);
    }

	@Override
    public Order selectOrderByOid(String oid) throws SQLException {
        return orderDao.selectOrderByOid(oid);
    }

    @Override
    public void insertOrder(Order order) throws SQLException {
        DruidUtils.beginTx();

        try {
            orderDao.insertOrder(order);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            DruidUtils.rollbackTx();

            throw throwables;
        }

        DruidUtils.endTx();
    }

    @Override
    public List<Order> showOrder(String username) throws SQLException {
        return orderDao.selectOrderByUser(username);
    }


}
