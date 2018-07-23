package utils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DButils<T> {
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;
	private ResultSetMetaData metaData ;
	
	public int Insert(String sql,Object...params) {
		try {
			//得到一个连接对象
			connection = DBManager.getConnection();
			//开启事务
			connection.setAutoCommit(false);
			//得到一个预编译对象
			ps = connection.prepareStatement(sql);
			//给参数赋值
			for(int i=0;i<params.length;i++){
				ps.setObject(i+1, params[i]);
			}
			//
			int count = ps.executeUpdate();
			System.out.println("受影响的行数: "+count);
			//提交事务
			connection.commit();
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
			//回滚
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			DBManager.closeAll(ps,connection);
		}
		return -1;
	}
	
	public List<T> Select(String sql,Class<T> clazz,Object...params){
		connection = DBManager.getConnection();
		List<T> list = new ArrayList<>();
		try {
			ps = connection.prepareStatement(sql);
			for(int i=0;i<params.length;i++) {
				ps.setObject(i+1, params[i]);
			}
			rs = ps.executeQuery();
			metaData = rs.getMetaData();
			int count = metaData.getColumnCount();
			while(rs.next()) {
				T ins = clazz.newInstance();//创建对象
				for(int i = 1; i<= count;i++) {
					//获取查询表列的名称 gtype_name
					String columnName = metaData.getColumnName(i);
					//获取查询表字段的值
					Object value = rs.getObject(i);
					Field field = clazz.getDeclaredField(columnName);
					//访问权限
					field.setAccessible(true);
					field.set(ins, value);
				}
				list.add(ins);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.closeAll(rs,ps,connection);
		}
		return list;
	}
	
	public T SelectOne(String sql,Class<T> clazz,Object...params){
		connection = DBManager.getConnection();
		try {
			ps = connection.prepareStatement(sql);
			for(int i=0;i<params.length;i++) {
				ps.setObject(i+1, params[i]);
			}
			rs = ps.executeQuery();
			metaData = rs.getMetaData();
			int count = metaData.getColumnCount();
			if(rs.next()) {
				T ins = clazz.newInstance();
				for(int i = 1; i<= count;i++) {
					//获取列的名称 gtype_name
					String columnName = metaData.getColumnName(i);
					//获取字段的值
					Object value = rs.getObject(i);
					Field field = clazz.getDeclaredField(columnName);
					//访问权限
					field.setAccessible(true);
					field.set(ins, value);
				}
				return ins;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBManager.closeAll(rs,ps,connection);
		}
		return null;
	}
	
	public int SelectCount(String sql,Object...objects) {
		connection = DBManager.getConnection();
		int count = -1;
		try {
			ps = connection.prepareStatement(sql);
			for (int i = 0; i<objects.length;i++) {
				ps.setObject(i+1, objects[i]);
			}
			rs=ps.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DBManager.closeAll(rs,ps,connection);
		}
		return count;
	}

}
