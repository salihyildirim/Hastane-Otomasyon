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
			//st=con.createStatement();
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
public boolean updateDoctor(int id,String tcno,String password,String name)	 throws SQLException {
	
	boolean key=false;	
	try {
		String query = "UPDATE user SET name = ?, tcno = ? , password = ? WHERE id = ? ";
		st=con.createStatement();
		preparedStatement= con.prepareStatement(query);
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, tcno);
		preparedStatement.setString(3, password);
		preparedStatement.setInt(4, id);
		preparedStatement.executeUpdate();
		key=true;
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	if (key)
		return true;
	else return false;
}
public boolean addWorker(int user_id,int clinic_id) throws SQLException {
	
	boolean key=false;
	int count=0;
	
	try {
		st=con.createStatement();
		rs = st.executeQuery("SELECT * FROM worker WHERE clinic_id= "+clinic_id+ " AND user_id=" + user_id);
		while(rs.next()) {
			count++;
		}
		if(count==0) {	
			preparedStatement= con.prepareStatement("INSERT INTO worker " + "(user_id,clinic_id) VALUES" + "(?,?)");
			preparedStatement.setInt(1, user_id);
			preparedStatement.setInt(2, clinic_id);
			preparedStatement.executeUpdate();
			key=true;
		}
	
		
	} catch (Exception e) {
		e.printStackTrace();
	}

	return key;
}
public ArrayList<User> getClinicDoctorList(int clinic_id) throws SQLException{	
	
	ArrayList<User> list=new ArrayList<>();
	User obj;

	try {	
		st=con.createStatement();
		rs=st.executeQuery("SELECT u.id,u.tcno,u.type,u.name,u.password FROM worker w LEFT JOIN user u ON w.user_id = u.id WHERE clinic_id ="+clinic_id);
		while(rs.next()) {
			obj=new User(rs.getInt("u.id"),rs.getString("u.tcno"),rs.getString("u.name"),rs.getString("u.password"),rs.getString("u.type"));
			list.add(obj);
		}
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list;

	}
	
	}


