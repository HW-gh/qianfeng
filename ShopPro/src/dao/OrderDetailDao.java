package dao;

import java.util.List;

import bean.OrderDetail;
import bean.goodsInfo;
import utils.DButils;

public class OrderDetailDao {
	private DButils<OrderDetail> odDButils = new DButils<>();

	public void addOD(OrderDetail od) {
		String sql = "insert t_order_detail(o_orderid,goodsid,goodsname,goodsprice,goods_description,goodsnum,goodspic,goods_total_price) values(?,?,?,?,?,?,?,?)";	
		odDButils.Insert(sql, od.getO_orderid(),od.getGoodsid(),od.getGoodsname(),od.getGoodsprice(),od.getGoods_description(),od.getGoodsnum(),od.getGoodspic(),od.getGoods_total_price());
	}

	public List<OrderDetail> getDetailList() {
		String sql = "select * from t_order_detail";
		return odDButils.Select(sql, OrderDetail.class);
	}

	public List<OrderDetail> getDetailById(String did) {
		String sql = "select * from t_order_detail where o_orderid = ?";
		return odDButils.Select(sql, OrderDetail.class,did);
	}

	public int deleteByOId(String did) {
		// TODO Auto-generated method stub
		String sql = "delete from t_order_detail where o_orderid = ?";
		return odDButils.Insert(sql, did);
	}
}
