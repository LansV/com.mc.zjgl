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

public class BasicData {
	Statement sql = null;
	ResultSet res = null;
	Dao d = new Dao();
	Connection con = d.getcon();

	public String[][] getGroup(JFrame f) {
		ArrayList<String> ls = new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select*from professionalgroup");
			while (res.next()) {
				ls.add(res.getString("professionalgroupid"));
				ls.add(res.getString("professionalgroupname"));
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			// **********************************************
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw, true);
			e.printStackTrace(pw);
			String mn = new Exception().getStackTrace()[0].getMethodName();// ��õ�ǰ�ķ�����
			String dn = new Exception().getStackTrace()[1].getClassName();// ��õ�����
			String errorString = "�����ࣺ" + this.getClass().getName() + "\n���󷽷���" + mn + "\n�����ࣺ" + dn + "\n������Ϣ��\n"
					+ sw.toString();
			Rectangle b = f.getBounds();
			new ErrorDialog(f, b, errorString);
			// ************************************************
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

	public int addGroup(JFrame f, String Item) {
		int i = 0;
		try {
			int maxid = 0;
			sql = con.createStatement();
			res = sql.executeQuery("select max (professionalgroupid) as pgid from professionalgroup");
			if (res.next()) {
				if (res.getString("pgid") == null) {
					maxid = 1001;
				} else {
					maxid = res.getInt("pgid") + 1;
					if (maxid >= 2000) {
						JOptionPane.showMessageDialog(f, "����������\nϵͳ�Զ��ر�\n����ϵ:18026753608");
						System.exit(0);
					}
				}

			}
			System.out.println(maxid);
			sql.execute("insert into professionalgroup values (" + maxid + ",'" + Item + "')");
			i = 1;
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			i = 2;
			// **********************************************
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw, true);
			e.printStackTrace(pw);
			String mn = new Exception().getStackTrace()[0].getMethodName();// ��õ�ǰ�ķ�����
			String dn = new Exception().getStackTrace()[1].getClassName();// ��õ�����
			String errorString = "�����ࣺ" + this.getClass().getName() + "\n���󷽷���" + mn + "\n�����ࣺ" + dn + "\n������Ϣ��\n"
					+ sw.toString();
			Rectangle b = f.getBounds();
			new ErrorDialog(f, b, errorString);
			// ************************************************
		}
		return i;
	}

	public String[][] getProfessionnal(JFrame f) {
		ArrayList<String> ls = new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select*from professional");
			while (res.next()) {
				ls.add(res.getString("professionalid"));
				ls.add(res.getString("professionalname"));
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			// **********************************************
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw, true);
			e.printStackTrace(pw);
			String mn = new Exception().getStackTrace()[0].getMethodName();// ��õ�ǰ�ķ�����
			String dn = new Exception().getStackTrace()[1].getClassName();// ��õ�����
			String errorString = "�����ࣺ" + this.getClass().getName() + "\n���󷽷���" + mn + "\n�����ࣺ" + dn + "\n������Ϣ��\n"
					+ sw.toString();
			Rectangle b = f.getBounds();
			new ErrorDialog(f, b, errorString);
			// ************************************************
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

	public int addProfessional(JFrame f, String Item) {
		int i = 0;
		try {
			int maxid = 0;
			sql = con.createStatement();
			res = sql.executeQuery("select max (professionalid) as pgid from professional");
			if (res.next()) {
				if (res.getString("pgid") == null) {
					maxid = 2001;
				} else {
					maxid = res.getInt("pgid") + 1;
					if (maxid >= 3000) {
						JOptionPane.showMessageDialog(f, "����������\nϵͳ�Զ��ر�\n����ϵ:18026753608");
						System.exit(0);
					}
				}

			}
			System.out.println(maxid);
			sql.execute("insert into professional values (" + maxid + ",'" + Item + "')");
			i = 1;
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			i = 2;
			// **********************************************
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw, true);
			e.printStackTrace(pw);
			String mn = new Exception().getStackTrace()[0].getMethodName();// ��õ�ǰ�ķ�����
			String dn = new Exception().getStackTrace()[1].getClassName();// ��õ�����
			String errorString = "�����ࣺ" + this.getClass().getName() + "\n���󷽷���" + mn + "\n�����ࣺ" + dn + "\n������Ϣ��\n"
					+ sw.toString();
			Rectangle b = f.getBounds();
			new ErrorDialog(f, b, errorString);
			// ************************************************
		}
		return i;
	}

	public String[][] getSex(JFrame f) {
		ArrayList<String> ls = new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select*from sex");
			while (res.next()) {
				ls.add(res.getString("sexid"));
				ls.add(res.getString("sex"));
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			// **********************************************
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw, true);
			e.printStackTrace(pw);
			String mn = new Exception().getStackTrace()[0].getMethodName();// ��õ�ǰ�ķ�����
			String dn = new Exception().getStackTrace()[1].getClassName();// ��õ�����
			String errorString = "�����ࣺ" + this.getClass().getName() + "\n���󷽷���" + mn + "\n�����ࣺ" + dn + "\n������Ϣ��\n"
					+ sw.toString();
			Rectangle b = f.getBounds();
			new ErrorDialog(f, b, errorString);
			// ************************************************
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

	public String[][] getEducation(JFrame f) {
		ArrayList<String> ls = new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select*from education");
			while (res.next()) {
				ls.add(res.getString("educationid"));
				ls.add(res.getString("educationname"));
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			// **********************************************
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw, true);
			e.printStackTrace(pw);
			String mn = new Exception().getStackTrace()[0].getMethodName();// ��õ�ǰ�ķ�����
			String dn = new Exception().getStackTrace()[1].getClassName();// ��õ�����
			String errorString = "�����ࣺ" + this.getClass().getName() + "\n���󷽷���" + mn + "\n�����ࣺ" + dn + "\n������Ϣ��\n"
					+ sw.toString();
			Rectangle b = f.getBounds();
			new ErrorDialog(f, b, errorString);
			// ************************************************
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

	public String[][] getOccupation(JFrame f) {
		ArrayList<String> ls = new ArrayList<String>();
		try {
			sql = con.createStatement();
			res = sql.executeQuery("select*from occupation");
			while (res.next()) {
				ls.add(res.getString("occupationid"));
				ls.add(res.getString("occupationname"));
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			// **********************************************
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw, true);
			e.printStackTrace(pw);
			String mn = new Exception().getStackTrace()[0].getMethodName();// ��õ�ǰ�ķ�����
			String dn = new Exception().getStackTrace()[1].getClassName();// ��õ�����
			String errorString = "�����ࣺ" + this.getClass().getName() + "\n���󷽷���" + mn + "\n�����ࣺ" + dn + "\n������Ϣ��\n"
					+ sw.toString();
			Rectangle b = f.getBounds();
			new ErrorDialog(f, b, errorString);
			// ************************************************
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
