package bean;

import java.math.BigDecimal;

public class goodsInfo {
	private long id;
	private String goods_name;
	private String goods_description;
	private String goods_pic;
	private double goods_price;
	private long goods_stock;
	private double goods_price_off;
	private double goods_discount;
	private long goods_parentid;
	private long goods_fatherid;
	private String isdelete;
	private int count;
	
	public double getDanPrice() {
		BigDecimal count = new BigDecimal(String.valueOf(this.count));
		BigDecimal price = new BigDecimal(String.valueOf(goods_price_off));
		return price.multiply(count).doubleValue();
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGoods_description() {
		return goods_description;
	}
	public void setGoods_description(String goods_description) {
		this.goods_description = goods_description;
	}
	public String getGoods_pic() {
		return goods_pic;
	}
	public void setGoods_pic(String goods_pic) {
		this.goods_pic = goods_pic;
	}
	public double getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(double goods_price) {
		this.goods_price = goods_price;
	}
	public long getGoods_stock() {
		return goods_stock;
	}
	public void setGoods_stock(long goods_stock) {
		this.goods_stock = goods_stock;
	}
	public double getGoods_price_off() {
		return goods_price_off;
	}
	public void setGoods_price_off(double goods_price_off) {
		this.goods_price_off = goods_price_off;
	}
	public double getGoods_discount() {
		return goods_discount;
	}
	public void setGoods_discount(double goods_discount) {
		this.goods_discount = goods_discount;
	}
	public long getGoods_parentid() {
		return goods_parentid;
	}
	public void setGoods_parentid(long goods_parentid) {
		this.goods_parentid = goods_parentid;
	}
	public long getGoods_fatherid() {
		return goods_fatherid;
	}
	public void setGoods_fatherid(long goods_fatherid) {
		this.goods_fatherid = goods_fatherid;
	}
	public String getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}
	@Override
	public String toString() {
		return "goodsInfo [id=" + id + ", goods_name=" + goods_name + ", goods_description=" + goods_description
				+ ", goods_pic=" + goods_pic + ", goods_price=" + goods_price + ", goods_stock=" + goods_stock
				+ ", goods_price_off=" + goods_price_off + ", goods_discount=" + goods_discount + ", goods_parentid="
				+ goods_parentid + ", goods_fatherid=" + goods_fatherid + ", isdelete=" + isdelete + "]";
	}
	
}
