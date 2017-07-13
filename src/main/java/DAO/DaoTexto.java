package DAO;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import Reflection.ReflectionClass;
import annotation.Column;
import intefaces.IModel;
import model.Model;

public class DaoTexto extends GenericDao{
	
	private static DaoTexto daoT;
	
	private DaoTexto() throws ClassNotFoundException, SQLException{
		super();
	}
	
	public static DaoTexto getInstance() throws ClassNotFoundException, SQLException{
		if(daoT == null){
			return daoT = new DaoTexto();
		}else{
			return daoT;
		}
	}
	
	
}
