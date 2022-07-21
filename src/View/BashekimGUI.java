package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Model.Bashekim;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BashekimGUI extends JFrame {
static Bashekim bashekim = new Bashekim();
	private JPanel w_pane;
	private JTextField fld_dName;
	private JTextField fld_dTcno;
	private JPasswordField fld_dPass;
	private JTextField fld_doctorID;
	private JTable table_doctor;
	private DefaultTableModel doctorModel=null;
	private Object[] doctorData=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public BashekimGUI(Bashekim bashekim) throws SQLException {
		
		doctorModel = new DefaultTableModel();
		Object[] colDoctorName=new Object[4];
		colDoctorName[0]="ID";	
		colDoctorName[1]="Ad Soyad";
		colDoctorName[2]="TC NO";
		colDoctorName[3]="Þifre";	
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData= new Object[4];
		for(int i=0; i<bashekim.getDoctorList().size();i++) {
			doctorData[0]=bashekim.getDoctorList().get(i).getId();
			doctorData[1]=bashekim.getDoctorList().get(i).getName();
			doctorData[2]=bashekim.getDoctorList().get(i).getTcno();
			doctorData[3]=bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
		
		
		setTitle("Hastane Yönetim Sistemi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoþgeldiniz, Sayýn " + bashekim.getName());
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 11, 238, 25);
		w_pane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Çýkýþ Yap");
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnNewButton.setBounds(607, 10, 117, 27);
		w_pane.add(btnNewButton);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(20, 47, 704, 403);
		w_pane.add(w_tab);
		
		JPanel w_doctor = new JPanel();
		w_doctor.setBackground(Color.WHITE);
		w_tab.addTab("Doktor Yönetimi", null, w_doctor, null);
		w_doctor.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Ad Soyad");
		lblNewLabel_1.setBounds(521, 22, 168, 25);
		w_doctor.add(lblNewLabel_1);
		
		fld_dName = new JTextField();
		fld_dName.setBounds(521, 48, 168, 25);
		w_doctor.add(fld_dName);
		fld_dName.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("T.C. No");
		lblNewLabel_1_1.setBounds(521, 84, 168, 25);
		w_doctor.add(lblNewLabel_1_1);
		
		fld_dTcno = new JTextField();
		fld_dTcno.setColumns(10);
		fld_dTcno.setBounds(521, 110, 168, 25);
		w_doctor.add(fld_dTcno);
		
		JLabel lblNewLabel_1_2 = new JLabel("\u015Eifre");
		lblNewLabel_1_2.setBounds(521, 146, 168, 25);
		w_doctor.add(lblNewLabel_1_2);
		
		fld_dPass = new JPasswordField();
		fld_dPass.setBounds(521, 168, 168, 25);
		w_doctor.add(fld_dPass);
		
		JButton btnNewButton_1 = new JButton("Ekle");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	if(fld_dName.getText().length()==0 || fld_dPass.getText().length()==0 || fld_dTcno.getText().length()==0) {
		Helper.showMsg("fill");
	}
	else {
		try {
			boolean control = bashekim.addDoctor(fld_dTcno.getText(), fld_dPass.getText(), fld_dName.getText());
			if(control) {}
			Helper.showMsg("success");
			fld_dName.setText(null);
			fld_dPass.setText(null);
			fld_dTcno.setText(null);
			updateDoctorModel();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
				
			}
		});
		btnNewButton_1.setBounds(521, 204, 168, 25);
		w_doctor.add(btnNewButton_1);
		
		JLabel lblNewLabel_1_3 = new JLabel("Kullan\u0131c\u0131 ID");
		lblNewLabel_1_3.setBounds(521, 240, 168, 25);
		w_doctor.add(lblNewLabel_1_3);
		
		fld_doctorID = new JTextField();
		fld_doctorID.setColumns(10);
		fld_doctorID.setBounds(521, 276, 168, 25);
		w_doctor.add(fld_doctorID);
		
		JButton btnNewButton_1_1 = new JButton("Sil");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				if(fld_doctorID.getText().length()==0) {
					Helper.showMsg("Lütfen geçerli bir doktor seçiniz !");
				}
				else {
					if(Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_doctorID.getText());
						try {
							boolean control = bashekim.deleteDoctor(selectID);
							if(control) {
								Helper.showMsg("success");
								fld_doctorID.setText(null);
								updateDoctorModel();
							}
						} catch (SQLException e1) {
							
							e1.printStackTrace();
						}
					}
				}
				
			}
		});
		btnNewButton_1_1.setBounds(521, 313, 168, 25);
		w_doctor.add(btnNewButton_1_1);
		
		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(10, 11, 501, 353);
		w_doctor.add(w_scrollDoctor);
		
		table_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(table_doctor);
		table_doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {	
				try {
					fld_doctorID.setText(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				
			}
		});
	}
	public void updateDoctorModel() throws SQLException {
		DefaultTableModel clearModel=(DefaultTableModel) table_doctor.getModel();
		clearModel.setRowCount(0);
		for(int i=0; i<bashekim.getDoctorList().size();i++) {
			doctorData[0]=bashekim.getDoctorList().get(i).getId();
			doctorData[1]=bashekim.getDoctorList().get(i).getName();
			doctorData[2]=bashekim.getDoctorList().get(i).getTcno();
			doctorData[3]=bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
		
	}
	
	
	
	
	
}
