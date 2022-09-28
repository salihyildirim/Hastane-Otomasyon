package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.xml.transform.Templates;

import Helper.Item;
import Model.Clinic;
import Model.Hasta;

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
		w_scrollDoctor.setBounds(10, 44, 261, 320);
		w_appointment.add(w_scrollDoctor);
		
		table_doctor = new JTable(doctorModel);
		w_scrollDoctor.setViewportView(table_doctor);
		
		JLabel lblNewLabel_1 = new JLabel("Doktor Listesi");
		lblNewLabel_1.setBounds(10, 22, 101, 20);
		w_appointment.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_4 = new JLabel("Poliklinik Ad\u0131");
		lblNewLabel_1_4.setBounds(281, 22, 168, 25);
		w_appointment.add(lblNewLabel_1_4);
		
		JComboBox select_clinic = new JComboBox();
		select_clinic.setBounds(281, 44, 150, 33);
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
				
			}
			
		});
		
		
		w_appointment.add(select_clinic);
	}
}
