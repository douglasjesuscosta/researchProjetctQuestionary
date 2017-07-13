package conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**Class DBConnection**/
/*Class that is responsible to establish the communication with database*/

public class DBConnection {
	
	private static DBConnection a_connection;
	private static Connection con;
	private static Statement state;
	
	/**Constructor**/
	/*Singleton Constructor**/
	
	private DBConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("JDBC CARREGADO");
			
			con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/projeto_cliente", "postgres", "postgres");
			System.out.println("COnnection");
			
			state = con.createStatement();
			System.out.println("Statement");
			
		} catch (Exception e) {
			System.out.println("<DBConnection> Problem with the connection with database or JDBC driver");
		}
	}
	
	/**Method DBConnection**/
	/*Method that returns a instance of a class*/
	
	public static DBConnection getAnInstance(){
		if(a_connection == null){
			return a_connection = new DBConnection();
		}else{
			return a_connection;
		}
		
	}
	
	/**Method getDbConnection**/
	/*MEthod that returns a connection with database(unused)*/
	
	public Connection getDbConnection(){
		return con;
	}
	
	/**Method getStatement**/
	/*Method thar always returns a new instance of statement for an execution of query*/
	
	public Statement getStatement(){
		Statement s = null;
		
		try {
			s = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
	
	/**Methods eexecuteInsertSql, executeDeleteSql, executesSearchSql, executeUpdateSql**/
	/*This methods just execute an sql sent by parameter.*/
	
	
	public void executeInsertSql(String sql){
		Statement s = getStatement();
		try{
			s.executeUpdate(sql);
			
		}catch (Exception e){
			System.out.println("<EXECUTESQL>"+ e.getMessage());
		}
	}
	
	public int executeDeleteSql(String sql) throws SQLException{
		Statement s = getStatement();
		int affected_lines = 0;
		affected_lines = s.executeUpdate(sql);
	
		return affected_lines;
	}
	
	public ResultSet executesSearchSql(String sql){
		Statement s = getStatement();
		ResultSet result = null;
		
		try{
			result = s.executeQuery(sql);
		}catch (Exception e){
			System.out.println("<EXECUTESEARCH>" + e.getMessage());
		}
		return result;
	}
	
	public void executeUpdateSql(String sql){
		Statement s = getStatement();
		try{
			s.executeUpdate(sql);
		}catch(Exception e){
			System.out.println("<EXECUTEUPDATESQL>" + e.getMessage());
		}
	}

	public ResultSet executeQuery(String sql) throws SQLException {
		Statement s = getStatement();
		return s.executeQuery(sql);
	}
	
}
