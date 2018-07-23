package service;

import java.util.List;

import bean.goodsType;
import dao.GoodsTypeDao;

public class GoodsTypeService {
	private GoodsTypeDao goodsTypeDao = new GoodsTypeDao();
	
	public List<goodsType> getTypeList(){
		return goodsTypeDao.getTypeList();
	}

	public void addGoodsType(String typename, String fatherid) {
		goodsTypeDao.addGoodsType(typename,fatherid);
	}

	public goodsType getTypeById(String typeId) {
		return goodsTypeDao.getTypeById(typeId);
	}

	public void updateGoodsType(String typeId, String typeName, String fatherId) {
		goodsTypeDao.updateGoodsType(typeId,typeName,fatherId);
	}

	public int deleteTypeById(String typeId) {
		return goodsTypeDao.deleteTypeById(Long.parseLong(typeId));
	}

	public List<goodsType> getTypeByParentId(String tid) {
		// TODO Auto-generated method stub
		return goodsTypeDao.getTypeByParentId(tid);
	}

}
