package dao;

import java.util.Date;
import java.util.List;

import bean.Order;
import utils.DButils;

public class OrderDao {

	private DButils<Order> orDButils = new DButils<>();
	
	/*private Integer	id;
	private String	o_sendtype;
	private String	o_paytype;
	private double	o_paycount;
	private Date	o_orderdate;
	private Integer	o_checkstate;
	private Date	o_checkdate;
	private String	o_checkperson;
	private Integer	userid;
	private String	o_shperson;
	private String	o_shphone;
	private String	o_shaddress;*/
	
	public void addOrder(Order o) {
		String sql = "insert t_order(id,o_sendtype,o_paytype,o_paycount,o_orderdate,userid,o_shperson,o_shphone,o_shaddress) value(?,?,?,?,?,?,?,?,?)";
		orDButils.Insert(sql, o.getId(),o.getO_sendtype(),o.getO_paytype(),o.getO_paycount(),o.getO_orderdate(),o.getUserid(),o.getO_shperson(),o.getO_shphone(),o.getO_shaddress());
	}

	public List<Order> getOrderList() {
		// TODO Auto-generated method stub
		String sql = "select * from t_order";
		return orDButils.Select(sql, Order.class);
	}

	public Order getOrderById(String did) {
		// TODO Auto-generated method stub
		String sql = "select * from t_order where id = ?";
		return orDButils.SelectOne(sql, Order.class, did);
	}

	public int deleteById(String did) {
		// TODO Auto-generated method stub
		String sql = "delete from t_order where id = ?";
		return orDButils.Insert(sql, did);
	}

	
	
}
