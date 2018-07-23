package dao;

import java.util.List;

import bean.Address;
import utils.DButils;

public class AddressDao {
	
	private DButils<Address> dButils = new DButils<>();

	public List<Address> getAddressByUserId(Integer id){
		String sql = "select * from t_address where userid = ?";
		return dButils.Select(sql, Address.class,id);
	}

	public int addAddress(Address address) {
		String sql = "insert t_address(shouhuoren,phone,address,userid,isdefault) value(?,?,?,?,?)";
		return dButils.Insert(sql, address.getShouhuoren(),address.getPhone(),address.getAddress(),address.getUserid(),"否");
	}
	
	public int updateDefault(int id,int userid) {
		String sql = "update t_address set isdefault='否' where userid=?";
		dButils.Insert(sql, userid);
		sql = "update t_address set isdefault='是' where id = ?";
		return dButils.Insert(sql, id);
	}
	
}
