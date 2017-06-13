package com.mc.zjgl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CheckFormat {
	public boolean isDate(String s){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		String dstr=s;  
		boolean bl = false;
		try {
			@SuppressWarnings("unused")
			java.util.Date date=sdf.parse(dstr);
			bl=true;
		} catch (ParseException e) {
			// TODO 自动生成的 catch 块
		} 
		//System.out.println(s);
		return bl;
	}
	public boolean isFloat(String s){
		boolean b=false;
		try{
			float f=Float.parseFloat(s);
			if(f>0){
				b=true;
			}else{
				b=false;
			}
		}catch(Exception e){
			// TODO 自动生成的 catch 块
		}
		return b;
	}
}
