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

/**
 * Servlet implementation class GoodsInfoServlet
 */
public class GoodsInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GoodsInfoService goodsInfoService = new GoodsInfoService();
	private GoodsTypeService goodsTypeService = new GoodsTypeService();
	
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String action = request.getParameter("action");
		if("queryById".equals(action)&&id!=null && !"".equals(id)) {
			goodsInfo good = goodsInfoService.getGoodsInfoById(Long.parseLong(id));
			request.setAttribute("good", good);
			request.getRequestDispatcher("/introduction.jsp").forward(request, response);;
		}else if ("queryAllGoodsInfo".equals(action)) {
			String page = request.getParameter("page");
			if(page==null || page.equals("")) {
				page="1";
			}
			List<goodsInfo> gList = goodsInfoService.getGoodsInfoList((Integer.parseInt(page)-1)*5,5);
			int count = goodsInfoService.getGoodsCount();
			int last = count%5==0? count/5 :count/5 +1;
			request.getSession().setAttribute("P", page);
			request.getSession().setAttribute("L", last);
			request.getSession().setAttribute("gList", gList);
			request.getRequestDispatcher("goods/goodsList.jsp").forward(request, response);
		}else if("delete".equals(action)){
			String gid = request.getParameter("id");
			goodsInfoService.deleteGoodsById(gid);
			request.getRequestDispatcher("back/GoodsInfoServlet?action=queryAllGoodsInfo").forward(request, response);
		}else if("queryType".equals(action)) {
			List<goodsType> tList = goodsTypeService.getTypeList();
			request.getSession().setAttribute("tList", tList);
			request.getRequestDispatcher("goods/goodsadd.jsp").forward(request, response);
		}else if ("add".equals(action)) {
			goodsInfoService.addGoods(request);
			request.getRequestDispatcher("back/GoodsInfoServlet?action=queryAllGoodsInfo").forward(request, response);
		}else if ("toUpdate".equals(action)) {
			String gid = request.getParameter("gid");
			goodsInfo good = goodsInfoService.getGoodsInfoById(Long.parseLong(gid));
			List<goodsType> tList = goodsTypeService.getTypeList();
			request.getSession().setAttribute("tList", tList);
			request.setAttribute("good", good);
			request.getRequestDispatcher("/back/goods/goodupdate.jsp").forward(request, response);
		}else if ("update".equals(action)) {
			goodsInfoService.addGoods(request);
			request.getRequestDispatcher("GoodsInfoServlet?action=queryAllGoodsInfo").forward(request, response);
		}
	}

}
