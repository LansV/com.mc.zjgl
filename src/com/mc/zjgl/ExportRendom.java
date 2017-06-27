package com.mc.zjgl;

import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.filechooser.FileSystemView;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExportRendom {
	static Image img=null;
	public static void exportExcel(JFrame frame,String filename,ArrayList<String> ls ,String[][] arr){
		FileSystemView fsv = FileSystemView.getFileSystemView(); 	
		FilterFileChooser jfch=new FilterFileChooser();
		jfch.setCurrentDirectory(fsv.getHomeDirectory());
		jfch.setSelectedFile(new File(filename));
		int chooserStatus=jfch.showDialog(frame, "保存");
		if(chooserStatus==FilterFileChooser.APPROVE_OPTION){
			File fi=jfch.getSelectedFile();
			String f = fi.getAbsolutePath();
			JDialog jf=new JDialog(frame, "导出进度");
			try{
		 	    img = Toolkit.getDefaultToolkit().getImage("order/Image/TLogo.png");
		 	    jf.setIconImage(img);
			}catch(Exception e){
				//JOptionPane.showMessageDialog(frame,"获取系统图标错误");
			}
			frame.setEnabled(false);
			jf.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
			jf.addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					frame.setEnabled(true);
					jf.dispose();
				}
			});
			jf.setResizable(false);
			jf.setBounds(600,300,250,80);
			Container jfc=jf.getContentPane();
			jfc.setLayout(null);
			JProgressBar jpb=new JProgressBar();
			jpb.setStringPainted(true);
			jpb.setBounds(20,10,200,25);
			jfc.add(jpb);
			jf.setVisible(true);
			try{
				jpb.setValue(0);
				jpb.setString("创建Excel中...");
				WritableWorkbook book = Workbook.createWorkbook(new File(f));
				// 生成名为“第一页”的工作表，参数0表示这是第一页
				WritableSheet sheet = book.createSheet("PriceList", 0);
				jpb.setValue(20);
				jpb.setString("写入数据中...");
				// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
				WritableFont titlefont= new WritableFont(WritableFont.ARIAL,20,WritableFont.BOLD);
				WritableCellFormat titleformat=new WritableCellFormat(titlefont); 
				titleformat.setAlignment(jxl.format.Alignment.CENTRE); 
				//把垂直对齐方式指定为居中 
				titleformat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE); 
				Label title = new Label(0, 0, "江门市安全生产管理协会专家抽选结果记录表",titleformat);
				// 将定义好的单元格添加到工作表中
				sheet.addCell(title);
				sheet.setRowView(0,700); 
				WritableFont contentfont= new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD);
				WritableCellFormat contentformat=new WritableCellFormat(contentfont); 
				contentformat.setAlignment(jxl.format.Alignment.CENTRE); 
				//把垂直对齐方式指定为居中 
				contentformat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE); 
				contentformat.setWrap(true);
				contentformat.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
				Label tableheader1=new Label(0,1,"项目名称",contentformat);
				Label tableheader2=new Label(1,1,ls.get(0),contentformat);
				Label tableheader3=new Label(4,1,"编号",contentformat);
				Label tableheader4=new Label(5,1,ls.get(1),contentformat);
				sheet.addCell(tableheader1);sheet.addCell(tableheader2);
				sheet.addCell(tableheader3);sheet.addCell(tableheader4);
				sheet.mergeCells(1, 1, 3, 1);
				Label tableheader5=new Label(0,2,"工作内容",contentformat);
				sheet.mergeCells(0, 2, 0, 4);
				Label tableheader6=new Label(1,2,ls.get(2),contentformat);
				sheet.mergeCells(1, 2, 3, 4);
				Label tableheader7=new Label(4,2,"需求方",contentformat);
				Label tableheader8=new Label(5,2,"",contentformat);
				Label tableheader9=new Label(4,3,"抽签人",contentformat);
				Label tableheader10=new Label(5,3,"",contentformat);
				Label tableheader11=new Label(4,4,"监督人",contentformat);
				Label tableheader12=new Label(5,4,"",contentformat);
				sheet.addCell(tableheader5);sheet.addCell(tableheader6);
				sheet.addCell(tableheader7);sheet.addCell(tableheader8);
				sheet.addCell(tableheader9);sheet.addCell(tableheader10);
				sheet.addCell(tableheader11);sheet.addCell(tableheader12);
				Label tableheader13=new Label(0,5,"抽取专家名单",contentformat);
				sheet.addCell(tableheader13);
				sheet.mergeCells(0, 5, 5, 5);
				Label tableheader14=new Label(0,6,"抽取时间："+ls.get(3),contentformat);
				Label tableheader15=new Label(3,6,"抽取人数：",contentformat);
				sheet.mergeCells(0, 6, 2, 6);sheet.mergeCells(3, 6, 5, 6);
				sheet.addCell(tableheader14);sheet.addCell(tableheader15);
				Label tbr1=new Label(0,7,"序号",contentformat);Label tbr2=new Label(1,7,"专业类别",contentformat);
				Label tbr3=new Label(2,7,"姓名",contentformat);Label tbr4=new Label(3,7,"联系电话",contentformat);
				Label tbr5=new Label(4,7,"专家签名",contentformat);Label tbr6=new Label(5,7,"备注",contentformat);
				sheet.addCell(tbr1);sheet.addCell(tbr2);sheet.addCell(tbr3);sheet.addCell(tbr4);sheet.addCell(tbr5);sheet.addCell(tbr6);
				int row=arr.length;
				for(int i=0;i<row;i++){
					Label data1=new Label(0,i+8,arr[i][0],contentformat);
					Label data2=new Label(1,i+8,arr[i][1],contentformat);
					Label data3=new Label(2,i+8,arr[i][2],contentformat);
					Label data4=new Label(3,i+8,arr[i][3],contentformat);
					Label data5=new Label(4,i+8,"",contentformat);
					Label data6=new Label(5,i+8,"",contentformat);
					sheet.addCell(data1);
					sheet.addCell(data2);
					sheet.addCell(data3);
					sheet.addCell(data4);
					sheet.addCell(data5);
					sheet.addCell(data6);
				}
				Label tbr7=new Label(0,row+8,"",contentformat);
				sheet.mergeCells(0, row+8, 5, row+8);
				sheet.addCell(tbr7);
				sheet.mergeCells(0, 0, 5, 0);
				//sheet.mergeCells(0, row+2, 5, row+2);
				jpb.setValue(80);
				jpb.setString("准备完成中...");
				// 写入数据并关闭文件
				book.write();
				book.close();
				jpb.setValue(100);
				jpb.setString("导出成功");
			}  
			catch(Exception e){
				jpb.setValue(0);
				jpb.setBackground(Color.RED);
				jpb.setForeground(Color.BLACK);
				jpb.setString("导出错误!");
			} 
		}
	}
/*	public static void main(String[] args){
		JFrame j=new JFrame();
		String[][] arr={{"1","100120","测试","台","9.2","10.2"},{"2","100120","测试","台","9.2","10.2"}};
		ExportRendom.exportExcel(j, "text.xls",arr);
	}*/
}
