package ch.ricpr23.samples.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlDb {

	private String dbName;
	private String loginName;
	private String loginPwd;
	
	private Connection connection = null;
	private Statement stmt = null;

	private MysqlDb(String dbName, String loginName, String loginPwd) {
		this.dbName = dbName;
		this.loginName = loginName;
		this.loginPwd = loginPwd;
	}
	
	public static MysqlDb getMysqlDb(String dbName) {
		return new MysqlDb(dbName, "root", "blaBli123"); // TODO: read from cfg & hide/encrypt password...
	}

	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver"); //Loads the JDBC driver for MySql
	    connection = DriverManager.getConnection("jdbc:mysql://localhost/"+dbName+"?useSSL=false", loginName, loginPwd);
    	connection.setAutoCommit(true);		
        stmt = connection.createStatement();	        
	}
	
	public void close() throws SQLException {
    	if (stmt != null) {
    		stmt.close();
    	}
    	if (connection != null) {
    		connection.close();
    	}		
	}

	public ResultSet query(String sql) throws SQLException {
		return stmt.executeQuery(sql);
	}

	public void update(String sql) throws SQLException {
		stmt.executeUpdate(sql);
	}
	
	public void commit() throws SQLException {
		connection.commit();
	}
	
	public void rollback() throws SQLException {
		connection.rollback();
	}

	public void setAutoCommit(boolean autoCommit) throws SQLException {
		connection.setAutoCommit(autoCommit);
	}
}
