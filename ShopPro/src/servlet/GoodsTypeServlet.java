package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.goodsType;
import net.sf.json.JSONArray;
import service.GoodsTypeService;

/**
 * Servlet implementation class GoodsTypeServlet
 */
public class GoodsTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GoodsTypeService goodsTypeService = new GoodsTypeService();
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if("queryAllType".equals(action)) {
			List<goodsType> list = goodsTypeService.getTypeList();
			request.getSession().setAttribute("Types", list);
			request.getRequestDispatcher("/back/goodstype/goodstype.jsp").forward(request, response);
		}else if("queryFather".equals(action)) {
			
			request.getRequestDispatcher("/back/goodstype/goodsadd.jsp").forward(request, response);
		}else if("add".equals(action)){
			String typename = request.getParameter("typename");
			String fatherid = request.getParameter("fatherid");
			goodsTypeService.addGoodsType(typename,fatherid);
			request.getRequestDispatcher("/back/GoodsTypeServlet?action=queryAllType").forward(request, response);
		}else if("toUpdate".equals(action)){
			String typeId = request.getParameter("id");
			goodsType type = goodsTypeService.getTypeById(typeId);
			request.getSession().setAttribute("type", type);
			request.getRequestDispatcher("/back/goodstype/goodstypeupdate.jsp").forward(request, response);
		}else if("update".equals(action)) {
			String typeId = request.getParameter("typeid");
			String typeName = request.getParameter("typename");
			String fatherId = request.getParameter("fatherid");
			goodsTypeService.updateGoodsType(typeId,typeName,fatherId);
			request.getRequestDispatcher("/back/GoodsTypeServlet?action=queryAllType").forward(request, response);
		}else if("delete".equals(action)) {
			String typeId = request.getParameter("id");
			goodsTypeService.deleteTypeById(typeId);
			request.getRequestDispatcher("/back/GoodsTypeServlet?action=queryAllType").forward(request, response);
		}else if("getSmallListByBigId".equals(action)) {
			String tid = request.getParameter("bigId");
			List<goodsType> tList = goodsTypeService.getTypeByParentId(tid);
			JSONArray jsonArray = JSONArray.fromObject(tList);
			response.getWriter().write(jsonArray.toString());
		}
	}

}
