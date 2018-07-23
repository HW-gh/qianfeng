package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Address;
import bean.ShopCart;
import bean.User;
import bean.goodsInfo;
import service.AddressService;
import service.GoodsInfoService;

/**
 * Servlet implementation class PayServlet
 */
public class PayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AddressService addressService = new AddressService();
	private GoodsInfoService goodsInfoService = new GoodsInfoService();
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		PrintWriter out = response.getWriter();
		if("checkLogin".equals(action)) {
			User username = (User) request.getSession().getAttribute("USER");
			if(username!=null){
				request.getRequestDispatcher("/PayServlet?action=pay").forward(request, response);
			}
			else {
				request.getSession().setAttribute("next", "shopcar");
				out.write("<script>alert('请登陆之后再结算！');location.href='login.jsp';</script>");
			}
		}else if("pay".equals(action)) {
			User user = (User) request.getSession().getAttribute("USER");
			List<Address> addresses =  addressService.getAddressByUserId(user.getId());
			for (Address address : addresses) {
				if("是".equals(address.getIsdefault())) {
					request.getSession().setAttribute("address", address);
					break;
				}
			}
			request.getSession().setAttribute("addressList", addresses);
			response.sendRedirect("pay.jsp");
		}else if ("buynow".equals(action)) {
			String id = request.getParameter("id");
			String count = request.getParameter("pic");
			goodsInfo good = goodsInfoService.getGoodsInfoById(Long.parseLong(id));
			good.setCount(Integer.parseInt(count));
			ShopCart shopCart = ShopCart.getShopCart(request.getSession());
			shopCart.addGood(good);
			request.getRequestDispatcher("/PayServlet?action=checkLogin").forward(request, response);
		}
	}

}
