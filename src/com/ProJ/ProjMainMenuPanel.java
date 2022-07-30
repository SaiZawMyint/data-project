package com.ProJ;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class ProjMainMenuPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel header,body,footer,mainbtn,n,m;
	JLabel title;
	JScrollPane main;
	JButton addinfo,addcolum,edit,delete,update;
	
	public ProjMainMenuPanel() {
		// TODO Auto-generated constructor stub
		
		this.setBackground(new Color(200,188,199));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		header=new JPanel();
		header.setBackground(Color.blue);
		
		title=new JLabel("My Tables");
		header.add(title,BorderLayout.CENTER);
		
		body=new JPanel();
		body.setBackground(Color.red);
		body.setLayout(new BorderLayout());
		
		mainbtn=new JPanel();
		
		mainbtn.setLayout(new GridLayout(15, 1, 1, 5));
		
		addinfo=new JButton("Add data");
		addinfo.setHorizontalAlignment(SwingConstants.LEADING);
		
		addcolum=new JButton("Add Columns");
		addcolum.setHorizontalAlignment(SwingConstants.LEADING);

		edit=new JButton("Edit Data");
		edit.setHorizontalAlignment(SwingConstants.LEADING);

		delete=new JButton("Delete Data");
		delete.setHorizontalAlignment(SwingConstants.LEADING);

		update=new JButton("Update data");
		update.setHorizontalAlignment(SwingConstants.LEADING);

		mainbtn.add(addinfo);
		mainbtn.add(addcolum);
		mainbtn.add(edit);
		mainbtn.add(update);
		mainbtn.add(delete);
		
		main=new JScrollPane(mainbtn);
		
		body.add(main,BorderLayout.CENTER);
		
		footer=new JPanel();
		footer.setBackground(Color.green);
		
		this.add(header);
		this.add(body);
		this.add(footer);
	}

}
