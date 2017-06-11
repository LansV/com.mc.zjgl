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

import javax.swing.JButton;
import javax.swing.JCheckBox;
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
	ExpertData ed=new ExpertData();
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
		expertGroup.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()=='\n'){
					addExpertGroup.doClick();
				}
			}
		});
		p.add(addExpertGroup);
		JScrollPane expertGroupJSP=new JScrollPane();
		String[] cn={"GroupID","专业组别"};
		String[][] arr=bd.getGroup(mf);
		JTable expertGroupTable=new JTable();
		DefaultTableModel expertGroupTableModel=new DefaultTableModel(arr,cn);
		addExpertGroup.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				    String s=expertGroup.getText().trim();
				    if(s.length()!=0){
				    	new SQLFilter(mf,s,user);
				    	int i=bd.addGroup(mf, s);
				    	if(i==1){
				    		JOptionPane.showMessageDialog(mf, "添加专业组别成功");
				    	}
				    	expertGroupTableModel.setDataVector(bd.getGroup(mf), cn);
				    	expertGroup.setText("");
				    }else{
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
		expertGroup1.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()=='\n'){
					addExpertGroup1.doClick();
				}
			}
		});
		addExpertGroup1.setBounds(355,125,90,25);
		p.add(addExpertGroup1);
		JScrollPane expertGroupJSP1=new JScrollPane();
		String[] cn1={"ProfessionID","专业领域"};
		String[][] arr1=bd.getProfessionnal(mf);
		JTable expertGroupTable1=new JTable();
		DefaultTableModel expertGroupTableModel1=new DefaultTableModel(arr1,cn1);
		addExpertGroup1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				    String s=expertGroup1.getText().trim();
				    if(s.length()!=0){
				    	new SQLFilter(mf,s,user);
				    	int i=bd.addProfessional(mf, s);
				    	if(i==1){
				    		JOptionPane.showMessageDialog(mf, "添加专业组别成功");
				    	}
				    	expertGroupTableModel1.setDataVector(bd.getProfessionnal(mf), cn1);
				    	expertGroup1.setText("");
				    }else{
				    	JOptionPane.showMessageDialog(mf, "输入不能为空");
				    }
			}
		});
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
		String[][] professionalgroup=bd.getGroup(mf);
		String[][] sex=bd.getSex(mf);
		String[][] education=bd.getEducation(mf);
		String[][] professional=bd.getProfessionnal(mf);
		String[][] occupation=bd.getOccupation(mf);
		JPanel p=new JPanel();
		p.setLayout(null);
		JLabel ptitle=new JLabel("添加专家信息",JLabel.CENTER);
		Font f=new Font("宋体", 1, 25);
		ptitle.setFont(f);
		ptitle.setBounds(515,20,200,25);
		p.add(ptitle);
		JComboBox<String> chooseExpertGroup=new JComboBox<String>();
		chooseExpertGroup.addItem("请选择专业组别");
		int pgl=professionalgroup.length;
		for(int i=0;i<pgl;i++){
			chooseExpertGroup.addItem(professionalgroup[i][1]);
		}
		chooseExpertGroup.setBounds(30,60,120,25);
		p.add(chooseExpertGroup);
		JTextField expertNameT=new JTextField("请输入专家姓名");
		expertNameT.addFocusListener(new MyFocusListener("请输入专家姓名", expertNameT));
		expertNameT.setBounds(180,60,120,25);
		p.add(expertNameT);
		JComboBox<String> chooseExpertSex=new JComboBox<String>();
		chooseExpertSex.addItem("请选择专业性别");
		int sexl=sex.length;
		for(int i=0;i<sexl;i++){
			chooseExpertSex.addItem(sex[i][1]);
		}
		chooseExpertSex.setBounds(330,60,120,25);
		p.add(chooseExpertSex);
		JTextField expertBurnDate=new JTextField("请输入专家出生日期");
		expertBurnDate.addFocusListener(new MyFocusListener("请输入专家出生日期", expertBurnDate));
		expertBurnDate.setBounds(480,60,120,25);
		p.add(expertBurnDate);
		JComboBox<String> chooseExpertEducation=new JComboBox<String>();
		chooseExpertEducation.addItem("请选择专业学历");
		int educationl=education.length;
		for(int i=0;i<educationl;i++){
			chooseExpertEducation.addItem(education[i][1]);
		}
		chooseExpertEducation.setBounds(630,60,120,25);
		p.add(chooseExpertEducation);
		JTextField expertCompany=new JTextField("请输入专家工作单位");
		expertCompany.addFocusListener(new MyFocusListener("请输入专家工作单位", expertCompany));
		expertCompany.setBounds(780,60,120,25);
		p.add(expertCompany);
		JComboBox<String> chooseExpertProfession=new JComboBox<String>();
		chooseExpertProfession.addItem("请选择专家专业");
		int professionall=professional.length;
		for(int i=0;i<professionall;i++){
			chooseExpertProfession.addItem(professional[i][1]);
		}
		chooseExpertProfession.setBounds(930,60,120,25);
		p.add(chooseExpertProfession);
		JComboBox<String> chooseExpertPosition=new JComboBox<String>();
		chooseExpertPosition.addItem("请选择专家职位");
		int occupationl=occupation.length;
		for(int i=0;i<occupationl;i++){
			chooseExpertPosition.addItem(occupation[i][1]);
		}
		chooseExpertPosition.setBounds(30,100,120,25);
		p.add(chooseExpertPosition);
		JTextField expertTel=new JTextField("请输入联系电话");
		expertTel.addFocusListener(new MyFocusListener("请输入联系电话", expertTel));
		expertTel.setBounds(180,100,120,25);
		p.add(expertTel);
		JTextField expertAddress=new JTextField("请输入联系地址");
		expertAddress.addFocusListener(new MyFocusListener("请输入联系地址", expertAddress));
		expertAddress.setBounds(330,100,500,25);
		p.add(expertAddress);
		JComboBox<String> chooseExpertArea=new JComboBox<String>();
		chooseExpertArea.addItem("请选择区域");
		chooseExpertArea.addItem("市内");
		chooseExpertArea.addItem("市外");
		chooseExpertArea.setBounds(870,100,120,25);
		p.add(chooseExpertArea);
		JTextField expertMark=new JTextField("请输入备注");
		expertMark.addFocusListener(new MyFocusListener("请输入备注", expertMark));
		expertMark.setBounds(30,140,800,25);
		p.add(expertMark);
		JButton addExpert=new JButton("添加");
		addExpert.setBounds(575,180,80,25);
		p.add(addExpert);
		JScrollPane expertGroupJSP=new JScrollPane();
		String[] cn={"ID","专业组别","姓名","性别","出生年月","学历","工作单位","专业领域","职称/职位","联系电话","区域","通讯地址","备注"};
		String[][] arr=ed.getExpert(mf);
		JTable expertGroupTable=new JTable();
		expertGroupTable.addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO 自动生成的方法存根
				if(e.getButton()==3){
					int sr=expertGroupTable.rowAtPoint(e.getPoint());
					expertGroupTable.setRowSelectionInterval(sr, sr);
					String pg=expertGroupTable.getValueAt(sr, 1).toString().trim();
					String name=expertGroupTable.getValueAt(sr, 2).toString().trim();
					new CheckBoxFrame(mf,professionalgroup,pg,name,e.getX(),e.getY());
				}
			}
		});
		expertGroupTable.getTableHeader().setReorderingAllowed(false);
		DefaultTableModel expertGroupTableModel=new DefaultTableModel(arr,cn);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(JLabel.CENTER);
		expertGroupTable.setDefaultRenderer(Object.class, tcr);
		expertGroupTable.setModel(expertGroupTableModel);
		expertGroupTable.setRowHeight(22);
		expertGroupJSP.setViewportView(expertGroupTable);
		expertGroupJSP.setBounds(30,220,1160,500);
		p.add(expertGroupJSP);
		addExpert.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				int cg=chooseExpertGroup.getSelectedIndex();
				int ent=expertNameT.getText().trim().length();
				int ces=chooseExpertSex.getSelectedIndex();
				int ebd=expertBurnDate.getText().trim().length();
				int cee=chooseExpertEducation.getSelectedIndex();
				int ec=expertCompany.getText().trim().length();
				int cepr=chooseExpertProfession.getSelectedIndex();
				int cepo=chooseExpertPosition.getSelectedIndex();
				int et=expertTel.getText().trim().length();
				int ea=expertAddress.getText().trim().length();
				int cea=chooseExpertArea.getSelectedIndex();
				if(cg!=0&&ent!=0&&ces!=0&&ebd!=0&&cee!=0&&ec!=0&&cepr!=0&&cepo!=0&&et!=0&&ea!=0&&cea!=0){
					if(expertNameT.getText().trim().equals("请输入专家姓名")||expertBurnDate.getText().trim().equals("请输入专家出生日期")
					||expertCompany.getText().trim().equals("请输入专家工作单位")||expertTel.getText().trim().equals("请输入联系电话")
					||expertAddress.getText().trim().equals("请输入联系地址")){
						JOptionPane.showMessageDialog(mf, "录入信息不完整");
					}else{
						try{
							String birthday=expertBurnDate.getText().trim();
							SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
							String dstr=birthday;  
							@SuppressWarnings("unused")
							java.util.Date date=sdf.parse(dstr); 
							System.out.println(birthday);
							String mark="";
							if(expertMark.getText().trim().equals("请输入备注")!=true){
								mark=expertMark.getText().trim();
							}
							ed.insertExpert(mf,professionalgroup[cg-1][0], expertNameT.getText().trim(), chooseExpertSex.getSelectedItem().toString(),
									birthday,chooseExpertEducation.getSelectedItem().toString(),
									expertCompany.getText().trim(),professional[cepr-1][0], occupation[cepo-1][0],
									expertTel.getText().trim(), chooseExpertArea.getSelectedItem().toString(),
									expertAddress.getText().trim(), mark);
							expertGroupTableModel.setDataVector(ed.getExpert(mf), cn);
						}catch(Exception e1){
							StringWriter sw = new StringWriter();
						    PrintWriter pw = new PrintWriter(sw, true);
						    e1.printStackTrace(pw);
						    String mn = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名 
						    String dn = new Exception().getStackTrace()[1].getClassName();// 获得调用类
							String errorString="错误类："+this.getClass().getName()+"\n错误方法："+mn+"\n调用类："+dn+"\n错误信息：\n"+sw.toString();
							Rectangle b = mf.getBounds();
							new ErrorDialog(mf, b, errorString);
						}
					}
				}else{
					JOptionPane.showMessageDialog(mf, "录入信息不完整");
				}
			}
		});
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
class CheckBoxFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3153430997940131717L;
	public CheckBoxFrame(){
		//default
	}
	public CheckBoxFrame(JFrame f,String[][] ls,String pg,String name,int x,int y){
		int lss=ls.length;
		this.setTitle("修改"+name+"组别");
		this.setResizable(false);
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		JPanel tp=new JPanel();
		tp.setLayout(new GridLayout(0, 2));
		JCheckBox[] boxs = new JCheckBox[lss];
		for(int i=0;i<lss;i++){
			boxs[i]=new JCheckBox(ls[i][0]+":"+ls[i][1]);
			String[] st=pg.split(",");
			int stl=st.length;
			for(int j=0;j<stl;j++){
				if(ls[i][1].equals(st[j])){
					System.out.println(st[j]);
					boxs[i].setSelected(true);
				}
			}
			tp.add(boxs[i]);
		}
		c.add(tp);
		JPanel bp=new JPanel();
		bp.setLayout(new FlowLayout());
		JButton b=new JButton("修改");
		b.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				int count=0;
				for(int i=0;i<lss;i++){
					if(boxs[i].isSelected()){
						count++;
						System.out.println(ls[i][0]);
						System.out.println(boxs[i].getText());
					}
				}
				if(count==0){
					JOptionPane.showMessageDialog(c, "请至少选择一项");
					return;
				}
			}
		});
		bp.add(b);
		c.add("South",bp);
		this.setBounds(x+50,y+320,300,80+20*lss);
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
    public void focusGained(FocusEvent e) {//获得焦点的时候,清空提示文字
        String temp = jtf.getText();
        if(temp.equals(info)){
            jtf.setText("");
        }
    }
    @Override
    public void focusLost(FocusEvent e) {//失去焦点的时候,判断如果为空,就显示提示文字
        String temp = jtf.getText();
        if(temp.equals("")){
            jtf.setText(info);
        }
    }
}
