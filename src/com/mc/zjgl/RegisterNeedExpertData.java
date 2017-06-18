package com.mc.zjgl;

import java.awt.Rectangle;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;

public class RegisterNeedExpertData {
	Statement sql=null;
   	ResultSet res=null;
   	Dao d=new Dao();
	Connection con = d.getcon();
	public String[][] getProfessionalgroup(JFrame f){
		ArrayList<String> ls = new ArrayList<String>();
		try{
			sql=con.createStatement();
			res=sql.executeQuery("select*from professionalgroup");
			while(res.next()){
				ls.add(res.getString("professionalgroupid"));
				ls.add(res.getString("professionalgroupname"));
			}
		}catch(SQLException e){
			StringWriter sw = new StringWriter();
		    PrintWriter pw = new PrintWriter(sw, true);
		    e.printStackTrace(pw);
		    String mn = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名 
		    String dn = new Exception().getStackTrace()[1].getClassName();// 获得调用类
			String errorString="错误类："+this.getClass().getName()+"\n错误方法："+mn+"\n调用类："+dn+"\n错误信息：\n"+sw.toString();
			Rectangle b = f.getBounds();
			new ErrorDialog(f, b, errorString);
			//************************************************
		}
		int xl = 2;
		String[][] data = new String[ls.size() / xl][xl];
		int count = 0;
		for (int i = 0; i < ls.size() / xl; i++) { // 行
			for (int j = 0; j < xl; j++) { // 列
				data[i][j] = ls.get(j + count * xl);

			}
			count++;
		}
		count = 0;
		return data;
	}
	public ArrayList<String> getOccupation(JFrame f){
		ArrayList<String> ls = new ArrayList<String>();
		try {
			sql=con.createStatement();
			res=sql.executeQuery("select*from occupation");
			while(res.next()){
				ls.add(res.getString("occupationname"));
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			//**********************************************
			StringWriter sw = new StringWriter();
		    PrintWriter pw = new PrintWriter(sw, true);
		    e.printStackTrace(pw);
		    String mn = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名 
		    String dn = new Exception().getStackTrace()[1].getClassName();// 获得调用类
			String errorString="错误类："+this.getClass().getName()+"\n错误方法："+mn+"\n调用类："+dn+"\n错误信息：\n"+sw.toString();
			Rectangle b = f.getBounds();
			new ErrorDialog(f, b, errorString);
			//************************************************
		}
		return ls;
	}
	public ArrayList<String> getAvoidCompany(JFrame f){
		ArrayList<String> ls = new ArrayList<String>();
		try {
			sql=con.createStatement();
			res=sql.executeQuery("select expertcompany from expert group by expertcompany");
			while(res.next()){
				ls.add(res.getString("expertcompany"));
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			//**********************************************
			StringWriter sw = new StringWriter();
		    PrintWriter pw = new PrintWriter(sw, true);
		    e.printStackTrace(pw);
		    String mn = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名 
		    String dn = new Exception().getStackTrace()[1].getClassName();// 获得调用类
			String errorString="错误类："+this.getClass().getName()+"\n错误方法："+mn+"\n调用类："+dn+"\n错误信息：\n"+sw.toString();
			Rectangle b = f.getBounds();
			new ErrorDialog(f, b, errorString);
			//************************************************
		}
		return ls;
	}
	public String getProjectId(JFrame f){
		String ls="";
		Date date=new Date();
		String y=String.format("%ty", date);
		String m=String.format("%tm", date);
		String d=String.format("%td", date);
		String fi=y+m+d;
		try {
			sql=con.createStatement();
			res=sql.executeQuery("select MAX(projectid) as projectid from projectregister where projectid like '"+fi+"%'");
			while(res.next()){
				if(res.getString("projectid")==null){
					ls=fi+"001";
				}else{
					int i=res.getInt("projectid")+1;
					ls=Integer.toString(i);
				}
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			//**********************************************
			StringWriter sw = new StringWriter();
		    PrintWriter pw = new PrintWriter(sw, true);
		    e.printStackTrace(pw);
		    String mn = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名 
		    String dn = new Exception().getStackTrace()[1].getClassName();// 获得调用类
			String errorString="错误类："+this.getClass().getName()+"\n错误方法："+mn+"\n调用类："+dn+"\n错误信息：\n"+sw.toString();
			Rectangle b = f.getBounds();
			new ErrorDialog(f, b, errorString);
			//************************************************
		}
		return ls;
	}
	public int insertProjectRegister(JFrame f,String str){
		int i=0;
		try {
			System.out.println(str);
			sql=con.createStatement();
			sql.execute(str);
			i=1;
		} catch (SQLException e) {
			i=2;
			// TODO 自动生成的 catch 块
			//**********************************************
			StringWriter sw = new StringWriter();
		    PrintWriter pw = new PrintWriter(sw, true);
		    e.printStackTrace(pw);
		    String mn = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名 
		    String dn = new Exception().getStackTrace()[1].getClassName();// 获得调用类
			String errorString="错误类："+this.getClass().getName()+"\n错误方法："+mn+"\n调用类："+dn+"\n错误信息：\n"+sw.toString();
			Rectangle b = f.getBounds();
			new ErrorDialog(f, b, errorString);
			//************************************************
		}
		return i;
	}
}
