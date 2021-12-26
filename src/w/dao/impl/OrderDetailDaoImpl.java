package w.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import w.utils.DruidUtils;

import w.bean.OrderDetail;
import w.dao.OrderDetailDao;

public class OrderDetailDaoImpl implements OrderDetailDao{
	private QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
	public void insertOrderDetail(OrderDetail orderDetail) throws SQLException {
        queryRunner.update(DruidUtils.getConnection(),
                           "insert into shop_orderdetail(oid, pid, num, money) values (?, ?, ?, ?)",
                           orderDetail.getOid(),
                           orderDetail.getPid(),
                           orderDetail.getNum(),
                           orderDetail.getMoney());
    }

    @Override
    public List<OrderDetail> selectOrderDetail(String oid) throws SQLException {
        return queryRunner.query("select id, oid, pid, num, money from shop_orderdetail where oid = ?",
                                 new BeanListHandler<OrderDetail>(OrderDetail.class),
                                 oid);
    }

}
