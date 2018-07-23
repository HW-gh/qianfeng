package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.goodsInfo;
import bean.goodsType;
import service.GoodsInfoService;
import service.GoodsTypeService;
import utils.DButils;

/**
 * Servlet implementation class DisplayServlet
 */
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GoodsTypeService goodsTypeService = new GoodsTypeService();
	private GoodsInfoService goodsInfoService = new GoodsInfoService();
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<goodsType> gTlist = goodsTypeService.getTypeList();
		System.out.println(gTlist);
		List<goodsInfo> gIlist = goodsInfoService.getGoodsInfoList();
		System.out.println(gIlist);
		request.getSession().setAttribute("gTlist", gTlist);
		request.getSession().setAttribute("gIlist", gIlist);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
