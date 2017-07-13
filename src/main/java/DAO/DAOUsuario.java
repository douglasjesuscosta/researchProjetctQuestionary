package DAO;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import Reflection.ReflectionClass;
import annotation.Column;
import intefaces.IModel;
import intefaces.IPack;
import util.Return;

public class DAOUsuario extends GenericDao{
	
	public DAOUsuario() throws SQLException, ClassNotFoundException {
		super();
		
	}

	public Return autentificar(IModel object, IPack pack){
		

		String tablename = ReflectionClass.getTableName(object);
		Class<?> metaClass = object.getClass();
		Field[] fields = metaClass.getDeclaredFields();
		HashMap<String,Object> hp = pack.getSearchParamethers();
		Return retorno = pack.getReturn();
		
		
		String sql = " select * from " + tablename + " where ";
		String sql1 = "";
		
		for(int i = 0; i < fields.length; i++ ){
			Column column = fields[i].getAnnotation(Column.class);
			if(column != null){
				if(column.autenticable()){
					sql1 += " AND ";
					sql1 += column.name() + " = " + "'"+hp.get(column.name())+"'";
				}
			}
		}
		
		sql = sql + sql1.substring(4);
		PreparedStatement ps = a_connection.getPreparedStatement(sql);
		try {
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				retorno.setSucesso();
			}else{
				retorno.setFracasso();
			}
		} catch (SQLException e) {
			retorno.setFracasso();
			retorno.addMensagem(e.getMessage());
		}
		
		System.out.println(sql);
		
		return retorno;
	}

}
