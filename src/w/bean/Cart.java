package w.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cart {
    private String username;
    private int pid;	//商品id
    private int num;	//购买商品数量
    private double money;	//一条购物车记录总额
    private Goods goods;	//一条购物车记录包含的商品
}