package com.mc.zjgl;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ExpertManage {
	JFrame mf;
	BasicData bd = new BasicData();
	ExpertData ed = new ExpertData();

	public ExpertManage(String user) {
		mf = new JFrame();
		mf.setTitle("ExpertManage");
		mf.setResizable(false);
		mf.setBounds(10, 10, 1230, 796);
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container mfc = mf.getContentPane();
		JPanel pane = new JPanel();
		JPanel pane1 = new JPanel();
		JTabbedPane MTPane = new JTabbedPane();
		MTPane.add("基础信息", basicInfo(user));
		MTPane.add("管理专家", manageExpert());
		MTPane.add("专家需求", registerProject());
		MTPane.add("抽选结果", pane);
		MTPane.add("系统设置", pane1);
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
		String[][] professional = bd.getProfessionnal(mf);
		String[][] occupation = bd.getOccupation(mf);
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
		JComboBox<String> chooseExpertProfession = new JComboBox<String>();
		chooseExpertProfession.addItem("请选择专家专业");
		int professionall = professional.length;
		for (int i = 0; i < professionall; i++) {
			chooseExpertProfession.addItem(professional[i][1]);
		}
		chooseExpertProfession.setBounds(930, 60, 120, 25);
		p.add(chooseExpertProfession);
		JComboBox<String> chooseExpertPosition = new JComboBox<String>();
		chooseExpertPosition.addItem("请选择专家职位");
		int occupationl = occupation.length;
		for (int i = 0; i < occupationl; i++) {
			chooseExpertPosition.addItem(occupation[i][1]);
		}
		chooseExpertPosition.setBounds(30, 100, 120, 25);
		p.add(chooseExpertPosition);
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
		JScrollPane expertGroupJSP = new JScrollPane();
		String[] cn = { "ID", "专业组别", "姓名", "性别", "出生年月", "学历", "工作单位", "专业领域", "职称/职位", "联系电话", "区域", "通讯地址", "备注" };
		String[][] arr = ed.getExpert(mf);
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
				new CheckBoxFrame(mf, professionalgroup, expertGroupTableModel, cn, expertid, pg, name,
						expertGroupTable.getX(), expertGroupTable.getY());
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
				expertGroupTableModel.setDataVector(ed.getExpert(mf), cn);
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
				int cepr = chooseExpertProfession.getSelectedIndex();
				int cepo = chooseExpertPosition.getSelectedIndex();
				int et = expertTel.getText().trim().length();
				int ea = expertAddress.getText().trim().length();
				int cea = chooseExpertArea.getSelectedIndex();
				if (cg != 0 && ent != 0 && ces != 0 && ebd != 0 && cee != 0 && ec != 0 && cepr != 0 && cepo != 0
						&& et != 0 && ea != 0 && cea != 0) {
					if (expertNameT.getText().trim().equals("请输入专家姓名")
							|| expertBurnDate.getText().trim().equals("请输入专家出生日期")
							|| expertCompany.getText().trim().equals("请输入专家工作单位")
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
									professional[cepr - 1][0], occupation[cepo - 1][0], expertTel.getText().trim(),
									chooseExpertArea.getSelectedItem().toString(), expertAddress.getText().trim(),
									mark);
							expertGroupTableModel.setDataVector(ed.getExpert(mf), cn);
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
		String[][] pgarr=rnd.getProfessionalgroup(mf);
		int pgarrl=pgarr.length;
		JScrollPane expertGroupJSP = new JScrollPane();
		String[] cn = { "需求专业类型", "需求人数" };
		String[][] arr = { { "", "" }, { "合计（单位:人）", "0" } };
		JComboBox<String> pg = new JComboBox<String>();
		pg.addItem("请选择专业组别");
		for(int i=0;i<pgarrl;i++){
			pg.addItem(pgarr[i][1]);
		}
		JTable expertGroupTable = new JTable() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 5683615638208058553L;

			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
				Double num;
				if (aValue.equals("请选择专业组别")) {
					return;
				}
				if (columnIndex == 1) {
					try {
						String st = (String) aValue;
						if (st.length() != 0) {
							num = Double.parseDouble(st);
							if (num < 0) {
								JOptionPane.showMessageDialog(null, "请勿输入负数");
								return;
							} else {
								super.setValueAt(aValue, rowIndex, columnIndex);
							}
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "只能输入数字!");
						return;
					}
				}
				super.setValueAt(aValue, rowIndex, columnIndex);
			}
		};
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
					expertGroupTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(pg));
					return true;
				}
				return false;
			}
		};
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(JLabel.CENTER);
		expertGroupTable.setDefaultRenderer(Object.class, tcr);
		expertGroupTable.setModel(expertGroupTableModel);
		expertGroupTable.setRowHeight(22);
		expertGroupJSP.setViewportView(expertGroupTable);
		expertGroupJSP.setBounds(80, 455, 980, 90);
		Vector<String> row = new Vector<String>();
		expertGroupTable.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO 自动生成的方法存根
				int r = expertGroupTable.getRowCount();
				if (e.getKeyCode() == '\n') {
					expertGroupTableModel.insertRow(r - 1, row);
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
								System.out.println(cp);
								String str = "insert into projectregister values(" + pjId + ",'" + pjNeed + "','"
										+ pjNeedDate + "','" + pjName + "'," + pjNeedDay + ",'" + pjContact + "'," + "'"
										+ pjOccupation + "','" + pjTel + "','" + pjMeetPlace + "','" + pjMeetDate
										+ "','" + pjWork + "','" + pjTraffic + "','" + pjAvoid + "'," + "'" + pjView
										+ "','" + pjMark + "',0)";
								int i = rnd.insertProjectRegister(mf, str);
								if (i == 1) {
									JOptionPane.showMessageDialog(mf, "登记成功");
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
			String name, int x, int y) {
		int lss = ls.length;
		this.setTitle("修改" + name + "组别");
		JFrame ft = this;
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
					ft.dispose();
					new CheckBoxFrame(f, ls, dm, cn, expertid, pg, name, x, y);
				} else {
					ed.editProfessionalGroup(f, str);
					JOptionPane.showMessageDialog(ft, "修改完成");
					dm.setDataVector(ed.getExpert(f), cn);
					ft.dispose();
				}
			}
		});
		bp.add(b);
		c.add("South", bp);
		this.setBounds(x + 50, y + 350, 300, 80 + 20 * lss);
		this.setVisible(true);
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
