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
	String[] unfcn={"��Ŀ���","״̬"};
	public ExpertManage(String user) {
		mf = new JFrame();
		mf.setTitle("ExpertManage");
		mf.setResizable(false);
		mf.setBounds(10, 10, 1230, 796);
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container mfc = mf.getContentPane();
		JTabbedPane MTPane = new JTabbedPane();
		MTPane.add("������Ϣ", basicInfo(user));
		MTPane.add("����ר��", manageExpert());
		MTPane.add("ר������", registerProject());
		MTPane.add("��ѡ���", allProject());
		MTPane.add("ϵͳ����", sysInfo());
		mfc.add(MTPane);
		mf.setVisible(true);
	}
	public static void main(String[] args) {
		new ExpertManage("test");
	}

	public JPanel basicInfo(String user) {
		JPanel p = new JPanel();
		p.setLayout(null);
		JLabel ptitle = new JLabel("������Ϣ���", JLabel.CENTER);
		Font f = new Font("����", 1, 25);
		ptitle.setFont(f);
		ptitle.setBounds(515, 20, 200, 25);
		p.add(ptitle);
		// ---------------------------------------------------------
		JLabel expertGT = new JLabel("����дרҵ���", JLabel.CENTER);
		expertGT.setBounds(30, 60, 200, 25);
		p.add(expertGT);
		JTextField expertGroup = new JTextField();
		expertGroup.setBounds(30, 90, 200, 25);
		p.add(expertGroup);
		JButton addExpertGroup = new JButton("������");
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
		String[] cn = { "GroupID", "רҵ���" };
		String[][] arr = bd.getGroup(mf);
		JTable expertGroupTable = new JTable();
		DefaultTableModel expertGroupTableModel = new DefaultTableModel(arr, cn);
		addExpertGroup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				String s = expertGroup.getText().trim();
				if (s.length() != 0) {
					new SQLFilter(mf, s, user);
					int i = bd.addGroup(mf, s);
					if (i == 1) {
						JOptionPane.showMessageDialog(mf, "���רҵ���ɹ�");
					}
					expertGroupTableModel.setDataVector(bd.getGroup(mf), cn);
					expertGroup.setText("");
				} else {
					JOptionPane.showMessageDialog(mf, "���벻��Ϊ��");
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
		JLabel expertGT1 = new JLabel("����дרҵ����", JLabel.CENTER);
		expertGT1.setBounds(300, 60, 200, 25);
		p.add(expertGT1);
		JTextField expertGroup1 = new JTextField();
		expertGroup1.setBounds(300, 90, 200, 25);
		p.add(expertGroup1);
		JButton addExpertGroup1 = new JButton("���רҵ");
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
		String[] cn1 = { "ProfessionID", "רҵ����" };
		String[][] arr1 = bd.getProfessionnal(mf);
		JTable expertGroupTable1 = new JTable();
		DefaultTableModel expertGroupTableModel1 = new DefaultTableModel(arr1, cn1);
		addExpertGroup1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				String s = expertGroup1.getText().trim();
				if (s.length() != 0) {
					new SQLFilter(mf, s, user);
					int i = bd.addProfessional(mf, s);
					if (i == 1) {
						JOptionPane.showMessageDialog(mf, "���רҵ���ɹ�");
					}
					expertGroupTableModel1.setDataVector(bd.getProfessionnal(mf), cn1);
					expertGroup1.setText("");
				} else {
					JOptionPane.showMessageDialog(mf, "���벻��Ϊ��");
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
		JLabel expertGT11 = new JLabel("�����...", JLabel.CENTER);
		expertGT11.setBounds(600, 60, 200, 25);
		p.add(expertGT11);
		//
		JLabel expertGT111 = new JLabel("�����...", JLabel.CENTER);
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
		JLabel ptitle = new JLabel("���ר����Ϣ", JLabel.CENTER);
		Font f = new Font("����", 1, 25);
		ptitle.setFont(f);
		ptitle.setBounds(515, 20, 200, 25);
		p.add(ptitle);
		JComboBox<String> chooseExpertGroup = new JComboBox<String>();
		chooseExpertGroup.addItem("��ѡ��רҵ���");
		int pgl = professionalgroup.length;
		for (int i = 0; i < pgl; i++) {
			chooseExpertGroup.addItem(professionalgroup[i][1]);
		}
		chooseExpertGroup.setBounds(30, 60, 120, 25);
		p.add(chooseExpertGroup);
		JTextField expertNameT = new JTextField("������ר������");
		expertNameT.addFocusListener(new MyFocusListener("������ר������", expertNameT));
		expertNameT.setBounds(180, 60, 120, 25);
		p.add(expertNameT);
		JComboBox<String> chooseExpertSex = new JComboBox<String>();
		chooseExpertSex.addItem("��ѡ��רҵ�Ա�");
		int sexl = sex.length;
		for (int i = 0; i < sexl; i++) {
			chooseExpertSex.addItem(sex[i][1]);
		}
		chooseExpertSex.setBounds(330, 60, 120, 25);
		p.add(chooseExpertSex);
		JTextField expertBurnDate = new JTextField("������ר�ҳ�������");
		expertBurnDate.addFocusListener(new MyFocusListener("������ר�ҳ�������", expertBurnDate));
		expertBurnDate.setBounds(480, 60, 120, 25);
		p.add(expertBurnDate);
		JComboBox<String> chooseExpertEducation = new JComboBox<String>();
		chooseExpertEducation.addItem("��ѡ��רҵѧ��");
		int educationl = education.length;
		for (int i = 0; i < educationl; i++) {
			chooseExpertEducation.addItem(education[i][1]);
		}
		chooseExpertEducation.setBounds(630, 60, 120, 25);
		p.add(chooseExpertEducation);
		JTextField expertCompany = new JTextField("������ר�ҹ�����λ");
		expertCompany.addFocusListener(new MyFocusListener("������ר�ҹ�����λ", expertCompany));
		expertCompany.setBounds(780, 60, 120, 25);
		p.add(expertCompany);
/*		JComboBox<String> chooseExpertProfession = new JComboBox<String>();
		chooseExpertProfession.addItem("��ѡ��ר��רҵ");
		int professionall = professional.length;
		for (int i = 0; i < professionall; i++) {
			chooseExpertProfession.addItem(professional[i][1]);
		}
		chooseExpertProfession.setBounds(930, 60, 120, 25);
		p.add(chooseExpertProfession);*/
		JTextField expertProfessional = new JTextField("������ר��רҵ����");
		expertProfessional.addFocusListener(new MyFocusListener("������ר��רҵ����", expertProfessional));
		expertProfessional.setBounds(930, 60, 120, 25);
		p.add(expertProfessional);
/*		JComboBox<String> chooseExpertPosition = new JComboBox<String>();
		chooseExpertPosition.addItem("��ѡ��ר��ְλ");
		int occupationl = occupation.length;
		for (int i = 0; i < occupationl; i++) {
			chooseExpertPosition.addItem(occupation[i][1]);
		}
		chooseExpertPosition.setBounds(30, 100, 120, 25);
		p.add(chooseExpertPosition);*/
		JTextField expertOccupation = new JTextField("������ר��ְλ");
		expertOccupation.addFocusListener(new MyFocusListener("������ר��ְλ", expertOccupation));
		expertOccupation.setBounds(30, 100, 120, 25);
		p.add(expertOccupation);
		JTextField expertTel = new JTextField("��������ϵ�绰");
		expertTel.addFocusListener(new MyFocusListener("��������ϵ�绰", expertTel));
		expertTel.setBounds(180, 100, 120, 25);
		p.add(expertTel);
		JTextField expertAddress = new JTextField("��������ϵ��ַ");
		expertAddress.addFocusListener(new MyFocusListener("��������ϵ��ַ", expertAddress));
		expertAddress.setBounds(330, 100, 500, 25);
		p.add(expertAddress);
		JComboBox<String> chooseExpertArea = new JComboBox<String>();
		chooseExpertArea.addItem("��ѡ������");
		chooseExpertArea.addItem("����");
		chooseExpertArea.addItem("����");
		chooseExpertArea.setBounds(870, 100, 120, 25);
		p.add(chooseExpertArea);
		JTextField expertMark = new JTextField("�����뱸ע");
		expertMark.addFocusListener(new MyFocusListener("�����뱸ע", expertMark));
		expertMark.setBounds(30, 140, 800, 25);
		p.add(expertMark);
		JButton addExpert = new JButton("���");
		addExpert.setBounds(575, 180, 80, 25);
		p.add(addExpert);
		JComboBox<String> fifterGroup = new JComboBox<String>();
		fifterGroup.addItem("ɸѡרҵ���");
		for (int i = 0; i < pgl; i++) {
			fifterGroup.addItem(professionalgroup[i][1]);
		}
		fifterGroup.setBounds(30, 180, 120, 25);
		p.add(fifterGroup);
		JScrollPane expertGroupJSP = new JScrollPane();
		String[] cn = { "ID", "רҵ���", "����", "�Ա�", "��������", "ѧ��", "������λ", "רҵ����", "ְ��/ְλ", "��ϵ�绰", "����", "ͨѶ��ַ", "��ע" };
		String[][] arr = ed.getExpert(mf,fiftergroup);
		JTable expertGroupTable = new JTable();
		DefaultTableModel expertGroupTableModel = new DefaultTableModel(arr, cn);
		JPopupMenu editRight = new JPopupMenu();
		JMenuItem edit = new JMenuItem("�༭");
		JMenuItem delete = new JMenuItem("ɾ��");
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
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
				// TODO �Զ����ɵķ������
				int sr = expertGroupTable.getSelectedRow();
				String expertid = expertGroupTable.getValueAt(sr, 0).toString().trim();
				ed.deleteExpert(mf, expertid);
				JOptionPane.showMessageDialog(mf, "ɾ���ɹ�");
				expertGroupTableModel.setDataVector(ed.getExpert(mf,fiftergroup), cn);
			}
		});
		expertGroupTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO �Զ����ɵķ������
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
				// TODO �Զ����ɵķ������
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
					if (expertNameT.getText().trim().equals("������ר������")
							|| expertBurnDate.getText().trim().equals("������ר�ҳ�������")
							|| expertCompany.getText().trim().equals("������ר�ҹ�����λ")
							||expertProfessional.getText().trim().equals("������ר��רҵ����")
							||expertOccupation.getText().trim().equals("������ר��ְλ")
							|| expertTel.getText().trim().equals("��������ϵ�绰")
							|| expertAddress.getText().trim().equals("��������ϵ��ַ")) {
						JOptionPane.showMessageDialog(mf, "¼����Ϣ������");
					} else {
						try {
							String birthday = expertBurnDate.getText().trim();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// Сд��mm��ʾ���Ƿ���
							String dstr = birthday;
							@SuppressWarnings("unused")
							java.util.Date date = sdf.parse(dstr);
							System.out.println(birthday);
							String mark = "";
							if (expertMark.getText().trim().equals("�����뱸ע") != true) {
								mark = expertMark.getText().trim();
							}
							ed.insertExpert(mf, professionalgroup[cg - 1][0], expertNameT.getText().trim(),
									chooseExpertSex.getSelectedItem().toString(), birthday,
									chooseExpertEducation.getSelectedItem().toString(), expertCompany.getText().trim(),
									expertProfessional.getText().trim(), expertOccupation.getText().trim(), expertTel.getText().trim(),
									chooseExpertArea.getSelectedItem().toString(), expertAddress.getText().trim(),
									mark);
							expertGroupTableModel.setDataVector(ed.getExpert(mf,fiftergroup), cn);
							chooseExpertGroup.setSelectedIndex(0);expertNameT.setText("������ר������");
							chooseExpertSex.setSelectedIndex(0);;expertBurnDate.setText("������ר�ҳ�������");
							chooseExpertEducation.setSelectedIndex(0);expertCompany.setText("������ר�ҹ�����λ");
							expertProfessional.setText("������ר��רҵ����");expertOccupation.setText("������ר��ְλ");
							expertTel.setText("��������ϵ�绰");expertAddress.setText("��������ϵ��ַ");
							expertMark.setText("�����뱸ע");
						} catch (Exception e1) {
							StringWriter sw = new StringWriter();
							PrintWriter pw = new PrintWriter(sw, true);
							e1.printStackTrace(pw);
							String mn = new Exception().getStackTrace()[0].getMethodName();// ��õ�ǰ�ķ�����
							String dn = new Exception().getStackTrace()[1].getClassName();// ��õ�����
							String errorString = "�����ࣺ" + this.getClass().getName() + "\n���󷽷���" + mn + "\n�����ࣺ" + dn
									+ "\n������Ϣ��\n" + sw.toString();
							Rectangle b = mf.getBounds();
							new ErrorDialog(mf, b, errorString);
						}
					}
				} else {
					JOptionPane.showMessageDialog(mf, "¼����Ϣ������");
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
		JLabel ptitle = new JLabel("ר������Ǽ�", JLabel.CENTER);
		Font f = new Font("����", 1, 25);
		ptitle.setFont(f);
		ptitle.setBounds(515, 20, 200, 25);
		p.add(ptitle);
		// -----------------------------------------------------
		JLabel projectIdL = new JLabel("��Ŀ��ţ�");
		projectIdL.setBounds(80, 60, 200, 20);
		JLabel projectId = new JLabel(rnd.getProjectId(mf));
		projectId.setBounds(150, 60, 200, 20);
		p.add(projectId);
		p.add(projectIdL);
		JLabel projectNeedL = new JLabel("����λ��");
		projectNeedL.setBounds(80, 95, 200, 25);
		p.add(projectNeedL);
		JTextField projectNeedT = new JTextField();
		projectNeedT.setBounds(150, 95, 300, 25);
		p.add(projectNeedT);
		JLabel projectDateL = new JLabel("���ڣ�");
		projectDateL.setBounds(850, 95, 200, 25);
		p.add(projectDateL);
		JTextField projectDateT = new JTextField();
		projectDateT.setBounds(890, 95, 120, 25);
		p.add(projectDateT);
		// --------------------
		JLabel projectNameL = new JLabel("��Ŀ���ƣ�");
		projectNameL.setBounds(80, 130, 200, 25);
		p.add(projectNameL);
		JTextField projectNameT = new JTextField();
		projectNameT.setBounds(150, 130, 300, 25);
		p.add(projectNameT);
		JLabel projectDateL1 = new JLabel("Ԥ�ƹ���ʱ����                       ��");
		projectDateL1.setBounds(850, 130, 500, 25);
		p.add(projectDateL1);
		JTextField projectDateT1 = new JTextField();
		projectDateT1.setBounds(940, 130, 60, 25);
		p.add(projectDateT1);
		// -------------------------------------------
		JLabel projectContactL = new JLabel("��Ŀ��ϵ�ˣ�");
		projectContactL.setBounds(80, 165, 200, 25);
		p.add(projectContactL);
		JTextField projectContactT = new JTextField();
		projectContactT.setBounds(160, 165, 300, 25);
		p.add(projectContactT);
		JLabel projectPosstionL = new JLabel("ְλ��");
		projectPosstionL.setBounds(550, 165, 200, 25);
		p.add(projectPosstionL);
		JComboBox<String> chooseExpertPosition = new JComboBox<String>();
		chooseExpertPosition.addItem("��ѡ��ְλ");
		ArrayList<String> ocls = rnd.getOccupation(mf);
		int oclss = ocls.size();
		for (int i = 0; i < oclss; i++) {
			chooseExpertPosition.addItem(ocls.get(i));
		}
		chooseExpertPosition.setBounds(585, 165, 120, 25);
		p.add(chooseExpertPosition);
		JLabel projectTelL = new JLabel("��ϵ�绰��");
		projectTelL.setBounds(850, 165, 500, 25);
		p.add(projectTelL);
		JTextField projectTelT = new JTextField();
		projectTelT.setBounds(910, 165, 120, 25);
		p.add(projectTelT);
		// --------------------------------------------
		JLabel projectMeetPlaceL = new JLabel("���ϵص㣺");
		projectMeetPlaceL.setBounds(80, 200, 200, 25);
		p.add(projectMeetPlaceL);
		JTextField projectMeetPlaceT = new JTextField();
		projectMeetPlaceT.setBounds(150, 200, 200, 25);
		p.add(projectMeetPlaceT);
		JLabel projectMeetDateL = new JLabel("�������ڣ�");
		projectMeetDateL.setBounds(850, 200, 200, 25);
		p.add(projectMeetDateL);
		JTextField projectMeetDateT = new JTextField();
		projectMeetDateT.setBounds(910, 200, 120, 25);
		p.add(projectMeetDateT);
		// ----------------------------------------------
		JLabel projectWorkL = new JLabel("�������ݣ�");
		projectWorkL.setBounds(80, 245, 200, 25);
		p.add(projectWorkL);
		JScrollPane showProjectWork = new JScrollPane();
		JTextArea projectWorkT = new JTextArea();
		projectWorkT.setLineWrap(true);
		showProjectWork.setViewportView(projectWorkT);
		showProjectWork.setBounds(150, 245, 880, 150);
		p.add(showProjectWork);
		// -----------------------------------------------
		JLabel projectTraficL = new JLabel("��ͨ���ţ�");
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
		String[] cn = { "����רҵ����", "��������" };
		String[][] arr = { { "", "" }, { "�ϼƣ���λ:�ˣ�", "0" } };
		JComboBox<String> pg = new JComboBox<String>();
		pg.addItem("��ѡ��רҵ���");
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
					if (aValue.equals("��ѡ��רҵ���")) {
						return;
					}
					int cs = comboboxselect.size();
					for (int i = 0; i < cs; i++) {
						if (aValue.equals(comboboxselect.get(i))) {
							JOptionPane.showMessageDialog(mf, aValue + "��ѡ��");
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
									JOptionPane.showMessageDialog(mf, "�������븺������");
									return;
								} else {
									super.setValueAt(aValue, rowIndex, columnIndex);
								}
							}
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(mf, "ֻ����������!");
							return;
						}
					} else {
						JOptionPane.showMessageDialog(this, "����ѡ�����");
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
				// TODO �Զ����ɵķ������
				int r = expertGroupTable.getRowCount();
				if (e.getKeyCode() == '\n') {
					String s = expertGroupTableModel.getValueAt(r - 2, 1).toString();
					System.out.println(s);
					if (s.length() != 0) {
						expertGroupTableModel.insertRow(r - 1, row);
					} else {
						JOptionPane.showMessageDialog(mf, "��һ��δ��д���");
					}
				}
			}
		});
		p.add(expertGroupJSP);
		// ------------------------------------------------
		JLabel avoidL = new JLabel("��ܹ�˾��");
		avoidL.setBounds(80, 555, 200, 25);
		JComboBox<String> avoidC = new JComboBox<String>();
		avoidC.setBounds(150, 555, 300, 25);
		avoidC.addItem("��ѡ���ܹ�˾");
		ArrayList<String> acls = rnd.getAvoidCompany(mf);
		int aclss = acls.size();
		for (int i = 0; i < aclss; i++) {
			avoidC.addItem(acls.get(i));
		}
		p.add(avoidC);
		p.add(avoidL);
		JLabel projectView = new JLabel("Э�������");
		projectView.setBounds(80, 590, 200, 25);
		p.add(projectView);
		JTextField projectViewT = new JTextField();
		projectViewT.setBounds(150, 590, 880, 25);
		p.add(projectViewT);
		// ----------------------------------------------------
		JLabel projectMarkL = new JLabel("��ע��");
		projectMarkL.setBounds(80, 625, 200, 25);
		p.add(projectMarkL);
		JTextField projectMarkT = new JTextField();
		projectMarkT.setBounds(150, 625, 880, 25);
		p.add(projectMarkT);
		JButton register = new JButton("�Ǽ�");
		register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
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
									int bf=JOptionPane.showConfirmDialog(mf,"�Ǽǳɹ�\n�Ƿ񵼳�excel","ѡ��",JOptionPane.YES_NO_OPTION );
									if(bf==JOptionPane.YES_OPTION){
										ExportProject.exportExcel(mf, "text.xls",exportls,exportda);
									}
								}
							} else {
								JOptionPane.showMessageDialog(mf, "����д��������");
							}
						} else {
							JOptionPane.showMessageDialog(mf, "Ԥ�ƹ���ʱ������д����");
						}
					} else {
						JOptionPane.showMessageDialog(mf, "�������ڸ�ʽ����");
					}
				} else {
					JOptionPane.showMessageDialog(mf, "��Ϣ������");
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
		JLabel unlabel=new JLabel("δ������Ŀ",JLabel.CENTER);
		unlabel.setFont(new Font("����",1, 20));
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
		
		JLabel resultlabel=new JLabel("��Ŀ��ѡ���",JLabel.CENTER);
		resultlabel.setFont(new Font("����",1, 20));
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
		JLabel label=new JLabel("<html>Author��LanLemon..<br>Version��beta.1.0.1<br>User�������а�ȫ��������Э��<br>"
				+ "Service Line��0750-3320133<br>Copyright 2017-2017 �������������ܿƼ����޹�˾ .All rights reserved.<br>Copyright 2017-2017 LanLemon Studio.All rights reserved.</html>",JLabel.CENTER);
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
		this.setTitle("�޸�" + name + "���");
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
		JButton b = new JButton("�޸�");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
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
					JOptionPane.showMessageDialog(c, "������ѡ��һ��");
					f.setEnabled(true);
					dispose();
					f.setEnabled(false);
					new CheckBoxFrame(f, ls, dm, cn, expertid, pg, name, x, y,fiftergroup);
				} else {
					ed.editProfessionalGroup(f, str);
					JOptionPane.showMessageDialog(c, "�޸����");
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
            //�������Ϊ�Զ�����
		setLineWrap(true); //����JTextArea���Զ����з���
		setAlignmentX((float) 0.5);
       }
       public Component getTableCellRendererComponent(JTable jtable, Object obj, //objָ���ǵ�Ԫ������
            boolean isSelected, boolean hasFocus, int row, int column) 
       { 
       setText(obj == null ? "" : obj.toString()); //����JTextArea��setText�����ı�����
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
	public void focusGained(FocusEvent e) {// ��ý����ʱ��,�����ʾ����
		String temp = jtf.getText();
		if (temp.equals(info)) {
			jtf.setText("");
		}
	}

	@Override
	public void focusLost(FocusEvent e) {// ʧȥ�����ʱ��,�ж����Ϊ��,����ʾ��ʾ����
		String temp = jtf.getText();
		if (temp.equals("")) {
			jtf.setText(info);
		}
	}
}
