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
		MTPane.add("基础信息", basicInfo(user));
		MTPane.add("管理专家", manageExpert());
		MTPane.add("专家需求", registerProject());
		MTPane.add("抽选结果",pane);
		MTPane.add("系统设置",pane1);
		mfc.add(MTPane);
		mf.setVisible(true);
	}
	public static void main(String[] args){
		new ExpertManage("test");
	}
	public JPanel basicInfo(String user){
		JPanel p=new JPanel();
		p.setLayout(null);
		JLabel ptitle=new JLabel("基础信息添加",JLabel.CENTER);
		Font f=new Font("宋体", 1, 25);
		ptitle.setFont(f);
		ptitle.setBounds(515,20,200,25);
		p.add(ptitle);
		//---------------------------------------------------------
		JLabel expertGT=new JLabel("请填写专业组别",JLabel.CENTER);
		expertGT.setBounds(30,60,200,25);
		p.add(expertGT);
		JTextField expertGroup=new JTextField();
		expertGroup.setBounds(30,90,200,25);
		p.add(expertGroup);
		JButton addExpertGroup=new JButton("添加组别");
		addExpertGroup.setBounds(85,125,90,25);
		addExpertGroup.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				    String s=expertGroup.getText().trim();
				    if(s.length()!=0){
				    	new SQLFilter(mf,s,user);
				    	bd.addGroup(mf, s);
				    }else{
				    	JOptionPane.showMessageDialog(mf, "输入不能为空");
				    }
			}
		});
		p.add(addExpertGroup);
		JScrollPane expertGroupJSP=new JScrollPane();
		String[] cn={"GroupID","专业组别"};
		String[][] arr={{"1","检查组"},{"2","执行组"}};
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
		JLabel expertGT1=new JLabel("请填写专业领域",JLabel.CENTER);
		expertGT1.setBounds(300,60,200,25);
		p.add(expertGT1);
		JTextField expertGroup1=new JTextField();
		expertGroup1.setBounds(300,90,200,25);
		p.add(expertGroup1);
		JButton addExpertGroup1=new JButton("添加专业");
		addExpertGroup1.setBounds(355,125,90,25);
		addExpertGroup1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				    String s=expertGroup1.getText().trim();
				    if(s.length()!=0){
				    	new SQLFilter(mf,s,user);
				    	bd.addGroup(mf, s);
				    }else{
				    	JOptionPane.showMessageDialog(mf, "输入不能为空");
				    }
			}
		});
		p.add(addExpertGroup1);
		JScrollPane expertGroupJSP1=new JScrollPane();
		String[] cn1={"ProfessionID","专业领域"};
		String[][] arr1={{"1","专业1"},{"2","专业2"}};
		JTable expertGroupTable1=new JTable();
		DefaultTableModel expertGroupTableModel1=new DefaultTableModel(arr1,cn1);
		expertGroupTable1.setDefaultRenderer(Object.class, tcr);
		expertGroupTable1.setModel(expertGroupTableModel1);
		expertGroupTable1.setRowHeight(22);
		expertGroupJSP1.setViewportView(expertGroupTable1);
		expertGroupJSP1.setBounds(300,165,200,550);
		p.add(expertGroupJSP1);
		//-----------------------------------------------------------
		JLabel expertGT11=new JLabel("待添加...",JLabel.CENTER);
		expertGT11.setBounds(600,60,200,25);
		p.add(expertGT11);
		//
		JLabel expertGT111=new JLabel("待添加...",JLabel.CENTER);
		expertGT111.setBounds(900,60,200,25);
		p.add(expertGT111);
		return p;
	}
	public JPanel manageExpert(){
		JPanel p=new JPanel();
		p.setLayout(null);
		JLabel ptitle=new JLabel("添加专家信息",JLabel.CENTER);
		Font f=new Font("宋体", 1, 25);
		ptitle.setFont(f);
		ptitle.setBounds(515,20,200,25);
		p.add(ptitle);
		JComboBox<String> chooseExpertGroup=new JComboBox<String>();
		chooseExpertGroup.addItem("请选择专业组别");
		chooseExpertGroup.setBounds(30,60,120,25);
		p.add(chooseExpertGroup);
		JTextField expertNameT=new JTextField("请输入专家姓名");
		expertNameT.setBounds(180,60,120,25);
		p.add(expertNameT);
		JComboBox<String> chooseExpertSex=new JComboBox<String>();
		chooseExpertSex.addItem("请选择专业性别");
		chooseExpertSex.setBounds(330,60,120,25);
		p.add(chooseExpertSex);
		JTextField expertBurnDate=new JTextField("请输入专家出生日期");
		expertBurnDate.setBounds(480,60,120,25);
		p.add(expertBurnDate);
		JComboBox<String> chooseExpertEducation=new JComboBox<String>();
		chooseExpertEducation.addItem("请选择专业学历");
		chooseExpertEducation.setBounds(630,60,120,25);
		p.add(chooseExpertEducation);
		JTextField expertCompany=new JTextField("请输入专家工作单位");
		expertCompany.setBounds(780,60,120,25);
		p.add(expertCompany);
		JComboBox<String> chooseExpertProfession=new JComboBox<String>();
		chooseExpertProfession.addItem("请选择专家专业");
		chooseExpertProfession.setBounds(930,60,120,25);
		p.add(chooseExpertProfession);
		JComboBox<String> chooseExpertPosition=new JComboBox<String>();
		chooseExpertPosition.addItem("请选择专家职位");
		chooseExpertPosition.setBounds(30,100,120,25);
		p.add(chooseExpertPosition);
		JTextField expertTel=new JTextField("请输入联系电话");
		expertTel.setBounds(180,100,120,25);
		p.add(expertTel);
		JTextField expertAddress=new JTextField("请输入联系地址");
		expertAddress.setBounds(330,100,500,25);
		p.add(expertAddress);
		JTextField expertMark=new JTextField("请输入备注");
		expertMark.setBounds(30,140,800,25);
		p.add(expertMark);
		JButton addExpert=new JButton("添加");
		addExpert.setBounds(575,180,80,25);
		p.add(addExpert);
		JScrollPane expertGroupJSP=new JScrollPane();
		String[] cn={"ID","专业组别","姓名","性别","出生年月","学历","工作单位","专业领域","职称/职位","联系电话","通讯地址","备注"};
		String[][] arr={{"1","检查组","test1","男","1984-2-5","本科","明创","检查","技术员","0750-3320133","广东江门","周六休息"},{"2","执行组","test2","女","1987-6-5","本科","明创","检查","经理","0750-3320133","广东江门","周日休息"}};
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
		JLabel ptitle=new JLabel("专家需求登记",JLabel.CENTER);
		Font f=new Font("宋体", 1, 25);
		ptitle.setFont(f);
		ptitle.setBounds(515,20,200,25);
		p.add(ptitle);
		//-----------------------------------------------------
		JLabel projectId=new JLabel("项目编号:XXXXXXXXXX");
		projectId.setBounds(30,60,200,20);
		p.add(projectId);
		JLabel projectNeedL=new JLabel("需求单位：");
		projectNeedL.setBounds(30,95,200,25);
		p.add(projectNeedL);
		JTextField projectNeedT=new JTextField();
		projectNeedT.setBounds(100,95,300,25);
		p.add(projectNeedT);
		JLabel projectDateL=new JLabel("日期：");
		projectDateL.setBounds(800,95,200,25);
		p.add(projectDateL);
		JTextField projectDateT=new JTextField();
		projectDateT.setBounds(840,95,120,25);
		p.add(projectDateT);
		//--------------------
		JLabel projectNameL=new JLabel("项目名称：");
		projectNameL.setBounds(30,130,200,25);
		p.add(projectNameL);
		JTextField projectNameT=new JTextField();
		projectNameT.setBounds(100,130,300,25);
		p.add(projectNameT);
		JLabel projectDateL1=new JLabel("预计工作时长：                       天");
		projectDateL1.setBounds(800,130,500,25);
		p.add(projectDateL1);
		JTextField projectDateT1=new JTextField();
		projectDateT1.setBounds(890,130,60,25);
		p.add(projectDateT1);
		//-------------------------------------------
		JLabel projectContactL=new JLabel("项目联系人：");
		projectContactL.setBounds(30,165,200,25);
		p.add(projectContactL);
		JTextField projectContactT=new JTextField();
		projectContactT.setBounds(110,165,300,25);
		p.add(projectContactT);
		JLabel projectPosstionL=new JLabel("职位：");
		projectPosstionL.setBounds(500,165,200,25);
		p.add(projectPosstionL);
		JComboBox<String> chooseExpertPosition=new JComboBox<String>();
		chooseExpertPosition.addItem("请选择职位");
		chooseExpertPosition.setBounds(535,165,120,25);
		p.add(chooseExpertPosition);
		JLabel projectTelL=new JLabel("联系电话：");
		projectTelL.setBounds(800,165,500,25);
		p.add(projectTelL);
		JTextField projectTelT=new JTextField();
		projectTelT.setBounds(860,165,120,25);
		p.add(projectTelT);
		//--------------------------------------------
		JLabel projectMeetPlaceL=new JLabel("集合地点：");
		projectMeetPlaceL.setBounds(30,200,200,25);
		p.add(projectMeetPlaceL);
		JTextField projectMeetPlaceT=new JTextField();
		projectMeetPlaceT.setBounds(100,200,200,25);
		p.add(projectMeetPlaceT);
		JLabel projectMeetDateL=new JLabel("集合日期：");
		projectMeetDateL.setBounds(800,200,200,25);
		p.add(projectMeetDateL);
		JTextField projectMeetDateT=new JTextField();
		projectMeetDateT.setBounds(860,200,120,25);
		p.add(projectMeetDateT);
		//----------------------------------------------
		JLabel projectWorkL=new JLabel("工作内容：");
		projectWorkL.setBounds(30,245,200,25);
		p.add(projectWorkL);
		JTextField projectWorkT=new JTextField();
		projectWorkT.setBounds(100,235,880,50);
		p.add(projectWorkT);
		//-----------------------------------------------
		JLabel projectTraficL=new JLabel("交通安排：");
		projectTraficL.setBounds(30,305,200,25);
		p.add(projectTraficL);
		JTextField projectTraficT=new JTextField();
		projectTraficT.setBounds(100,295,880,50);
		p.add(projectTraficT);
		//---------------------------------------------------------------
		JScrollPane expertGroupJSP=new JScrollPane();
		String[] cn={"需求专业类型","需求人数"};
		String[][] arr={{"检查组","1"},{"执行组","2"},{"合计（单位:人）","3"}};
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
		JLabel projectView=new JLabel("协会意见：");
		projectView.setBounds(30,455,200,25);
		p.add(projectView);
		JTextField projectViewT=new JTextField();
		projectViewT.setBounds(100,455,880,25);
		p.add(projectViewT);
		//----------------------------------------------------
		JLabel projectMarkL=new JLabel("备注：");
		projectMarkL.setBounds(30,490,200,25);
		p.add(projectMarkL);
		JTextField projectMarkT=new JTextField();
		projectMarkT.setBounds(100,490,880,25);
		p.add(projectMarkT);
		return p;
	}
}
