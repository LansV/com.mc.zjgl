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
import javax.swing.JOptionPane;

public class ExpertData {
	Statement sql=null;
   	ResultSet res=null;
   	Dao d=new Dao();
	Connection con = d.getcon();
	public int insertExpert(JFrame f,String pgid,String en,String es,String ebd,String educ,String ec,String prof,String occup,
			String tel,String area,String address,String mark,String user){
		int i=0;
		String id="";
		try {
			sql=con.createStatement();
			res=sql.executeQuery("select max(expertid) as id from expert");
			if(res.next()){
				if(res.getString("id")==null){
					id="10001";
				}else{
					int maxid=0;
					maxid=res.getInt("id")+1;
					if(maxid>=20000){
						JOptionPane.showMessageDialog(f, "超过限制量\n系统自动关闭\n请联系:18026753608");
						System.exit(0);
					}
					id=Integer.toString(maxid);
				}
				String sqlstr="insert into expert values ("+id+",'"+en+"','"+es+"','"+ebd+"','"+educ+"','"+ec+"',"
						+ "'"+prof+"','"+occup+"','"+tel+"','"+area+"','"+address+"','"+mark+"','"+user+"');"
								+ "insert into expertgroup values("+id+","+pgid+")";
				System.out.println(sqlstr);
				sql.execute(sqlstr);
				i=1;
			}
		} catch (SQLException e) {
			i=2;
			// TODO 自动生成的 catch 块
			StringWriter sw = new StringWriter();
		    PrintWriter pw = new PrintWriter(sw, true);
		    e.printStackTrace(pw);
		    String mn = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名 
		    String dn = new Exception().getStackTrace()[1].getClassName();// 获得调用类
			String errorString="错误类："+this.getClass().getName()+"\n错误方法："+mn+"\n调用类："+dn+"\n错误信息：\n"+sw.toString();
			Rectangle b = f.getBounds();
			new ErrorDialog(f, b, errorString);
		}
		
		return i;
	}

	public String[][] getExpert(JFrame f,String fifterGroup){
		ArrayList<String> ls = new ArrayList<String>();
		try {
			sql=con.createStatement();
			res=sql.executeQuery("select expertid,professionalgroupname=("
					+ "stuff("
					+ "("
					+ "select ','+professionalgroupname  from ("
					+ " select expert.expertid,professionalgroupname from "
					+ " expert"
					+ " inner join expertgroup on expert.expertid=expertgroup.expertid"
					+ " inner join professionalgroup on expertgroup.professionalgroup=professionalgroup.professionalgroupid "
					+ " )  b "
					+ " WHERE expertid = A.expertid  for xml path('')),1,1,'')"
					+ "),MAX(expertname) as expertname ,MAX(expertsex) as expertsex,MAX(expertburndate) as expertburndate,"
					+ "max(age) as age,"
					+ "max(education) as education,MAX(expertcompany) as expertcompany,MAX(professional) as professionalname,"
					+ "MAX(occupation) as occupationname,MAX(tel) as tel,MAX(area) as area,MAX(address) as address,"
					+ "MAX(mark) as  mark"
					+ " from "
					+ "("
					+ "select expert.expertid,professionalgroupname,expertname,expertsex,expertburndate,"
					+ "DATEDIFF(YEAR,expertburndate,convert(char(10),getdate(),120)) as age,education,"
					+ "expertcompany,expert.professional,expert.occupation,tel,area,address,mark from "
					+ " expert"
					+ " inner join expertgroup on expert.expertid=expertgroup.expertid"
					//+ " inner join professional on expert.professional=professional.professionalid"
					+ " inner join professionalgroup on expertgroup.professionalgroup=professionalgroup.professionalgroupid"
					//+ " inner join occupation on expert.occupation=occupation.occupationid"
					+ " ) A where professionalgroupname like '%"+fifterGroup+"%' group by expertid ");
			while(res.next()){
				ls.add(res.getString("expertid"));
				ls.add(res.getString("professionalgroupname"));
				ls.add(res.getString("expertname"));
				ls.add(res.getString("expertsex"));
				ls.add(res.getString("expertburndate"));
				ls.add(res.getString("age"));
				ls.add(res.getString("education"));
				ls.add(res.getString("expertcompany"));
				ls.add(res.getString("professionalname"));
				ls.add(res.getString("occupationname"));
				ls.add(res.getString("tel"));
				ls.add(res.getString("area"));
				ls.add(res.getString("address"));
				ls.add(res.getString("mark"));
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			StringWriter sw = new StringWriter();
		    PrintWriter pw = new PrintWriter(sw, true);
		    e.printStackTrace(pw);
		    String mn = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名 
		    String dn = new Exception().getStackTrace()[1].getClassName();// 获得调用类
			String errorString="错误类："+this.getClass().getName()+"\n错误方法："+mn+"\n调用类："+dn+"\n错误信息：\n"+sw.toString();
			Rectangle b = f.getBounds();
			new ErrorDialog(f, b, errorString);
		}
		int xl=14;
		String[][] data=new String[ls.size()/xl][xl];
	   	int count=0;
	   	for(int i=0;i<ls.size()/xl;i++){  //行
	   		for(int j=0;j<xl;j++){  //列
	   			data[i][j]=ls.get(j+count*xl);
	   			
	   		}
	   		count++;
	   	}
	   	count=0;
		return data;
	}

	public int editProfessionalGroup(JFrame f,String s){
		int i=0;
		try {
			sql=con.createStatement();
			sql.execute(s);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			StringWriter sw = new StringWriter();
		    PrintWriter pw = new PrintWriter(sw, true);
		    e.printStackTrace(pw);
		    String mn = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名 
		    String dn = new Exception().getStackTrace()[1].getClassName();// 获得调用类
			String errorString="错误类："+this.getClass().getName()+"\n错误方法："+mn+"\n调用类："+dn+"\n错误信息：\n"+sw.toString();
			Rectangle b = f.getBounds();
			new ErrorDialog(f, b, errorString);
		}
		return i;
	}
	public int deleteExpert(JFrame f,String s){
		int i=0;
		try {
			sql=con.createStatement();
			sql.execute("delete expert where expertid="+s+";delete expertgroup where expertid="+s+";");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			StringWriter sw = new StringWriter();
		    PrintWriter pw = new PrintWriter(sw, true);
		    e.printStackTrace(pw);
		    String mn = new Exception().getStackTrace()[0].getMethodName();// 获得当前的方法名 
		    String dn = new Exception().getStackTrace()[1].getClassName();// 获得调用类
			String errorString="错误类："+this.getClass().getName()+"\n错误方法："+mn+"\n调用类："+dn+"\n错误信息：\n"+sw.toString();
			Rectangle b = f.getBounds();
			new ErrorDialog(f, b, errorString);
		}
		return i;
	}
}
