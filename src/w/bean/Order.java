package w.bean;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	private String id;	//随机生成订单号
    private String username;	//订单用户
    private double money;	//订单总额
    private String status;	//订单付款发货状态
    private String time;	//订单生成时间
    private String address;	//订单发货地址
}
