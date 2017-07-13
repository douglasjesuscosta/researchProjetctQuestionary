package conection;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import util.PropertiesFactory;

public class ConnectionBd {
	
	private Properties programProperties;
	private static ConnectionBd a_connection;
	private static Connection connection;
	
	private ConnectionBd() throws SQLException, ClassNotFoundException{
		Class.forName("org.postgresql.Driver");
		connection = DriverManager.getConnection("jdbc:postgresql://35.184.236.1:5432/questionario","postgres","m748596");
		
//		Class.forName("org.postgresql.Driver");
//		 String jdbcUrl = String.format(
//			        "jdbc:postgresql://google/%s?questionarioinstancia=%s&"
//			            + "socketFactory=com.google.cloud.sql.postgresql.SocketFactory",
//			        "questionario",
//			        "questionaarioinstancia");
//		  Connection connection = DriverManager.getConnection(jdbcUrl, "postgres", "m748596");
		
		//conectar();
	}
	
	public static PreparedStatement getPreparedStatement(String sql){
		
		try{
			return connection.prepareStatement(sql);
		}catch (Exception e) {
			throw new RuntimeException("<ConnectionBr> Problema ao retornar preparedstatement");
		}
	}
	
	public static ConnectionBd getConnctionBd() throws SQLException, ClassNotFoundException{
		if(a_connection == null){
			a_connection = new ConnectionBd();
		}
		return a_connection;
	}
	
	public Statement createStatement() throws SQLException{
		return connection.createStatement();
	}
	
	public void executeSqlWithoutReturn(String sql) throws SQLException{
		Statement st = connection.createStatement();
		st.execute(sql);
	}
	
	public ResultSet executeSqlWithReturn(String sql) throws SQLException{
		Statement st = createStatement();
		return st.executeQuery(sql);
	}
	
	  
	public Connection conectar(){
		// TODO: fill this in
	    // The instance connection name can be obtained from the instance overview page in Cloud Console
	    // or by running "gcloud sql instances describe <instance> | grep connectionName".
	    String instanceConnectionName = "quationario:us-central1:instanciaquestionario";
	    Connection conexao = null;

	    // TODO: fill this in
	    // The database from which to list tables.
	    String databaseName = "postgresql";

	    String username = "postgres";

	    // TODO: fill this in
	    // This is the password that was set via the Cloud Console or empty if never set
	    // (not recommended).
	    String password = "m748596";

//	    if (instanceConnectionName.equals("<insert_connection_name>")) {
//	      System.err.println("Please update the sample to specify the instance connection name.");
//	      System.exit(1);
//	    }
//
//	    if (password.equals("<insert_password>")) {
//	      System.err.println("Please update the sample to specify the mysql password.");
//	      System.exit(1);
//	    }

	    //[START doc-example]
	    String jdbcUrl = String.format(
	        "jdbc:postgresql://google/%s?cloudSqlInstance=%s&"
	            + "socketFactory=com.google.cloud.sql.postgres.SocketFactory",
	        databaseName,
	        instanceConnectionName);
	    
	    String url = "jdbc:postgresql://google/${questionario}?useSSL=false&amp;socketFactoryArg=${quationario:us-central1:instanciaquestionario}&amp;socketFactory=com.google.cloud.sql.postgres.SocketFactory&amp;user=${postgres}&amp;password=${m748596}";
	 
	    try {
			conexao = DriverManager.getConnection(jdbcUrl, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return conexao;
	   //[END doc-example]
	}
}
