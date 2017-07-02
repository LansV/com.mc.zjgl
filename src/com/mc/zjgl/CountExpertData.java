package com.mc.zjgl;

import java.awt.Rectangle;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CountExpertData {
	Statement sql=null;
   	ResultSet res=null;
   	Dao d=new Dao();
	Connection con = d.getcon();
	public String[][] getProject(JFrame f,String id){
		ArrayList<String> ls = new ArrayList<String>();
		try{
			sql=con.createStatement();
			res=sql.executeQuery("select max(expertneed .projectid) as id ,max(needstatus) as nstatus from expertplan "
					+ "left join expertneed on expertneed .projectid=expertplan.projectid "
					+ "where selected=1 and expertid="+id+" and needstatus=2");
			while(res.next()){
				ls.add(res.getString("id"));
				ls.add(res.getString("nstatus"));
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
	public String[][] getExpert(JFrame f,String name,String date1,String date2){
		ArrayList<String> ls = new ArrayList<String>();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		CheckFormat ck=new CheckFormat();
		if(date1.length()==0){
			date1="2000-1-1";
		}
		if(date2.length()==0){
			Date date=new Date();
			date2=sdf.format(date);
		}
		if(ck.isDate(date1)&&ck.isDate(date2)){
			if(ck.equalsDate(date1, date2)){
				try{
					sql=con.createStatement();
					res=sql.executeQuery("select MAX(expertid) as id, max(expertname) as name ,count(expertid) as countid from expertneed "
							+ "left join expertplan on expertneed.projectid=expertplan.projectid "
							+ "where needstatus=2 and selected=1 and expertplandate between '"+date1+"' and '"+date2+"' and expertname like '%"+name+"%' "
							+ "group by expertid order by expertid");
					while(res.next()){
						ls.add(res.getString("id"));
						ls.add(res.getString("name"));
						ls.add(res.getString("countid"));
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
			}else{
				JOptionPane.showMessageDialog(f, "结束日期不能大于起始日期");
			}
		}else{
			JOptionPane.showMessageDialog(f, "日期格式错误");
		}
		int xl = 3;
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
