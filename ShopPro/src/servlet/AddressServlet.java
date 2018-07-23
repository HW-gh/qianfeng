package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Address;
import bean.User;
import service.AddressService;

/**
 * Servlet implementation class AddressServlet
 */
public class AddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	AddressService addressService = new AddressService();
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if("addAddress".equals(action)) {
			Address address = addressService.compeletAddress(request);
			User user = (User) request.getSession().getAttribute("USER");
			addressService.addAddress(address);
			request.getSession().setAttribute("address", address);
			List<Address> lists = addressService.getAddressByUserId(user.getId());
			request.getSession().setAttribute("addressList", lists);
			request.getRequestDispatcher("pay.jsp").forward(request, response);
		}else if("update".equals(action)){
			String id = request.getParameter("id");
			int userid = ((User)request.getSession().getAttribute("USER")).getId();
			addressService.updateDefault(Integer.parseInt(id), userid);
			request.getRequestDispatcher("PayServlet?action=checkLogin").forward(request, response);
			
		}
	}

}
