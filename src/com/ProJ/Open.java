package com.ProJ;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Open extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7728735608241888802L;
	JLabel title,tbname;
	JPanel header,body,footer;
	JComboBox<String> tab;

	public Open() {
		// TODO Auto-generated constructor stub
		
		this.setBackground(new Color(200,199,225));
		this.setLayout(new BorderLayout());
		
		header=new JPanel();
		header.setBackground(getBackground());
		title=new JLabel("Tables");
		title.setFont(new Font("", Font.BOLD, 15));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		header.add(title);
		
		body=new JPanel();
		body.setBackground(getBackground());
		tbname=new JLabel("table name");
		tbname.setFont(new Font("", Font.BOLD, 15));
		tbname.setHorizontalAlignment(SwingConstants.CENTER);
		
		tab=new JComboBox<String>();
		addItem();
		
		body.add(tbname);
		body.add(tab);
		
		this.add(header,BorderLayout.NORTH);
		this.add(body,BorderLayout.CENTER);
	}

	private void addItem() {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/guiproject?useSSL=false", "root",
					"root");
			
			String sql="select tbname from tb_info";
			PreparedStatement ps=c.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				this.tab.addItem(rs.getString(1));
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
