package bean;

public class OrderDetail {
	private Integer	id;
	private Integer	o_orderid;
	private Integer	goodsid;
	private String	goodsname;
	private double	goodsprice;
	private String	goods_description;
	private Integer	goodsnum;
	private String	goodspic;
	private double	goods_total_price;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getO_orderid() {
		return o_orderid;
	}
	public void setO_orderid(Integer o_orderid) {
		this.o_orderid = o_orderid;
	}
	public Integer getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public double getGoodsprice() {
		return goodsprice;
	}
	public void setGoodsprice(double goodsprice) {
		this.goodsprice = goodsprice;
	}
	public String getGoods_description() {
		return goods_description;
	}
	public void setGoods_description(String goods_description) {
		this.goods_description = goods_description;
	}
	public Integer getGoodsnum() {
		return goodsnum;
	}
	public void setGoodsnum(Integer goodsnum) {
		this.goodsnum = goodsnum;
	}
	public String getGoodspic() {
		return goodspic;
	}
	public void setGoodspic(String goodspic) {
		this.goodspic = goodspic;
	}
	public double getGoods_total_price() {
		return goods_total_price;
	}
	public void setGoods_total_price(double goods_total_price) {
		this.goods_total_price = goods_total_price;
	}

}
