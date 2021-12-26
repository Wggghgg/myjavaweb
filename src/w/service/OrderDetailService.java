package w.service;

import java.sql.SQLException;
import java.util.List;

import w.bean.OrderDetail;

public interface OrderDetailService {
	void insertOrderDetail(List<OrderDetail> orderDetail) throws SQLException;

    List<OrderDetail> selectOrderDetail(String oid) throws SQLException;

	List<OrderDetail> addOrderDetail(String username, String oid) throws SQLException;
}
