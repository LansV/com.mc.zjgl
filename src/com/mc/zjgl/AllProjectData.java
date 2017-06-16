package com.mc.zjgl;

import java.awt.Rectangle;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;

public class AllProjectData {
	Statement sql=null;
   	ResultSet res=null;
   	Dao d=new Dao();
	Connection con = d.getcon();
	public String[][] getUnFinishProject(JFrame f){
		ArrayList<String> ls = new ArrayList<String>();
		try{
			sql=con.createStatement();
			res=sql.executeQuery("select projectid,max(needstatus) as ns from expertneed where needstatus=0 group by projectid");
			while(res.next()){
				ls.add(res.getString("projectid"));
				if(res.getInt("ns")==0){
					ls.add("未安排");
				}else{
					ls.add("错误");
				}
				
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
}
