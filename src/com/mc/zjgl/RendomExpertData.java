package com.mc.zjgl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class RendomExpertData {
	Statement sql = null;
	ResultSet res = null;
	Dao d = new Dao();
	Connection con = d.getcon();

	public String[][] getNeed(String pid) {
		ArrayList<String> ls = new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select*from expertneed where projectid=" + pid);
			while (res.next()) {
				ls.add(res.getString("needgroup"));
				ls.add(res.getString("neednum"));
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
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

	public String[][] getRendomExpert(String pid, String group,int num ,String plandate,String avoid,ArrayList<String> countls) {
		ArrayList<String> ls = new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery(
					"select expert.expertid,professionalgroup.professionalgroupname,expert.expertname,expert.tel from "
							+ "expertneed left join expertgroup on needgroup=expertgroup.professionalgroup "
							+ "left join professionalgroup on expertgroup.professionalgroup=professionalgroup.professionalgroupid "
							+ "left join expertplan on expertplan.expertid=expertgroup.expertid "
							+ "left join expert on expert.expertid=expertgroup.expertid "
							+ "where expertneed.projectid = " + pid + " and expertneed.needgroup=" + group + " and expert.expertcompany !='"+avoid+"' and "
							+ "(expertplan.expertplandate is null or expertplan.expertplandate!='" + plandate + "')");
			int i = 1;
			while (res.next()) {
				ls.add(Integer.toString(i));
				ls.add(res.getString("expertid"));
				ls.add(res.getString("professionalgroupname"));
				ls.add(res.getString("expertname"));
				ls.add(res.getString("tel"));
				i++;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		int xl = 5;
		int lss=ls.size()/xl;
		String[][] data = new String[lss][xl];
		int count = 0;
		for (int i = 0; i < lss; i++) {
			boolean b=false;
			for (int j = 0; j < xl; j++) { // 列
				if(j==1){
					if(countls.size()<1){
						System.out.println("add "+ls.get(j + count * xl));
						countls.add(ls.get(j + count * xl));
					}else{
						for(int t=0;t<countls.size();t++){
							if(countls.get(t).equals(ls.get(j + count * xl))){
								lss=lss-1;
								b=false;
								System.out.println("get repeat "+ls.get(j + count * xl));
								break;
							}else{
								b=true;
							}
						}
						if(b==true){
							countls.add(ls.get(j + count * xl));
							System.out.println("add2 "+ls.get(j + count * xl));
						}
					}
				}
				data[i][j] = ls.get(j + count * xl);
			}
			System.out.println(data[i][0] + "    " + data[i][1] + "    " + data[i][2] + "    " + data[i][3]);
			count++;
		}
		count = 0;
		if(lss==0){
			JOptionPane.showMessageDialog(null, "未查询到符合条件专家");
		}else if(lss<num*2){
			JOptionPane.showMessageDialog(null, "符合条件专家比例未到1:2");
		}else if(lss<num){
			JOptionPane.showMessageDialog(null, "符合条件专家数量未达到最低要求");
		}

		return data;
	}

	public static void main(String[] args) {
		RendomExpertData red = new RendomExpertData();
		String pid = "170617001";
		String[][] narr = red.getNeed(pid);
		int narrl = narr.length;
		ArrayList<String> countls = new ArrayList<String>();
		for (int i = 0; i < narrl; i++) {
			System.out.println("第" + (i + 1) + "次筛选"+"     需要"+narr[i][1]+"人");
			red.getRendomExpert(pid, narr[i][0],Integer.parseInt(narr[i][1]), "","",countls);
		}

	}
}
