package com.mc.zjgl;

import java.awt.Component;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SQLFilter {
	static String reg = "(')|(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"  
            + "(\\b(select|update|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)"; 
	static Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE); 
	public SQLFilter(Component n,String text,String user){
		if(sqlPattern.matcher(text).find()){
			JOptionPane.showMessageDialog(n, "ϵͳ��⵽[ SQLע����Ϣ ]:"
					+ "\n[ "+text+" ];"
					+ "\nϵͳ�Ѷ�����˺�[ "+user+" ]"
					+ "\nϵͳ���ռ�[ SQLע����Ϣ ]��[ ��ȫ�ܿ�������Ϣ ]"
					+ "\n[ �ⶳ�˺� ]����ϵ[ ������ϴ�豸���޹�˾ ]"
					+ "\nȷ�Ϻ�ϵͳ�Զ��˳�...");
			System.exit(0);
		}
	}
	public SQLFilter(JFrame mf, String s,String user) {
		// TODO �Զ����ɵĹ��캯�����
		if(sqlPattern.matcher(s).find()){
			JOptionPane.showMessageDialog(mf, "ϵͳ��⵽[ SQLע����Ϣ ]:"
					+ "\n[ "+s+" ];"
					+ "\nϵͳ�Ѷ�����˺�[ "+user+" ]"
					+ "\nϵͳ���ռ�[ SQLע����Ϣ ]��[ ��ȫ�ܿ�������Ϣ ]"
					+ "\n[ �ⶳ�˺� ]����ϵ[ ������ϴ�豸���޹�˾ ]"
					+ "\nȷ�Ϻ�ϵͳ�Զ��˳�...");
			System.exit(0);
		}
	}
}
