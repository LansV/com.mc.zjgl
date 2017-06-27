package com.mc.zjgl;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FilterFileChooser extends JFileChooser {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FilterFileChooser(){
		super();
	}
    public void approveSelection() {
    	File file=getSelectedFile();
    	if(file.exists()){
    		JOptionPane.showMessageDialog(getParent(),"�ļ��Ѵ���");
    		return;
    	}
    	super.approveSelection();
    }
}
