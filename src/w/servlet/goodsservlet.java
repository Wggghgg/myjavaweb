package w.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import w.bean.Goods;
import w.service.GoodsService;
import w.service.impl.GoodsServiceImpl;

/**
 * Servlet implementation class goodsservlet
 */
@WebServlet("/goodsservlet")
public class goodsservlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GoodsService goodsService = new GoodsServiceImpl();
	public void add(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("add");
		String name=request.getParameter("name");
		double price=Double.parseDouble(request.getParameter("price"));
		int sales=Integer.parseInt(request.getParameter("sales"));
		int stock=Integer.parseInt(request.getParameter("stock"));
		String type=request.getParameter("type");
		String intro=request.getParameter("intro");
		String picture=request.getParameter("picture");
		String date=new Date().toString();
		Goods goods=new Goods(name,date,price,sales,stock,type,intro,picture);
		System.out.println(goods.toString());
		
		goodsService.addGoods(goods);
		System.out.print("list");
		List<Goods> goodslist = goodsService.selectAllGoods();
		try {
			request.setAttribute("goods",goodslist);
			request.getRequestDispatcher("editgoods.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		int id=Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		goodsService.deleteGoodsById(id);
		System.out.println("delete");
		System.out.print("list");
		List<Goods> goodslist = goodsService.selectAllGoods();
        request.setAttribute("goods",goodslist);
		try {
				request.getRequestDispatcher("editgoods.jsp").forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public void update(HttpServletRequest request, HttpServletResponse response) {
		int id=Integer.parseInt(request.getParameter("id"));
		String name=request.getParameter("name");
		double price=Double.parseDouble(request.getParameter("price"));
		int sales=Integer.parseInt(request.getParameter("sales"));
		int stock=Integer.parseInt(request.getParameter("stock"));
		String type=request.getParameter("type");
		String intro=request.getParameter("intro");
		String picture=request.getParameter("picture");
		String date=new Date().toString();
		Goods goods=new Goods(name,date,price,sales,stock,type,intro,picture);
		System.out.println(goods.toString());
		System.out.println("id"+id);
		goodsService.updateGoods(goods,id);
		System.out.print("updatelist");
		List<Goods> goodslist = goodsService.selectAllGoods();
		request.setAttribute("goods",goodslist);
		try {
			request.getRequestDispatcher("editgoods.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void getGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		Goods goods=goodsService.selectById(id);
		request.setAttribute("goods", goods);
		request.getRequestDispatcher("updategoods.jsp").forward(request, response);
	}
	public void showGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		Goods goods=goodsService.selectById(id);
		request.setAttribute("goods", goods);
		request.getRequestDispatcher("goodsdetail.jsp").forward(request, response);
	}
	public void list(HttpServletRequest request, HttpServletResponse response) {
		System.out.print("list");
		List<Goods> goodslist = goodsService.selectAllGoods();
        request.setAttribute("goods",goodslist);
        try {
			request.getRequestDispatcher("editgoods.jsp").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
