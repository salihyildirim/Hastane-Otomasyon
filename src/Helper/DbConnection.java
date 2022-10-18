package Helper;
import java.sql.*;

public class DbConnection {
	Connection connect;
	
	public DbConnection() {}	
		
	public Connection connDb() {
		try {
			this.connect=DriverManager.getConnection("jdbc:mariadb://localhost:3306/hospital?user=root&password=123456");
			return connect;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connect;
	}
	
	}


