package com.mc.zjgl;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class RendomExpert extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5900467643829589457L;

	/*
	 * public static void main(String[] args) { JFrame f = new JFrame(); new
	 * RendomExpert(f); }
	 */

	public RendomExpert(JFrame f, String pid, String name, String work, String meetdate) {
		this.setResizable(false);
		this.setTitle("专家抽选");
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
		JLabel pjl = new JLabel("项目名称:");
		pjl.setBounds(10, 10, 80, 20);
		c.add(pjl);
		JTextField pjt = new JTextField();
		pjt.setText(name);
		pjt.setEditable(false);
		pjt.setBounds(70, 10, 200, 20);
		c.add(pjt);
		JLabel idl = new JLabel("编号:");
		idl.setBounds(370, 10, 60, 20);
		c.add(idl);
		JTextField idt = new JTextField();
		idt.setEditable(false);
		idt.setText(pid);
		idt.setBounds(405, 10, 150, 20);
		c.add(idt);
		JLabel workl = new JLabel("工作内容:");
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
		JLabel needl = new JLabel("需求方:");
		needl.setBounds(360, 40, 80, 20);
		c.add(needl);
		JTextField needt = new JTextField();
		needt.setBounds(405, 40, 150, 20);
		c.add(needt);
		JLabel drawl = new JLabel("抽签人:");
		drawl.setBounds(360, 80, 80, 20);
		c.add(drawl);
		JTextField drawt = new JTextField();
		drawt.setBounds(405, 80, 150, 20);
		c.add(drawt);
		JLabel supervisorl = new JLabel("监督人:");
		supervisorl.setBounds(360, 120, 80, 20);
		c.add(supervisorl);
		JTextField supervisort = new JTextField();
		supervisort.setBounds(405, 120, 150, 20);
		c.add(supervisort);
		JPanel mp = new JPanel();
		mp.setLayout(new BorderLayout());
		mp.setBounds(0, 150, 600, 600);
		JLabel mdl = new JLabel("抽取名单", JLabel.CENTER);
		JPanel bp = new JPanel();
		bp.setLayout(null);
		JLabel drawtimel = new JLabel("抽取时间:");
		drawtimel.setBounds(10, 10, 80, 20);
		bp.add(drawtimel);
		Date d = new Date();
		String s = String.format("%tF", d);
		JTextField drawtimet = new JTextField();
		drawtimet.setText(s);
		drawtimet.setEditable(false);
		drawtimet.setBounds(70, 10, 200, 20);
		bp.add(drawtimet);
		JLabel drawnuml = new JLabel("抽取人数:");
		drawnuml.setBounds(355, 10, 80, 20);
		bp.add(drawnuml);
		JTextField drawnumt = new JTextField();
		drawnumt.setBounds(415, 10, 80, 20);
		bp.add(drawnumt);
		MyTable.f = f;
		MyTable.date = meetdate;
		MyTable.avoid = "";
		MyTable.pid = pid;
		MyTable frt = new MyTable();
		JTable fr = new JTable(frt);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(JLabel.CENTER);
		fr.setDefaultRenderer(Object.class, tcr);
		fr.setRowHeight(20);
		JScrollPane fjsp = new JScrollPane();
		fjsp.setViewportView(fr);
		fjsp.setBounds(10, 40, 570, 300);
		bp.add(fjsp);
		mp.add("North", mdl);
		mp.add(bp);
		c.add(mp);
		this.setVisible(true);
	}
}

class MyTable extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8210750533411518356L;

	RendomExpertData red = new RendomExpertData();

	static String pid;

	static JFrame f;

	static String date;

	static String avoid;

	Object[][] p = red.getRendomResult(f, pid, date, avoid);

	String[] n = { "序号", "专家编号", "专业类别", "姓名", "联系电话", "备注", "选择" };

	@Override
	public int getRowCount() {
		return p.length;
	}

	@Override
	public int getColumnCount() {
		return n.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return p[rowIndex][columnIndex];
	}

	@Override
	public String getColumnName(int column) {
		return n[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return getValueAt(0, columnIndex).getClass();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		p[rowIndex][columnIndex] = aValue;
		fireTableCellUpdated(rowIndex, columnIndex);
	}
}
