package com.mc.zjgl;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class ExpertManage {
	JFrame mf;
	BasicData bd = new BasicData();
	ExpertData ed = new ExpertData();
	String fiftergroup="";
	DefaultTableModel undm;
	DefaultTableModel rdm;
	String[] unfcn={"项目编号","状态"};
	public ExpertManage(String user) {
		mf = new JFrame();
		mf.setTitle("ExpertManage");
		mf.setResizable(false);
		mf.setBounds(10, 10, 1230, 796);
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container mfc = mf.getContentPane();
		JTabbedPane MTPane = new JTabbedPane();
		MTPane.add("基础信息", basicInfo(user));
		MTPane.add("管理专家", manageExpert());
		MTPane.add("专家需求", registerProject());
		MTPane.add("抽选结果", allProject());
		MTPane.add("系统设置", sysInfo());
		mfc.add(MTPane);
		mf.setVisible(true);
	}
	public static void main(String[] args) {
		new ExpertManage("test");
	}

	public JPanel basicInfo(String user) {
		JPanel p = new JPanel();
		p.setLayout(null);
		JLabel ptitle = new JLabel("基础信息添加", JLabel.CENTER);
		Font f = new Font("宋体", 1, 25);
		ptitle.setFont(f);
		ptitle.setBounds(515, 20, 200, 25);
		p.add(ptitle);
		// ---------------------------------------------------------
		JLabel expertGT = new JLabel("请填写专业组别", JLabel.CENTER);
		expertGT.setBounds(30, 60, 200, 25);
		p.add(expertGT);
		JTextField expertGroup = new JTextField();
		expertGroup.setBounds(30, 90, 200, 25);
		p.add(expertGroup);
		JButton addExpertGroup = new JButton("添加组别");
		addExpertGroup.setBounds(85, 125, 90, 25);
		expertGroup.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == '\n') {
					addExpertGroup.doClick();
				}
			}
		});
		p.add(addExpertGroup);
		JScrollPane expertGroupJSP = new JScrollPane();
		String[] cn = { "GroupID", "专业组别" };
		String[][] arr = bd.getGroup(mf);
		JTable expertGroupTable = new JTable();
		DefaultTableModel expertGroupTableModel = new DefaultTableModel(arr, cn);
		addExpertGroup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				String s = expertGroup.getText().trim();
				if (s.length() != 0) {
					new SQLFilter(mf, s, user);
					int i = bd.addGroup(mf, s);
					if (i == 1) {
						JOptionPane.showMessageDialog(mf, "添加专业组别成功");
					}
					expertGroupTableModel.setDataVector(bd.getGroup(mf), cn);
					expertGroup.setText("");
				} else {
					JOptionPane.showMessageDialog(mf, "输入不能为空");
				}
			}
		});
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(JLabel.CENTER);
		expertGroupTable.setDefaultRenderer(Object.class, tcr);
		expertGroupTable.setModel(expertGroupTableModel);
		expertGroupTable.setRowHeight(22);
		expertGroupJSP.setViewportView(expertGroupTable);
		expertGroupJSP.setBounds(30, 165, 200, 550);
		p.add(expertGroupJSP);
		// -----------------------------------------------------------------------
		JLabel expertGT1 = new JLabel("请填写专业领域", JLabel.CENTER);
		expertGT1.setBounds(300, 60, 200, 25);
		p.add(expertGT1);
		JTextField expertGroup1 = new JTextField();
		expertGroup1.setBounds(300, 90, 200, 25);
		p.add(expertGroup1);
		JButton addExpertGroup1 = new JButton("添加专业");
		expertGroup1.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == '\n') {
					addExpertGroup1.doClick();
				}
			}
		});
		addExpertGroup1.setBounds(355, 125, 90, 25);
		p.add(addExpertGroup1);
		JScrollPane expertGroupJSP1 = new JScrollPane();
		String[] cn1 = { "ProfessionID", "专业领域" };
		String[][] arr1 = bd.getProfessionnal(mf);
		JTable expertGroupTable1 = new JTable();
		DefaultTableModel expertGroupTableModel1 = new DefaultTableModel(arr1, cn1);
		addExpertGroup1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				String s = expertGroup1.getText().trim();
				if (s.length() != 0) {
					new SQLFilter(mf, s, user);
					int i = bd.addProfessional(mf, s);
					if (i == 1) {
						JOptionPane.showMessageDialog(mf, "添加专业组别成功");
					}
					expertGroupTableModel1.setDataVector(bd.getProfessionnal(mf), cn1);
					expertGroup1.setText("");
				} else {
					JOptionPane.showMessageDialog(mf, "输入不能为空");
				}
			}
		});
		expertGroupTable1.setDefaultRenderer(Object.class, tcr);
		expertGroupTable1.setModel(expertGroupTableModel1);
		expertGroupTable1.setRowHeight(22);
		expertGroupJSP1.setViewportView(expertGroupTable1);
		expertGroupJSP1.setBounds(300, 165, 200, 550);
		p.add(expertGroupJSP1);
		// -----------------------------------------------------------
		JLabel expertGT11 = new JLabel("待添加...", JLabel.CENTER);
		expertGT11.setBounds(600, 60, 200, 25);
		p.add(expertGT11);
		//
		JLabel expertGT111 = new JLabel("待添加...", JLabel.CENTER);
		expertGT111.setBounds(900, 60, 200, 25);
		p.add(expertGT111);
		return p;
	}

	public JPanel manageExpert() {
		String[][] professionalgroup = bd.getGroup(mf);
		String[][] sex = bd.getSex(mf);
		String[][] education = bd.getEducation(mf);
		//String[][] professional = bd.getProfessionnal(mf);
		//String[][] occupation = bd.getOccupation(mf);
		JPanel p = new JPanel();
		p.setLayout(null);
		JLabel ptitle = new JLabel("添加专家信息", JLabel.CENTER);
		Font f = new Font("宋体", 1, 25);
		ptitle.setFont(f);
		ptitle.setBounds(515, 20, 200, 25);
		p.add(ptitle);
		JComboBox<String> chooseExpertGroup = new JComboBox<String>();
		chooseExpertGroup.addItem("请选择专业组别");
		int pgl = professionalgroup.length;
		for (int i = 0; i < pgl; i++) {
			chooseExpertGroup.addItem(professionalgroup[i][1]);
		}
		chooseExpertGroup.setBounds(30, 60, 120, 25);
		p.add(chooseExpertGroup);
		JTextField expertNameT = new JTextField("请输入专家姓名");
		expertNameT.addFocusListener(new MyFocusListener("请输入专家姓名", expertNameT));
		expertNameT.setBounds(180, 60, 120, 25);
		p.add(expertNameT);
		JComboBox<String> chooseExpertSex = new JComboBox<String>();
		chooseExpertSex.addItem("请选择专业性别");
		int sexl = sex.length;
		for (int i = 0; i < sexl; i++) {
			chooseExpertSex.addItem(sex[i][1]);
		}
		chooseExpertSex.setBounds(330, 60, 120, 25);
		p.add(chooseExpertSex);
		JTextField expertBurnDate = new JTextField("请输入专家出生日期");
		expertBurnDate.addFocusListener(new MyFocusListener("请输入专家出生日期", expertBurnDate));
		expertBurnDate.setBounds(480, 60, 120, 25);
		p.add(expertBurnDate);
		JComboBox<String> chooseExpertEducation = new JComboBox<String>();
		chooseExpertEducation.addItem("请选择专业学历");
		int educationl = education.length;
		for (int i = 0; i < educationl; i++) {
			chooseExpertEducation.addItem(education[i][1]);
		}
		chooseExpertEducation.setBounds(630, 60, 120, 25);
		p.add(chooseExpertEducation);
		JTextField expertCompany = new JTextField("请输入专家工作单位");
		expertCompany.addFocusListener(new MyFocusListener("请输入专家工作单位", expertCompany));
		expertCompany.setBounds(780, 60, 120, 25);
		p.add(expertCompany);
/*		JComboBox<String> chooseExpertProfession = new JComboBox<String>();
		chooseExpertProfession.addItem("请选择专家专业");
		int professionall = professional.length;
		for (int i = 0; i < professionall; i++) {
			chooseExpertProfession.addItem(professional[i][1]);
		}
		chooseExpertProfession.setBounds(930, 60, 120, 25);
		p.add(chooseExpertProfession);*/
		JTextField expertProfessional = new JTextField("请输入专家专业领域");
		expertProfessional.addFocusListener(new MyFocusListener("请输入专家专业领域", expertProfessional));
		expertProfessional.setBounds(930, 60, 120, 25);
		p.add(expertProfessional);
/*		JComboBox<String> chooseExpertPosition = new JComboBox<String>();
		chooseExpertPosition.addItem("请选择专家职位");
		int occupationl = occupation.length;
		for (int i = 0; i < occupationl; i++) {
			chooseExpertPosition.addItem(occupation[i][1]);
		}
		chooseExpertPosition.setBounds(30, 100, 120, 25);
		p.add(chooseExpertPosition);*/
		JTextField expertOccupation = new JTextField("请输入专家职位");
		expertOccupation.addFocusListener(new MyFocusListener("请输入专家职位", expertOccupation));
		expertOccupation.setBounds(30, 100, 120, 25);
		p.add(expertOccupation);
		JTextField expertTel = new JTextField("请输入联系电话");
		expertTel.addFocusListener(new MyFocusListener("请输入联系电话", expertTel));
		expertTel.setBounds(180, 100, 120, 25);
		p.add(expertTel);
		JTextField expertAddress = new JTextField("请输入联系地址");
		expertAddress.addFocusListener(new MyFocusListener("请输入联系地址", expertAddress));
		expertAddress.setBounds(330, 100, 500, 25);
		p.add(expertAddress);
		JComboBox<String> chooseExpertArea = new JComboBox<String>();
		chooseExpertArea.addItem("请选择区域");
		chooseExpertArea.addItem("市内");
		chooseExpertArea.addItem("市外");
		chooseExpertArea.setBounds(870, 100, 120, 25);
		p.add(chooseExpertArea);
		JTextField expertMark = new JTextField("请输入备注");
		expertMark.addFocusListener(new MyFocusListener("请输入备注", expertMark));
		expertMark.setBounds(30, 140, 800, 25);
		p.add(expertMark);
		JButton addExpert = new JButton("添加");
		addExpert.setBounds(575, 180, 80, 25);
		p.add(addExpert);
		JComboBox<String> fifterGroup = new JComboBox<String>();
		fifterGroup.addItem("筛选专业组别");
		for (int i = 0; i < pgl; i++) {
			fifterGroup.addItem(professionalgroup[i][1]);
		}
		fifterGroup.setBounds(30, 180, 120, 25);
		p.add(fifterGroup);
		JScrollPane expertGroupJSP = new JScrollPane();
		String[] cn = { "ID", "专业组别", "姓名", "性别", "出生年月", "学历", "工作单位", "专业领域", "职称/职位", "联系电话", "区域", "通讯地址", "备注" };
		String[][] arr = ed.getExpert(mf,fiftergroup);
		JTable expertGroupTable = new JTable();
		DefaultTableModel expertGroupTableModel = new DefaultTableModel(arr, cn);
		JPopupMenu editRight = new JPopupMenu();
		JMenuItem edit = new JMenuItem("编辑");
		JMenuItem delete = new JMenuItem("删除");
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				int sr = expertGroupTable.getSelectedRow();
				String expertid = expertGroupTable.getValueAt(sr, 0).toString().trim();
				String pg = expertGroupTable.getValueAt(sr, 1).toString().trim();
				String name = expertGroupTable.getValueAt(sr, 2).toString().trim();
				mf.setEnabled(false);
				new CheckBoxFrame(mf, professionalgroup, expertGroupTableModel, cn, expertid, pg, name,
						mf.getX(), mf.getY(),fiftergroup);
			}
		});
		fifterGroup.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED){
					if(fifterGroup.getSelectedIndex()!=0){
						fiftergroup=fifterGroup.getSelectedItem().toString().trim();
						expertGroupTableModel.setDataVector(ed.getExpert(mf,fiftergroup), cn);
					}else{
						fiftergroup="";
						expertGroupTableModel.setDataVector(ed.getExpert(mf,fiftergroup), cn);
					}
					System.out.println("change");
				}
			}
			
		});
		editRight.add(edit);
		editRight.add(delete);
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				int sr = expertGroupTable.getSelectedRow();
				String expertid = expertGroupTable.getValueAt(sr, 0).toString().trim();
				ed.deleteExpert(mf, expertid);
				JOptionPane.showMessageDialog(mf, "删除成功");
				expertGroupTableModel.setDataVector(ed.getExpert(mf,fiftergroup), cn);
			}
		});
		expertGroupTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO 自动生成的方法存根
				if (e.getButton() == 3) {
					int sr = expertGroupTable.rowAtPoint(e.getPoint());
					expertGroupTable.setRowSelectionInterval(sr, sr);
					editRight.show(expertGroupTable, e.getX(), e.getY());
				}
			}
		});
		expertGroupTable.getTableHeader().setReorderingAllowed(false);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(JLabel.CENTER);
		expertGroupTable.setDefaultRenderer(Object.class, tcr);
		expertGroupTable.setModel(expertGroupTableModel);
		expertGroupTable.setRowHeight(22);
		expertGroupJSP.setViewportView(expertGroupTable);
		expertGroupJSP.setBounds(30, 220, 1160, 500);
		p.add(expertGroupJSP);
		addExpert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				int cg = chooseExpertGroup.getSelectedIndex();
				int ent = expertNameT.getText().trim().length();
				int ces = chooseExpertSex.getSelectedIndex();
				int ebd = expertBurnDate.getText().trim().length();
				int cee = chooseExpertEducation.getSelectedIndex();
				int ec = expertCompany.getText().trim().length();
				int cepr =expertProfessional.getText().trim().length();
				int cepo = expertOccupation.getText().trim().length();
				int et = expertTel.getText().trim().length();
				int ea = expertAddress.getText().trim().length();
				int cea = chooseExpertArea.getSelectedIndex();
				if (cg != 0 && ent != 0 && ces != 0 && ebd != 0 && cee != 0 && ec != 0 && cepr != 0 && cepo != 0
						&& et != 0 && ea != 0 && cea != 0) {
					if (expertNameT.getText().trim().equals("请输入专家姓名")
							|| expertBurnDate.getText().trim().equals("请输入专家出生日期")
							|| expertCompany.getText().trim().equals("请输入专家工作单位")
							||expertProfessional.getText().trim().equals("请输入专家专业领域")
							||expertOccupation.getText().trim().equals("请输入专家职位")
							|| expertTel.getText().trim().equals("请输入联系电话")
							|| expertAddress.getText().trim().equals("请输入联系地址")) {
						JOptionPane.showMessageDialog(mf, "录入信息不完整");
					} else {
						try {
							String birthday = expertBurnDate.getText().trim();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 小写的mm表示的是分钟
							String dstr = birthday;
							@SuppressWarnings("unused")
							java.util.Date date = sdf.parse(dstr);
							System.out.println(birthday);
							String mark = "";
							if (expertMark.getText().trim().equals("请输入备注") != true) {
								mark = expertMark.getText().trim();
							}
							ed.insertExpert(mf, professionalgroup[cg - 1][0], expertNameT.getText().trim(),
									chooseExpertSex.getSelectedItem().toString(), birthday,
									chooseExpertEducation.getSelectedItem().toString(), expertCompany.getText().trim(),
									expertProfessional.getText().trim(), expertOccupation.getText().trim(), expertTel.getText().trim(),
									chooseExpertArea.getSelectedItem().toString(), expertAddress.getText().trim(),
									mark);
							expertGroupTableModel.setDataVector(ed.getExpert(mf,fiftergroup), cn);
							chooseExpertGroup.setSelectedIndex(0);expertNameT.setText("请输入专家姓名");
							chooseExpertSex.setSelectedIndex(0);;expertBurnDate.setText("请输入专家出生日期");
							chooseExpertEducation.setSelectedIndex(0);expertCompany.setText("请输入专家工作单位");
							expertProfessional.setText("请输入专家专业领域");expertOccupation.setText("请输入专家职位");
							expertTel.setText("请输入联系电话");expertAddress.setText("请输入联系地址");
							expertMark.setText("请输入备注");
						} catch (Exception e1) {
							StringWriter sw = new StringWriter();
							PrintWriter pw = new PrintWriter(sw, true);
							e1.printStackTrace(pw);
							String mn = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名
							String dn = new Exception().getStackTrace()[1].getClassName();// 获得调用类
							String errorString = "错误类：" + this.getClass().getName() + "\n错误方法：" + mn + "\n调用类：" + dn
									+ "\n错误信息：\n" + sw.toString();
							Rectangle b = mf.getBounds();
							new ErrorDialog(mf, b, errorString);
						}
					}
				} else {
					JOptionPane.showMessageDialog(mf, "录入信息不完整");
				}
			}
		});
		return p;
	}

	public JPanel registerProject() {
		RegisterNeedExpertData rnd = new RegisterNeedExpertData();
		CheckFormat cf = new CheckFormat();
		JPanel p = new JPanel();
		p.setLayout(null);
		JLabel ptitle = new JLabel("专家需求登记", JLabel.CENTER);
		Font f = new Font("宋体", 1, 25);
		ptitle.setFont(f);
		ptitle.setBounds(515, 20, 200, 25);
		p.add(ptitle);
		// -----------------------------------------------------
		JLabel projectIdL = new JLabel("项目编号：");
		projectIdL.setBounds(80, 60, 200, 20);
		JLabel projectId = new JLabel(rnd.getProjectId(mf));
		projectId.setBounds(150, 60, 200, 20);
		p.add(projectId);
		p.add(projectIdL);
		JLabel projectNeedL = new JLabel("需求单位：");
		projectNeedL.setBounds(80, 95, 200, 25);
		p.add(projectNeedL);
		JTextField projectNeedT = new JTextField();
		projectNeedT.setBounds(150, 95, 300, 25);
		p.add(projectNeedT);
		JLabel projectDateL = new JLabel("日期：");
		projectDateL.setBounds(850, 95, 200, 25);
		p.add(projectDateL);
		JTextField projectDateT = new JTextField();
		projectDateT.setBounds(890, 95, 120, 25);
		p.add(projectDateT);
		// --------------------
		JLabel projectNameL = new JLabel("项目名称：");
		projectNameL.setBounds(80, 130, 200, 25);
		p.add(projectNameL);
		JTextField projectNameT = new JTextField();
		projectNameT.setBounds(150, 130, 300, 25);
		p.add(projectNameT);
		JLabel projectDateL1 = new JLabel("预计工作时长：                       天");
		projectDateL1.setBounds(850, 130, 500, 25);
		p.add(projectDateL1);
		JTextField projectDateT1 = new JTextField();
		projectDateT1.setBounds(940, 130, 60, 25);
		p.add(projectDateT1);
		// -------------------------------------------
		JLabel projectContactL = new JLabel("项目联系人：");
		projectContactL.setBounds(80, 165, 200, 25);
		p.add(projectContactL);
		JTextField projectContactT = new JTextField();
		projectContactT.setBounds(160, 165, 300, 25);
		p.add(projectContactT);
		JLabel projectPosstionL = new JLabel("职位：");
		projectPosstionL.setBounds(550, 165, 200, 25);
		p.add(projectPosstionL);
		JComboBox<String> chooseExpertPosition = new JComboBox<String>();
		chooseExpertPosition.addItem("请选择职位");
		ArrayList<String> ocls = rnd.getOccupation(mf);
		int oclss = ocls.size();
		for (int i = 0; i < oclss; i++) {
			chooseExpertPosition.addItem(ocls.get(i));
		}
		chooseExpertPosition.setBounds(585, 165, 120, 25);
		p.add(chooseExpertPosition);
		JLabel projectTelL = new JLabel("联系电话：");
		projectTelL.setBounds(850, 165, 500, 25);
		p.add(projectTelL);
		JTextField projectTelT = new JTextField();
		projectTelT.setBounds(910, 165, 120, 25);
		p.add(projectTelT);
		// --------------------------------------------
		JLabel projectMeetPlaceL = new JLabel("集合地点：");
		projectMeetPlaceL.setBounds(80, 200, 200, 25);
		p.add(projectMeetPlaceL);
		JTextField projectMeetPlaceT = new JTextField();
		projectMeetPlaceT.setBounds(150, 200, 200, 25);
		p.add(projectMeetPlaceT);
		JLabel projectMeetDateL = new JLabel("集合日期：");
		projectMeetDateL.setBounds(850, 200, 200, 25);
		p.add(projectMeetDateL);
		JTextField projectMeetDateT = new JTextField();
		projectMeetDateT.setBounds(910, 200, 120, 25);
		p.add(projectMeetDateT);
		// ----------------------------------------------
		JLabel projectWorkL = new JLabel("工作内容：");
		projectWorkL.setBounds(80, 245, 200, 25);
		p.add(projectWorkL);
		JScrollPane showProjectWork = new JScrollPane();
		JTextArea projectWorkT = new JTextArea();
		projectWorkT.setLineWrap(true);
		showProjectWork.setViewportView(projectWorkT);
		showProjectWork.setBounds(150, 245, 880, 150);
		p.add(showProjectWork);
		// -----------------------------------------------
		JLabel projectTraficL = new JLabel("交通安排：");
		projectTraficL.setBounds(80, 413, 200, 25);
		p.add(projectTraficL);
		JTextField projectTraficT = new JTextField();
		projectTraficT.setBounds(150, 413, 880, 25);
		p.add(projectTraficT);
		// ---------------------------------------------------------------
		ArrayList<String> comboboxselect = new ArrayList<String>();
		String[][] pgarr = rnd.getProfessionalgroup(mf);
		int pgarrl = pgarr.length;
		JScrollPane expertGroupJSP = new JScrollPane();
		String[] cn = { "需求专业类型", "需求人数" };
		String[][] arr = { { "", "" }, { "合计（单位:人）", "0" } };
		JComboBox<String> pg = new JComboBox<String>();
		pg.addItem("请选择专业组别");
		for (int i = 0; i < pgarrl; i++) {
			pg.addItem(pgarr[i][1]);
		}
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
							JOptionPane.showMessageDialog(mf, aValue + "已选择");
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
									JOptionPane.showMessageDialog(mf, "请勿输入负数或零");
									return;
								} else {
									super.setValueAt(aValue, rowIndex, columnIndex);
								}
							}
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(mf, "只能输入数字!");
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
		pg.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String s = pg.getSelectedItem().toString();
					System.out.println(s);
				}
			}

		});
		DefaultTableModel expertGroupTableModel = new DefaultTableModel(arr, cn) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 9174681503529842494L;

			public boolean isCellEditable(int r, int c) {
				int i = expertGroupTable.getRowCount() - 1;
				if (r < i && c == 1) {
					return true;
				}
				if (r < i && c == 0) {
					return true;
				}
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
		expertGroupTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(pg));
		expertGroupTable.setRowHeight(22);
		expertGroupJSP.setViewportView(expertGroupTable);
		expertGroupJSP.setBounds(80, 455, 980, 90);
		Object[] row = new Object[2];
		row[0] = "";
		row[1] = "";
		expertGroupTable.addKeyListener(new KeyAdapter() {
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
						JOptionPane.showMessageDialog(mf, "上一行未填写完成");
					}
				}
			}
		});
		p.add(expertGroupJSP);
		// ------------------------------------------------
		JLabel avoidL = new JLabel("规避公司：");
		avoidL.setBounds(80, 555, 200, 25);
		JComboBox<String> avoidC = new JComboBox<String>();
		avoidC.setBounds(150, 555, 300, 25);
		avoidC.addItem("请选择规避公司");
		ArrayList<String> acls = rnd.getAvoidCompany(mf);
		int aclss = acls.size();
		for (int i = 0; i < aclss; i++) {
			avoidC.addItem(acls.get(i));
		}
		p.add(avoidC);
		p.add(avoidL);
		JLabel projectView = new JLabel("协会意见：");
		projectView.setBounds(80, 590, 200, 25);
		p.add(projectView);
		JTextField projectViewT = new JTextField();
		projectViewT.setBounds(150, 590, 880, 25);
		p.add(projectViewT);
		// ----------------------------------------------------
		JLabel projectMarkL = new JLabel("备注：");
		projectMarkL.setBounds(80, 625, 200, 25);
		p.add(projectMarkL);
		JTextField projectMarkT = new JTextField();
		projectMarkT.setBounds(150, 625, 880, 25);
		p.add(projectMarkT);
		JButton register = new JButton("登记");
		register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
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
				if (expertGroupTable.isEditing()) {
					expertGroupTable.getCellEditor().stopCellEditing();
				}
				if (pjId.length() != 0 && pjNeed.length() != 0 && pjNeedDate.length() != 0 && pjName.length() != 0
						&& pjNeedDay.length() != 0 && pjContact.length() != 0
						&& chooseExpertPosition.getSelectedIndex() != 0 && pjTel.length() != 0
						&& pjMeetPlace.length() != 0 && pjMeetDate.length() != 0 && pjWork.length() != 0
						&& pjTraffic.length() != 0 && pjView.length() != 0) {
					if (cf.isDate(pjMeetDate) && cf.isDate(pjNeedDate)) {
						if (cf.isFloat(pjNeedDay)) {
							if (avoidC.getSelectedIndex() == 0) {
								pjAvoid = "";
							}
							String s = expertGroupTable.getValueAt(expertGroupTable.getRowCount() - 1, 1).toString();
							int cp = Integer.parseInt(s);
							if (cp > 0) {
								//System.out.println(cp);
								ArrayList<String> exportls = new ArrayList<String>();
								String str = "insert into projectregister values(" + pjId + ",'" + pjNeed + "','"
										+ pjNeedDate + "','" + pjName + "'," + pjNeedDay + ",'" + pjContact + "'," + "'"
										+ pjOccupation + "','" + pjTel + "','" + pjMeetPlace + "','" + pjMeetDate
										+ "','" + pjWork + "','" + pjTraffic + "','" + pjAvoid + "'," + "'" + pjView
										+ "','" + pjMark + "',0);";
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
								String str2 = "";
								String[][] exportda=new String[rowc][2];
								for (int t = 0; t < rowc; t++) {
									String f = expertGroupTable.getValueAt(t, 0).toString();
									String se = expertGroupTable.getValueAt(t, 1).toString();
									exportda[t][0]=f;
									exportda[t][1]=se;
									String id = "";
									for (int tt = 0; tt < pgarrl; tt++) {
										if (f.equals(pgarr[tt][1])) {
											id = pgarr[tt][0];
										}
									}
									str2 = str2 + "insert into expertneed values(" + pjId + "," + id + "," + se
											+ ",0,NULL);";
								}
								str = str + str2;
								int i = rnd.insertProjectRegister(mf, str);
								if (i == 1) {
									AllProjectData apd=new AllProjectData();
									String[][] unfarr=apd.getUnFinishProject(mf);
									undm.setDataVector(unfarr,unfcn);
									int bf=JOptionPane.showConfirmDialog(mf,"登记成功\n是否导出excel","选择",JOptionPane.YES_NO_OPTION );
									if(bf==JOptionPane.YES_OPTION){
										ExportProject.exportExcel(mf, "text.xls",exportls,exportda);
									}
								}
							} else {
								JOptionPane.showMessageDialog(mf, "请填写需求人数");
							}
						} else {
							JOptionPane.showMessageDialog(mf, "预计工作时长请填写正数");
						}
					} else {
						JOptionPane.showMessageDialog(mf, "输入日期格式错误");
					}
				} else {
					JOptionPane.showMessageDialog(mf, "信息不完整");
				}
			}
		});
		register.setBounds(550, 680, 80, 25);
		p.add(register);
		return p;
	}
	public JPanel allProject(){
		AllProjectData apd=new AllProjectData();
		JPanel p=new JPanel();
		p.setLayout(new GridLayout(1,2,5,5));
		JPanel lp=new JPanel();
		lp.setLayout(new BorderLayout());
		JPanel rp=new JPanel();
		rp.setLayout(new BorderLayout());
		JLabel unlabel=new JLabel("未安排项目",JLabel.CENTER);
		unlabel.setFont(new Font("宋体",1, 20));
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(JLabel.CENTER);
		JTable unFinishTable=new JTable();
		unFinishTable.setDefaultRenderer(Object.class, tcr);
		unFinishTable.setRowHeight(20);
		String[][] unarr=apd.getUnFinishProject(mf);
		undm=new DefaultTableModel(unarr,unfcn){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		unFinishTable.setModel(undm);

		JPanel unp=new JPanel();
		unp.setLayout(null);
		JScrollPane unjsp=new JScrollPane();
		unjsp.setBounds(105,10,400,700);
		unjsp.setViewportView(unFinishTable);
		unp.add(unjsp);
		lp.add("North",unlabel);
		lp.add(unp);
		p.add(lp);
		
		JLabel resultlabel=new JLabel("项目抽选结果",JLabel.CENTER);
		resultlabel.setFont(new Font("宋体",1, 20));
		rp.add("North",resultlabel);
		JPanel rbp=new JPanel();
		rbp.setLayout(null);
		JScrollPane rjsp=new JScrollPane();
		String[][] rarr=apd.getFinishProject(mf);
		rdm=new DefaultTableModel(rarr,unfcn){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		JTable rtable=new JTable();
		rtable.setRowHeight(20);
		rtable.setModel(rdm);
		rtable.setDefaultRenderer(Object.class, tcr);
		rjsp.setViewportView(rtable);
		rjsp.setBounds(105,10,400,700);
		rbp.add(rjsp);
		rp.add(rbp);
		p.add(rp);
		unFinishTable.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(e.getClickCount()==2&&e.getButton()==1){
					int r=unFinishTable.rowAtPoint(e.getPoint());
					String pid=unFinishTable.getValueAt(r, 0).toString();
					mf.setEnabled(false);
					new ShowProjectFrame(mf,pid,undm,rdm,unfcn);
				}
			}
		});
		rtable.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(e.getClickCount()==2&&e.getButton()==1){
					int r=rtable.rowAtPoint(e.getPoint());
					String pid=rtable.getValueAt(r, 0).toString();
					mf.setEnabled(false);
					new ShowResultFrame(mf,pid);
				}
			}
		});
		return p;
	}
	

	public JPanel sysInfo(){
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		JLabel label=new JLabel("<html>Author：LanLemon..<br>Version：beta.1.0.1<br>User：江门市安全生产管理协会<br>"
				+ "Service Line：0750-3320133<br>Copyright 2017-2017 江门市明创智能科技有限公司 .All rights reserved.<br>Copyright 2017-2017 LanLemon Studio.All rights reserved.</html>",JLabel.CENTER);
		p.add(label,BorderLayout.CENTER);
		return p;
	}
}

class CheckBoxFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3153430997940131717L;
	ExpertData ed = new ExpertData();

	public CheckBoxFrame() {
		// default
	}

	public CheckBoxFrame(JFrame f, String[][] ls, DefaultTableModel dm, String[] cn, String expertid, String pg,
			String name, int x, int y,String fiftergroup) {
		int lss = ls.length;
		this.setTitle("修改" + name + "组别");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				f.setEnabled(true);
				dispose();
			}
		});
		this.setResizable(false);
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		JPanel tp = new JPanel();
		tp.setLayout(new GridLayout(0, 2));
		JCheckBox[] boxs = new JCheckBox[lss];
		ArrayList<Integer> oldSelect = new ArrayList<Integer>();
		for (int i = 0; i < lss; i++) {
			boxs[i] = new JCheckBox(ls[i][0] + ":" + ls[i][1]);
			String[] st = pg.split(",");
			int stl = st.length;
			for (int j = 0; j < stl; j++) {
				if (ls[i][1].equals(st[j])) {
					System.out.println(st[j]);
					boxs[i].setSelected(true);
					oldSelect.add(i);
				}
			}
			tp.add(boxs[i]);
		}
		c.add(tp);
		JPanel bp = new JPanel();
		bp.setLayout(new FlowLayout());
		JButton b = new JButton("修改");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				int count = 0;
				int osl = oldSelect.size();
				String str = "";
				for (int i = 0; i < lss; i++) {
					if (boxs[i].isSelected()) {
						count++;
						if (osl != 0) {
							for (int j = 0; j < osl; j++) {
								if (oldSelect.get(j) == i) {
									oldSelect.remove(j);
									osl = oldSelect.size();
									break;
								} else {
									str = str + "insert into expertgroup values(" + expertid + "," + ls[i][0] + ");";
								}
							}
						} else {
							str = str + "insert into expertgroup values(" + expertid + "," + ls[i][0] + ");";
						}
					} else {
						for (int j = 0; j < osl; j++) {
							if (oldSelect.get(j) == i) {
								oldSelect.remove(j);
								osl = oldSelect.size();
								str = str + "delete expertgroup where expertid=" + expertid
										+ " and professionalgroup = " + ls[i][0] + ";";
								break;
							}
						}
					}
				}
				if (count == 0) {
					JOptionPane.showMessageDialog(c, "请至少选择一项");
					f.setEnabled(true);
					dispose();
					f.setEnabled(false);
					new CheckBoxFrame(f, ls, dm, cn, expertid, pg, name, x, y,fiftergroup);
				} else {
					ed.editProfessionalGroup(f, str);
					JOptionPane.showMessageDialog(c, "修改完成");
					dm.setDataVector(ed.getExpert(f,fiftergroup), cn);
					f.setEnabled(true);
					dispose();
				}
			}
		});
		bp.add(b);
		c.add("South", bp);
		this.setBounds(x + 330, y + 350, 300, 80 + 20 * lss);
		this.setVisible(true);
	}
}

class TableViewRenderer extends JTextArea implements TableCellRenderer { 
       /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TableViewRenderer() { 
            //将表格设为自动换行
		setLineWrap(true); //利用JTextArea的自动换行方法
		setAlignmentX((float) 0.5);
       }
       public Component getTableCellRendererComponent(JTable jtable, Object obj, //obj指的是单元格内容
            boolean isSelected, boolean hasFocus, int row, int column) 
       { 
       setText(obj == null ? "" : obj.toString()); //利用JTextArea的setText设置文本方法
       return this; 
       } 
    } 


class MyFocusListener implements FocusListener {
	String info;
	JTextField jtf;

	public MyFocusListener(String info, JTextField jtf) {
		this.info = info;
		this.jtf = jtf;
	}

	@Override
	public void focusGained(FocusEvent e) {// 获得焦点的时候,清空提示文字
		String temp = jtf.getText();
		if (temp.equals(info)) {
			jtf.setText("");
		}
	}

	@Override
	public void focusLost(FocusEvent e) {// 失去焦点的时候,判断如果为空,就显示提示文字
		String temp = jtf.getText();
		if (temp.equals("")) {
			jtf.setText(info);
		}
	}
}
