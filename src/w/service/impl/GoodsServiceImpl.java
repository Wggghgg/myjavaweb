package w.service.impl;

import java.util.List;

import w.bean.Goods;
import w.service.GoodsService;
import w.dao.GoodsDao;
import w.dao.impl.GoodsDaoImpl;

public class GoodsServiceImpl implements GoodsService {
	private GoodsDao gd=new GoodsDaoImpl();
	@Override
	public void addGoods(Goods goods) {
		// TODO Auto-generated method stub
		gd.insertGoods(goods);
	}

	@Override
	public void deleteGoodsById(int id) {
		// TODO Auto-generated method stub
		gd.deleteGoods(id);
	}

	
	@Override
	public void updateGoods(Goods goods,int i) {
		// TODO Auto-generated method stub
		gd.updateGoods(goods,i);
	}

	@Override
	public Goods selectById(int id) {
		// TODO Auto-generated method stub
		return gd.selectgoodsbyid(id);
	}

	@Override
	public List<Goods> selectAllGoods() {
		// TODO Auto-generated method stub
		return gd.selectallgoods();
	}

}
