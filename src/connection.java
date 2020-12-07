import java.sql.*;


public class connection {
private Connection conn=null;

public connection(Connection conn) {
	this.conn = conn;
}

public Connection getConnection() {
	return this.conn;
}

public void connect() {
	 try {
		 Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		 String connString ="jdbc:mysql://127.0.0.1/QLCF?user=root&password=190220&useUnicode=true&characterEncoding=UTF-8";
		 conn=DriverManager.getConnection(connString);
		//System.out.println("Nối kết thành công"); 
		
	 }catch(Exception e) {
		 System.out.println("Nối kết không thành công"); 
	 }
}


public void closeConnect() {
	try {
		conn.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
}
