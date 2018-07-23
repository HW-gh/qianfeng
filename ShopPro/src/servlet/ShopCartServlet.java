package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.OrderDetail;
import bean.ShopCart;
import bean.goodsInfo;
import service.GoodsInfoService;
import service.OrderDetailService;

/**
 * Servlet implementation class ShopCartServlet
 */
public class ShopCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GoodsInfoService goodsInfoService = new GoodsInfoService();
	private OrderDetailService orderDetailService = new OrderDetailService();
	
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShopCart shopCart = ShopCart.getShopCart(request.getSession());
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		String id = request.getParameter("id");
		String count = request.getParameter("count");
		if("add".equals(action)) {
			goodsInfo good = goodsInfoService.getGoodsInfoById(Long.parseLong(id));
			good.setCount(Integer.parseInt(count));
			shopCart.addGood(good);
		}else if("delete".equals(action)) {
			System.out.println(id);
			shopCart.deleteGood(Long.parseLong(id));
		}else if("update".equals(action)) {
			shopCart.updateGood(Long.parseLong(id), Integer.parseInt(count));
			String tag = request.getParameter("tag");
			if("frompay".equals(tag)) {
				out.write("1");
				return ;
			}
		}else if("pay".equals(action)) {
			String o_orderid = request.getParameter("orderid");
			for (goodsInfo good : shopCart.getgIList()) {
				OrderDetail oDetail = new OrderDetail();
				oDetail.setGoodsname(good.getGoods_name());
				oDetail.setO_orderid(Integer.parseInt(o_orderid));
				oDetail.setGoodsid(Integer.parseInt(String.valueOf(good.getId())));
				oDetail.setGoodsprice(good.getGoods_price_off());
				oDetail.setGoods_description(good.getGoods_description());
				oDetail.setGoodsnum(good.getCount());
				oDetail.setGoodspic(good.getGoods_pic());
				oDetail.setGoods_total_price(good.getDanPrice());
				orderDetailService.addOD(oDetail);
			}
			request.getSession().removeAttribute("ShopCart");
			request.getRequestDispatcher("success.jsp").forward(request, response);
			return ;
		}
		double totalPrice = shopCart.getTotalPrice();
		response.sendRedirect(request.getContextPath()+"/shopcar.jsp");
	}

}
