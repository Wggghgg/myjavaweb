package w.servlet;

import w.bean.Goods;
import w.bean.User;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import w.servlet.BaseServlet;

import w.service.UserService;
import w.service.impl.UserServiceImpl;
import w.service.GoodsService;
import w.service.impl.GoodsServiceImpl;

/**
 * Servlet implementation class userservlet
 */
@WebServlet("/userservlet")
public class userservlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserService    userService    = new UserServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		User user=null;
		try {
			user=userService.selectUser(username, password);
		}catch(SQLException throwables) {
			throwables.printStackTrace();
		}
		if(user==null) {
			request.getSession().setAttribute("msg", "用户名或密码错误");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		request.getSession().setAttribute("user", user);
		// 收取自动登录参数
        String auto = request.getParameter("auto");

        // 自动登录
        if (auto != null) {
            Cookie autoCookie = new Cookie("auto", user.getUsername() + "#" + user.getPassword());

            autoCookie.setMaxAge(60 * 60 * 24 * 14);
            response.addCookie(autoCookie);
        }
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		Cookie autoCookie = new Cookie("auto", "");
		autoCookie.setMaxAge(0);
		response.addCookie(autoCookie);
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}
	public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		System.out.println(username+password);
		User user=null;
		try {
			user=userService.selectUserByUsername(username);
		}catch(SQLException throwables) {
			throwables.printStackTrace();
		}
		if(user==null)
		{
			System.out.println("user==null");
			String email=request.getParameter("email");
			String nickname=request.getParameter("nickname");
			user=new User(username,nickname,password,email);
			int result=0;
			try {
				result=userService.insertUser(user);
			}catch(SQLException throwables) {
				throwables.printStackTrace();
			}
			if(result<=0) {
				request.setAttribute("registerMsg", "注册失败");
				request.getRequestDispatcher("register.jsp").forward(request,response);
			}
			request.setAttribute("registerMsg", "注册成功");
			request.getRequestDispatcher("login.jsp").forward(request,response);
		}
		request.setAttribute("registerMsg", "用户名被使用");
		request.getRequestDispatcher("register.jsp").forward(request,response);
	}

}
