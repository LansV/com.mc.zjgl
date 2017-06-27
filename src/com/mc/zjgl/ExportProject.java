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
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExportProject {
	static Image img=null;
	public static void exportExcel(JFrame frame,String filename,ArrayList<String> ls,String[][] arr){
		FileSystemView fsv = FileSystemView.getFileSystemView(); 	
		FilterFileChooser jfch=new FilterFileChooser();
		jfch.setCurrentDirectory(fsv.getHomeDirectory());
		jfch.setSelectedFile(new File(filename));
		int chooserStatus=jfch.showDialog(frame, "����");
		if(chooserStatus==FilterFileChooser.APPROVE_OPTION){
			File fi=jfch.getSelectedFile();
			String f = fi.getAbsolutePath();
			JDialog jf=new JDialog(frame, "��������");
			try{
		 	    img = Toolkit.getDefaultToolkit().getImage("order/Image/TLogo.png");
		 	    jf.setIconImage(img);
			}catch(Exception e){
				//JOptionPane.showMessageDialog(frame,"��ȡϵͳͼ�����");
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
				jpb.setString("����Excel��...");
				WritableWorkbook book = Workbook.createWorkbook(new File(f));
				// ������Ϊ����һҳ���Ĺ���������0��ʾ���ǵ�һҳ
				WritableSheet sheet = book.createSheet("NeedList", 0);
				jpb.setValue(20);
				jpb.setString("д��������...");
				// ��Label����Ĺ�������ָ����Ԫ��λ���ǵ�һ�е�һ��(0,0)
				WritableFont titlefont= new WritableFont(WritableFont.ARIAL,20,WritableFont.BOLD);
				WritableCellFormat titleformat=new WritableCellFormat(titlefont); 
				titleformat.setAlignment(jxl.format.Alignment.CENTRE); 
				//�Ѵ�ֱ���뷽ʽָ��Ϊ���� 
				titleformat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE); 
				Label title = new Label(0, 0, "�����а�ȫ��������Э��ר������ǼǱ�",titleformat);
				// ������õĵ�Ԫ����ӵ���������
				sheet.addCell(title);
				sheet.setRowView(0,700); 
				WritableFont contentfont= new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD);
				WritableCellFormat contentformat=new WritableCellFormat(contentfont); 
				contentformat.setAlignment(jxl.format.Alignment.CENTRE); 
				//�Ѵ�ֱ���뷽ʽָ��Ϊ���� 
				contentformat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE); 
				contentformat.setWrap(true);
				contentformat.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);
				Label tableheader1=new Label(0,1,"��Ŀ���",contentformat);
				Label tableheader2=new Label(0,2,"����λ",contentformat);
				Label tableheader3=new Label(0,3,"��Ŀ����",contentformat);
				Label tableheader4=new Label(0,4,"��Ŀ��ϵ��",contentformat);
				Label tableheader5=new Label(0,5,"ְλ",contentformat);
				Label tableheader6=new Label(0,6,"���ϵص�",contentformat);
				Label content1=new Label(1,1,ls.get(0),contentformat);
				Label content2=new Label(1,2,ls.get(1),contentformat);
				Label content3=new Label(1,3,ls.get(2),contentformat);
				Label content4=new Label(1,4,ls.get(3),contentformat);
				Label content5=new Label(1,5,ls.get(4),contentformat);
				Label content6=new Label(1,6,ls.get(5),contentformat);
				Label tableheader7=new Label(2,2,"����",contentformat);
				Label tableheader8=new Label(2,3,"����ʱ��(��)",contentformat);
				Label tableheader9=new Label(2,4,"��ϵ�绰",contentformat);
				Label tableheader10=new Label(2,5,"��������",contentformat);
				Label content7=new Label(3,2,ls.get(6),contentformat);
				Label content8=new Label(3,3,ls.get(7),contentformat);
				Label content9=new Label(3,4,ls.get(8),contentformat);
				Label content10=new Label(3,5,ls.get(9),contentformat);
				sheet.addCell(tableheader1);sheet.addCell(tableheader2);sheet.addCell(tableheader3);sheet.addCell(tableheader4);
				sheet.addCell(tableheader5);sheet.addCell(tableheader6);sheet.addCell(tableheader7);sheet.addCell(tableheader8);
				sheet.addCell(tableheader9);sheet.addCell(tableheader10);
				sheet.addCell(content1);sheet.addCell(content2);sheet.addCell(content3);sheet.addCell(content4);
				sheet.addCell(content5);sheet.addCell(content6);sheet.addCell(content7);sheet.addCell(content8);
				sheet.addCell(content9);sheet.addCell(content10);
				Label tableheader11=new Label(0,7,"��������",contentformat);
				Label content11=new Label(1,7,ls.get(10),contentformat);
				sheet.addCell(tableheader11);sheet.addCell(content11);
				Label tableheader12=new Label(0,8,"��ͨ����",contentformat);
				Label content12=new Label(1,8,ls.get(11),contentformat);
				sheet.addCell(tableheader12);sheet.addCell(content12);
				Label tableheader13=new Label(0,9,"רҵ���",contentformat);
				Label tableheader14=new Label(2,9,"����",contentformat);
				sheet.addCell(tableheader13);
				sheet.addCell(tableheader14);
				sheet.setColumnView(0,15);
				sheet.setColumnView(1,35);
				sheet.setColumnView(2,15);
				sheet.setColumnView(3,35);
				int row=arr.length;
				for(int i=0;i<row;i++){
					Label data1=new Label(0,i+10,arr[i][0],contentformat);
					Number data2=new Number(2,i+10,Integer.parseInt(arr[i][1]),contentformat);
					sheet.addCell(data1);sheet.addCell(data2);
					sheet.mergeCells(0, i+10, 1, i+10);
					sheet.mergeCells(2, i+10, 3, i+10);
				}
				Label tablefooter=new Label(0,row+10,"��Ӌ",contentformat);
				sheet.addCell(tablefooter);
				sheet.mergeCells(0, row+10, 1, row+10);
				sheet.addCell(new Formula(2,row+10,"SUM(C11:C"+Integer.toString(row+10)+")",contentformat));
				sheet.mergeCells(2, row+10, 3, row+10);
				Label tableheader15=new Label(0,row+11,"Э�����",contentformat);
				Label data1=new Label(1,row+11,ls.get(12),contentformat);
				sheet.addCell(tableheader15);sheet.addCell(data1);
				sheet.mergeCells(1, row+11, 3, row+11);
				Label tableheader16=new Label(0,row+12,"��ע",contentformat);
				Label data2=new Label(1,row+12,ls.get(13),contentformat);
				sheet.addCell(tableheader16);sheet.addCell(data2);
				sheet.mergeCells(1, row+12, 3, row+12);
				Label tableheader17=new Label(0,row+13,"���",contentformat);
				Label data3=new Label(1,row+13,ls.get(14),contentformat);
				sheet.addCell(tableheader17);sheet.addCell(data3);
				sheet.mergeCells(1, row+13, 3, row+13);
				sheet.mergeCells(0, 0, 3, 0);
				sheet.mergeCells(1, 1, 3, 1);
				sheet.mergeCells(1, 6, 3, 6);
				sheet.mergeCells(1, 7, 3, 7);
				sheet.mergeCells(1, 8, 3, 8);
				sheet.mergeCells(0, 9, 1, 9);
				sheet.mergeCells(2, 9, 3, 9);
				jpb.setValue(80);
				jpb.setString("׼�������...");
				// д�����ݲ��ر��ļ�
				book.write();
				book.close();
				jpb.setValue(100);
				jpb.setString("�����ɹ�");
			}  
			catch(Exception e){
				jpb.setValue(0);
				jpb.setBackground(Color.RED);
				jpb.setForeground(Color.BLACK);
				jpb.setString("��������!");
			} 
		}
	}
/*	public static void main(String[] args){
		JFrame j=new JFrame();
		String[][] arr={{"�������1","1"},{"�������2","1"}};
		CreateExcel.exportExcel(j, "text.xls",arr);
	}*/
}
