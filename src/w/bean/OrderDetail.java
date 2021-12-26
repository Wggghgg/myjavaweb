package w.bean;

import w.bean.Goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDetail {
	String id;	//订单细节id
	int pid;	//商品id
	int num;	//商品数量
	String oid;	//订单id
	double money;	//订单细节总额
	private Goods goods;	//订单细节商品
}
