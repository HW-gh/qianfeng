package filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class CharacterEncdoingFilter implements Filter {


	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		//解决Post方式的请求乱码问题
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		MyEncodingWrapper requestWrapper = new MyEncodingWrapper(req);
		chain.doFilter(requestWrapper, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}

class MyEncodingWrapper extends HttpServletRequestWrapper{

	public MyEncodingWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String name) {
		//PayServlet?action=checkLogin&name=张三
		String value = super.getParameter(name);
		if(value!=null){
			if("GET".equals(super.getMethod())){
				try {
					value = new String(value.getBytes("iso-8859-1"), "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}
	
}
