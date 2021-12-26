package w.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import w.utils.DruidUtils;

import w.bean.Goods;
import w.dao.GoodsDao;

public class GoodsDaoImpl implements GoodsDao {
	private QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
	@Override
	public int insertGoods(Goods goods) {
		// TODO Auto-generated method stub
		try {
			return qr.update("insert into shop_goods(name,pubdate,price,sales,stock,type,intro,picture) values(?,?,?,?,?,?,?,?)",
							goods.getName(),
							goods.getPubdate(),
							goods.getPrice(),
							goods.getSales(),
							goods.getStock(),
							goods.getType(),
							goods.getIntro(),
							goods.getPicture());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteGoods(int id) {
		// TODO Auto-generated method stub
		try {
			return qr.update("delete from shop_goods where id=?",id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateGoods(Goods goods,int i) {
		// TODO Auto-generated method stub
		try {
			return qr.update("update shop_goods set name=?,pubdate=?,price=?,sales=?,stock=?,type=?,intro=?,picture=? where id=?",
							goods.getName(),
							goods.getPubdate(),
							goods.getPrice(),
							goods.getSales(),
							goods.getStock(),
							goods.getType(),
							goods.getIntro(),
							goods.getPicture(),
							i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Goods> selectallgoods() {
		// TODO Auto-generated method stub
		try {
			return qr.query("select id,name,pubdate,price,sales,stock,type,intro,picture from shop_goods", new BeanListHandler<Goods>(Goods.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Goods selectgoodsbyid(int id) {
		// TODO Auto-generated method stub
		try {
			return qr.query("select id,name,pubdate,price,sales,stock,type,intro,picture from shop_goods where id=?", new BeanHandler<Goods>(Goods.class),id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
