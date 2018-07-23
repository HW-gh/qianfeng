package dao;

import java.util.List;

import bean.goodsType;
import utils.DButils;

public class GoodsTypeDao {

	private DButils<goodsType> gTutils = new DButils<goodsType>();
	
	public List<goodsType> getTypeList(){
		String sql = "select * from t_goods_type";
		List<goodsType> list = gTutils.Select(sql, goodsType.class);
		return list;
	}

	public int addGoodsType(String typename, String fatherid) {
		String sql = "insert t_goods_type(gtype_name,gtype_parentid) value(?,?)";
		return gTutils.Insert(sql, typename,fatherid);
	}

	public goodsType getTypeById(String typeId) {
		String sql = "select * from t_goods_type where id = ?";
		return gTutils.SelectOne(sql, goodsType.class, Long.parseLong(typeId));
	}

	public int updateGoodsType(String typeId, String typeName, String fatherId) {
		String sql = "update t_goods_type set gtype_name=?,gtype_parentid=? where id = ?";
		return gTutils.Insert(sql, typeName,fatherId,typeId);
	}

	public int deleteTypeById(long parseLong) {
		String sql = "delete from t_goods_type where id = ?";
		return gTutils.Insert(sql, parseLong);
	}

	public List<goodsType> getTypeByParentId(String tid) {
		// TODO Auto-generated method stub
		String sql = "select b.* from t_goods_type a,t_goods_type b where a.id = b.gtype_parentid and a.id=?";
		return gTutils.Select(sql, goodsType.class, tid);
	}
}
