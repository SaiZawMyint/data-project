package com.ProJ;

import java.awt.BorderLayout;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class OpenTableMenu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel p, title, body, footer;
	JScrollPane sp;
	JTree[] tree;
	JTree mt, tab;
	JLabel lab, help;
	DefaultMutableTreeNode treemodel;
	DefaultMutableTreeNode def;

	public OpenTableMenu() {
		// TODO Auto-generated constructor stub
		this.setLayout(new BorderLayout());

		p = new JPanel();
		title = new JPanel();
		title.setLayout(new BorderLayout());
		body = new JPanel();
		body.setLayout(new BorderLayout());
		footer = new JPanel();

		lab = new JLabel("Table Overview :");
		title.add(lab, BorderLayout.WEST);
		tab = new JTree();

		treemodel = new DefaultMutableTreeNode("Tables");
		tab.setModel(new DefaultTreeModel(treemodel) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1700942675562599505L;

			{

				def = new DefaultMutableTreeNode();

				getTableFolder(def);
			}

		});
		help = new JLabel("Feedback us on our original website http://J9TableProject.com");
		help.setFont(new Font("", Font.ITALIC, 12));
		footer.add(help);
		sp = new JScrollPane(tab);
		body.add(sp, BorderLayout.CENTER);
		this.add(title, BorderLayout.NORTH);
		this.add(body, BorderLayout.CENTER);
		this.add(footer, BorderLayout.SOUTH);
	}

	private void getTableFolder(DefaultMutableTreeNode def) {
		// TODO Auto-generated method stub

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:9906/guiproject?useSSL=false", "root",
					"root");

			String sql = "select * from tb_info group by foldername;";
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			String folderall = "";

			int count = 0;
			while (rs.next()) {
				folderall += rs.getString(1) + "@";
				count++;
			}

			String s = "";

			for (int i = 0; i < count; i++) {
				s = folderall.substring(0, folderall.indexOf("@"));
				folderall = folderall.substring(folderall.indexOf("@") + 1, folderall.length());
				def = new DefaultMutableTreeNode(s);

				String tb = "";
				String sql1 = "select tbname from tb_info where foldername='" + s + "';";
				PreparedStatement ps1 = c.prepareStatement(sql1);
				ResultSet rs1 = ps1.executeQuery();
				int cc = 0;
				while (rs1.next()) {
					tb += rs1.getString(1) + "#";
					cc++;
				}
				String tbname = "";
				for (int j = 0; j < cc; j++) {
					tbname = tb.substring(0, tb.indexOf("#"));
					tb = tb.substring(tb.indexOf("#") + 1, tb.length());
					def.add(new DefaultMutableTreeNode(tbname));
				}
				this.treemodel.add(def);
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

}
