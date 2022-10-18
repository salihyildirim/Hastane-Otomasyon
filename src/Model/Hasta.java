package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import Helper.Helper;

public class Hasta extends User {

	Statement st = null;
	ResultSet rs = null;
	Connection con = conn.connDb();
	PreparedStatement preparedStatement;

	public Hasta() {
	}

	public Hasta(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type);
		// TODO Auto-generated constructor stub
	}

	public boolean register(String tcno, String name, String passw) throws SQLException {
		boolean key = false;
		int count = 0;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE tcno = " + tcno);
			while (rs.next()) {
				count++;
				Helper.showMsg("Girilen TC numarasýna ait bir kayýt bulunmaktadýr !");
			}

			if (count == 0) {
				preparedStatement = con
						.prepareStatement("INSERT INTO user " + "(tcno,password,name,type) VALUES" + "(?,?,?,?)");
				preparedStatement.setString(1, tcno);
				preparedStatement.setString(2, passw);
				preparedStatement.setString(3, name);
				preparedStatement.setString(4, "hasta");
				preparedStatement.executeUpdate();
				key = true;
			}
		} catch (Exception e) {

		}

		return key;
	}

	public boolean addAppointment(int doctor_id, int hasta_id, String appDate) throws SQLException {
		int key = 0;
		String query = "INSERT INTO appointment" + "(doctor_id,hasta_id,app_date) VALUES" + "(?,?,?)";

		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, doctor_id);
			preparedStatement.setInt(2, hasta_id);
			preparedStatement.setString(3, appDate);
			preparedStatement.executeUpdate();
			key = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (key == 1) {	
			return true;
		} else
			return false;
	}
	public boolean updateWhourStatus(int doctor_id,String wdate) throws SQLException {
		int key = 0;
		String query = "UPDATE whour SET status = ? WHERE doctor_id= ? AND wdate= ?";
		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, "p");
			preparedStatement.setInt(2, doctor_id);
			preparedStatement.setString(3, wdate);
			preparedStatement.executeUpdate();
			key = 1;
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (key == 1) {	
			return true;
		} else
			return false;
	}
	
}
