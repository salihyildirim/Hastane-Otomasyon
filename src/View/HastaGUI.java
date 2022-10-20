package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.xml.transform.Templates;

import Helper.Helper;
import Helper.Item;
import Model.Appointment;
import Model.Clinic;
import Model.Hasta;
import Model.Whour;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class HastaGUI extends JFrame {

	private JPanel w_pane;
	private static Hasta hasta = new Hasta();
	private Clinic clinic=new Clinic();
	private JTable table_doctor;
	private DefaultTableModel doctorModel;
	private Object [] doctorData=null;
	private JTable table_whour;
	private Whour whour=new Whour();
	private DefaultTableModel whourModel;
	private Object [] whourData=null;
	private int selectDoctorID=0;
	private String selectDoctorName=null;
	private JTable table_appoint;
	private DefaultTableModel appointModel;
	private Object [] appointData=null;
	private Appointment appoint=new Appointment();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HastaGUI frame = new HastaGUI(hasta);
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
	public HastaGUI(Hasta hasta) throws SQLException {
		
		doctorModel = new DefaultTableModel();
		Object[] colDoctor=new Object[2];
		colDoctor[0]="ID";	
		colDoctor[1]="Ad Soyad";
		doctorModel.setColumnIdentifiers(colDoctor);
		doctorData= new Object[2];
		
		whourModel = new DefaultTableModel();
		Object[] colWhour=new Object[2];
		colWhour[0]="ID";	
		colWhour[1]="Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData= new Object[2];
		
		appointModel = new DefaultTableModel();
		Object[] colAppoint=new Object[2];
		colAppoint[0]="ID";	
		colAppoint[1]="Tarih";
		appointModel.setColumnIdentifiers(colAppoint);
		appointData= new Object[2];
		for(int i=0;i<appoint.getHastaList(hasta.getId()).size(); i++)
		{
			appointData[0]=appoint.getHastaList(hasta.getId()).get(i).getId();
			appointData[1]=appoint.getHastaList(hasta.getId()).get(i).getAppDate();
			appointModel.addRow(appointData);
			
		}
		
		
		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoþgeldiniz Sayýn "+hasta.getName());
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblNewLabel.setBounds(20, 12, 238, 25);
		w_pane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Çýkýþ Yap");
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnNewButton.setBounds(617, 11, 117, 27);
		w_pane.add(btnNewButton);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(20, 48, 704, 403);
		w_pane.add(w_tab);
		
		JPanel w_appointment = new JPanel();
		w_appointment.setBackground(Color.WHITE);
		w_tab.addTab("Randevu Sistemi", null, w_appointment, null);
		w_appointment.setLayout(null);
		
		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(10, 44, 245, 320);
		w_appointment.add(w_scrollDoctor);
		
		table_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(table_doctor);
		
		JLabel lblNewLabel_1 = new JLabel("Doktor Listesi");
		lblNewLabel_1.setBounds(10, 22, 101, 20);
		w_appointment.add(lblNewLabel_1);	
		
		JLabel lblNewLabel_1_4 = new JLabel("Poliklinik Ad\u0131");
		lblNewLabel_1_4.setBounds(265, 20, 168, 25);
		w_appointment.add(lblNewLabel_1_4);
		
		JComboBox select_clinic = new JComboBox();
		select_clinic.setBounds(265, 47, 150, 33);
		select_clinic.addItem("Poliklinik Seç");
		for(int i=0;i<clinic.getList().size();i++)
		{
			select_clinic.addItem(new Item(clinic.getList().get(i).getId(),clinic.getList().get(i).getName()));	
		}
		select_clinic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(select_clinic.getSelectedIndex() != 0) {
					JComboBox comboBox = (JComboBox) e.getSource();	
					Item item= (Item) comboBox.getSelectedItem();
					
					DefaultTableModel clearModel= (DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
					
					try {
						for(int i=0; i<clinic.getClinicDoctorList(item.getKey()).size();i++ ) {
							doctorData[0]=clinic.getClinicDoctorList(item.getKey()).get(i).getId();
							doctorData[1]=clinic.getClinicDoctorList(item.getKey()).get(i).getName();	
							doctorModel.addRow(doctorData);
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
					
					
					}
				else {DefaultTableModel clearModel= (DefaultTableModel) table_doctor.getModel();
				clearModel.setRowCount(0);}
				
			}
			
		});
		
		
		w_appointment.add(select_clinic);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Doktor Se\u00E7");
		lblNewLabel_1_4_1.setBounds(265, 142, 150, 25);
		w_appointment.add(lblNewLabel_1_4_1);
		
		JButton btn_selDoctor = new JButton("Se\u00E7");
		btn_selDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row=table_doctor.getSelectedRow();
				if(row>=0) {
					String value=table_doctor.getValueAt(row, 0).toString();
					int id=Integer.parseInt(value);
					DefaultTableModel clearModel= (DefaultTableModel) table_whour.getModel();
					clearModel.setRowCount(0);
					
					try {
						for(int i=0; i<whour.getWhourList(id).size();i++) {
							whourData[0]=whour.getWhourList(id).get(i).getId();
							whourData[1]=whour.getWhourList(id).get(i).getWdate();
							whourModel.addRow(whourData);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				table_whour.setModel(whourModel);
				selectDoctorID=(int) table_doctor.getValueAt(row, 0);	
				selectDoctorName=(String) table_doctor.getValueAt(row,1);
				
				
				}else {
					Helper.showMsg("Lütfen Bir Doktor Seçiniz !");
				}
				
				
			}
		});
		btn_selDoctor.setBounds(265, 162, 168, 32);
		w_appointment.add(btn_selDoctor);
		
		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(443, 44, 246, 320);
		w_appointment.add(w_scrollWhour);
		
		table_whour = new JTable(whourModel);
		w_scrollWhour.setViewportView(table_whour);
		
		JLabel lblNewLabel_1_4_1_1 = new JLabel("Randevu Al");
		lblNewLabel_1_4_1_1.setBounds(265, 247, 150, 25);
		w_appointment.add(lblNewLabel_1_4_1_1);
		
		JButton btn_addAppoint = new JButton("Se\u00E7");	
		btn_addAppoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow=table_whour.getSelectedRow();
				if(selRow>=0) {
					String date=table_whour.getValueAt(selRow, 1).toString();
					try {
						boolean control=hasta.addAppointment(selectDoctorID, hasta.getId(), date);
						if(control) {
							Helper.showMsg("success");
							hasta.updateWhourStatus(selectDoctorID, date);
							updateWhourModel(selectDoctorID);
							updateAppointModel(hasta.getId());
						}	else {Helper.showMsg("error");	
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					Helper.showMsg("GEÇERLÝ BÝR TARÝH SEÇÝNÝZ");
				}
			}
		});
		btn_addAppoint.setBounds(265, 267, 168, 32);
		w_appointment.add(btn_addAppoint);
		
		JPanel w_appoint = new JPanel();
		w_appoint.setBackground(Color.WHITE);
		w_tab.addTab("Randevularým", null, w_appoint, null);
		w_appoint.setLayout(null);
		
		JScrollPane w_scrollAppoint = new JScrollPane();
		w_scrollAppoint.setBounds(10, 11, 679, 353);
		w_appoint.add(w_scrollAppoint);
		
		table_appoint = new JTable(appointModel);
		w_scrollAppoint.setViewportView(table_appoint);
		table_whour.getColumnModel().getColumn(0).setPreferredWidth(5);
	}
	public void updateWhourModel(int doctor_id) throws SQLException { 
		DefaultTableModel clearModel=(DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0); 
		for(int i=0; i<whour.getWhourList(doctor_id).size();i++) {
			whourData[0]=whour.getWhourList(doctor_id).get(i).getId();
			whourData[1]=whour.getWhourList(doctor_id).get(i).getWdate();
			whourModel.addRow(whourData);	
		}
		
	}
	public void updateAppointModel(int hasta_id) throws SQLException { 
		DefaultTableModel clearModel=(DefaultTableModel) table_appoint.getModel();
		clearModel.setRowCount(0); 
		for(int i=0;i<appoint.getHastaList(hasta.getId()).size(); i++)
		{
			appointData[0]=appoint.getHastaList(hasta.getId()).get(i).getId();
			appointData[1]=appoint.getHastaList(hasta.getId()).get(i).getAppDate();
			
		}
		
	}
}




