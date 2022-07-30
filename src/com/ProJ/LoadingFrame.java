package com.ProJ;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class LoadingFrame extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8779898811360851733L;
	static int i=0;
	JProgressBar jb;
	JLabel titlep;
	
	public LoadingFrame() {
		// TODO Auto-generated constructor stub
		this.setLayout(new FlowLayout());
		 jb = new JProgressBar();
		jb.setValue(0);
		jb.setStringPainted(true);
		jb.setForeground(Color.GREEN);
		jb.setBackground(Color.YELLOW);
		
		this.add(jb);
		
	}
	void loading() {
		// TODO Auto-generated method stub
				
				 do{
					this.jb.setValue(i);
					i++;
					
					try{
						Thread.sleep(50);
					}catch(Exception ex){
						ex.printStackTrace();
						
						}
					
					
					}while(i<=100);
				
	}
	void logo() {
		this.jb.setValue(100);
		
	}
}
