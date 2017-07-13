package Reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.management.RuntimeErrorException;


import DAO.GenericDao;
import annotation.Column;
import intefaces.IModel;
import model.Model;
import annotation.Table;


public class ReflectionClass {
	
	
	public static IModel setObjectByTableName(Object value, String fieldName, IModel model, boolean isNamefromDatabase) throws Exception{
		Class<?> metaClass = model.getClass();
		Field[] fields = metaClass.getDeclaredFields();
		
		for(int i =0; i < fields.length; i++){
			
			Column column = fields[i].getAnnotation(Column.class);
			
			if(column != null && ((isNamefromDatabase && column.name().equals(fieldName)) ||
								 (!isNamefromDatabase && column.name().equals(fields[i].getName())))){
									 
				String methodName = "set" + firstToUpper(fields[i].getName());
				Method method = findMethod(fields[i].getDeclaringClass(), fields[i].getClass().getSuperclass(), methodName, fields[i].getType());
				
				
				if(Model.class.isAssignableFrom(fields[i].getType())){
					try {
						Object fkClass = fields[i].getType().newInstance();
						IModel modelFk = (IModel) fkClass;
						modelFk.setPk(value);
						modelFk = GenericDao.getGenericDao().getObject(modelFk);
						method.invoke(model, modelFk);
						
					} catch (Exception e) {
						throw new RuntimeException("<SETOBJECTBYTABLENAME>FAil to instance a class");
					}
				}else{
					try{
						method.invoke(model, value);
					}catch (Exception e) {
						throw new RuntimeException("<SETOBJECTBYTABLENAME>Fail to set call methof for: " + "set" + fields[i].getName());
					}
				}
				break;
			}	
		}
		return model;
	}
	
	private static Method findMethod(Class<?> classe, Class<?> superclass, String methodName, Class<?>... parameterTypes) {
		try {
			return classe.getDeclaredMethod(methodName, parameterTypes);
		} catch (NoSuchMethodException e) {
			if (superclass.isAssignableFrom(classe.getSuperclass())) {
				return findMethod(classe.getSuperclass(), superclass, methodName, parameterTypes);
			} else {
				throw new RuntimeException("<FINDMETHOD> Can't find method: "+ methodName, e);
			}
		}
	}
	
	/**
	 * Class that will return a Iterator with the names of columns in the database
	 * @param IModel
	 * @return Iterator<String>
	 */
	
	public static ArrayList<String> getColumnsAndValuesString(IModel table){
		String columns = "";
		String values = "";
		Class<?> mTClass = table.getClass();
		ArrayList<String> retorno = new ArrayList<String>();
		
		Field[] fields = mTClass.getDeclaredFields();
		
		for(int i = 0; i < fields.length; i++){
			Column coluna = fields[i].getAnnotation(Column.class); 
			columns += "," + coluna.name();
			values += "," + "?"; 
		}
		
		retorno.add(values.substring(1));
		retorno.add(columns.substring(1));
		
		return retorno;
	}
	
	
	/**
	 * Class that will return a sting with all column names of database.
	 * @param table
	 * @return string
	 */
	
	public static String getColumnsNames(IModel table){
		String columns = "";
		String values = "";
		Class<?> mTClass = table.getClass();
		
		Field[] fields = mTClass.getDeclaredFields();
		
		for(int i = 0; i < fields.length; i++){
			Column coluna = fields[i].getAnnotation(Column.class); 
			columns += "," + coluna.name();
		}
		
		return columns.substring(1);
	}
	
	/**
	 * Class thar returns the name or value of a Primary key
	 * 
	 */
	
	public static Object getPkNameOrValue(IModel object, Boolean value){
		Class<?> metaClass = object.getClass();
		
		Field[] fields = metaClass.getDeclaredFields();
		
		for(int i = 0; i < fields.length; i++){
			Column column = fields[i].getAnnotation(Column.class);
			
			if(column !=null){
				if(column.pk()){
					if(value){
						return getFieldValueByDatabaseOrModelName(object,fields[i].getName());
					}else{
						return fields[i].getName();
					}
				}
			}
		}
		throw new RuntimeException("<GETPK> Classe: " + metaClass.getName() + " não contém PK declarada");
	}
	
	public static String getTableName(IModel object){
		
		Class<?> metaClass = object.getClass();
		Table annotation = metaClass.getAnnotation(Table.class);
		
		if(annotation != null){
			return annotation.name();
		}else{
			throw new RuntimeException("Classe: " + metaClass.getName() + "não possui anotação de tabela");
		}
	}
	
	public static Object getFieldValueByDatabaseOrModelName(IModel object, String fieldName){
		Class<?> metaClass = object.getClass();
		Field[] fields = metaClass.getDeclaredFields();
		Object objectReturn = null;
		
		try{
			for(int i = 0; i < fields.length; i++){
				Column column = fields[i].getAnnotation(Column.class);
				
				if((fields[i].getName().equals(fieldName))
					|| (column.name().equals(fieldName))){
					
					String MethodName = "get" +  firstToUpper(fieldName);
					Method method = metaClass.getMethod(MethodName);
					objectReturn = method.invoke(object);
					break;
				}
			}
		}catch(Exception e){
			throw new RuntimeException("<REFLECTION> Failed to load method");
		} 
		return objectReturn;
	}
	
	public static PreparedStatement completeStatement(PreparedStatement stmt, IModel object) throws Exception{
		Class<?> metaClass = object.getClass();
		Field[] fields = metaClass.getDeclaredFields();
		int fieldN = 1;
		
		for(int i =0; i < fields.length; i++){
			Column field = fields[i].getAnnotation(Column.class);
			
			if(field != null){
				
				Object value = getFieldValueByDatabaseOrModelName(object, fields[i].getName());
				
				if(value instanceof Model<?>){
					stmt.setObject(fieldN++, ((Model<?>)value).getPk());
				}else{
					stmt.setObject(fieldN++, value);
				}
			}else{
				
				throw new Exception("<COMPLETE STATEMENT> Field with no annotation");
			}
		}
		return stmt;
		
	}
	
	public static String firstToUpper(String palavra){
		palavra = palavra.substring(0,1).toUpperCase().concat(palavra.substring(1));
		return palavra;
	}
	
}
