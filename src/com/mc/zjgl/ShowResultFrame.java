package com.mc.zjgl;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ShowResultFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7531205243496977969L;
	
/*	public static void main(String[] args){
		JFrame f=new JFrame();
		new ShowProjectFrame(f,"");
	}*/
	public ShowResultFrame(){
		
	}
	public ShowResultFrame(JFrame f,String pid,String user){
		this.setResizable(false);
		this.setTitle("项目信息");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){

				f.setEnabled(true);
				dispose();
			}
		});
		this.setBounds(150,40,1140,750);
		this.add(registerProject(this,pid,user));
		this.setVisible(true);
	}
	public JPanel registerProject(JFrame f,String pid,String user) {
		//RegisterNeedExpertData rnd = new RegisterNeedExpertData();
		AllProjectData apd=new AllProjectData();
		ArrayList<String> ls = apd.getProjectInfo(f, pid);
		//CheckFormat cf = new CheckFormat();
		JPanel p = new JPanel();
		p.setLayout(null);
		JLabel ptitle = new JLabel("专家需求记录表", JLabel.CENTER);
		Font ft = new Font("宋体", 1, 25);
		ptitle.setFont(ft);
		ptitle.setBounds(515, 20, 200, 25);
		p.add(ptitle);
		// -----------------------------------------------------
		JLabel projectIdL = new JLabel("项目编号：");
		projectIdL.setBounds(80, 60, 200, 20);
		JLabel projectId = new JLabel(pid);
		projectId.setBounds(150, 60, 200, 20);
		p.add(projectId);
		p.add(projectIdL);
		JLabel projectNeedL = new JLabel("需求单位：");
		projectNeedL.setBounds(80, 95, 200, 25);
		p.add(projectNeedL);
		JTextField projectNeedT = new JTextField();
		projectNeedT.setText(ls.get(0));
		projectNeedT.setEditable(false);
		projectNeedT.setBounds(150, 95, 300, 25);
		p.add(projectNeedT);
		JLabel projectDateL = new JLabel("日期：");
		projectDateL.setBounds(850, 95, 200, 25);
		p.add(projectDateL);
		JTextField projectDateT = new JTextField();
		projectDateT.setText(ls.get(1));
		projectDateT.setBounds(890, 95, 120, 25);
		p.add(projectDateT);
		// --------------------
		JLabel projectNameL = new JLabel("项目名称：");
		projectNameL.setBounds(80, 130, 200, 25);
		p.add(projectNameL);
		JTextField projectNameT = new JTextField();
		projectNameT.setText(ls.get(2));
		projectNameT.setBounds(150, 130, 300, 25);
		p.add(projectNameT);
		JLabel projectDateL1 = new JLabel("预计工作时长：                       天");
		projectDateL1.setBounds(850, 130, 500, 25);
		p.add(projectDateL1);
		JTextField projectDateT1 = new JTextField();
		projectDateT1.setText(ls.get(3));
		projectDateT1.setBounds(940, 130, 60, 25);
		p.add(projectDateT1);
		// -------------------------------------------
		JLabel projectContactL = new JLabel("项目联系人：");
		projectContactL.setBounds(80, 165, 200, 25);
		p.add(projectContactL);
		JTextField projectContactT = new JTextField();
		projectContactT.setText(ls.get(4));
		projectContactT.setBounds(160, 165, 300, 25);
		p.add(projectContactT);
		JLabel projectPosstionL = new JLabel("职位：");
		projectPosstionL.setBounds(550, 165, 200, 25);
		p.add(projectPosstionL);
		JComboBox<String> chooseExpertPosition = new JComboBox<String>();
		chooseExpertPosition.setEnabled(false);
		chooseExpertPosition.addItem(ls.get(5));
/*		ArrayList<String> ocls = rnd.getOccupation(f);
		int oclss = ocls.size();
		for (int i = 0; i < oclss; i++) {
			chooseExpertPosition.addItem(ocls.get(i));
		}*/
		chooseExpertPosition.setBounds(585, 165, 120, 25);
		p.add(chooseExpertPosition);
		JLabel projectTelL = new JLabel("联系电话：");
		projectTelL.setBounds(850, 165, 500, 25);
		p.add(projectTelL);
		JTextField projectTelT = new JTextField();
		projectTelT.setText(ls.get(6));
		projectTelT.setBounds(910, 165, 120, 25);
		p.add(projectTelT);
		// --------------------------------------------
		JLabel projectMeetPlaceL = new JLabel("集合地点：");
		projectMeetPlaceL.setBounds(80, 200, 200, 25);
		p.add(projectMeetPlaceL);
		JTextField projectMeetPlaceT = new JTextField();
		projectMeetPlaceT.setText(ls.get(7));
		projectMeetPlaceT.setBounds(150, 200, 200, 25);
		p.add(projectMeetPlaceT);
		JLabel projectMeetDateL = new JLabel("集合日期：");
		projectMeetDateL.setBounds(850, 200, 200, 25);
		p.add(projectMeetDateL);
		JTextField projectMeetDateT = new JTextField();
		projectMeetDateT.setText(ls.get(8));
		projectMeetDateT.setBounds(910, 200, 120, 25);
		p.add(projectMeetDateT);
		// ----------------------------------------------
		JLabel projectWorkL = new JLabel("工作内容：");
		projectWorkL.setBounds(80, 245, 200, 25);
		p.add(projectWorkL);
		JScrollPane showProjectWork = new JScrollPane();
		JTextArea projectWorkT = new JTextArea();
		projectWorkT.setText(ls.get(9));
		projectWorkT.setLineWrap(true);
		showProjectWork.setViewportView(projectWorkT);
		showProjectWork.setBounds(150, 245, 880, 150);
		p.add(showProjectWork);
		// -----------------------------------------------
		JLabel projectTraficL = new JLabel("交通安排：");
		projectTraficL.setBounds(80, 413, 200, 25);
		p.add(projectTraficL);
		JTextField projectTraficT = new JTextField();
		projectTraficT.setText(ls.get(10));
		projectTraficT.setBounds(150, 413, 880, 25);
		p.add(projectTraficT);
		// ---------------------------------------------------------------
		ArrayList<String> comboboxselect = new ArrayList<String>();
		//String[][] pgarr = rnd.getProfessionalgroup(f);
		//int pgarrl = pgarr.length;
		JScrollPane expertGroupJSP = new JScrollPane();
		String[] cn = { "需求专业类型", "需求人数" };
		String[][] arr = apd.getExpertNeed(f, pid);
/*		JComboBox<String> pg = new JComboBox<String>();
		pg.addItem("请选择专业组别");
		for (int i = 0; i < pgarrl; i++) {
			pg.addItem(pgarr[i][1]);
		}*/
		JTable expertGroupTable = new JTable() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 5683615638208058553L;

			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
				Double num;
				if (columnIndex == 0) {
					if (aValue.equals("请选择专业组别")) {
						return;
					}
					int cs = comboboxselect.size();
					for (int i = 0; i < cs; i++) {
						if (aValue.equals(comboboxselect.get(i))) {
							JOptionPane.showMessageDialog(f, aValue + "已选择");
							return;
						}
					}
					if (rowIndex == 0 && cs != 0) {
						comboboxselect.remove(0);
						comboboxselect.add(0, aValue.toString());
					} else {
						comboboxselect.add(aValue.toString());
					}

				}
				if (columnIndex == 1) {
					String s = this.getValueAt(rowIndex, 0).toString();
					if (s.length() != 0) {
						try {
							String st = (String) aValue;
							if (st.length() != 0) {
								num = Double.parseDouble(st);
								if (num <= 0) {
									JOptionPane.showMessageDialog(f, "请勿输入负数或零");
									return;
								} else {
									super.setValueAt(aValue, rowIndex, columnIndex);
								}
							}
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(f, "只能输入数字!");
							return;
						}
					} else {
						JOptionPane.showMessageDialog(this, "请先选择组别");
						return;
					}
				}
				super.setValueAt(aValue, rowIndex, columnIndex);
			}

			public void processKeyEvent(KeyEvent e) {
				if (this.getEditorComponent() == null && e.getKeyCode() != KeyEvent.VK_UP
						&& e.getKeyCode() != KeyEvent.VK_DOWN && e.getKeyCode() != KeyEvent.VK_RIGHT
						&& e.getKeyCode() != KeyEvent.VK_LEFT && e.getKeyCode() != KeyEvent.VK_TAB
						&& e.getKeyCode() != KeyEvent.VK_ENTER)
					return;
				else
					super.processKeyEvent(e);
			}
		};
/*		pg.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String s = pg.getSelectedItem().toString();
					System.out.println(s);
				}
			}
		});*/
		DefaultTableModel expertGroupTableModel = new DefaultTableModel(arr, cn) {
			/**
			 */
			private static final long serialVersionUID = 9174681503529842494L;

			public boolean isCellEditable(int r, int c) {
/*				int i = expertGroupTable.getRowCount() - 1;
				if (r < i && c == 1) {
					return true;
				}
				if (r < i && c == 0) {
					return true;
				}*/
				return false;
			}
		};
		expertGroupTableModel.addTableModelListener(new TableModelListener() {
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				int i = expertGroupTable.getRowCount() - 1;
				int ta = 0;
				if (e.getColumn() == 1 && e.getFirstRow() != i) {
					for (int j = 0; j < i; j++) {
						String s = expertGroupTable.getValueAt(j, 1).toString();
						if (s.length() != 0) {
							int t = Integer.parseInt(s);
							ta = ta + t;
						}
					}
					expertGroupTable.setValueAt(Integer.toString(ta), i, 1);
				}
			}
		});
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(JLabel.CENTER);
		expertGroupTable.setDefaultRenderer(Object.class, tcr);
		expertGroupTable.setModel(expertGroupTableModel);
		//expertGroupTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(pg));
		expertGroupTable.setRowHeight(22);
		expertGroupJSP.setViewportView(expertGroupTable);
		expertGroupJSP.setBounds(80, 455, 980, 90);
/*		Object[] row = new Object[2];
		row[0] = "";
		row[1] = "";*/
/*		expertGroupTable.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO 自动生成的方法存根
				int r = expertGroupTable.getRowCount();
				if (e.getKeyCode() == '\n') {
					String s = expertGroupTableModel.getValueAt(r - 2, 1).toString();
					System.out.println(s);
					if (s.length() != 0) {
						expertGroupTableModel.insertRow(r - 1, row);
					} else {
						JOptionPane.showMessageDialog(f, "上一行未填写完成");
					}
				}
			}
		});*/
		p.add(expertGroupJSP);
		// ------------------------------------------------
		JLabel avoidL = new JLabel("规避公司：");
		avoidL.setBounds(80, 555, 200, 25);
		JComboBox<String> avoidC = new JComboBox<String>();
		avoidC.setBounds(150, 555, 300, 25);
		avoidC.addItem(ls.get(11));
		avoidC.setEnabled(false);
/*		ArrayList<String> acls = rnd.getAvoidCompany(f);
		int aclss = acls.size();
		for (int i = 0; i < aclss; i++) {
			avoidC.addItem(acls.get(i));
		}*/
		p.add(avoidC);
		p.add(avoidL);
		JLabel projectView = new JLabel("协会意见：");
		projectView.setBounds(80, 590, 200, 25);
		p.add(projectView);
		JTextField projectViewT = new JTextField();
		projectViewT.setText(ls.get(12));
		projectViewT.setBounds(150, 590, 880, 25);
		p.add(projectViewT);
		// ----------------------------------------------------
		JLabel projectMarkL = new JLabel("备注：");
		projectMarkL.setBounds(80, 625, 200, 25);
		p.add(projectMarkL);
		JTextField projectMarkT = new JTextField();
		projectMarkT.setText(ls.get(13));
		projectMarkT.setBounds(150, 625, 880, 25);
		p.add(projectMarkT);
		JButton register = new JButton("查看");
		register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				String work=projectWorkT.getText();
				String meetdate=projectMeetDateT.getText();
				String name=projectNameT.getText();
				String need=projectNeedT.getText();
				String contact=projectContactT.getText();
				f.setEnabled(false);
				new ShowRendomExpert(f,pid, name, work, meetdate,need,contact,user);
			}
		});
		register.setBounds(550, 680, 80, 25);
		JButton exportb=new JButton("导出");
		exportb.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String pjId = projectId.getText().trim();
				String pjNeed = projectNeedT.getText().trim();
				String pjNeedDate = projectDateT.getText().trim();
				String pjName = projectNameT.getText().trim();
				String pjNeedDay = projectDateT1.getText().trim();
				String pjContact = projectContactT.getText().trim();
				String pjOccupation = chooseExpertPosition.getSelectedItem().toString().trim();
				String pjTel = projectTelT.getText().trim();
				String pjMeetPlace = projectMeetPlaceT.getText().trim();
				String pjMeetDate = projectMeetDateT.getText().trim();
				String pjWork = projectWorkT.getText().trim();
				String pjTraffic = projectTraficT.getText().trim();
				String pjAvoid = avoidC.getSelectedItem().toString().trim();
				String pjView = projectViewT.getText().trim();
				String pjMark = projectMarkT.getText().trim();
				ArrayList<String> exportls = new ArrayList<String>();
				exportls.add(pjId);
				exportls.add(pjNeed);
				exportls.add(pjName);
				exportls.add(pjContact);
				exportls.add(pjOccupation);
				exportls.add(pjMeetPlace);
				exportls.add(pjNeedDate);
				exportls.add(pjNeedDay);
				exportls.add(pjTel);
				exportls.add(pjMeetDate);
				exportls.add(pjWork);
				exportls.add(pjTraffic);
				exportls.add(pjView);
				exportls.add(pjMark);
				exportls.add(pjAvoid);
				int rowc = expertGroupTable.getRowCount() - 1;
				String[][] exportda=new String[rowc][2];
				for (int t = 0; t < rowc; t++) {
					String f = expertGroupTable.getValueAt(t, 0).toString();
					String se = expertGroupTable.getValueAt(t, 1).toString();
					exportda[t][0]=f;
					exportda[t][1]=se;
				}
				ExportProject.exportExcel(f,pid+ "需求单.xls",exportls,exportda);
			}
		});
		exportb.setBounds(650, 680, 80, 25);
		p.add(exportb);
		p.add(register);
		return p;
	}
}
