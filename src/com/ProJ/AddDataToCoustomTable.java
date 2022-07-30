package com.ProJ;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AddDataToCoustomTable extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4293729150759762781L;
	JPanel titlepanel, labpanel, inputpanel, footer, space;
	JLabel title, f;
	JLabel[] labs;
	JTextField[] tx;

	public AddDataToCoustomTable(String tbname) {
		// TODO Auto-generated constructor stub
		this.setBackground(new Color(199, 129, 224));
		this.setLayout(new BorderLayout());

		titlepanel = new JPanel();
		labpanel = new JPanel();
		space = new JPanel();
		inputpanel = new JPanel();
		footer = new JPanel();

		title = new JLabel("Add Data to" + tbname);
		title.setFont(new Font("", Font.BOLD, 14));

		titlepanel.add(title);

		UpdateDataToAdd(tbname);

		f = new JLabel("Feedback us on our original website http://J9TableProject.com.mm");
		f.setFont(new Font("", Font.ITALIC, 11));
		footer.add(f);
		this.add(titlepanel, BorderLayout.NORTH);
		this.add(labpanel, BorderLayout.WEST);
		this.add(space, BorderLayout.CENTER);
		this.add(inputpanel, BorderLayout.EAST);
		this.add(footer, BorderLayout.SOUTH);
	}

	private void UpdateDataToAdd(String tbname) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:9906/guiproject?useSSL=false", "root", "root");

			String getlab = "select tbcolname from tb_info where tbname='" + tbname + "';";
			PreparedStatement ps = c.prepareStatement(getlab);
			ResultSet rs = ps.executeQuery();
			String labname = "";
			if (rs.next()) {
				labname = rs.getString(1);
			}
			int count = 0;
			for (int i = 0; i < labname.length() - 1; i++) {
				if (labname.charAt(i) == '@' && labname.charAt(i + 1) == '#') {
					count++;
				}
			}
			labs = new JLabel[count];
			this.labpanel.setLayout(new GridLayout(count, 1, 1, 5));
			for (int i = 0; i < labs.length; i++) {
				JLabel guesslab = new JLabel();
				guesslab.setText(labname.substring(0, labname.indexOf("@#")));
				guesslab.setHorizontalAlignment(SwingConstants.TRAILING);
				labname = labname.substring(labname.indexOf("@#") + 2, labname.length());
				labs[i] = guesslab;
				this.labpanel.add(labs[i]);
			}
			tx = new JTextField[count];
			this.inputpanel.setLayout(new GridLayout(count, 1, 1, 5));
			for (int i = 0; i < tx.length; i++) {
				JTextField t = new JTextField(20);
				tx[i] = t;
				this.inputpanel.add(tx[i]);
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
