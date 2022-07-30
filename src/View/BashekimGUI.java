package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import org.mariadb.jdbc.type.Point;
import org.w3c.dom.events.MouseEvent;

import Helper.Helper;
import Helper.Item;
import Model.Bashekim;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import java.awt.*;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import Model.Clinic;
import javax.swing.JComboBox;

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
	private JTable table_clinic;
	private JTextField fld_clinicName;
private DefaultTableModel clinicModel;
private Object[] clinicData;
private JPopupMenu clinicMenu;
Clinic clinic=new Clinic(); 
private JTable table_worker;
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
		//Doctor Model
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
		//Clinic Model
		clinicModel = new DefaultTableModel();
		Object[] colClinic=new Object[2];
		colClinic[0]="ID";
		colClinic[1]="Poliklinik Adý";
		clinicModel.setColumnIdentifiers(colClinic); 
		clinicData = new Object[2];
		for(int i=0;i<clinic.getList().size();i++){
			clinicData[0]=clinic.getList().get(i).getId();
			clinicData[1]=clinic.getList().get(i).getName();
			clinicModel.addRow(clinicData);
			
		}
		//WorkerModel
		DefaultTableModel workerModel=new DefaultTableModel();
		Object [] colWorker=new Object[2];
		colWorker[0]="ID";
		colWorker[1]="Ad Soyad";
		workerModel.setColumnIdentifiers(colWorker);
		Object[] workerData= new Object[2];
		
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
				
				table_doctor.getModel().addTableModelListener(new TableModelListener() {
					
					@Override
					public void tableChanged(TableModelEvent e) {
						if(e.getType()==TableModelEvent.UPDATE) {
							int selectID = Integer.parseInt(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
							String selectName =table_doctor.getValueAt(table_doctor.getSelectedRow(), 1).toString();
							String  selectTcno= table_doctor.getValueAt(table_doctor.getSelectedRow(), 2).toString();
							String selectPass=table_doctor.getValueAt(table_doctor.getSelectedRow(), 3).toString();
							
							try {
								bashekim.updateDoctor(selectID, selectTcno, selectPass, selectName);
								
								
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
						
					}
				});
			}
		});
		JPanel w_clinic = new JPanel();
		w_clinic.setBackground(Color.WHITE);
		w_tab.addTab("Poliklinikler", null, w_clinic, null);
		w_clinic.setLayout(null);
		
		JScrollPane w_scrollClinic = new JScrollPane();
		w_scrollClinic.setBounds(10, 11, 233, 353);
		w_clinic.add(w_scrollClinic);
		
		
		clinicMenu = new JPopupMenu(); //CREATE A POPUP MENU ON POLIKLINIKS
		JMenuItem updateMenu=new JMenuItem("Güncelle");
		JMenuItem deleteMenu=new JMenuItem("Sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);
		
		updateMenu.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) {
						int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
						
							Clinic selectClinic;
							try {
								selectClinic = clinic.getFetch(selID);
								UpdateClinicGUI updateGUI=new UpdateClinicGUI(selectClinic);
								updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
								updateGUI.setVisible(true);
								updateGUI.addWindowListener(new WindowListener() {
		

									@Override
									public void windowOpened(WindowEvent e) {
										// TODO Auto-generated method stub
										
									}

									@Override
									public void windowClosing(WindowEvent e) {
										// TODO Auto-generated method stub
										
									}

									@Override
									public void windowClosed(WindowEvent e) {
										try {
											updateClinicModel();
										} catch (SQLException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
										
									}

									@Override
									public void windowIconified(WindowEvent e) {
										// TODO Auto-generated method stub
										
									}

									@Override
									public void windowDeiconified(WindowEvent e) {
										// TODO Auto-generated method stub
										
									}

									@Override
									public void windowActivated(WindowEvent e) {
										// TODO Auto-generated method stub
										
									}

									@Override
									public void windowDeactivated(WindowEvent e) {
										// TODO Auto-generated method stub
										
									}
								});
							
						}
							 catch (SQLException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}}});
		deleteMenu.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("sure")) {
					int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
					try {
						if(clinic.deleteClinic(selID)) {
							Helper.showMsg("success");
							updateClinicModel();
						}
						else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				}
				
			}}
						
		);
		
		table_clinic = new JTable(clinicModel);
		table_clinic.setComponentPopupMenu(clinicMenu);
		table_clinic.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(java.awt.event.MouseEvent e) {
				java.awt.Point point=e.getPoint(); 
				int selectedRow = table_clinic.rowAtPoint(point);
				table_clinic.setRowSelectionInterval(selectedRow, selectedRow);
				
				
			}

			@Override
			public void mouseReleased(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(java.awt.event.MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		

		}
			
		);
		
		w_scrollClinic.setViewportView(table_clinic);
		
		JLabel lblNewLabel_1_4 = new JLabel("Poliklinik Ad\u0131");
		lblNewLabel_1_4.setBounds(267, 7, 168, 25);
		w_clinic.add(lblNewLabel_1_4);
		
		fld_clinicName = new JTextField();
		fld_clinicName.setColumns(10);
		fld_clinicName.setBounds(267, 37, 168, 25);
		w_clinic.add(fld_clinicName);
		
		JButton btn_addClinic = new JButton("Ekle");
		btn_addClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_clinicName.getText().length()==0) {
					Helper.showMsg("fill");
				}
				else { 
				try {
					clinic.addClinic(fld_clinicName.getText());
					Helper.showMsg("success");
					fld_clinicName.setText(null);
					updateClinicModel();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}
		});
		btn_addClinic.setBounds(267, 73, 168, 32);
		w_clinic.add(btn_addClinic);
		
		JScrollPane w_scrollWorker = new JScrollPane();
		w_scrollWorker.setBounds(456, 11, 233, 353);
		w_clinic.add(w_scrollWorker);
		
		table_worker = new JTable();
		w_scrollWorker.setViewportView(table_worker);
		
		JComboBox select_doctor = new JComboBox();
		select_doctor.setBounds(267, 285, 168, 32);
		for(int i=0;i<bashekim.getDoctorList().size();i++) {
			select_doctor.addItem(new Item(bashekim.getDoctorList().get(i).getId(),bashekim.getDoctorList().get(i).getName()));
		
		}
		select_doctor.addActionListener(e -> {
			JComboBox c=(JComboBox) e.getSource();
			Item item=(Item) c.getSelectedItem();
			System.out.println(item.getKey()+" : "+item.getValue()); 
			}
		);
		w_clinic.add(select_doctor);
		
		JButton btn_addWorker = new JButton("Ekle");
		btn_addWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow=table_clinic.getSelectedRow();
				if(selRow>=0) {
						String selClinic = table_clinic.getModel().getValueAt(selRow, 0).toString();
						int selClinicID= Integer.parseInt(selClinic);
						Item doctorItem = (Item) select_doctor.getSelectedItem();
						try {
							boolean control = bashekim.addWorker(doctorItem.getKey(), selClinicID);
							if(control) {
								Helper.showMsg("success");
								DefaultTableModel clearModel= (DefaultTableModel) table_worker.getModel();
								clearModel.setRowCount(0);
								for(int i=0;i<bashekim.getClinicDoctorList(selClinicID).size();i++) {
									workerData[0]=bashekim.getClinicDoctorList(selClinicID).get(i).getId();
									workerData[1]=bashekim.getClinicDoctorList(selClinicID).get(i).getName();
									workerModel.addRow(workerData);
									table_worker.setModel(workerModel);
								}
							}
							else {
								Helper.showMsg("error");
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
				else {
					Helper.showMsg("Lütfen Bir Poliklinik seçiniz !");
				}
			}
		});
		btn_addWorker.setBounds(267, 328, 168, 36);
		w_clinic.add(btn_addWorker);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Poliklinik Ad\u0131");
		lblNewLabel_1_4_1.setBounds(267, 162, 168, 25);
		w_clinic.add(lblNewLabel_1_4_1);
		
		JButton btn_workerSelect = new JButton("Se\u00E7");
		btn_workerSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selRow=table_clinic.getSelectedRow();
					if(selRow>=0) {
						String selClinic=table_clinic.getModel().getValueAt(selRow, 0).toString();
						int selClinicID=Integer.parseInt(selClinic);
						DefaultTableModel clearModel= (DefaultTableModel) table_worker.getModel();
						clearModel.setRowCount(0);
						
						try {
							for(int i=0;i<bashekim.getClinicDoctorList(selClinicID).size();i++) {
								workerData[0]=bashekim.getClinicDoctorList(selClinicID).get(i).getId();
								workerData[1]=bashekim.getClinicDoctorList(selClinicID).get(i).getName();
								workerModel.addRow(workerData);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						table_worker.setModel(workerModel);
					}
					else {
						Helper.showMsg("Lütfen Bir Poliklinik Seçiniz !");
					}
			}
		});
		btn_workerSelect.setBounds(267, 184, 168, 32);
		w_clinic.add(btn_workerSelect);
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
	
	public void updateClinicModel() throws SQLException {
		DefaultTableModel clearModel=(DefaultTableModel) table_clinic.getModel();
		clearModel.setRowCount(0);
		for(int i=0;i<clinic.getList().size();i++){
			clinicData[0]=clinic.getList().get(i).getId();
			clinicData[1]=clinic.getList().get(i).getName();
			clinicModel.addRow(clinicData);
			
		}
		
	}
}
