package com.mc.zjgl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JFrame;
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

	public String[][] getRendomExpert(JFrame f,String pid, String group, int num, String plandate, String avoid,
			ArrayList<String> countls, ArrayList<Object> rs) {
		ArrayList<String> ls = new ArrayList<String>();
		try {
			String existid="";
			if(countls.size()==0){
				existid="''";
			}else{
				for(int i=0;i<countls.size();i++){
					if(i!=countls.size()-1){
						existid=existid+countls.get(i)+",";
					}else{
						existid=existid+countls.get(i);
					}
				}
			}
			System.out.println(existid);
			sql = con.createStatement();
			res = sql.executeQuery(
					"select expert.expertid,professionalgroup.professionalgroupname,expert.expertname,expert.tel from "
							+ "expertneed left join expertgroup on needgroup=expertgroup.professionalgroup "
							+ "left join professionalgroup on expertgroup.professionalgroup=professionalgroup.professionalgroupid "
							+ "left join expertplan on expertplan.expertid=expertgroup.expertid "
							+ "left join expert on expert.expertid=expertgroup.expertid "
							+ "where expertneed.projectid = " + pid + " and expertneed.needgroup=" + group
							+ " and expert.expertcompany !='" + avoid + "' and "
							+ "(expertplan.expertplandate is null or expertplan.expertplandate!='" + plandate + "') "
							+ "and expert.expertid  not in ("+existid+")");
			int i=1;
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
		int lss = ls.size() / xl;
		int lsst = lss;
		String[][] data = new String[lss][xl];
		int count = 0;
		for (int i = 0; i < lsst; i++) {
			//boolean b = false;
			for (int j = 0; j < xl; j++) { // 列
				data[i][j] = ls.get(j + count * xl);
			}
			count++;
		}
		count = 0;
		HashSet<Integer> set = new HashSet<Integer>();
		if (lss == 0) {
			JOptionPane.showMessageDialog(f, "未查询到符合条件专家");
		} else if (lss >= num && lss < num * 2) {
			JOptionPane.showMessageDialog(f, "符合条件专家比例未到1:2");
			// System.out.println(lss);
			randomSet(0, lss, num, set);
			// System.out.print("抽选值：");
			for (int i : set) {
				int h = rs.size() / 7;
				rs.add(Integer.toString(h + 1));
				rs.add(data[i][1]);
				countls.add(data[i][1]);
				rs.add(data[i][2]);
				rs.add(data[i][3]);
				rs.add(data[i][4]);
				rs.add("");
				rs.add(false);
				// System.out.print(i+" ");
			}
			// System.out.println();
		} else if (lss < num) {
			JOptionPane.showMessageDialog(f, "符合条件专家数量未达到最低要求");
			for (int i = 0; i < lss; i++) {
				int h = rs.size() / 7;
				rs.add(Integer.toString(h + 1));
				rs.add(data[i][1]);
				countls.add(data[i][1]);
				rs.add(data[i][2]);
				rs.add(data[i][3]);
				rs.add(data[i][4]);
				rs.add("");
				rs.add(false);
			}
		} else if (lss >= num * 2) {
			// System.out.println(lss);
			randomSet(0, lss, num * 2, set);
			// System.out.print("抽选值：");
			for (int i : set) {
				int h = rs.size() / 7;
				rs.add(Integer.toString(h + 1));
				rs.add(data[i][1]);
				countls.add(data[i][1]);
				rs.add(data[i][2]);
				rs.add(data[i][3]);
				rs.add(data[i][4]);
				rs.add("");
				rs.add(false);
				// System.out.print(i+" ");
			}
			// System.out.println();
		}
		return data;
	}

	public Object[][] getRendomResult(JFrame f,String pid, String date, String avoid) {
		RendomExpertData red = new RendomExpertData();
		String[][] narr = red.getNeed(pid);
		int narrl = narr.length;
		ArrayList<String> countls = new ArrayList<String>();
		ArrayList<Object> res = new ArrayList<Object>();
		for (int i = 0; i < narrl; i++) {
			 System.out.println("第" + (i + 1) + "次筛选"+" 需要"+narr[i][1]+"人");
			red.getRendomExpert(f,pid, narr[i][0], Integer.parseInt(narr[i][1]), date, avoid, countls, res);
		}
		int xl = 7;
		int resl = res.size() / xl;
		Object[][] data = new Object[resl][xl];
		int count = 0;
		for (int i = 0; i < resl; i++) { // 行
			for (int j = 0; j < xl; j++) { // 列
				data[i][j] = res.get(j + count * xl);

			}
			count++;
		}
		count = 0;
		for (Object[] i : data) {
			for (Object t : i) {
				System.out.print(t + "  ");
			}
			System.out.println();
		}
		return data;
	}

/*	public static void main(String[] args) {

	}*/

	public static void randomSet(int min, int max, int n, HashSet<Integer> set) {
		if (n > (max - min + 1) || max < min) {
			return;
		}
		int setSize = 0;
		while (true) {
			int num = (int) (Math.random() * (max - min)) + min;
			set.add(num);// 将不同的数存入HashSet中
			setSize = set.size();
			if (setSize >= n) {
				break;
			}
		}
	}
}
