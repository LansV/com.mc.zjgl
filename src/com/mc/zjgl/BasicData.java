package com.mc.zjgl;

import java.awt.Rectangle;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;

public class BasicData {
	Statement sql=null;
   	ResultSet res=null;
   	Dao d=new Dao();
	Connection con = d.getcon();	
	public int addGroup(JFrame f,String Item){
		int i=0;
		try {
			System.out.println("1");
			sql=con.createStatement();
			sql.execute("select*from ");
			System.out.println("1");
			i=1;
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			i=2;
			//**********************************************
			StringWriter sw = new StringWriter();
		    PrintWriter pw = new PrintWriter(sw, true);
		    e.printStackTrace(pw);
		    String mn = new Exception().getStackTrace()[0].getMethodName();// ��õ�ǰ�ķ����� 
		    String dn = new Exception().getStackTrace()[1].getClassName();// ��õ�����
			String errorString="�����ࣺ"+this.getClass().getName()+"\n���󷽷���"+mn+"\n�����ࣺ"+dn+"\n������Ϣ��\n"+sw.toString();
			Rectangle b = f.getBounds();
			new ErrorDialog(f, b, errorString);
			//************************************************
		}
		return i;
	}
}
