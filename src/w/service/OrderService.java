package w.service;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import w.bean.Order;
import w.bean.OrderDetail;

public interface OrderService {

    List<Order> showOrder(String username) throws SQLException, InvocationTargetException, IllegalAccessException;

    void insertOrder(Order order) throws SQLException;

    void inserOrderDetail(OrderDetail orderDetail) throws SQLException;

    List<Order> selectOrderList(String username) throws SQLException;

	Order addOrder(String username, String address) throws SQLException;

	void updateOrderStatus(String oid) throws SQLException;

	Order selectOrderByOid(String oid) throws SQLException;

	List<Order> selectOrderAll() throws SQLException;
}
