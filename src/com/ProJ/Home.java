package com.ProJ;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;


public class Home extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5028608503388794038L;
	static Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
	Container c;
	
	//set menu
	JMenuBar jmb;
	JMenu file,edit,setting;
	JMenuItem item;
	
	//split
	static JSplitPane sp;
	//local
	static NotiPanel np;
	static ShowPanel sm;
	static Screen sc;
	
	
	ImageIcon logo=new ImageIcon(getClass().getClassLoader().getResource("balance help.png"));

	
	public Home() {
		// TODO Auto-generated constructor stub
		setTitle("My Database");
		setSize(size);
		setIconImage(logo.getImage());
		//container
		c=getContentPane();
		
		//set menu
		fileBar();
		editBar();
		settingBar();
		jmb=new JMenuBar();
		setJMenuBar(jmb);
		jmb.add(file);
		jmb.add(edit);
		jmb.add(setting);
		
		np=new NotiPanel();
		sm=new ShowPanel();
		
		sc=new Screen();
		
		sp=new JSplitPane();
		sp.setDividerLocation(getWidth()/2);
		sp.setDividerSize(3);
		sp.setLeftComponent(null);
		sp.setRightComponent(sm);
		
		c.add(sp);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void fileBar() {
		// TODO Auto-generated method stub
		file=new JMenu("File");
		
		item=new JMenuItem("New");
		item.addActionListener(this);
		file.add(item);
		
		item=new JMenuItem("Open");
		item.addActionListener(this);
		file.add(item);
		
		item=new JMenuItem("Save");
		item.addActionListener(this);
		file.add(item);
	}

	private void editBar() {
		// TODO Auto-generated method stub
		edit=new JMenu("Edit");
		
		item=new JMenuItem("Add");
		item.addActionListener(this);
		edit.add(item);
		
		item=new JMenuItem("Update");
		item.addActionListener(this);
		edit.add(item);
		
		item=new JMenuItem("Delete");
		item.addActionListener(this);
		edit.add(item);
	}

	private void settingBar() {
		// TODO Auto-generated method stub
		setting=new JMenu("Setting");
		
		item=new JMenuItem("Exit");
		item.addActionListener(this);
		setting.add(item);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Home home=new Home();
		home.setVisible(true);
		home.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s=e.getActionCommand();
		//exit
		if(s.equals("Exit")) {
			if(JOptionPane.showConfirmDialog(null, "Exit Project?")==JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
		if(s.equals("Add")) {
			ShowPanel.add.doClick();
		}
		if(s.equals("New")) {
			ShowPanel.create.doClick();
		}
		if(s.equals("Open")) {
			ShowPanel.open.doClick();
		}
	}

}
