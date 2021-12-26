package w.dao;

import java.sql.SQLException;
import java.util.List;

import w.bean.Order;

public interface OrderDao {
    List<Order> selectOrderByUser(String username) throws SQLException;

    void insertOrder(Order order) throws SQLException;

    Order selectOrderMoney(String oid) throws SQLException;

    void updateOrder(String oid) throws SQLException;

    Order selectOrderByOid(String oid) throws SQLException;
    
    List<Order> selectOrderAll() throws SQLException;
}
