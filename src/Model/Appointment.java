package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DbConnection;

public class Appointment {

	private int id,doctorID,hastaID;
	private String appDate;
	
	DbConnection conn=new DbConnection();
	Statement st=null;
	ResultSet rs=null;
	PreparedStatement preparedStatement;
	
	
	public Appointment(int id,int doctorID, int hastaID, String appDate) {
		super();
		this.id=id;
		this.doctorID = doctorID;
		this.hastaID = hastaID;
		this.appDate = appDate;
	}
	
	public Appointment() {}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}
	
	public int getHastaID() {
		return hastaID;
	}

	public void setHastaID(int hastaID) {
		this.hastaID = hastaID;
	}

	public String getAppDate() {
		return appDate;
	}

	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}
	
public ArrayList<Appointment> getHastaList(int hasta_id) throws SQLException{	
		
		ArrayList<Appointment> list=new ArrayList<>();
		Appointment obj;
		Connection con=conn.connDb();
		try {
			
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM appointment WHERE hasta_id =" + hasta_id);
			while(rs.next())	{
				obj=new Appointment();
				obj.setId(rs.getInt("id"));
				obj.setDoctorID(rs.getInt("doctor_id")); 
				obj.setHastaID(rs.getInt("hasta_id")); 
				obj.setAppDate(rs.getString("app_date"));
				list.add(obj); 
			}	
			
		}
		catch(SQLException e) {e.printStackTrace();}
		finally {	
			st.close();
			rs.close();
			con.close();
		}
		
		return list;
	
		}

public ArrayList<Appointment> getDoctorList(int doctor_id) throws SQLException{	
	
	ArrayList<Appointment> list=new ArrayList<>();
	Appointment obj;
	Connection con=conn.connDb();
	try {
		
		st=con.createStatement();
		rs=st.executeQuery("SELECT * FROM appointment WHERE doctor_id =" + doctor_id);
		while(rs.next())	{
			obj=new Appointment();
			obj.setId(rs.getInt("id"));
			obj.setDoctorID(rs.getInt("doctor_id")); 
			obj.setHastaID(rs.getInt("hasta_id")); 
			obj.setAppDate(rs.getString("app_date"));
			list.add(obj); 
		}	
		
	}
	catch(SQLException e) {e.printStackTrace();}
	finally {	
		st.close();
		rs.close();
		con.close();
	}
	
	return list;

	}
}
