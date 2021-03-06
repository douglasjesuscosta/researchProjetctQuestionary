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
	//Class.forName("org.postgresql.Driver");
	//	connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/questionario","postgres","m748596");
	
//		Class.forName("org.postgresql.Driver");
//		 String jdbcUrl = String.format(
//			        "jdbc:postgresql://google/%s?questionarioinstancia=%s&"
//			            + "socketFactory=com.google.cloud.sql.postgresql.SocketFactory",
//			        "questionario",
//			        "questionaarioinstancia");
//		  Connection connection = DriverManager.getConnection(jdbcUrl, "postgres", "m748596");
		
		
		
		conectarMysql();
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
	
	  
	public void conectarMysql(){
		
		
		
		String url = System.getProperty("ae-cloudsql.cloudsql-database-url");
	    
		if (System
			  .getProperty("com.google.appengine.runtime.version").startsWith("Google App Engine/")) {
		      // Check the System properties to determine if we are running on appengine or not
		      // Google App Engine sets a few system properties that will reliably be present on a remote
		      // instance.
		      url = System.getProperty("cloudsql");
		      System.out.println("APPENGINE");
			  // Load the class that provides the new "jdbc:google:mysql://" prefix.
			  //Class.forName("com.mysql.jdbc.GoogleDriver");
		} else {
		      System.out.println("LOCAL");
		      //Class.forName("com.mysql.jdbc.Driver");
			 url = System.getProperty("cloudsql-local");
		     
		}
		
	    // Load the class that provides the new "jdbc:google:mysql://" prefix.
	    try {
	    	//Class.forName("com.mysql.jdbc.GoogleDriver");
			connection = DriverManager.getConnection(url);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	}
	
	public Connection conectarPostgres(){
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
	            "jdbc:postgresql://google/%s?socketFactory=com.google.cloud.sql.postgres.SocketFactory" +
	                "&socketFactoryArg=%s",
	            databaseName,
	            instanceConnectionName);
	    String url2 = "jdbc:google:postgres://${quationario:us-central1:instanciaquestionario}/${questionario}?user=${postgres}&amp;password=${m748596}";
	    String url = "jdbc:postgresql://google/${questionario}?useSSL=false&amp;socketFactoryArg=${quationario:us-central1:instanciaquestionario}&amp;socketFactory=com.google.cloud.sql.postgres.SocketFactory&amp;user=${postgres}&amp;password=${m748596}";
	 
	    try {
			conexao = DriverManager.getConnection(url2, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return conexao;
	   //[END doc-example]
	}
}
