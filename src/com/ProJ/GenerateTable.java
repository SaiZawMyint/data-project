package com.ProJ;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class GenerateTable extends JTable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6695048970057966545L;
	JSplitPane sp;
	static	Object[][] data;
	String[] title;
	//AddCoustomTable
	public GenerateTable(String tbname){
		
		TableData(tbname);
		setModel(new DefaultTableModel(data,title));
		getTableHeader().setFont(new Font("", Font.BOLD, 20));
		getTableHeader().setOpaque(false);
		getTableHeader().setBackground(new Color(32,136,203));
		getTableHeader().setForeground(new Color(225,225,225));
		setRowHeight(30);
		setBackground(getBackground());
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setSelectionBackground(new Color(153, 204, 255));
		
	}
	
	public void TableData(String tbname) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:9906/guiproject?useSSL=false", "root",
					"root");
			String sql="select * from tb_info where tbname='"+tbname+"';";
			PreparedStatement ps=c.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			String s="";
			int col=0;
			if(rs.next()) {
				col=rs.getInt(3);
				s=rs.getString(4);
			}
			
			String tb=tbname.replaceAll(" ", "_");
			String sql1="select * from "+tb+";";
			PreparedStatement ps1=c.prepareStatement(sql1);
			ResultSet rs1=ps1.executeQuery();
			int rowcount=0;
			String getvalue="";
			while(rs1.next()) {
				rowcount++;
			}
			
			data=new Object[rowcount][col+1];
			
			title=new String[col];
			
			for(int i=0;i<title.length;i++) {
				title[i]=s.substring(0, s.indexOf("@#"));
				s=s.substring(s.indexOf("@#")+2,s.length());
			}
			String sql2="select * from "+tb+" order by cr_adm_date,cr_adm_time desc;";
			PreparedStatement ps2=c.prepareStatement(sql2);
			ResultSet rs2=ps2.executeQuery();
			while(rs2.next()) {
				for(int i=0;i<title.length;i++) {
					getvalue+=rs2.getString(i+1)+"#";
				}
				
			}
			for(int i=0;i<data.length;i++) {
				for(int j=0;j<title.length;j++) {
					data[i][j]=getvalue.substring(0, getvalue.indexOf("#"));
					getvalue=getvalue.substring(getvalue.indexOf("#")+1, getvalue.length());
				}
			}
			c.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void updateAdd(String tbname) {
		new GenerateTable(tbname);
	}

}
