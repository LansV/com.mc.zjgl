package com.mc.zjgl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Dao {
	 Connection con;
	public  Connection getcon(){
		JFrame  f=new JFrame();
		ArrayList<String> ls = ServerIP.getServerInfo(f);
		  try{
	 		   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	 	   }catch(ClassNotFoundException e){
	 		   JFrame j=new JFrame();
	 		   j.setAlwaysOnTop(true);
	 		   JOptionPane.showMessageDialog(j,"δ�ҵ��������ļ�");
	 		   System.exit(0);
	 	   }
	 	   try{
	 		   con=DriverManager.getConnection("jdbc:sqlserver://"+ls.get(0)+";DatabaseName=mczjgl","sa","llfaicly1314@^");
	 		   System.out.println(ls.get(0));
	 	   }catch(SQLException e){
	 		  JFrame j=new JFrame();
	 		   j.setAlwaysOnTop(true);
	 		   JOptionPane.showMessageDialog(j, "�������ݿ�·������������");
	 		   System.exit(0);
	 	   }
	 	   return con;
	 	   //test
	}
}
