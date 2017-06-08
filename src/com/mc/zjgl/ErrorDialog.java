package com.mc.zjgl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ErrorDialog extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5921455312758538566L;

	public ErrorDialog(JFrame f,Rectangle b,String error){
		super(f,"错误信息",true);
		double x = b.getX();
		double y = b.getY();
		double h = b.getHeight();
		double w = b.getWidth();
		double dh = h/2;
		double dw = w/2;
		Container container=getContentPane();//创建一个容器
		JTextArea jta=new JTextArea();
		container.setLayout(new BorderLayout(5,5));
		jta.setEditable(false);
		jta.setLineWrap(true);
		jta.setText("获取到错误信息！\n请截图或打印当前页面后联系 \n\n Author：Lemon Lan\n Tel：18026753608\n\n"+error);
		JScrollPane jsp=new JScrollPane();
		jsp.setViewportView(jta);
		container.add(jsp);
		JPanel bottom=new JPanel();
		bottom.setLayout(new FlowLayout());
		JButton jb=new JButton("确认");
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter(){
			   public void windowClosing(WindowEvent e) {
			    dispose();
			            }
			  });
		jb.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				dispose();
			}
		});
		jta.setForeground(new Color(252,65,83));
		jta.setBackground(Color.darkGray);
		jta.setCaretPosition(0);
		bottom.add(jb);
		JButton jb2=new JButton("打印");
		bottom.add(jb2);
		container.add("South",bottom);
		Rectangle db=new Rectangle();
		db.setFrame(x+dw/2, y+dh/2, dw, dh);
		setBounds(db);
		setVisible(true);
	}
}
