package dao;

import java.util.List;

import bean.User;
import utils.DButils;

public class UserDao {
	DButils<User> uDButils = new DButils<>();
	
	public User getUserByName(String name) {
		 String sql = "select * from t_user where user_name = ?";
		 return uDButils.SelectOne(sql, User.class, name);
	}
	
	public int checkUsernameExits(String name) {
		String sql = "select count(*) from t_user where user_name = ?";
		return uDButils.SelectCount(sql, name);
	}
	
	public int addUser(String username,String password) {
		String sql = "insert t_user(user_name,pwd) value(?,?)";
		return uDButils.Insert(sql, username,password);
	}

	public int updateUser(User user) {
		String sql = "update t_user set nick_name=?,sex=?,birthday=?,phone=?,email=?,regist_date=?,address=?,codes=?,isadmin=?,lockstate=? where user_name = ?";
		return uDButils.Insert(sql, user.getNick_name(),user.getSex(),user.getBirthday(),user.getPhone(),user.getEmail(),user.getRegist_date(),user.getAddress(),user.getCodes(),"否",0,user.getUser_name());
	}

	public List<User> getUserList() {
		String sql = "select * from t_user";
		return uDButils.Select(sql, User.class);
	}

	public int addCompleteUser(String name, String pwd, String nickname, String role, String sex, String birthday,
			String email, String phone, String address, String code) {
		String sql = "insert t_user(user_name,nick_name,pwd,sex,birthday,phone,email,regist_date,address,codes,isadmin,lockstate) value(?,?,?,?,?,?,?,now(),?,?,?,0)";
		return uDButils.Insert(sql, name,nickname,pwd,sex,birthday,phone,email,address,code,role);
	}

	public int deleteUserById(String uId) {
		// TODO Auto-generated method stub
		String sql = "delete from t_user where id = ?";
		return uDButils.Insert(sql,Integer.parseInt(uId));
	}

	public User getUserById(String uId) {
		// TODO Auto-generated method stub
		String sql = "select * from t_user where id = ?";
		return uDButils.SelectOne(sql, User.class, uId);
	}
	
	public int updateById(String uId,String user_name,String nick_name,String pwd,String sex,String birthday,String phone,String email,String address,String codes,String isadmin) {
		String sql = "update t_user set user_name=?,nick_name=?,pwd=?,sex=?,birthday=?,phone=?,email=?,address=?,codes=?,isadmin=? where id = ?";
		return uDButils.Insert(sql, user_name,nick_name,pwd,sex,birthday,phone,email,address,codes,isadmin,uId);
	}
	
	public int changeState(String uId,String state) {
		String sql = "update t_user set lockstate=? where id = ?";
		return uDButils.Insert(sql, state,uId);
	}
}
