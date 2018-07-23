package bean;

import java.util.Date;

public class User {

	private Integer	id ;
	private String	user_name;
	private String	nick_name;
	private Integer	pwd;
	private String	sex;
	private Date	birthday;
	private String	phone;
	private String	email;
	private Date	regist_date;
	private String	address;
	private String	codes;
	private String	isadmin;
	private Integer	lockstate;
	
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", user_name=" + user_name + ", nick_name=" + nick_name + ", pwd=" + pwd + ", sex="
				+ sex + ", birthday=" + birthday + ", phone=" + phone + ", email=" + email + ", regist_date="
				+ regist_date + ", address=" + address + ", codes=" + codes + ", isadmin=" + isadmin + ", lockstate="
				+ lockstate + "]";
	}
	public User(String user_name) {
		super();
		this.user_name = user_name;
	}
	public User(String user_name, Integer pwd) {
		super();
		this.user_name = user_name;
		this.pwd = pwd;
	}
	public User() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public Integer getPwd() {
		return pwd;
	}
	public void setPwd(Integer pwd) {
		this.pwd = pwd;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRegist_date() {
		return regist_date;
	}
	public void setRegist_date(Date regist_date) {
		this.regist_date = regist_date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCodes() {
		return codes;
	}
	public void setCodes(String codes) {
		this.codes = codes;
	}
	public String getIsadmin() {
		return isadmin;
	}
	public void setIsadmin(String isadmin) {
		this.isadmin = isadmin;
	}
	public Integer getLockstate() {
		return lockstate;
	}
	public void setLockstate(Integer lockstate) {
		this.lockstate = lockstate;
	}

	
	
}
