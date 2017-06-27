package com.mc.zjgl;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ShowRendomExpert extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5900467643829589457L;

	/*
	 * public static void main(String[] args) { JFrame f = new JFrame(); new
	 * RendomExpert(f); }
	 */

	public ShowRendomExpert(JFrame f, String pid, String name, String work, String meetdate) {
		RendomExpertData red = new RendomExpertData();
		JFrame ft=this;
		this.setResizable(false);
		this.setTitle("ר�ҳ�ѡ");
		this.setBounds(300, 70, 600, 750);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.setEnabled(true);
				dispose();
			}
		});
		this.setLayout(null);
		Container c = this.getContentPane();
		JLabel pjl = new JLabel("��Ŀ����:");
		pjl.setBounds(10, 10, 80, 20);
		c.add(pjl);
		JTextField pjt = new JTextField();
		pjt.setText(name);
		pjt.setEditable(false);
		pjt.setBounds(70, 10, 200, 20);
		c.add(pjt);
		JLabel idl = new JLabel("���:");
		idl.setBounds(370, 10, 60, 20);
		c.add(idl);
		JTextField idt = new JTextField();
		idt.setEditable(false);
		idt.setText(pid);
		idt.setBounds(405, 10, 150, 20);
		c.add(idt);
		JLabel workl = new JLabel("��������:");
		workl.setBounds(10, 80, 80, 20);
		c.add(workl);
		JTextArea workta = new JTextArea();
		workta.setEditable(false);
		workta.setText(work);
		workta.setLineWrap(true);
		JScrollPane workjsp = new JScrollPane();
		workjsp.setViewportView(workta);
		workjsp.setBounds(70, 40, 200, 100);
		c.add(workjsp);
		JLabel needl = new JLabel("����:");
		needl.setBounds(360, 40, 80, 20);
		c.add(needl);
		JTextField needt = new JTextField();
		needt.setBounds(405, 40, 150, 20);
		c.add(needt);
		JLabel drawl = new JLabel("��ǩ��:");
		drawl.setBounds(360, 80, 80, 20);
		c.add(drawl);
		JTextField drawt = new JTextField();
		drawt.setBounds(405, 80, 150, 20);
		c.add(drawt);
		JLabel supervisorl = new JLabel("�ල��:");
		supervisorl.setBounds(360, 120, 80, 20);
		c.add(supervisorl);
		JTextField supervisort = new JTextField();
		supervisort.setBounds(405, 120, 150, 20);
		c.add(supervisort);
		JPanel mp = new JPanel();
		mp.setLayout(new BorderLayout());
		mp.setBounds(0, 150, 600, 600);
		JLabel mdl = new JLabel("��ȡ����", JLabel.CENTER);
		JPanel bp = new JPanel();
		bp.setLayout(null);
		JLabel drawtimel = new JLabel("��ȡʱ��:");
		drawtimel.setBounds(10, 10, 80, 20);
		bp.add(drawtimel);
		String s = red.getRendomDate(ft, pid);
		JTextField drawtimet = new JTextField();
		drawtimet.setText(s);
		drawtimet.setEditable(false);
		drawtimet.setBounds(70, 10, 200, 20);
		bp.add(drawtimet);
		JLabel drawnuml = new JLabel("��ȡ����:");
		drawnuml.setBounds(355, 10, 80, 20);
		bp.add(drawnuml);
		JTextField drawnumt = new JTextField();
		drawnumt.setBounds(415, 10, 80, 20);
		bp.add(drawnumt);
		/*MyTable.f = f;
		MyTable.date = meetdate;
		MyTable.avoid = "";
		MyTable.pid = pid;*/
		//MyTable frt = new MyTable();
		JTable fr = new JTable();
		String[] cn={"���", "ר�ұ��", "רҵ���", "����","רҵ","ְλ", "��ϵ�绰", "��ע", "ѡ��" };
		String[][] arr=red.getExpertPlan(ft, pid);
		DefaultTableModel dm=new DefaultTableModel(arr,cn);
		fr.setModel(dm);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(JLabel.CENTER);
		fr.setDefaultRenderer(Object.class, tcr);
		fr.setRowHeight(20);
		JScrollPane fjsp = new JScrollPane();
		fjsp.setViewportView(fr);
		fjsp.setBounds(10, 40, 570, 300);
		bp.add(fjsp);
		JButton b = new JButton("����");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				int cr = fr.getRowCount();
				System.out.println(cr);
				//String sqlex="";
				String[][] expa=new String[cr][4];
				for (int i = 0; i < cr; i++) {
					//int b=0;
				/*	if((boolean) fr.getValueAt(i, 8)){
						b=1;
					}*/
					//sqlex=sqlex+"insert into expertplan values("+pid+",";
/*					for(int j=0;j<10;j++){
						if(fr.getValueAt(i, j)!=null){
							if(j>2&&j<8){
								sqlex=sqlex+"'"+fr.getValueAt(i, j).toString()+"',";
							}else{
								sqlex=sqlex+fr.getValueAt(i, j).toString();
							}
						}
					}*/
					expa[i][0]=fr.getValueAt(i, 0).toString();
					expa[i][1]=fr.getValueAt(i, 2).toString();
					expa[i][2]=fr.getValueAt(i, 3).toString();
					expa[i][3]=fr.getValueAt(i, 6).toString();
				   /* sqlex=sqlex+"insert into expertplan values("
				    	+ ""+pid+","+fr.getValueAt(i, 0).toString()+","+fr.getValueAt(i, 1).toString()+","
				    	+ "'"+fr.getValueAt(i, 2).toString()+"','"+fr.getValueAt(i, 3).toString()+"','"+fr.getValueAt(i, 4).toString()+"',"
				    	+ "'"+fr.getValueAt(i, 5).toString()+"','"+fr.getValueAt(i, 6)+"',"
				    	+ "'"+fr.getValueAt(i, 7).toString()+"',"+b+",'"+meetdate+"');"+"\n";*/
				}
				//sqlex=sqlex+"update expertneed set needstatus = 1 where projectid="+pid;
				//red.insertExpertPlan(sqlex);
				int bf=JOptionPane.showConfirmDialog(ft,"�Ƿ񵼳�excel","ѡ��",JOptionPane.YES_NO_OPTION );
				if(bf==JOptionPane.YES_OPTION){
				   ArrayList<String> ls = new ArrayList<String>();
				   ls.add(name);
				   ls.add(pid);
				   ls.add(work);
				   ls.add(s);
				   ExportRendom.exportExcel(ft, "test.xls",ls, expa);
				}
				b.setEnabled(false);
				//System.out.println(sqlex);
			}
		});
		b.setBounds(250, 360, 80, 24);
		bp.add(b);
		mp.add("North", mdl);
		mp.add(bp);
		c.add(mp);
		this.setVisible(true);
	}
}

