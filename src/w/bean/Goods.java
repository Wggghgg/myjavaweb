package w.bean;

import java.sql.Date;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Goods {
    private int id;	//商品id
    private String name;	//商品名称
    private String pubdate;	//商品发布日期
    private double price;	//商品价格
    private int sales;	//商品销量
    private int stock;	//商品库存
    private String type;	//商品种类
    private String intro;	//商品介绍
    private String picture;	//商品图片
    public Goods(String name,String pubdate,double price,int sales,int stock,String type,String intro,String picture)
    {
    	this.name=name;
    	this.pubdate=pubdate;
    	this.price=price;
    	this.sales=sales;
    	this.stock=stock;
    	this.type=type;
    	this.intro=intro;
    	this.picture=picture;
    }
    
}