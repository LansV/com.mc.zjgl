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
	 		   JOptionPane.showMessageDialog(j,"未找到到驱动文件");
	 		   System.exit(0);
	 	   }
	 	   try{
	 		   con=DriverManager.getConnection("jdbc:sqlserver://"+ls.get(0)+";DatabaseName=mczjgl","sa","llfaicly1314@^");
	 		   System.out.println(ls.get(0));
	 	   }catch(SQLException e){
	 		  JFrame j=new JFrame();
	 		   j.setAlwaysOnTop(true);
	 		   JOptionPane.showMessageDialog(j, "请检查数据库路径及数据名称");
	 		   System.exit(0);
	 	   }
	 	   return con;
	 	   //test
	}
}
