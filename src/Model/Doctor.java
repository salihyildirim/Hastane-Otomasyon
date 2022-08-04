package Model;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.Helper;

public class Doctor extends User {
	
	Statement st=null;
	ResultSet rs=null;
	Connection con=conn.connDb();
	PreparedStatement preparedStatement;
	
	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Doctor(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type);
		// TODO Auto-generated constructor stub
	}
	
	public boolean addWhour(int doctor_id,String doctor_name,String wDate) throws SQLException {
		boolean key=false;
		int count=0;
			try {
			st=con.createStatement(); 
			rs = st.executeQuery(
					"SELECT * FROM whour WHERE status='a' AND doctor_id = " + doctor_id + " AND wdate= '" + wDate + "'");
			while(rs.next()) {
				count++;
			}
			
			if(count==0) {
				preparedStatement=con.prepareStatement("INSERT INTO whour "+ "(doctor_id,doctor_name,wdate) VALUES" + "(?,?,?)");
				preparedStatement.setInt(1,doctor_id);
				preparedStatement.setString(2, doctor_name);
				preparedStatement.setString(3, wDate);
				preparedStatement.executeUpdate();
				key=true;
				}
			}
			catch (Exception e) {
			
			}
			
		return key;
	}
public ArrayList<Whour> getWhourList(int doctor_id) throws SQLException{	
		
		ArrayList<Whour> list=new ArrayList<>();
		Whour obj;
		
		try {	
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM whour WHERE status='a' AND doctor_id ="+doctor_id);
			while(rs.next()) {
				obj=new Whour(); 
				obj.setId(rs.getInt("id"));
				obj.setDoctor_id(rs.getInt("doctor_id"));
				obj.setDoctor_name(rs.getString("doctor_name"));
				obj.setWdate(rs.getString("wdate")); 
				obj.setStatus(rs.getString("status"));
				list.add(obj);
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	
		}
public boolean deleteWhour(int id)	 throws SQLException {
	
	boolean key=false;
	try {
		String query = "DELETE FROM whour WHERE id = ? ";
		st=con.createStatement();
		preparedStatement= con.prepareStatement(query);
		preparedStatement.setInt(1, id);
		preparedStatement.executeUpdate();
		key=true;
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return key;
}
	
}
