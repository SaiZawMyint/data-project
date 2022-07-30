package com.ProJ;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class DisplayScreen extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3707386940692249396L;
	static JPanel topPanel,mainPanel,bPanel;
	static JButton addBtn;
	////////
	JPanel title,body;
	JLabel titlelb;
	JScrollPane sp;
	static GenerateTable table;
	Choice ViewBy;
	String tbname;
	public DisplayScreen(String tbname) {
		// TODO Auto-generated constructor stub
		this.setLayout(new BorderLayout());
		
		topPanel=new JPanel();
		topPanel.setBackground(getBackground());
		
		mainPanel=new JPanel();
		mainPanel.setBackground(getBackground());
		mainPanel.setLayout(new BorderLayout());
		
		addBtn=new JButton("Add");
		addBtn.addActionListener(this);
		
		ViewBy=new Choice();
		setDisplayFrame(tbname);
		
		this.add(topPanel,BorderLayout.NORTH);
		this.add(mainPanel,BorderLayout.CENTER);
		
		this.tbname=tbname;
	}
	
	private void setDisplayFrame(String tbname) {
		// TODO Auto-generated method stub
		title=new JPanel();
		title.setLayout(new BorderLayout());
		
		titlelb=new JLabel(tbname);
		titlelb.setHorizontalAlignment(SwingConstants.CENTER);
		titlelb.setForeground(Color.blue);
		titlelb.setFont(new Font("Arial Rounded MT", Font.BOLD, 20));
		title.add(titlelb,BorderLayout.NORTH);
		
		body=new JPanel();
		body.add(DisplayScreen.addBtn);
		
		title.add(body,BorderLayout.CENTER);
		
		table=new GenerateTable(tbname);
		ShowPanel.tb_arys.add(table);
		sp=new JScrollPane(table);
		
		DisplayScreen.mainPanel.add(sp,BorderLayout.CENTER);
		
		DisplayScreen.topPanel.add(title);
		
	}
	
	 void updateFrame(int index) {
		ShowPanel.dis_ary.get(index).removeAll();
		DisplayScreen dis=new DisplayScreen(tbname);
		ShowPanel.dis_ary.get(index).add(dis,BorderLayout.CENTER);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==DisplayScreen.addBtn)
			ShowPanel.hideaddbtn.doClick();
	}
	

}
