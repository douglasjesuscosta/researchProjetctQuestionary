package DAO;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.management.RuntimeErrorException;

import Reflection.ReflectionClass;
import annotation.Column;
import conection.ConnectionBd;
import conection.DBConnection;
import intefaces.IDao;
import intefaces.IModel;
import jdk.nashorn.internal.ir.ReturnNode;
import model.Model;
import util.Return;

public class GenericDao {

	protected static GenericDao a_dao;
	protected ConnectionBd a_connection;
	protected String sqlSearch;

	protected GenericDao() throws SQLException, ClassNotFoundException {
		initConnection();
	}

	public static GenericDao getGenericDao() throws SQLException, ClassNotFoundException {
		if (a_dao == null) {
			a_dao = new GenericDao();
			return a_dao;
		} else {
			return a_dao;
		}
	}

	public void initConnection() throws SQLException, ClassNotFoundException {
		a_connection = ConnectionBd.getConnctionBd();
	}

	public Return insert(IModel object) throws SQLException {
		Return retorno = new Return();
		
		String classe = ReflectionClass.getTableName(object);
		ArrayList<String> values = ReflectionClass.getColumnsAndValuesString(object);
		String tableName = ReflectionClass.getTableName(object);
				
				
		String sql = "insert into " + tableName;
			   sql += " (" + values.get(1) + ") " + "values";
		       sql +=  "(" + values.get(0) + ")";
		System.out.println(sql);

		try {
			PreparedStatement ps = a_connection.getPreparedStatement(sql);
			ReflectionClass.completeStatement(ps, object);
			System.out.println(ps.toString());
			ps.execute();
			retorno.setSucesso();
			
		} catch (Exception e) {
			retorno.setFracasso();
			throw new RuntimeException("<INSERT ERRO AT CREATE STATEMENT>" + e.getMessage());
		}
		
		return retorno;
	}

	public Return delete(IModel object) {
		Return retorno = new Return();

		Object pkvalue = ReflectionClass.getPkNameOrValue(object, true);
		String table = ReflectionClass.getTableName(object);
		String pkName = (String)ReflectionClass.getPkNameOrValue(object, false);
		String sql = "delete from " + table + " where " + pkName + "=" + pkvalue;
		PreparedStatement ps = a_connection.getPreparedStatement(sql);

		try {
			ps.execute();
			retorno.setSucesso();
		} catch (Exception e) {
			retorno.setFracasso();
			retorno.addMensagem("<DELETE - GENERICDAO: >" + e.getMessage());
		}
		return retorno;
	}
	
	public Return update(IModel object){
		Return retorno = new Return();
		
		String valuesvariables = "";
		Class<?> cls = object.getClass();
		Field[] fields = cls.getDeclaredFields();
		String sql1 = "";
		String tablename = ReflectionClass.getTableName(object);
		String pkname = (String) ReflectionClass.getPkNameOrValue(object, false);
		String pkvalue =  Integer.toString((Integer) ReflectionClass.getPkNameOrValue(object, true));
		
		
		for(int i =0; i<fields.length; i++){
			Column column = fields[i].getAnnotation(Column.class);
			
			if(column != null){
				if(column.name() != null){
					sql1 += "," + column.name() + "=" + "?";
				}
			}
		}
		
		String sql =  "update "  + tablename
					+ " set " 	 + sql1.substring(1)	
					+ " where "  + pkname + "=" + pkvalue;
		
		System.out.println(sql);
		PreparedStatement ps = a_connection.getPreparedStatement(sql);
		
		try {
			ReflectionClass.completeStatement(ps, object);
			ps.execute();
			retorno.setSucesso();
			
				
		} catch (Exception e) {
			e.printStackTrace();
			retorno.setFracasso();
		}
		
		return retorno;
	}
	
	public List<IModel> search(HashMap<String, Object> paramethers, IModel object) throws InstantiationException, IllegalAccessException, SQLException {
		String sql = selectSql(object) + " where ";
		Class<?> metaClass = object.getClass();
		Field[] fields = metaClass.getDeclaredFields();
		String sqlP = "";
		
		
		for(String column:paramethers.keySet()){
			sqlP = " OR ";
			Object paramether = paramethers.get(column);
				if(paramether.getClass().getTypeName().equals(String.class.getName())){
					sqlP +=  column + " LIKE " 
						 + "'%"+paramethers.get(column)+"%'";
				}else if(paramether.getClass().getTypeName().equals(Integer.class)){
					sqlP += column + " == " + paramethers.get(column);
				}
		}
		sql = sql + sqlP.substring(3);
		
		System.out.println(sql);
		
		PreparedStatement ps = a_connection.getPreparedStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		return createList(object, rs);
	}
		
		
	
	public IModel getObject(IModel object) {
		Object pkvalue = ReflectionClass.getPkNameOrValue(object, true);
		Object pkname = ReflectionClass.getPkNameOrValue(object, false);
		
		ArrayList<String> values = ReflectionClass.getColumnsAndValuesString(object);
		ArrayList<IModel> objects = new ArrayList<IModel>();
		
		String columnvalues = values.get(0);
		String columnames = values.get(1);
		
		String tablename = ReflectionClass.getTableName(object);

		String sql = "select " + columnames + " from " + tablename + " where " + pkname + " = " + pkvalue;
		System.out.println(sql);
		
		try {
			PreparedStatement ps = a_connection.getPreparedStatement(sql);
			ResultSet rs = ps.executeQuery();
			objects = createList(object, rs);
			return objects.get(0);
		} catch (Exception e) {
			throw new RuntimeException("<GETSINGLEOBJECT> Error at getting object: " + e.getMessage());
		}
	}
	

	public Integer getNextId(IModel tableObject) throws SQLException {
		Class<?> metaClass = tableObject.getClass();
		
		String pkName = ReflectionClass.getPkNameOrValue(tableObject, false).toString();
		String table = ReflectionClass.getTableName(tableObject);

		String sql = "select max(" + pkName + ") from " + table;
		System.out.println(sql);
		ResultSet rs = a_connection.executeSqlWithReturn(sql);
		rs.next();
		Integer numString = (Integer)rs.getObject(1);

		if (numString == null) {
			return 1;
		} else {
			return numString + 1;
		}
	}
	
	public String selectSql(IModel tableObject)
			throws SQLException, InstantiationException, IllegalAccessException {
		String sql = "select * from " + ReflectionClass.getTableName(tableObject);		
		return sql;
	}
	

	public List<IModel> listAll(IModel tableObject)
			throws SQLException, InstantiationException, IllegalAccessException {
		String sql = "select * from " + ReflectionClass.getTableName(tableObject);

		ResultSet rs = a_connection.executeSqlWithReturn(sql);
		return createList(tableObject, rs);
	}

	protected ArrayList<IModel> createList(IModel object, ResultSet rs)
		 {
		ArrayList<IModel> listObjects = new ArrayList<IModel>();
		try{
			while (rs.next()) {
				object = object.getClass().newInstance();
				object = getObjectWithOrWhitoutResulset(object, rs);
				listObjects.add(object);
			}
		}catch (Exception e) {
			throw new RuntimeException("<CREATELIST>Failed to create a list of objects: " + e.getMessage());
		}
		return listObjects;
	}
	
	private IModel getObjectWithOrWhitoutResulset(IModel object, ResultSet rs){
		if(rs != null){
			return getObject(object, rs);
		}
		return getObject(object);
	}
	
	
	private IModel getObject(IModel object, ResultSet rs) {
		
		try{
			for(int i =1; i <= rs.getMetaData().getColumnCount(); i++){
				Object value = rs.getObject(i);
				String colName = rs.getMetaData().getColumnName(i).toLowerCase();
				object = ReflectionClass.setObjectByTableName(value, colName, object, true);
			}
			return object;
		}catch (Exception e) {
			throw new RuntimeException("<GETOBJECT>Failed to get object: " + e.getMessage());
		}
		
	}
}
	
/*
	public boolean exist(Model<?> object) {
		String sql = "select " + object.getPkName() + " from " + object.getTable() + " where " + object.getPkName()
				+ " = " + object.getPk();
		try {
			ResultSet rs = a_connection.executeSqlWithReturn(sql);
			return rs.next();
		} catch (Exception e) {
			throw new RuntimeException("<EXIST>" + e.getMessage());
		}
	}

	public void update(Model<?> object) throws SQLException {
		String[] coluns = object.getColumnNames().split(",");
		String[] values = object.getColumnValues().split(",");
		String sql = "";

		for (int i = 0; i < coluns.length; i++) {
			sql += "," + coluns[i] + " = '" + values[i] + "'";
		}
		sql = sql.substring(1);
		sql = "update " + object.getTable() +  " set " + sql + " where " + object.getPkName() + " = " + object.getPk();
		
		a_connection.executeSqlWithoutReturn(sql);
	}

	public ArrayList<Model<?>> search(String search_criteria, Model object) {
		ArrayList<Model<?>> listaTable = new ArrayList<>();
		sqlSearch = "select * from " + object.getTable();
		return  listaTable;
	}

}*/