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
		String webUrl4 = "http://www.ntsc.ac.cn";// 中国科学院国家授时中心
		try {
			URL url = new URL(webUrl4);// 取得资源对象
			URLConnection uc = url.openConnection();// 生成连接对象
			uc.connect();// 发出连接
			long ld = uc.getDate();// 读取网站日期时间
			Date date = new Date(ld);// 转换为标准时间对象
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);// 输出北京时间
			String term="2017-9-1";
			String s = sdf.format(date);
			if (s.equals(sd) == true) {
				// JOptionPane.showMessageDialog(null,"日期核对成功");
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
					JOptionPane.showMessageDialog(c, "Beta测试结束\n联系服务商更换版本");
					System.exit(0);
				}
				
			} else {
				JOptionPane.showMessageDialog(c, "系统日期与服务器日期不相符\n请同步系统日期后重试\n确认后系统自动退出..");
				System.exit(0);
			}
		} catch (MalformedURLException e) {
			JOptionPane.showMessageDialog(c, "URL格式错误，请联系天澜公司处理BUG");
			System.exit(0);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(c, "请检查网络，确保网络畅通");
			System.exit(0);
		}
		return false;
	}
}
