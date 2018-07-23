package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import bean.User;
import dao.UserDao;

public class UserService {
	
	private UserDao userDao = new UserDao();
	
	public User  getUserByName(String name) {
		return userDao.getUserByName(name);
	}
	
	public int checkUsernameExits(String name) {
		return userDao.checkUsernameExits(name);
	}

	public int addUser(String username,String password) {
		return userDao.addUser(username, password);
	}

	public User compeletUser(HttpServletRequest request) {
		String nick_name = request.getParameter("nick_name");
		String sex = request.getParameter("sex");
		String birthday= request.getParameter("birthday");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String code = request.getParameter("code");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		User user = new User(username);
		user.setNick_name(nick_name);
		user.setSex(sex);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			user.setBirthday(sdf.parse(birthday));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		user.setPhone(phone);
		user.setAddress(address);
		user.setEmail(email);
		user.setCodes(code);
		return user;
	}

	public int updateUser(User user) {
		return userDao.updateUser(user);
	}

	public List<User> getUserList() {
		// TODO Auto-generated method stub
		return userDao.getUserList();
	}

	public int addCompleteUser(String name, String pwd, String nickname, String role, String sex, String birthday,
			String email, String phone, String address, String code) {
		return userDao.addCompleteUser(name,pwd,nickname,role,sex,birthday,email,phone,address,code);		
	}

	public int deleteUserById(String uId) {
		// TODO Auto-generated method stub
		return userDao.deleteUserById(uId);
	}

	public User getUserById(String uId) {
		// TODO Auto-generated method stub
		return userDao.getUserById(uId);
	}

	public int updateById(String uId,String user_name,String nick_name,String pwd,String sex,String birthday,String phone,String email,String address,String codes,String isadmin) {
		return userDao.updateById(uId, user_name, nick_name, pwd, sex, birthday, phone, email, address, codes, isadmin);
	}

	public int changeState(String uId,String state) {
		return userDao.changeState(uId, state);
	}
	
}
