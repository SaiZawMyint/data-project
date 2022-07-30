package com.ProJ;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

public class CreateCustomProject extends JPanel implements ActionListener, ChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2179945040580236236L;
	JPanel title_panel, lable_panel, input_panel, footer_panel, space, hh, rename_col, rename_title, rename_lab,
			rename_input, rename_footer, rs, rowp, folder, check;
	JLabel title, tb_name, colum, tbview, rtitle, rows, rowheight, tbfolder;
	static JTextField tb_n, tb_folder;
	JSpinner colums, row, rowh;
	JTable overview;
	Object[][] data;
	String[] colums_name;
	JCheckBox renameablecol, password;
	JScrollPane t, rename;
	JTextArea a;
	JTabbedPane tb_col;
	JButton help, updatebtn, getfolder;
	JLabel[] lb;
	JTextField[] tx;
	JComboBox<String> tbfo;
	static boolean renameable = true, password_status;

	@SuppressWarnings({ })
	public CreateCustomProject() {
		// TODO Auto-generated constructor stub

		Cursor hand = new Cursor(Cursor.HAND_CURSOR);
		this.setBackground(new Color(200, 199, 199));
		this.setLayout(new BorderLayout());

		title_panel = new JPanel();
		lable_panel = new JPanel();
		lable_panel.setLayout(new GridLayout(5, 1, 2, 5));
		space = new JPanel();
		input_panel = new JPanel();
		input_panel.setLayout(new GridLayout(5, 1, 2, 5));
		footer_panel = new JPanel();
		footer_panel.setLayout(new BorderLayout());
		title = new JLabel("Create Table");
		title.setFont(new Font("", Font.BOLD, 15));

		tb_name = new JLabel("Table name");
		tb_name.setFont(new Font("", Font.PLAIN, 12));

		tbfolder = new JLabel("Table Folder");
		tbfolder.setFont(new Font("", Font.PLAIN, 12));

		tb_folder = new JTextField();
		tb_folder.setBounds(0, 0, 350, 23);
		tb_folder.setText("");
		tb_folder.setSelectedTextColor(Color.blue);

		colum = new JLabel("Columns");
		colum.setFont(new Font("", Font.PLAIN, 12));

		colums = new JSpinner();
		colums.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		colums.addChangeListener(this);
		overview = new JTable();
		overview.setOpaque(false);
		overview.getTableHeader().setFont(new Font("", Font.BOLD, 12));
		overview.getTableHeader().setBackground(new Color(200, 200, 200));

		tb_n = new JTextField(40);

		renameablecol = new JCheckBox("Renameable colums' name");
		renameablecol.setSelected(true);
		renameablecol.addActionListener(this);
		password = new JCheckBox("Set table password");
		password.addActionListener(this);

		tbfo = new JComboBox<String>();
		tbfo.addActionListener(this);
		getFolderName();
		if (tbfo.getItemCount() == 0) {
			tbfo.addItem("No Folder yet !");
		}
		folder = new JPanel();
		folder.setLayout(new GridLayout(1, 2, 5, 0));
		folder.add(tb_folder);
		folder.add(tbfo);

		check = new JPanel();
		check.setLayout(new GridLayout(1, 2, 5, 0));
		check.add(renameablecol);
		check.add(password);

		title_panel.add(title);
		lable_panel.add(tbfolder);
		lable_panel.add(tb_name);
		lable_panel.add(colum);
		input_panel.add(folder);
		input_panel.add(tb_n);
		input_panel.add(colums);
		input_panel.add(check);
		tbview = new JLabel("Table preview : ");

		t = new JScrollPane(overview);

		tb_col = new JTabbedPane();
		tb_col.addTab("Add Colums", t);

		rename_col = new JPanel();
		rename_col.setLayout(new BorderLayout());

		updatebtn = new JButton("Rename");
		updatebtn.setBackground(getBackground());
		updatebtn.setCursor(hand);
		updatebtn.addActionListener(this);

		rename_title = new JPanel();
		rename_lab = new JPanel();
		rename_lab.setLayout(new GridLayout(10, 0, 1, 5));
		rename_input = new JPanel();
		rename_input.setLayout(new GridLayout(10, 0, 1, 5));
		rename_footer = new JPanel();

		rename_footer.add(updatebtn);

		rtitle = new JLabel("Rename Coloums");
		rtitle.setFont(new Font("", Font.BOLD, 15));

		rename_title.add(rtitle);
		rs = new JPanel();
		rename_col.add(rename_title, BorderLayout.NORTH);
		rename_col.add(rename_lab, BorderLayout.CENTER);
		rename_col.add(rename_input, BorderLayout.EAST);
		rename_col.add(rename_footer, BorderLayout.SOUTH);

		tb_col.addTab("Rename Colums", rename_col);

		help = new JButton("?");
		help.setBackground(getBackground());
		help.setCursor(hand);
		hh = new JPanel();
		hh.setLayout(new BorderLayout());
		hh.add(help, BorderLayout.EAST);

		footer_panel.add(tbview, BorderLayout.NORTH);
		footer_panel.add(tb_col, BorderLayout.CENTER);
		footer_panel.add(hh, BorderLayout.SOUTH);

		this.add(title_panel, BorderLayout.NORTH);
		this.add(lable_panel, BorderLayout.WEST);
		this.add(space, BorderLayout.CENTER);
		this.add(input_panel, BorderLayout.EAST);
		this.add(footer_panel, BorderLayout.SOUTH);
	}

	private void updateTableView(int colums) {
		// TODO Auto-generated method stub
		String setcol = "";

		data = new Object[1][colums];
		colums_name = new String[colums];
		for (int i = 0; i < colums; i++) {
			setcol = "Column_" + (i + 1);
			colums_name[i] = setcol;
		}
		this.overview.setModel(new DefaultTableModel(data, colums_name));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == renameablecol) {
			if (renameablecol.isSelected()) {
				renameable = true;
			} else {
				renameable = false;
				int col = (int) this.colums.getValue();
				String setcol = "";

				data = new Object[1][col];
				colums_name = new String[col];
				for (int i = 0; i < col; i++) {
					setcol = "Column_" + (i + 1);
					colums_name[i] = setcol;
				}
				this.overview.setModel(new DefaultTableModel(data, colums_name));

			}
			updateRenameable();
		}
		if (e.getSource() == updatebtn) {
			String s = "";
			data = new Object[1][tx.length];
			colums_name = new String[tx.length];
			if (colums_name.length != 0) {

				for (int i = 0; i < tx.length; i++) {
					s = tx[i].getText();
					colums_name[i] = s;
				}
				this.overview.setModel(new DefaultTableModel(data, colums_name));
				updateRenameable();
			} else {
				JOptionPane.showMessageDialog(null, "No Colums Renamable !");
			}
		}
		if (e.getSource() == tbfo) {
			CreateCustomProject.tb_folder.setText("" + tbfo.getSelectedItem());
		}
		if (e.getSource() == password) {
			if (password.isSelected()) {
				password_status = true;
			} else {
				password_status = false;
			}
		}
	}

	private void getFolderName() {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:9906/guiproject?useSSL=false", "root", "root");
			String sql = "select foldername from tb_info group by foldername;";
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				this.tbfo.addItem(rs.getString(1));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updateRenameable() {
		// TODO Auto-generated method stub
		if (renameable) {
			this.tb_col.addTab("Rename Colums", rename_col);
		} else {
			this.tb_col.remove(rename_col);
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == colums) {
			int col = (int) this.colums.getValue();
			this.updateTableView(col);
			updateRenameable();
			if (renameable) {
				this.rename_lab.removeAll();
				this.rename_input.removeAll();
				this.updateRenameColums(col);
			}

		}
	}

	private void updateRenameColums(int col) {
		// TODO Auto-generated method stub
		lb = new JLabel[col];
		tx = new JTextField[col];
		for (int i = 0; i < col; i++) {
			JLabel s = new JLabel();
			s.setText("Column_" + (i + 1));
			s.setHorizontalAlignment(SwingConstants.LEADING);
			lb[i] = s;
			JTextField tf = new JTextField(30);
			tx[i] = tf;
			this.rename_input.add(tx[i]);
			this.rename_lab.add(lb[i]);
		}

	}

}
