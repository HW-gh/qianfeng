package service;

import java.util.List;

import bean.OrderDetail;
import bean.goodsInfo;
import dao.OrderDetailDao;

public class OrderDetailService {
	
	private OrderDetailDao orderDetailDao = new OrderDetailDao();

	public void addOD(OrderDetail od) {
		orderDetailDao.addOD(od);
	}

	public List<OrderDetail> getDetailList() {
		// TODO Auto-generated method stub
		return orderDetailDao.getDetailList();
	}

	public List<OrderDetail> getDetailById(String did) {
		// TODO Auto-generated method stub
		return orderDetailDao.getDetailById(did);
	}

	public int deleteByOId(String did) {
		// TODO Auto-generated method stub
		return orderDetailDao.deleteByOId(did);
	}

}
