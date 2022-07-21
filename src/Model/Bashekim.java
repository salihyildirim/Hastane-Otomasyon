package Model;

import java.security.KeyStore.TrustedCertificateEntry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.management.Query;

public class Bashekim extends User{

	Statement st=null;
	ResultSet rs=null;
	Connection con=conn.connDb();
	PreparedStatement preparedStatement;
	
	public Bashekim(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type);
		// TODO Auto-generated constructor stub
	}
	public Bashekim() {
		
	}
	public ArrayList<User> getDoctorList() throws SQLException{	
		
		ArrayList<User> list=new ArrayList<>();
		User obj;

		
		
		try {	
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM user WHERE type='doktor'");
			while(rs.next()) {
				obj=new User(rs.getInt("id"),rs.getString("tcno"),rs.getString("name"),rs.getString("password"),rs.getString("type"));
				list.add(obj);
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	
		}
	
	public boolean addDoctor(String tcNo,String passw,String name) throws SQLException {
		
		boolean key=false;
		try {
			String query = "INSERT INTO user " + "(tcno,password,name,type) VALUES" + "(?,?,?,?)";
			st=con.createStatement();
			preparedStatement= con.prepareStatement(query);
			preparedStatement.setString(1, tcNo);
			preparedStatement.setString(2, passw);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, "doktor");
			preparedStatement.executeUpdate();
			key=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return key? false : true;
	}
public boolean deleteDoctor(int id)	 throws SQLException {
		
		boolean key=false;
		try {
			String query = "DELETE FROM user WHERE id = ? ";
			st=con.createStatement();
			preparedStatement= con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			key=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (key)
			return true;
		else return false;
	}
	
	
	}


