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
	public String[][] getExpertNeed(JFrame f,String pid){
		ArrayList<String> ls = new ArrayList<String>();
		try{
			int i=0;
			sql=con.createStatement();
			res=sql.executeQuery("select*from expertneed left join professionalgroup on "
					+ "expertneed.needgroup=professionalgroup.professionalgroupid where needstatus = 0 and projectid="+pid);
			while(res.next()){
				ls.add(res.getString("professionalgroupname"));
				i=i+res.getInt("neednum");
				ls.add(res.getString("neednum"));
			}
			ls.add("�ϼƣ���λ:�ˣ�");
			ls.add(Integer.toString(i));
		}catch(SQLException e){
			StringWriter sw = new StringWriter();
		    PrintWriter pw = new PrintWriter(sw, true);
		    e.printStackTrace(pw);
		    String mn = new Exception().getStackTrace()[0].getMethodName();// ��õ�ǰ�ķ����� 
		    String dn = new Exception().getStackTrace()[1].getClassName();// ��õ�����
			String errorString="�����ࣺ"+this.getClass().getName()+"\n���󷽷���"+mn+"\n�����ࣺ"+dn+"\n������Ϣ��\n"+sw.toString();
			Rectangle b = f.getBounds();
			new ErrorDialog(f, b, errorString);
		}
		int xl = 2;
		String[][] data = new String[ls.size() / xl][xl];
		int count = 0;
		for (int i = 0; i < ls.size() / xl; i++) { // ��
			for (int j = 0; j < xl; j++) { // ��
				data[i][j] = ls.get(j + count * xl);

			}
			count++;
		}
		count = 0;
		return data;
	}
	public ArrayList<String> getProjectInfo(JFrame f,String pid){
		ArrayList<String> ls = new ArrayList<String>();
		try{
			sql=con.createStatement();
			res=sql.executeQuery("select*from projectregister where projectid="+pid);
			while(res.next()){
				ls.add(res.getString("needcompany"));
				ls.add(res.getString("registerdate"));
				ls.add(res.getString("projectname"));
				ls.add(res.getString("needday"));
				ls.add(res.getString("contact"));
				ls.add(res.getString("occupation"));
				ls.add(res.getString("tel"));
				ls.add(res.getString("allplace"));
				ls.add(res.getString("alldate"));
				ls.add(res.getString("jobcontent"));
				ls.add(res.getString("traffic"));
				ls.add(res.getString("avoidcompany"));
				ls.add(res.getString("opinion"));
				ls.add(res.getString("mark"));
				ls.add(res.getString("projectstatus"));
			}
		}catch(SQLException e){
			StringWriter sw = new StringWriter();
		    PrintWriter pw = new PrintWriter(sw, true);
		    e.printStackTrace(pw);
		    String mn = new Exception().getStackTrace()[0].getMethodName();// ��õ�ǰ�ķ����� 
		    String dn = new Exception().getStackTrace()[1].getClassName();// ��õ�����
			String errorString="�����ࣺ"+this.getClass().getName()+"\n���󷽷���"+mn+"\n�����ࣺ"+dn+"\n������Ϣ��\n"+sw.toString();
			Rectangle b = f.getBounds();
			new ErrorDialog(f, b, errorString);
		}
		return ls;
	}
	public String[][] getUnFinishProject(JFrame f){
		ArrayList<String> ls = new ArrayList<String>();
		try{
			sql=con.createStatement();
			res=sql.executeQuery("select projectid,max(needstatus) as ns from expertneed where needstatus=0 group by projectid");
			while(res.next()){
				ls.add(res.getString("projectid"));
				if(res.getInt("ns")==0){
					ls.add("δ����");
				}else{
					ls.add("����");
				}
				
			}
		}catch(SQLException e){
			StringWriter sw = new StringWriter();
		    PrintWriter pw = new PrintWriter(sw, true);
		    e.printStackTrace(pw);
		    String mn = new Exception().getStackTrace()[0].getMethodName();// ��õ�ǰ�ķ����� 
		    String dn = new Exception().getStackTrace()[1].getClassName();// ��õ�����
			String errorString="�����ࣺ"+this.getClass().getName()+"\n���󷽷���"+mn+"\n�����ࣺ"+dn+"\n������Ϣ��\n"+sw.toString();
			Rectangle b = f.getBounds();
			new ErrorDialog(f, b, errorString);
		}
		int xl = 2;
		String[][] data = new String[ls.size() / xl][xl];
		int count = 0;
		for (int i = 0; i < ls.size() / xl; i++) { // ��
			for (int j = 0; j < xl; j++) { // ��
				data[i][j] = ls.get(j + count * xl);

			}
			count++;
		}
		count = 0;
		return data;
	}
}
