package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Helper.Helper;

public class Hasta extends User {

	Statement st=null;
	ResultSet rs=null;
	Connection con=conn.connDb();
	PreparedStatement preparedStatement;
	
	public Hasta() {
	}

	public Hasta(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type);
		// TODO Auto-generated constructor stub
	}
	public boolean register(String tcno, String name, String passw) throws SQLException {
		boolean key=false;
		int count=0;
			try {
			st=con.createStatement(); 
			rs = st.executeQuery(
					"SELECT * FROM user WHERE tcno = "+tcno);
			while(rs.next()) {
				count++;
				Helper.showMsg("Girilen TC numarasýna ait bir kayýt bulunmaktadýr !");
			}
			
			if(count==0) {
				preparedStatement=con.prepareStatement("INSERT INTO user "+ "(tcno,password,name,type) VALUES" + "(?,?,?,?)");
				preparedStatement.setString(1,tcno);
				preparedStatement.setString(2, passw);
				preparedStatement.setString(3, name);
				preparedStatement.setString(4, "hasta");
				preparedStatement.executeUpdate();
				key=true;
				}
			}
			catch (Exception e) {
			
			}
			
		return key;
	}

}
