package View;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import Helper.*;
import Model.Bashekim;
import Model.Doctor;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fld_hastaTC;
	private JTextField fld_doctorTC;
	private JPasswordField fld_doctorPassw;
	private JPasswordField fld_hastaPassw;
	private DbConnection conn = new DbConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setTitle("Hastane Otomasyonu");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("pharmacy (1).png")));
		lbl_logo.setBounds(172, 0, 90, 53);
		contentPane.add(lbl_logo);
		
		JLabel lblNewLabel = new JLabel("Hastane Y\u00F6netim Sistemine Ho\u015Fgeldiniz.");
		lblNewLabel.setFont(new Font("Cambria", Font.BOLD, 16));
		lblNewLabel.setBounds(74, 49, 297, 20);
		contentPane.add(lblNewLabel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(110, 170, 5, 5);
		contentPane.add(tabbedPane);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 74, 414, 176);
		contentPane.add(w_tabpane);
		
		JPanel w_hastaLogin = new JPanel();
		w_hastaLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Hasta Giriþi", null, w_hastaLogin, null);
		w_hastaLogin.setLayout(null);
		
		JLabel lblTc = new JLabel("T.C. Numaran\u0131z :");
		lblTc.setFont(new Font("Cambria", Font.BOLD, 16));
		lblTc.setBounds(10, 15, 131, 20);
		w_hastaLogin.add(lblTc);
		
		JLabel lblifre = new JLabel("\u015Eifre :");
		lblifre.setFont(new Font("Cambria", Font.BOLD, 16));
		lblifre.setBounds(10, 47, 118, 20);
		w_hastaLogin.add(lblifre);
		
		fld_hastaTC = new JTextField();
		fld_hastaTC.setFont(new Font("Arial", Font.PLAIN, 16));
		fld_hastaTC.setBounds(138, 13, 238, 25);
		w_hastaLogin.add(fld_hastaTC);
		fld_hastaTC.setColumns(10);
		
		JButton btn_register = new JButton("Kay\u0131t Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI registerGUI=new RegisterGUI();
				registerGUI.setVisible(true);
				dispose();
			}
		});
		btn_register.setBounds(31, 101, 158, 36);
		w_hastaLogin.add(btn_register);
		
		JButton btn_hastaLogin = new JButton("Giri\u015F Yap");
		btn_hastaLogin.setBounds(217, 101, 158, 36);
		w_hastaLogin.add(btn_hastaLogin);
		
		fld_hastaPassw = new JPasswordField();
		fld_hastaPassw.setBounds(138, 46, 238, 26);
		w_hastaLogin.add(fld_hastaPassw);
		
		JPanel w_doctorLogin = new JPanel();
		w_doctorLogin.setBackground(Color.WHITE);
		w_tabpane.addTab("Doktor Giriþi", null, w_doctorLogin, null);
		w_doctorLogin.setLayout(null);
		
		JLabel lblTc_1 = new JLabel("T.C. Numaran\u0131z :");
		lblTc_1.setFont(new Font("Cambria", Font.BOLD, 16));
		lblTc_1.setBounds(10, 13, 131, 20);
		w_doctorLogin.add(lblTc_1);
		
		fld_doctorTC = new JTextField();
		fld_doctorTC.setFont(new Font("Arial", Font.PLAIN, 16));
		fld_doctorTC.setColumns(10);
		fld_doctorTC.setBounds(138, 11, 238, 25);
		w_doctorLogin.add(fld_doctorTC);
		
		JLabel lblifre_1 = new JLabel("\u015Eifre :");
		lblifre_1.setFont(new Font("Cambria", Font.BOLD, 16));
		lblifre_1.setBounds(10, 50, 118, 20);
		w_doctorLogin.add(lblifre_1);
		
		JButton btn_doctorLogin = new JButton("Giri\u015F Yap");
		btn_doctorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_doctorTC.getText().length()==0 || fld_doctorPassw.getText().length()==0) {
					Helper.showMsg("fill");
				}
				else {
					try {
						Connection con= conn.connDb();
						Statement st= con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while(rs.next()) {
							if(fld_doctorTC.getText().equals(rs.getString("tcno"))&&fld_doctorPassw.getText().equals(rs.getString("password"))){
							if(rs.getString("type").equals("bashekim")) {
								Bashekim bhekim= new Bashekim();
								bhekim.setId(rs.getInt("id"));
								bhekim.setPassword("password");
								bhekim.setTcno(rs.getString("tcno"));
								bhekim.setName(rs.getString("name"));
								bhekim.setType(rs.getString("type"));
								BashekimGUI bGUI = new BashekimGUI(bhekim);
								bGUI.setVisible(true);
								dispose();
							}
							if(rs.getString("type").equals("doktor")) {
								Doctor doctor=new Doctor();
								doctor.setId(rs.getInt("id"));
								doctor.setPassword("password");
								doctor.setTcno(rs.getString("tcno"));
								doctor.setName(rs.getString("name"));
								doctor.setType(rs.getString("type"));
								DoctorGUI dGUI= new DoctorGUI(doctor);
								dGUI.setVisible(true);
								dispose();
							}
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_doctorLogin.setBounds(10, 99, 366, 36);
		w_doctorLogin.add(btn_doctorLogin);
		
		fld_doctorPassw = new JPasswordField();
		fld_doctorPassw.setBounds(138, 47, 238, 25);
		w_doctorLogin.add(fld_doctorPassw);
	}
}
