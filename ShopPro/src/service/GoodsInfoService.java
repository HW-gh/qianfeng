package service;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import bean.goodsInfo;
import dao.GoodsInfoDao;

public class GoodsInfoService {

	private GoodsInfoDao goodsInfoDao = new GoodsInfoDao();

	public List<goodsInfo> getGoodsInfoList(int page,int size) {
		return goodsInfoDao.getGoodsInfoList(page,size);
	}
	
	public List<goodsInfo> getGoodsInfoList() {
		return goodsInfoDao.getGoodsInfoList();
	}

	public int getGoodsCount() {
		return goodsInfoDao.getGoodsCount();
	}

	
	public goodsInfo getGoodsInfoById(long id) {
		return goodsInfoDao.getGoodsInfoById(id);
	}

	public int deleteGoodsById(String id) {
		return goodsInfoDao.deleteGoodsById(id);
	}

	public int addGoods(HttpServletRequest request) {
		goodsInfo good = new goodsInfo();
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> ite = items.iterator();
				
				while (ite.hasNext()) {
					FileItem item = ite.next();
					if (item.isFormField()) {
						String fieldName = item.getFieldName();
						if ("goods_name".equals(fieldName)) {
							good.setGoods_name(item.getString("utf-8"));
						} else if ("goods_description".equals(fieldName)) {
							good.setGoods_description(item.getString("utf-8"));
						} else if ("goods_price".equals(fieldName)) {
							good.setGoods_price(Double.parseDouble(item.getString()));
						} else if ("goods_stock".equals(fieldName)) {
							good.setGoods_stock(Long.parseLong(item.getString("utf-8")));
						} else if ("goods_price_off".equals(fieldName)) {
							good.setGoods_price_off(Double.parseDouble(item.getString("utf-8")));
						} else if ("goods_discount".equals(fieldName)) {
							good.setGoods_discount(Double.parseDouble(item.getString("utf-8"))/10);
						} else if ("goods_parentid".equals(fieldName)) {
							good.setGoods_parentid(Long.parseLong(item.getString("utf-8")));
						} else if ("goods_fatherid".equals(fieldName)) {
							good.setGoods_fatherid(Long.parseLong(item.getString("utf-8")));
						} else if ("id".equals(fieldName)) {
							good.setId(Long.parseLong(item.getString("utf-8")));
						}
					} else {
							String fileName = item.getName();// 文件名称
						
							System.out.println(fileName);
							if(fileName!=null&&!"".equals(fileName)) {
								String suffix = fileName.substring(fileName.lastIndexOf('.'));
								//System.out.println("扩展名：" + suffix);
	
								// 新文件名（唯一）
								String newFileName = new Date().getTime() + suffix;
								//使用原文件名
								//String newFileName = fileName;
								System.out.println("新文件名：" + newFileName);
								good.setGoods_pic(newFileName);
								File file = new File(request.getSession().getServletContext().getRealPath("")+"/images/" + newFileName);
								//File file2 = new File("D:\\Program Files\\feiq\\Recv Files\\project\\day2\\文档\\ShopPro\\WebContent\\images\\"+newFileName);
								System.out.println(file.getAbsolutePath());
								//System.out.println(file2.getAbsolutePath());
								
								//item.write(file);
								item.write(file);
							}
					}
				}
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		good.setIsdelete("否");
		if(good.getId()==0) {
			return goodsInfoDao.addGoods(good);
		}else {
			return goodsInfoDao.updateGoods(good);
		}
		
	}
}
