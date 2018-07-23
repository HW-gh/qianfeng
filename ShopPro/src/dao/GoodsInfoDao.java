package dao;

import java.util.List;

import bean.goodsInfo;
import utils.DButils;

public class GoodsInfoDao {
	private DButils<goodsInfo> gIutils = new DButils<goodsInfo>();

	public List<goodsInfo> getGoodsInfoList() {
		String sql = "select * from t_goods_info where isdelete='否'";
		List<goodsInfo> list = gIutils.Select(sql, goodsInfo.class);
		return list;
	}
	
	public List<goodsInfo> getGoodsInfoList(int page,int size) {
		String sql = "select * from t_goods_info where isdelete='否' limit ?,?";
		List<goodsInfo> list = gIutils.Select(sql, goodsInfo.class,page,size);
		return list;
	}
	
	public int getGoodsCount() {
		String sql = "select count(*) from t_goods_info where isdelete='否'";
		return gIutils.SelectCount(sql);
	}

	public goodsInfo getGoodsInfoById(long id) {
		String sql = "select * from t_goods_info where id = ?";
		goodsInfo good = gIutils.SelectOne(sql, goodsInfo.class, id);
		return good;
	}

	public int deleteGoodsById(String id) {
		String sql = "update t_goods_info set isdelete='是' where id = ?";
		return gIutils.Insert(sql, id);
	}

	public int addGoods(goodsInfo good) {
		String sql = "insert t_goods_info(goods_name,goods_description,goods_pic,goods_price,goods_stock,goods_price_off,goods_discount,goods_fatherid,goods_parentid,isdelete)"
				+ " value(?,?,?,?,?,?,?,?,?,?)";
		return gIutils.Insert(sql, good.getGoods_name(), good.getGoods_description(), good.getGoods_pic(),
				good.getGoods_price(), good.getGoods_stock(), good.getGoods_price_off(), good.getGoods_discount(),
				good.getGoods_fatherid(), good.getGoods_parentid(), good.getIsdelete());

	}

	public int updateGoods(goodsInfo good) {
		System.out.println("pic:"+good.getGoods_pic());
		if (good.getGoods_pic() != null) {
			String sql = "update t_goods_info set goods_name=?,goods_description=?,goods_pic=?,goods_price=?,goods_stock=?,goods_fatherid=?,goods_parentid=?,isdelete=? where id = ?";
			return gIutils.Insert(sql, good.getGoods_name(), good.getGoods_description(), good.getGoods_pic(),
					good.getGoods_price(), good.getGoods_stock(), good.getGoods_fatherid(), good.getGoods_parentid(),
					good.getIsdelete(), good.getId());
		} else {
			String sql = "update t_goods_info set goods_name=?,goods_description=?,goods_price=?,goods_stock=?,goods_fatherid=?,goods_parentid=?,isdelete=? where id = ?";
			return gIutils.Insert(sql, good.getGoods_name(), good.getGoods_description(), good.getGoods_price(),
					good.getGoods_stock(), good.getGoods_fatherid(), good.getGoods_parentid(), good.getIsdelete(),
					good.getId());
		}
	}
}
