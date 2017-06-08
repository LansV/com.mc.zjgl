package com.mc.zjgl;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ExpertManage {
	JFrame mf;
	BasicData bd=new BasicData();
	public ExpertManage(String user){
		mf=new JFrame();
		mf.setTitle("ExpertManage");
		mf.setResizable(false);
		mf.setBounds(10,10,1230,796);
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container mfc=mf.getContentPane();
		JPanel pane=new JPanel();
		JPanel pane1=new JPanel();
		JTabbedPane MTPane=new JTabbedPane();
		MTPane.add("������Ϣ", basicInfo(user));
		MTPane.add("����ר��", manageExpert());
		MTPane.add("ר������", registerProject());
		MTPane.add("��ѡ���",pane);
		MTPane.add("ϵͳ����",pane1);
		mfc.add(MTPane);
		mf.setVisible(true);
	}
	public static void main(String[] args){
		new ExpertManage("test");
	}
	public JPanel basicInfo(String user){
		JPanel p=new JPanel();
		p.setLayout(null);
		JLabel ptitle=new JLabel("������Ϣ���",JLabel.CENTER);
		Font f=new Font("����", 1, 25);
		ptitle.setFont(f);
		ptitle.setBounds(515,20,200,25);
		p.add(ptitle);
		//---------------------------------------------------------
		JLabel expertGT=new JLabel("����дרҵ���",JLabel.CENTER);
		expertGT.setBounds(30,60,200,25);
		p.add(expertGT);
		JTextField expertGroup=new JTextField();
		expertGroup.setBounds(30,90,200,25);
		p.add(expertGroup);
		JButton addExpertGroup=new JButton("������");
		addExpertGroup.setBounds(85,125,90,25);
		addExpertGroup.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				    String s=expertGroup.getText().trim();
				    if(s.length()!=0){
				    	new SQLFilter(mf,s,user);
				    	bd.addGroup(mf, s);
				    }else{
				    	JOptionPane.showMessageDialog(mf, "���벻��Ϊ��");
				    }
			}
		});
		p.add(addExpertGroup);
		JScrollPane expertGroupJSP=new JScrollPane();
		String[] cn={"GroupID","רҵ���"};
		String[][] arr={{"1","�����"},{"2","ִ����"}};
		JTable expertGroupTable=new JTable();
		DefaultTableModel expertGroupTableModel=new DefaultTableModel(arr,cn);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(JLabel.CENTER);
		expertGroupTable.setDefaultRenderer(Object.class, tcr);
		expertGroupTable.setModel(expertGroupTableModel);
		expertGroupTable.setRowHeight(22);
		expertGroupJSP.setViewportView(expertGroupTable);
		expertGroupJSP.setBounds(30,165,200,550);
		p.add(expertGroupJSP);
		//-----------------------------------------------------------------------
		JLabel expertGT1=new JLabel("����дרҵ����",JLabel.CENTER);
		expertGT1.setBounds(300,60,200,25);
		p.add(expertGT1);
		JTextField expertGroup1=new JTextField();
		expertGroup1.setBounds(300,90,200,25);
		p.add(expertGroup1);
		JButton addExpertGroup1=new JButton("���רҵ");
		addExpertGroup1.setBounds(355,125,90,25);
		addExpertGroup1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				    String s=expertGroup1.getText().trim();
				    if(s.length()!=0){
				    	new SQLFilter(mf,s,user);
				    	bd.addGroup(mf, s);
				    }else{
				    	JOptionPane.showMessageDialog(mf, "���벻��Ϊ��");
				    }
			}
		});
		p.add(addExpertGroup1);
		JScrollPane expertGroupJSP1=new JScrollPane();
		String[] cn1={"ProfessionID","רҵ����"};
		String[][] arr1={{"1","רҵ1"},{"2","רҵ2"}};
		JTable expertGroupTable1=new JTable();
		DefaultTableModel expertGroupTableModel1=new DefaultTableModel(arr1,cn1);
		expertGroupTable1.setDefaultRenderer(Object.class, tcr);
		expertGroupTable1.setModel(expertGroupTableModel1);
		expertGroupTable1.setRowHeight(22);
		expertGroupJSP1.setViewportView(expertGroupTable1);
		expertGroupJSP1.setBounds(300,165,200,550);
		p.add(expertGroupJSP1);
		//-----------------------------------------------------------
		JLabel expertGT11=new JLabel("�����...",JLabel.CENTER);
		expertGT11.setBounds(600,60,200,25);
		p.add(expertGT11);
		//
		JLabel expertGT111=new JLabel("�����...",JLabel.CENTER);
		expertGT111.setBounds(900,60,200,25);
		p.add(expertGT111);
		return p;
	}
	public JPanel manageExpert(){
		JPanel p=new JPanel();
		p.setLayout(null);
		JLabel ptitle=new JLabel("���ר����Ϣ",JLabel.CENTER);
		Font f=new Font("����", 1, 25);
		ptitle.setFont(f);
		ptitle.setBounds(515,20,200,25);
		p.add(ptitle);
		JComboBox<String> chooseExpertGroup=new JComboBox<String>();
		chooseExpertGroup.addItem("��ѡ��רҵ���");
		chooseExpertGroup.setBounds(30,60,120,25);
		p.add(chooseExpertGroup);
		JTextField expertNameT=new JTextField("������ר������");
		expertNameT.setBounds(180,60,120,25);
		p.add(expertNameT);
		JComboBox<String> chooseExpertSex=new JComboBox<String>();
		chooseExpertSex.addItem("��ѡ��רҵ�Ա�");
		chooseExpertSex.setBounds(330,60,120,25);
		p.add(chooseExpertSex);
		JTextField expertBurnDate=new JTextField("������ר�ҳ�������");
		expertBurnDate.setBounds(480,60,120,25);
		p.add(expertBurnDate);
		JComboBox<String> chooseExpertEducation=new JComboBox<String>();
		chooseExpertEducation.addItem("��ѡ��רҵѧ��");
		chooseExpertEducation.setBounds(630,60,120,25);
		p.add(chooseExpertEducation);
		JTextField expertCompany=new JTextField("������ר�ҹ�����λ");
		expertCompany.setBounds(780,60,120,25);
		p.add(expertCompany);
		JComboBox<String> chooseExpertProfession=new JComboBox<String>();
		chooseExpertProfession.addItem("��ѡ��ר��רҵ");
		chooseExpertProfession.setBounds(930,60,120,25);
		p.add(chooseExpertProfession);
		JComboBox<String> chooseExpertPosition=new JComboBox<String>();
		chooseExpertPosition.addItem("��ѡ��ר��ְλ");
		chooseExpertPosition.setBounds(30,100,120,25);
		p.add(chooseExpertPosition);
		JTextField expertTel=new JTextField("��������ϵ�绰");
		expertTel.setBounds(180,100,120,25);
		p.add(expertTel);
		JTextField expertAddress=new JTextField("��������ϵ��ַ");
		expertAddress.setBounds(330,100,500,25);
		p.add(expertAddress);
		JTextField expertMark=new JTextField("�����뱸ע");
		expertMark.setBounds(30,140,800,25);
		p.add(expertMark);
		JButton addExpert=new JButton("���");
		addExpert.setBounds(575,180,80,25);
		p.add(addExpert);
		JScrollPane expertGroupJSP=new JScrollPane();
		String[] cn={"ID","רҵ���","����","�Ա�","��������","ѧ��","������λ","רҵ����","ְ��/ְλ","��ϵ�绰","ͨѶ��ַ","��ע"};
		String[][] arr={{"1","�����","test1","��","1984-2-5","����","����","���","����Ա","0750-3320133","�㶫����","������Ϣ"},{"2","ִ����","test2","Ů","1987-6-5","����","����","���","����","0750-3320133","�㶫����","������Ϣ"}};
		JTable expertGroupTable=new JTable();
		DefaultTableModel expertGroupTableModel=new DefaultTableModel(arr,cn);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(JLabel.CENTER);
		expertGroupTable.setDefaultRenderer(Object.class, tcr);
		expertGroupTable.setModel(expertGroupTableModel);
		expertGroupTable.setRowHeight(22);
		expertGroupJSP.setViewportView(expertGroupTable);
		expertGroupJSP.setBounds(30,220,1160,500);
		p.add(expertGroupJSP);
		return p;
	}
	public JPanel registerProject(){
		JPanel p=new JPanel();
		p.setLayout(null);
		JLabel ptitle=new JLabel("ר������Ǽ�",JLabel.CENTER);
		Font f=new Font("����", 1, 25);
		ptitle.setFont(f);
		ptitle.setBounds(515,20,200,25);
		p.add(ptitle);
		//-----------------------------------------------------
		JLabel projectId=new JLabel("��Ŀ���:XXXXXXXXXX");
		projectId.setBounds(30,60,200,20);
		p.add(projectId);
		JLabel projectNeedL=new JLabel("����λ��");
		projectNeedL.setBounds(30,95,200,25);
		p.add(projectNeedL);
		JTextField projectNeedT=new JTextField();
		projectNeedT.setBounds(100,95,300,25);
		p.add(projectNeedT);
		JLabel projectDateL=new JLabel("���ڣ�");
		projectDateL.setBounds(800,95,200,25);
		p.add(projectDateL);
		JTextField projectDateT=new JTextField();
		projectDateT.setBounds(840,95,120,25);
		p.add(projectDateT);
		//--------------------
		JLabel projectNameL=new JLabel("��Ŀ���ƣ�");
		projectNameL.setBounds(30,130,200,25);
		p.add(projectNameL);
		JTextField projectNameT=new JTextField();
		projectNameT.setBounds(100,130,300,25);
		p.add(projectNameT);
		JLabel projectDateL1=new JLabel("Ԥ�ƹ���ʱ����                       ��");
		projectDateL1.setBounds(800,130,500,25);
		p.add(projectDateL1);
		JTextField projectDateT1=new JTextField();
		projectDateT1.setBounds(890,130,60,25);
		p.add(projectDateT1);
		//-------------------------------------------
		JLabel projectContactL=new JLabel("��Ŀ��ϵ�ˣ�");
		projectContactL.setBounds(30,165,200,25);
		p.add(projectContactL);
		JTextField projectContactT=new JTextField();
		projectContactT.setBounds(110,165,300,25);
		p.add(projectContactT);
		JLabel projectPosstionL=new JLabel("ְλ��");
		projectPosstionL.setBounds(500,165,200,25);
		p.add(projectPosstionL);
		JComboBox<String> chooseExpertPosition=new JComboBox<String>();
		chooseExpertPosition.addItem("��ѡ��ְλ");
		chooseExpertPosition.setBounds(535,165,120,25);
		p.add(chooseExpertPosition);
		JLabel projectTelL=new JLabel("��ϵ�绰��");
		projectTelL.setBounds(800,165,500,25);
		p.add(projectTelL);
		JTextField projectTelT=new JTextField();
		projectTelT.setBounds(860,165,120,25);
		p.add(projectTelT);
		//--------------------------------------------
		JLabel projectMeetPlaceL=new JLabel("���ϵص㣺");
		projectMeetPlaceL.setBounds(30,200,200,25);
		p.add(projectMeetPlaceL);
		JTextField projectMeetPlaceT=new JTextField();
		projectMeetPlaceT.setBounds(100,200,200,25);
		p.add(projectMeetPlaceT);
		JLabel projectMeetDateL=new JLabel("�������ڣ�");
		projectMeetDateL.setBounds(800,200,200,25);
		p.add(projectMeetDateL);
		JTextField projectMeetDateT=new JTextField();
		projectMeetDateT.setBounds(860,200,120,25);
		p.add(projectMeetDateT);
		//----------------------------------------------
		JLabel projectWorkL=new JLabel("�������ݣ�");
		projectWorkL.setBounds(30,245,200,25);
		p.add(projectWorkL);
		JTextField projectWorkT=new JTextField();
		projectWorkT.setBounds(100,235,880,50);
		p.add(projectWorkT);
		//-----------------------------------------------
		JLabel projectTraficL=new JLabel("��ͨ���ţ�");
		projectTraficL.setBounds(30,305,200,25);
		p.add(projectTraficL);
		JTextField projectTraficT=new JTextField();
		projectTraficT.setBounds(100,295,880,50);
		p.add(projectTraficT);
		//---------------------------------------------------------------
		JScrollPane expertGroupJSP=new JScrollPane();
		String[] cn={"����רҵ����","��������"};
		String[][] arr={{"�����","1"},{"ִ����","2"},{"�ϼƣ���λ:�ˣ�","3"}};
		JTable expertGroupTable=new JTable();
		DefaultTableModel expertGroupTableModel=new DefaultTableModel(arr,cn);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(JLabel.CENTER);
		expertGroupTable.setDefaultRenderer(Object.class, tcr);
		expertGroupTable.setModel(expertGroupTableModel);
		expertGroupTable.setRowHeight(22);
		expertGroupJSP.setViewportView(expertGroupTable);
		expertGroupJSP.setBounds(30,355,980,90);
		p.add(expertGroupJSP);
		//------------------------------------------------
		JLabel projectView=new JLabel("Э�������");
		projectView.setBounds(30,455,200,25);
		p.add(projectView);
		JTextField projectViewT=new JTextField();
		projectViewT.setBounds(100,455,880,25);
		p.add(projectViewT);
		//----------------------------------------------------
		JLabel projectMarkL=new JLabel("��ע��");
		projectMarkL.setBounds(30,490,200,25);
		p.add(projectMarkL);
		JTextField projectMarkT=new JTextField();
		projectMarkT.setBounds(100,490,880,25);
		p.add(projectMarkT);
		return p;
	}
}
