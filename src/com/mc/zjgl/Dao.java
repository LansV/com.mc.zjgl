package com.mc.zjgl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Dao {
	 Connection con;
	public  Connection getcon(){
		  try{
	 		   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	 	   }catch(ClassNotFoundException e){
	 		   JFrame j=new JFrame();
	 		   j.setAlwaysOnTop(true);
	 		   JOptionPane.showMessageDialog(j,"δ�ҵ��������ļ�");
	 		   System.exit(0);
	 	   }
	 	   try{
	 		   con=DriverManager.getConnection("jdbc:sqlserver://192.168.0.13:1433;DatabaseName=","sa","llfaicly1314@^");
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
