package w.dao;

import java.sql.SQLException;
import java.util.List;

import w.bean.Goods;

public interface GoodsDao {
    public int insertGoods(Goods goods);
    public int deleteGoods(int id);
    public int updateGoods(Goods goods,int i);
    public List<Goods> selectallgoods();
    public Goods selectgoodsbyid(int id);
}
