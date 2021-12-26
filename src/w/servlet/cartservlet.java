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

import w.bean.Cart;
import w.bean.User;
import w.service.CartService;
import w.service.impl.CartServiceImpl;

/**
 * Servlet implementation class cartservlet
 */
@WebServlet("/cartservlet")
public class cartservlet extends BaseServlet {
	private CartService cartService = new CartServiceImpl();

	public void addCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String goodsIdStr = request.getParameter("pid");
        String numberStr  = request.getParameter("number");
        System.out.println(goodsIdStr+","+numberStr);
        User   user       = (User) request.getSession().getAttribute("user");
        System.out.println("user:"+user.toString());
        Cart   cart       = new Cart();

        if (user != null) {
            cart.setUsername(user.getUsername());
            cart.setPid(Integer.parseInt(goodsIdStr));
            cart.setNum(Integer.parseInt(numberStr));

            try {
                cartService.selectCartNum(cart);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    public void addCartAjax(HttpServletRequest request, HttpServletResponse response)
            throws IOException, SQLException, ServletException {
    	System.out.println("addCartAjax");
        String goodsIdStr = request.getParameter("pid");
        String numberStr  = request.getParameter("number");
        System.out.println(goodsIdStr+numberStr);
        User   user       = (User) request.getSession().getAttribute("user");
        Cart   cart       = new Cart();
        System.out.println(user.toString());
        if (user != null) {
            cart.setUsername(user.getUsername());
            cart.setPid(Integer.parseInt(goodsIdStr));

            int number = Integer.parseInt(numberStr);

            if (number == 0) {
                cartService.updateCartNum(cart);
            }

            cart.setNum(number);

            try {
                cartService.selectCartNum(cart);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        System.out.println(cart.toString());
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    public void clearCartAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

        try {
            cartService.clearCart(user.getUsername());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    public void selectCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user= (User) request.getSession().getAttribute("user");
        System.out.println(user.toString());
        List<Cart> cartList = null;

        try {
            cartList = cartService.showCart(user.getUsername());
        } catch (SQLException | InvocationTargetException | IllegalAccessException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(cartList);

        request.setAttribute("cartItems", cartList);

        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

}
