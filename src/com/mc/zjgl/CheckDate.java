package com.mc.zjgl;

import java.awt.Component;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;

public class CheckDate {
	public static boolean ReturnCheckDateResult(Component c) {
		Date d=new Date();
		String sd=String.format("%tF", d);
		System.out.println(sd);
		String webUrl4 = "http://www.ntsc.ac.cn";// �й���ѧԺ������ʱ����
		try {
			URL url = new URL(webUrl4);// ȡ����Դ����
			URLConnection uc = url.openConnection();// �������Ӷ���
			uc.connect();// ��������
			long ld = uc.getDate();// ��ȡ��վ����ʱ��
			Date date = new Date(ld);// ת��Ϊ��׼ʱ�����
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);// �������ʱ��
			String term="2017-9-1";
			String s = sdf.format(date);
			if (s.equals(sd) == true) {
				// JOptionPane.showMessageDialog(null,"���ں˶Գɹ�");
				 Calendar cal = Calendar.getInstance(); 
				 long time1 = 0;
				 long time2 = 0;
				 try {
					cal.setTime(sdf.parse(s));
					time1=cal.getTimeInMillis();
					cal.setTime(sdf.parse(term));
					time2=cal.getTimeInMillis();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				long bd=(time2-time1)/(1000*3600*24);  
				if(bd>0){
					return true;
				}else{
					JOptionPane.showMessageDialog(c, "Beta���Խ���\n��ϵ�����̸����汾");
					System.exit(0);
				}
				
			} else {
				JOptionPane.showMessageDialog(c, "ϵͳ��������������ڲ����\n��ͬ��ϵͳ���ں�����\nȷ�Ϻ�ϵͳ�Զ��˳�..");
				System.exit(0);
			}
		} catch (MalformedURLException e) {
			JOptionPane.showMessageDialog(c, "URL��ʽ��������ϵ������˾����BUG");
			System.exit(0);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(c, "�������磬ȷ�����糩ͨ");
			System.exit(0);
		}
		return false;
	}
}
