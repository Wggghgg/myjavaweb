package w.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import w.bean.*;
import w.servlet.BaseServlet;
import w.service.*;
import w.service.impl.*;
import w.utils.*;


/**
 * Servlet implementation class orderservlet
 */
@WebServlet("/orderservlet")
public class orderservlet extends BaseServlet {
    private OrderService       orderService       = new OrderServiceImpl();
    private OrderDetailService orderDetailService = new OrderDetailServiceImpl();
    private GoodsService       goodsService       = new GoodsServiceImpl();
    private CartService        cartService        = new CartServiceImpl();
    private UserService        userService        = new UserServiceImpl();

    public void addOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        User   user   = (User) request.getSession().getAttribute("user");
        String address = request.getParameter("address");

        if (user == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);;
        }

        Order             order       = orderService.addOrder(user.getUsername(), address);
        List<OrderDetail> orderDetail = orderDetailService.addOrderDetail(user.getUsername(), order.getId());

        order.setUsername(user.getUsername());
        order.setAddress(address);
        orderService.insertOrder(order);
        orderDetailService.insertOrderDetail(orderDetail);
        cartService.clearCart(user.getUsername());
        request.setAttribute("order", order);

        request.getRequestDispatcher("orderSuccess.jsp").forward(request, response);
    }

    public void selectOrderView(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, InvocationTargetException, IllegalAccessException, ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response); ;
        }

        List<Cart> cartList = cartService.selectCartListByun(user.getUsername());

        for (Cart cart : cartList) {
            Goods  goods      = goodsService.selectById(cart.getPid());
            double totalPrice = goods.getPrice() * cart.getNum();

            cart.setMoney(totalPrice);
            cart.setGoods(goods);
        }

        //List<Address> addresses = addressService.selectAddressListByUid(user.getId());

        //request.setAttribute("addList", addresses);
        request.setAttribute("cartItems", cartList);

        request.getRequestDispatcher("order.jsp").forward(request, response);;
    }

    public void getOrderDetail(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, InvocationTargetException, IllegalAccessException, ServletException, IOException {
        User              user            = (User) request.getSession().getAttribute("user");

        if (user == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

        String            oid             = request.getParameter("oid");
        OrderDetailView   orderDetailView = new OrderDetailView();
        List<OrderDetail> orderDetail     = orderDetailService.selectOrderDetail(oid);
        Order             order           = orderService.selectOrderByOid(oid);

        orderDetailView.setOrderDetails(orderDetail);
        orderDetailView.setOrder(order);
        System.out.println(orderDetailView);

        request.setAttribute("vo", orderDetailView);

        request.getRequestDispatcher("orderDetail.jsp").forward(request, response);;
    }

    public void getOrderList(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        User        user      = (User) request.getSession().getAttribute("user");
        List<Order> orderList = orderService.selectOrderList(user.getUsername());
        List<String> address=null;
        System.out.println(user.toString());
        for (Order order : orderList) {
            address.add(order.getAddress());
        }
        System.out.println(address);
        //Address address = addressService.selectAddressById(aid);

        request.setAttribute("address", address);
        request.setAttribute("orderList", orderList);

        request.getRequestDispatcher("orderList.jsp").forward(request, response);
    }
    public void updateStatus(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException
    {
    	String oid=request.getParameter("oid");
    	User user= userService.selectUserByUsername(request.getParameter("user"));
    	orderService.updateOrderStatus(oid);
    	System.out.println(user.toString());
    	EmailUtils.sendMessage(user);
    	request.getRequestDispatcher("ordermanage.jsp").forward(request, response);
    }
}
