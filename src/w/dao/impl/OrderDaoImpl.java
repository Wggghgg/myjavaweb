package w.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import w.utils.DruidUtils;

import w.bean.Order;
import w.dao.OrderDao;

public class OrderDaoImpl implements OrderDao {

	private QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

    @Override
    public void insertOrder(Order order) throws SQLException {
        queryRunner.update(DruidUtils.getConnection(),
                           "insert into shop_order(id, username, money, status, time, address) values (?, ?, ?, ?, ?, ?)",
                           order.getId(),
                           order.getUsername(),
                           order.getMoney(),
                           order.getStatus(),
                           order.getTime(),
                           order.getAddress());
    }

    @Override
    public Order selectOrderByOid(String oid) throws SQLException {
        return queryRunner.query("select id, username, money, status, time, address from shop_order where id = ?",
                                 new BeanHandler<Order>(Order.class),
                                 oid);
    }


    @Override
    public Order selectOrderMoney(String oid) throws SQLException {
        return queryRunner.query("select money from shop_order where id = ?", new BeanHandler<Order>(Order.class), oid);
    }

    @Override
    public void updateOrder(String oid) throws SQLException {
        queryRunner.update("update shop_order set status = 2 where id = ?", oid);
    }

	@Override
	public List<Order> selectOrderAll() throws SQLException {
		// TODO Auto-generated method stub
		return queryRunner.query("select id, username, money, status, time, address from shop_order",
                new BeanListHandler<Order>(Order.class));
	}

	@Override
	public List<Order> selectOrderByUser(String username) throws SQLException {
		// TODO Auto-generated method stub
		return queryRunner.query("select id, username, money, status, time, address from shop_order where username = ?",
                new BeanListHandler<Order>(Order.class),
                username);
	}

}
