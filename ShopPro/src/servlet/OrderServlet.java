package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.javafx.sg.prism.web.NGWebView;

import bean.Order;
import bean.OrderDetail;
import bean.ShopCart;
import bean.User;
import service.OrderDetailService;
import service.OrderService;

/**
 * Servlet implementation class OrderServlet
 */
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private OrderService orderService = new OrderService();
	private OrderDetailService orderDetailService = new OrderDetailService();

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if("add".equals(action)) {
			String shouhuoren = request.getParameter("shouhuoren");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String send = request.getParameter("express");
			String pay = request.getParameter("bank");
			ShopCart shopCart = ShopCart.getShopCart(request.getSession());
			User user = (User) request.getSession().getAttribute("USER");
			Order order = new Order(send, pay, shopCart.getTotalPrice(), user.getId(), shouhuoren, phone, address);
			Order list = orderService.addOrder(order);
			request.setAttribute("order", list);
			request.getRequestDispatcher("/ShopCartServlet?action=pay&orderid="+list.getId()).forward(request, response);
		}else if ("queryAllOrderList".equals(action)) {
			List<Order> oList = orderService.getOrderList();
			List<OrderDetail> odList = orderDetailService.getDetailList();
			request.getSession().setAttribute("oList", oList);
			request.getSession().setAttribute("odList", odList);
			request.getRequestDispatcher("/back/order/orderlist.jsp").forward(request, response);
		}else if("queryDetail".equals(action)) {
			String did = request.getParameter("id");
			List<OrderDetail> odList = orderDetailService.getDetailById(did);
			Order order = orderService.getOrderById(did);
			request.setAttribute("odetail", odList);
			request.setAttribute("order", order);
			request.getRequestDispatcher("/back/order/order.jsp").forward(request, response);
		}else if("delete".equals(action)) {
			String did = request.getParameter("id");
			orderDetailService.deleteByOId(did);
			orderService.deleteById(did);
			request.getRequestDispatcher("OrderServlet?action=queryAllOrderList").forward(request, response);
		}
	}

}
