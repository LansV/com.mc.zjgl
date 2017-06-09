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
			String tel,String area,String address,String mark){
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
						JOptionPane.showMessageDialog(f, "����������\nϵͳ�Զ��ر�\n����ϵ:18026753608");
						System.exit(0);
					}
					id=Integer.toString(maxid);
				}
				String sqlstr="insert into expert values ("+id+","+pgid+",'"+en+"','"+es+"','"+ebd+"','"+educ+"','"+ec+"',"
						+ ""+prof+","+occup+",'"+tel+"','"+area+"','"+address+"','"+mark+"')";
				System.out.println(sqlstr);
				sql.execute(sqlstr);
				i=1;
			}
		} catch (SQLException e) {
			i=2;
			// TODO �Զ����ɵ� catch ��
			StringWriter sw = new StringWriter();
		    PrintWriter pw = new PrintWriter(sw, true);
		    e.printStackTrace(pw);
		    String mn = new Exception().getStackTrace()[0].getMethodName();// ��õ�ǰ�ķ����� 
		    String dn = new Exception().getStackTrace()[1].getClassName();// ��õ�����
			String errorString="�����ࣺ"+this.getClass().getName()+"\n���󷽷���"+mn+"\n�����ࣺ"+dn+"\n������Ϣ��\n"+sw.toString();
			Rectangle b = f.getBounds();
			new ErrorDialog(f, b, errorString);
		}
		
		return i;
	}

	public String[][] getExpert(JFrame f){
		ArrayList<String> ls = new ArrayList<String>();
		try {
			sql=con.createStatement();
			res=sql.executeQuery("select * from expert "
					+ "inner join professionalgroup on expert.professionalgroup=professionalgroup.professionalgroupid "
					+ "inner join professional on expert.professional=professional.professionalid "
					+ "inner join occupation on expert.occupation=occupation.occupationid order by expertid");
			while(res.next()){
				ls.add(res.getString("expertid"));
				ls.add(res.getString("professionalgroupname"));
				ls.add(res.getString("expertname"));
				ls.add(res.getString("expertsex"));
				ls.add(res.getString("expertburndate"));
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
			// TODO �Զ����ɵ� catch ��
			StringWriter sw = new StringWriter();
		    PrintWriter pw = new PrintWriter(sw, true);
		    e.printStackTrace(pw);
		    String mn = new Exception().getStackTrace()[0].getMethodName();// ��õ�ǰ�ķ����� 
		    String dn = new Exception().getStackTrace()[1].getClassName();// ��õ�����
			String errorString="�����ࣺ"+this.getClass().getName()+"\n���󷽷���"+mn+"\n�����ࣺ"+dn+"\n������Ϣ��\n"+sw.toString();
			Rectangle b = f.getBounds();
			new ErrorDialog(f, b, errorString);
		}
		int xl=13;
		String[][] data=new String[ls.size()/xl][xl];
	   	int count=0;
	   	for(int i=0;i<ls.size()/xl;i++){  //��
	   		for(int j=0;j<xl;j++){  //��
	   			data[i][j]=ls.get(j+count*xl);
	   			
	   		}
	   		count++;
	   	}
	   	count=0;
		return data;
	}
}
