package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import service.UserService;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserService();

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if ("login".equals(action)) {
			User user = userService.getUserByName(username);
			if (user != null && user.getPwd() == Integer.parseInt(password) && user.getLockstate()==0) {
				String next = (String) request.getSession().getAttribute("next");
				request.getSession().setAttribute("USER", user);
				if (next != null && "shopcar".equals(next)) {
					response.sendRedirect("shopcar.jsp");
				}else {
					response.sendRedirect("home.jsp");
				}
			} else {
				out.write("<script>alert('登陆失败，请重新登陆!');location.href='login.jsp?username=" + username + "'</script>");
			}
		} else if ("checkUsernameExits".equals(action)) {
			int count = userService.checkUsernameExits(username);
			if (count > 0) {
				out.write("1");
			} else {
				out.write("0");
			}
			System.out.println(count);
		} else if ("regist".equals(action)) {
			String repwd = request.getParameter("passwordRepeat");
			if (password != null && repwd != null && password.equals(repwd)) {
				int result = userService.addUser(username, password);
				User user = new User(username,Integer.parseInt(password));
				if (result > 0) {
					request.setAttribute("username", username);
					request.getRequestDispatcher("information.jsp").forward(request, response);
				} else {
					out.write("<script>alert('注册失败，请重新注册';location.href='register.jsp')</script>");
				}
			} else {
				out.write("<script>alert('重复密码不一致，请重新注册';location.href='register.jsp')</script>");
			}
		} else if ("zhuxiao".equals(action)) {
			request.getSession().removeAttribute("USER");
			request.getSession().removeAttribute("next");
			//request.getSession().removeAttribute("ShopCart");
			response.sendRedirect("home.jsp");
		}else if("modifeRegistInfo".equals(action)) {
			User nowUser = (User) request.getSession().getAttribute("USER");
			if(nowUser!=null) {
				nowUser = userService.compeletUser(request);
				request.getSession().setAttribute("USER", nowUser);
				request.getRequestDispatcher("home.jsp").forward(request, response);
			}
			else {
				User user = userService.compeletUser(request);
				userService.updateUser(user);
				request.getRequestDispatcher("login.jsp?username="+user.getUser_name()).forward(request, response);
			}
		}else if("backlogin".equals(action)) {
			User backuser = userService.getUserByName(username);
			if(backuser!=null&&password.equals(backuser.getPwd().toString())) {
				request.getSession().setAttribute("USER", backuser);
				response.sendRedirect(request.getContextPath()+"/back/main.jsp");
			}else {
				request.getSession().setAttribute("username", username);
				response.getWriter().print("<script>alert('登陆失败，重新登陆');location.href='backLogin.jsp'</script>");
			}
		}else if ("queryAllUserInfo".equals(action)) {
			List<User> list = userService.getUserList();
			request.getSession().setAttribute("uList", list);
			request.getRequestDispatcher("/back/user/userinfo.jsp").forward(request, response);
		}else if ("add".equals(action)) {
			String name = request.getParameter("username");
			String pwd = request.getParameter("password");
			String nickname = request.getParameter("nickname");
			String role = request.getParameter("role");
			String sex = request.getParameter("sex");
			String birthday = request.getParameter("birthday");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String code = request.getParameter("code");
			userService.addCompleteUser(name, pwd,nickname,role,sex,birthday,email,phone,address,code);
			request.getRequestDispatcher("/back/UserServlet?action=queryAllUserInfo").forward(request, response);
		}else if ("delete".equals(action)) {
			String uId = request.getParameter("userid");
			userService.deleteUserById(uId);
			request.getRequestDispatcher("/back/UserServlet?action=queryAllUserInfo").forward(request, response);
		}else if ("toUpdate".equals(action)) {
			String uId = request.getParameter("userid");
			User user = userService.getUserById(uId);
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("/back/user/updateuser.jsp").forward(request, response);
		}else if ("update".equals(action)) {
			String id = request.getParameter("id");
			String nickname = request.getParameter("nickname");
			String role = request.getParameter("role");
			String sex = request.getParameter("sex");
			String birthday = request.getParameter("birthday");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String code = request.getParameter("code");
			userService.updateById(id, username, nickname, password, sex, birthday, phone, email, address, code, role);
			request.getRequestDispatcher("/back/UserServlet?action=queryAllUserInfo").forward(request, response);
		}else if ("Lock".equals(action)) {
			String uId = request.getParameter("userid");
			String state = request.getParameter("state");
			userService.changeState(uId, state);
			request.getRequestDispatcher("/back/UserServlet?action=queryAllUserInfo").forward(request, response);
		}else if ("backExit".equals(action)) {
			request.getSession().removeAttribute("USER");
			request.getRequestDispatcher("/back").forward(request, response);
		}
	}

}
