package w.dao;

import java.sql.SQLException;
import java.util.List;

import w.bean.OrderDetail;

public interface OrderDetailDao {
	void insertOrderDetail(OrderDetail orderDetail) throws SQLException;

    List<OrderDetail> selectOrderDetail(String oid) throws SQLException;
}
