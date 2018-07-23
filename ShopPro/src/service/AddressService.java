package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import bean.Address;
import bean.User;
import dao.AddressDao;

public class AddressService {

	private AddressDao addressDao = new AddressDao();
	
	public List<Address> getAddressByUserId(Integer id){
		return addressDao.getAddressByUserId(id);
	}

	public Address compeletAddress(HttpServletRequest request) {
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String shouhuoren = request.getParameter("shouhuoren");
		User user = (User) request.getSession().getAttribute("USER");
		Integer userid = user.getId();
		Address address2 = new Address(shouhuoren,phone,address,userid);
		return address2;
	}

	public int addAddress(Address address) {
		return addressDao.addAddress(address);
	}
	
	public int updateDefault(int id,int userid) {
		return addressDao.updateDefault(id, userid);
	}
	
}
