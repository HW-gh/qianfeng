package bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.taglibs.standard.tag.common.core.ForEachSupport;

import jdk.nashorn.internal.ir.Flags;

public class ShopCart {

	private List<goodsInfo> gIList = new ArrayList<goodsInfo>();
	
	public List<goodsInfo> getgIList() {
		return gIList;
	}

	public void setgIList(List<goodsInfo> gIList) {
		this.gIList = gIList;
	}

	public static ShopCart getShopCart(HttpSession session) {
		ShopCart shopCart =  (ShopCart) session.getAttribute("ShopCart");
		if(shopCart==null) {
			shopCart = new ShopCart();
			session.setAttribute("ShopCart", shopCart);
		}
		return shopCart;
	}
	
	public void addGood(goodsInfo good) {
		for (goodsInfo goodsInfo : gIList) {
			if(goodsInfo.getId()==good.getId()) {
				goodsInfo.setCount(goodsInfo.getCount()+good.getCount());
				return ;
			}
		}
		gIList.add(good);
	}
	
	public void deleteGood(long gid) {
		for(goodsInfo good : getgIList()){
			if(good.getId()==gid){
				//移除
				getgIList().remove(good);
				return;
			}
		}
	}
	
	public void updateGood(long id,int count) {
		for (goodsInfo goodsInfo : this.getgIList()) {
			if(goodsInfo.getId()==id) {
				goodsInfo.setCount(count);
				
			}
		}
	}
	
	public double getTotalPrice() {
		BigDecimal totalPrice = new BigDecimal(String.valueOf(0));
		for(goodsInfo good : getgIList()){
			BigDecimal danPrice = new BigDecimal(String.valueOf(good.getDanPrice()));
			totalPrice = totalPrice.add(danPrice);
		}
		return totalPrice.doubleValue();
	}
}
