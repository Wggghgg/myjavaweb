package w.service;

import java.util.List;

import w.bean.Goods;

public interface GoodsService {
	public void addGoods(Goods goods);
    public void deleteGoodsById(int id);
  
    public Goods selectById(int id);
    public List<Goods> selectAllGoods();
	public void updateGoods(Goods goods, int i);
}
