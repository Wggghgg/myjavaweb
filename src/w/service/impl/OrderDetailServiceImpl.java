package w.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import w.bean.*;
import w.dao.*;
import w.dao.impl.*;
import w.utils.DruidUtils;

import w.service.OrderDetailService;

public class OrderDetailServiceImpl implements OrderDetailService{
	private OrderDetailDao orderDetailDao = new OrderDetailDaoImpl();
    private CartDao        cartDao        = new CartDaoImpl();
    private GoodsDao       goodsDao       = new GoodsDaoImpl();
    private OrderDao       orderDao       = new OrderDaoImpl();

	@Override
    public List<OrderDetail> addOrderDetail(String username, String oid) throws SQLException {
		System.out.println("service:addorderdetail:oid:"+oid);
        List<OrderDetail> orderDetail = new ArrayList<>();
        List<Cart>        cartList    = cartDao.selectCartList(username);

        for (Cart cart : cartList) {
            OrderDetail o     = new OrderDetail();
            Goods       goods = goodsDao.selectgoodsbyid(cart.getPid());

            o.setOid(oid);
            o.setPid(cart.getPid());
            o.setNum(cart.getNum());
            o.setMoney(cart.getNum() * goods.getPrice());
            orderDetail.add(o);
        }

        System.out.println("service:addorderdetail:"+orderDetail);

        return orderDetail;
    }

    @Override
    public List<OrderDetail> selectOrderDetail(String oid) throws SQLException {
        List<OrderDetail> details = orderDetailDao.selectOrderDetail(oid);
        for (OrderDetail detail : details) {
            Goods goods = goodsDao.selectgoodsbyid(detail.getPid());
            detail.setGoods(goods);
        }
        return details;
    }

    @Override
    public void insertOrderDetail(List<OrderDetail> orderDetail) throws SQLException {
        DruidUtils.beginTx();

        try {
            for (OrderDetail detail : orderDetail) {
                orderDetailDao.insertOrderDetail(detail);
                System.out.println(detail);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        DruidUtils.endTx();
    }
}
