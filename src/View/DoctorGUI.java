package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Doctor;
import Model.Whour;

import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import Helper.Helper;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class DoctorGUI extends JFrame {
	
	
	private JPanel w_pane;
	private static Doctor doctor=new Doctor();
	private JTable table_whour;
	private DefaultTableModel whourModel;
	Object[] whourData=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorGUI frame = new DoctorGUI(doctor);
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
	public DoctorGUI(Doctor doctor) throws SQLException {
		
		whourModel = new DefaultTableModel();
		Object[] colWhour=new Object[2];
		colWhour[0]= "ID";
		colWhour[1]= "Tarih";
		whourModel.setColumnIdentifiers(colWhour); 
		whourData = new Object[2];
		for(int i=0;i<doctor.getWhourList(doctor.getId()).size();i++) {
			whourData[0]=doctor.getWhourList(doctor.getId()).get(i).getId();
			whourData[1]=doctor.getWhourList(doctor.getId()).get(i).getWdate();
			whourModel.addRow(whourData); 
		}
		
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoþgeldiniz Sayýn " +doctor.getName());
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 11, 238, 25);
		w_pane.add(lblNewLabel);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(20, 47, 704, 403);
		w_pane.add(w_tab);
		
		JPanel w_whour = new JPanel();
		w_whour.setBackground(Color.WHITE);
		w_tab.addTab("Çalýþma Saatleri", null, w_whour, null);
		w_whour.setLayout(null);
		
		JDateChooser select_date = new JDateChooser();
		select_date.setBounds(10, 11, 151, 27);
		w_whour.add(select_date);
		
		JComboBox select_time = new JComboBox();
		select_time.setModel(new DefaultComboBoxModel(new String[] {"10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30"}));
		select_time.setBounds(171, 11, 66, 27);
		w_whour.add(select_time);
		
		JButton btn_addWhour = new JButton("Ekle");
		btn_addWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(select_date.getDate()); 
				SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");		
				String date="";
				try {
					date = sdf.format(select_date.getDate());
				}
				catch (Exception e2) {
					
				}
                if (date.length()==0) {
					Helper.showMsg("Lütfen Geçerli Bir Tarih Giriniz !");
				}
                else {
				String time=" " + select_time.getSelectedItem().toString() + ":00";
				String selectDate= date+time;
				boolean control;
					try {
						control = doctor.addWhour(doctor.getId(), doctor.getName(), selectDate);
						if (control) {
							Helper.showMsg("success");
							updateWhourModel(doctor);

						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
               
         
		});
		btn_addWhour.setBounds(247, 11, 89, 27);
		w_whour.add(btn_addWhour);
		
		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(10, 46, 689, 329);
		w_whour.add(w_scrollWhour);
		
		table_whour = new JTable(whourModel);
		w_scrollWhour.setViewportView(table_whour);
		
		JButton btn_deleteWhour = new JButton("Sil");
		btn_deleteWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				int selectRow=table_whour.getSelectedRow();
				if(selectRow>=0) {
					int selID=Integer.parseInt((table_whour.getValueAt(selectRow, 0)).toString()); 
					boolean control;
					try {
						control = doctor.deleteWhour(selID);	
						if(control) {
							Helper.showMsg("success");
							updateWhourModel(doctor);
						}
						else {
							Helper.showMsg("VeriTabaný Hatasý ! Silenemedi");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else { 
					Helper.showMsg("Lütfen bir kayýt seçiniz !");
				}
			}
		});
		btn_deleteWhour.setBounds(393, 11, 89, 27);
		w_whour.add(btn_deleteWhour);
		
		JButton btnNewButton = new JButton("Çýkýþ Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login=new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 14));
		btnNewButton.setBounds(607, 10, 117, 27);
		w_pane.add(btnNewButton);
		
		
	}
	public void updateWhourModel(Doctor doctor) throws SQLException {
		DefaultTableModel clearModel=(DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		for(int i=0;i<doctor.getWhourList(doctor.getId()).size();i++) {
			whourData[0]=doctor.getWhourList(doctor.getId()).get(i).getId();
			whourData[1]=doctor.getWhourList(doctor.getId()).get(i).getWdate();
			whourModel.addRow(whourData); 
		}
		
	}
}
