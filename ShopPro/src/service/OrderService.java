package service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import bean.Order;
import dao.OrderDao;

public class OrderService {
	
	private OrderDao orderDao = new OrderDao();

	public Order addOrder(Order order) {
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(calendar.YEAR);
		Random random = new Random();  
		String result="";  
		for (int i=0;i<5;i++)  
		{  
		    result+=random.nextInt(10);  
		}
		result = year+result;
		order.setId(Integer.parseInt(result));
		order.setO_orderdate(new Date());
		orderDao.addOrder(order);
		return order;
	}

	public List<Order> getOrderList() {
		// TODO Auto-generated method stub
		return orderDao.getOrderList();
	}

	public Order getOrderById(String did) {
		// TODO Auto-generated method stub
		return orderDao.getOrderById(did);
	}

	public int deleteById(String did) {
		// TODO Auto-generated method stub
		return orderDao.deleteById(did);
	}

}
