package w.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailView {
    private String address;
    private Order order;
    private List<OrderDetail> orderDetails;
}