package com.ProJ;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ShowPanel extends JPanel implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4267407185955620506L;

	JPanel ptop, ptops, pleft, pbottom, pright, pmain, pmainc, pmaind, pmains, title, main, mde, titleAddp, in, no,
			tbedp;

	JTextField search;
	static JButton refresh, search_btn, create, add, open, newp, recent_btn, ex, add_tbdata, delete_tbdata, hideaddbtn,
			hidedeletebtn, hideeditbtn;

	JScrollPane sp, noti_1, noti_2, noti_3, noti_4;

	JLabel tt, td, name_title, roll, Class_s, grade, tabletitle, tb_de, noopen, noop, npop, npc, nclick, nn;

	JTextArea name_t;

	Choice selectsearch;

	JSplitPane mainsp;

	static JTabbedPane maintab;

	ProjMainMenuPanel menu;

	static GenerateTable tb_ary[];
	static ArrayList<GenerateTable> tb_arys = new ArrayList<GenerateTable>();

	static GenerateTable table;
	Object[][] data;
	String[] columsname;

	static boolean addOpenTable = false;

	int sleep = 0;

	JProgressBar jb;

	// array lists
	JPanel[] mainpanelforeach;

	DisplayScreen dis;

	static ArrayList<DisplayScreen> dis_ary = new ArrayList<DisplayScreen>();

	public ShowPanel() {
		this.setBackground(new Color(0, 153, 153));
		this.setLayout(new BorderLayout());
		Cursor hand = new Cursor(Cursor.HAND_CURSOR);

		ptop = new JPanel();
		ptop.setBackground(new Color(54, 33, 89));
		ptop.setLayout(new BoxLayout(ptop, BoxLayout.X_AXIS));
		refresh = new JButton("Restore");
		refresh.setBackground(new Color(54, 33, 89));
		refresh.setForeground(new Color(225, 225, 225));
		refresh.setFont(new Font("", Font.ITALIC, 15));
		refresh.setCursor(hand);
		refresh.addActionListener(this);
		refresh.addMouseListener(this);

		search = new JTextField(30);
		search.setFont(new Font("", Font.PLAIN, 20));

		selectsearch = new Choice();
		selectsearch.setFont(new Font("", Font.ITALIC, 15));
		selectBy();

		search_btn = new JButton("Search");
		search_btn.setFont(new Font("", Font.ITALIC, 15));
		search_btn.setBackground(new Color(54, 33, 89));
		search_btn.setForeground(new Color(225, 225, 225));
		search_btn.addActionListener(this);
		search_btn.addMouseListener(this);
		search_btn.setCursor(hand);

		ptops = new JPanel();
		ptops.setBackground(new Color(54, 33, 89));
		ptops.add(refresh);
		ptops.add(search);
		ptops.add(selectsearch);
		ptops.add(search_btn);
		pmain = new JPanel();
		pmain.setBackground(getBackground());
		pmain.setLayout(new BorderLayout());
		tt = new JLabel(" OR ");
		tt.setFont(new Font("", Font.ITALIC, 15));
		tt.setHorizontalAlignment(SwingConstants.LEADING);

		create = new JButton("New");
		create.setFont(new Font("", Font.PLAIN, 15));
		create.setBackground(new Color(54, 33, 89));
		create.setForeground(new Color(225, 225, 225));
		create.setCursor(hand);
		create.addActionListener(this);
		create.addMouseListener(this);

		newp = new JButton("Edit");
		newp.setFont(new Font("", Font.PLAIN, 15));
		newp.setBackground(new Color(54, 33, 89));
		newp.setForeground(new Color(225, 225, 225));
		newp.setCursor(hand);
		newp.addMouseListener(this);
		newp.addActionListener(this);

		add = new JButton("Add");
		add.setFont(new Font("", Font.PLAIN, 15));
		add.addActionListener(this);
		add.setBackground(new Color(54, 33, 89));
		add.setForeground(new Color(225, 225, 225));
		add.setCursor(hand);
		add.addMouseListener(this);
		add.addMouseListener(this);

		open = new JButton("Open");
		open.setFont(new Font("", Font.PLAIN, 15));
		open.setBackground(new Color(54, 33, 89));
		open.setForeground(new Color(225, 225, 225));
		open.setCursor(hand);
		open.addMouseListener(this);
		open.addActionListener(this);

		/**
		 * ptop.add(create); ptop.add(open); ptop.add(add); ptop.add(newp);
		 * ptop.add(ptops);
		 */

		pmains = new JPanel();
		pmains.setBackground(getBackground());
		pmains.setLayout(new BorderLayout());

		menu = new ProjMainMenuPanel();

		maintab = new JTabbedPane();
		maintab.setForeground(Color.blue);
		maintab.addMouseListener(this);

		pmains.add(maintab, BorderLayout.CENTER);

		no = new JPanel();
		no.setBackground(null);

		GuessOpen();

		pmaind = new JPanel();
		pmaind.setBackground(getBackground());
		td = new JLabel("Your Recent Project");
		td.setFont(new Font("", Font.BOLD, 20));

		recent_btn = new JButton("Project1");
		recent_btn.setFont(new Font("", Font.BOLD, 30));
		recent_btn.setBackground(getBackground());
		recent_btn.setCursor(hand);

		// pmaind.add(td);
		// pmaind.add(recent_btn);

		pmainc = new JPanel();
		pmainc.setBackground(getBackground());

		tabletitle = new JLabel("Table Lists");
		tabletitle.setFont(new Font("", Font.BOLD, 15));

		// pmainc.add(tabletitle);

		pmain.add(pmainc, BorderLayout.NORTH);
		pmain.add(pmains, BorderLayout.CENTER);
		pmain.add(pmaind, BorderLayout.SOUTH);
		pleft = new JPanel();
		pleft.setBackground(getBackground());

		pbottom = new JPanel();
		pbottom.setBackground(getBackground());

		pright = new JPanel();
		pright.setBackground(getBackground());

		// this.add(ptop, BorderLayout.NORTH);
		this.add(pleft, BorderLayout.WEST);
		this.add(pbottom, BorderLayout.SOUTH);
		this.add(pright, BorderLayout.EAST);
		this.add(pmain, BorderLayout.CENTER);

		hideaddbtn = new JButton("Add");
		hideaddbtn.addActionListener(this);

		hideeditbtn = new JButton("Edit");
		hideeditbtn.addActionListener(this);

		hidedeletebtn = new JButton("Delete");
		hidedeletebtn.addActionListener(this);
	}

	private void selectBy() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:9906/guiproject?useSSL=false", "root",
					"root");
			String sql = "select foldername from tb_info group by foldername;";
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				this.selectsearch.addItem(rs.getString(1));
			}
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void GuessOpen() {

		if (ShowPanel.maintab.getTabCount() < 1) {

			noopen = new JLabel("You have not open any Table. ");
			noopen.setFont(new Font("Arial Rounded MT", Font.BOLD, 20));
			noopen.setForeground(Color.white);
			noopen.setVerticalAlignment(SwingConstants.BOTTOM);
			nclick = new JLabel("Click");
			nclick.setForeground(Color.white);
			nclick.setFont(new Font("Arial Rounded MT", Font.PLAIN, 15));
			nclick.setVerticalAlignment(SwingConstants.BOTTOM);
			noop = new JLabel("Here");
			noop.setVerticalAlignment(SwingConstants.BOTTOM);

			noop.setFont(new Font("Arial Rounded MT", Font.ITALIC, 15));
			noop.setCursor(new Cursor(Cursor.HAND_CURSOR));
			noop.addMouseListener(this);
			noop.setForeground(new Color(0, 0, 51));

			nn = new JLabel("to Create project or click");
			nn.setFont(new Font("Arial Rounded MT", Font.PLAIN, 15));
			nn.setVerticalAlignment(SwingConstants.BOTTOM);
			nn.setForeground(Color.white);
			npop = new JLabel("Here");
			npop.setFont(new Font("Arial Rounded MT", Font.ITALIC, 15));
			npop.setCursor(new Cursor(Cursor.HAND_CURSOR));
			npop.addMouseListener(this);
			npop.setForeground(new Color(0, 0, 51));

			npc = new JLabel("to open existing projects.");
			npc.setFont(new Font("Arial Rounded MT", Font.PLAIN, 15));
			npc.setVerticalAlignment(SwingConstants.BOTTOM);
			npc.setForeground(Color.white);
			this.no.add(noopen);
			this.no.add(nclick);
			this.no.add(noop);
			this.no.add(nn);
			this.no.add(npop);
			this.no.add(npc);
			// this.pmains.remove(this.maintab);
			ShowPanel.maintab.setVisible(false);
			this.pmains.add(this.no, BorderLayout.NORTH);
		} else {
			this.no.removeAll();
			// this.pmains.add(this.maintab);
			ShowPanel.maintab.setVisible(true);

		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == open) {
			String type = "open";
			OpenTableMenu opn = new OpenTableMenu();
			Object[] openobj = { opn };
			if (JOptionPane.showConfirmDialog(null, openobj, "Open table", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
				Object s = opn.tab.getSelectionModel();
				String b = s.toString();
				if (b.length() < 0) {
					JOptionPane.showMessageDialog(null, "Please select a valid file.", "System Notification",
							JOptionPane.ERROR_MESSAGE);
				} else {
					String a = b.substring(b.lastIndexOf("[") + 1, b.indexOf("]"));
					String tbname = a.substring(a.lastIndexOf(",") + 2, a.length());
					int g = 0;
					for (int i = 0; i < a.length(); i++) {
						if (a.charAt(i) == ',') {
							g++;
						}
					}
					if (g < 2) {
						JOptionPane.showMessageDialog(null, "Please select a valid file.", "System Notification",
								JOptionPane.ERROR_MESSAGE);
					} else {
						passion(tbname, type);
						GuessOpen();
					}
					//
				}
			}
		}

		if (e.getSource() == search_btn) {

		}

		if (e.getSource() == refresh) {

		}

		// open
		if (e.getSource() == add) {
			String type = "add";
			OpenTableMenu opn = new OpenTableMenu();
			Object[] openobj = { opn };
			if (JOptionPane.showConfirmDialog(null, openobj, "Add Data", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
				Object s = opn.tab.getSelectionModel();
				String b = s.toString();
				String a = b.substring(b.lastIndexOf("[") + 1, b.indexOf("]"));
				String tbname = a.substring(a.lastIndexOf(",") + 2, a.length());

				passion(tbname, type);

				GuessOpen();

			}

		}

		// edit
		if (e.getSource() == newp) {
			int i = ShowPanel.table.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "No item selected !");
			} else {
				JCheckBox edit = new JCheckBox("Update");
				JCheckBox delete = new JCheckBox("Delete");
				ButtonGroup bg = new ButtonGroup();
				bg.add(edit);
				bg.add(delete);

				Object[] obj = { edit, delete };
				JOptionPane.showMessageDialog(null, obj);

				if (delete.isSelected()) {

				}

				if (edit.isSelected()) {

				}
			}
		}

		// addhide
		if (e.getSource() == hideaddbtn) {
			int selectedTab = ShowPanel.maintab.getSelectedIndex();
			String tbname = ShowPanel.maintab.getTitleAt(selectedTab);
			AddDataToCoustomTable adt = new AddDataToCoustomTable(tbname);
			Object[] obj = { adt };
			if (JOptionPane.showConfirmDialog(null, obj, "Add Data", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection c = DriverManager.getConnection("jdbc:mysql://localhost:9906/guiproject?useSSL=false",
							"root", "root");
					String tb = tbname.replaceAll(" ", "_");
					String value = "";
					for (int i = 0; i < adt.tx.length; i++) {
						value += "?,";
					}
					SimpleDateFormat spdf1 = new SimpleDateFormat("dd/MM/yyyy");
					SimpleDateFormat spdf2 = new SimpleDateFormat("hh:mm:ss a");
					GregorianCalendar g = new GregorianCalendar();
					Date date = new Date();
					String date1 = spdf1.format(date);
					String time = spdf2.format(date);

					value = value.substring(0, value.lastIndexOf(","));
					String sql = "insert into " + tb + " values(" + value + ",?,?,?);";
					PreparedStatement ps = c.prepareStatement(sql);

					for (int i = 0; i < adt.tx.length + 3; i++) {
						if (i < adt.tx.length) {
							ps.setString(i + 1, adt.tx[i].getText());
						} else {
							if (i == adt.tx.length) {
								ps.setString(i + 1, date1);
							}
							if (i == adt.tx.length + 1) {
								ps.setString(i + 1, time);
							}
							if (i == adt.tx.length + 2) {
								ps.setLong(i + 1, g.getTimeInMillis());
							}
						}
					}

					ps.executeUpdate();

					// maintab.remove(this.maintab.getSelectedIndex());
					// updateAddTable(tbname);

					table = new GenerateTable(tbname);
					int selectTab = ShowPanel.maintab.getSelectedIndex();
					ShowPanel.table.repaint();
					dis_ary.get(selectTab).updateFrame(selectTab);

					c.close();
				} catch (ClassNotFoundException e1) {

					e1.printStackTrace();
				} catch (SQLException e1) {

					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Something Went Wrong !");
				}
			}
		}

		// create
		if (e.getSource() == create) {
			CreateCustomProject cc = new CreateCustomProject();
			Object[] obj = { cc };

			if (JOptionPane.showConfirmDialog(null, obj, "Create Custom Table", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
				if (CreateCustomProject.tb_n.getText().length() < 0) {
					JOptionPane.showMessageDialog(null, "Please Fill Table Name !");
				} else {
					String s = "";
					String pass = "";
					if (CreateCustomProject.password_status) {
						JPasswordField tx = new JPasswordField(10);
						JRadioButton show = new JRadioButton("Show");
						show.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {

								if (show.isSelected()) {
									tx.setEchoChar((char) 0);
								} else {
									tx.setEchoChar('x');
								}
							}
						});
						Object[] o = { "Password :", tx, show };
						JOptionPane.showMessageDialog(null, o, "Set Password", JOptionPane.PLAIN_MESSAGE);
						pass = tx.getText();

						CreateCustomProject.password_status = false;
					}

					if (CreateCustomProject.renameable) {

						for (int i = 0; i < cc.colums_name.length; i++) {
							s += cc.colums_name[i] + "@#";
						}
					} else {
						for (int i = 0; i < cc.colums_name.length; i++) {
							s += "Column_" + (i + 1) + "@#";
						}
					}

					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection c = DriverManager
								.getConnection("jdbc:mysql://localhost:9906/guiproject?useSSL=false", "root", "root");
						String sql = "insert into tb_info values(?,?,?,?,?,?,?)";

						SimpleDateFormat spdf1 = new SimpleDateFormat("dd/mm/yyyy");
						SimpleDateFormat spdf2 = new SimpleDateFormat("hh:mm:ss a");

						Date date = new Date();
						String date1 = spdf1.format(date);
						String time = spdf2.format(date);

						GregorianCalendar g = new GregorianCalendar();

						PreparedStatement ps = c.prepareStatement(sql);
						ps.setString(1, CreateCustomProject.tb_folder.getText());
						ps.setString(2, CreateCustomProject.tb_n.getText());
						ps.setInt(3, (int) cc.colums.getValue());
						ps.setString(4, s);
						ps.setString(5, date1 + "-" + time);
						if (pass == "") {
							pass = "";
						}
						ps.setLong(6, g.getTimeInMillis());
						ps.setString(7, pass);

						ps.executeUpdate();
						s = s.substring(0, s.lastIndexOf("#"));
						s = s.replaceAll(" ", "_");
						s = s.replaceAll("@", " varchar(100)");
						s = s.replaceAll("#", ",");

						String tbnamec = CreateCustomProject.tb_n.getText().replaceAll(" ", "_");
						String sql2 = "create table " + tbnamec + "(" + s
								+ ",cr_adm_date varchar(100),cr_adm_time varchar(100),cr_adm_df_id varchar(100))";
						PreparedStatement ps2 = c.prepareStatement(sql2);

						ps2.executeUpdate();

						JOptionPane.showMessageDialog(null,
								"Table " + CreateCustomProject.tb_n.getText() + " Create Done!");
						updateTableData(CreateCustomProject.tb_n.getText());
						GuessOpen();
					} catch (ClassNotFoundException e1) {

						e1.printStackTrace();
					} catch (SQLException e1) {

						e1.printStackTrace();
						JLabel notic = new JLabel("Notic : Password length must be less than 10 ( <10 ) !\n");
						notic.setFont(new Font("", Font.BOLD, 12));
						notic.setForeground(new Color(232, 57, 95));
						JLabel adnotic = new JLabel(
								"And please notic, some of table's colums name cannot be same with the database variables !! ");
						adnotic.setFont(new Font("", Font.BOLD, 12));
						adnotic.setForeground(new Color(232, 57, 95));
						JLabel err = new JLabel("ERROR : " + e1.getErrorCode());
						err.setFont(new Font("", Font.BOLD, 12));
						err.setForeground(new Color(232, 57, 95));
						JLabel page = new JLabel(
								"Feedback us on our original website http://com.J9TableProject.com.mm");
						page.setFont(new Font("", Font.ITALIC, 11));
						Object[] ob = { "Table " + CreateCustomProject.tb_n.getText()
								+ " Create Fail!\nMay be Tabel name is Already Used Or Table Colum name is not allowed to Create !\n",
								"Check table name or table colums name and try again !\n\n", notic, adnotic, err,
								page };
						JOptionPane.showMessageDialog(null, ob, "Create Table Failed !", JOptionPane.ERROR_MESSAGE);

						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection con = DriverManager.getConnection(
									"jdbc:mysql://localhost:9906/guiproject?useSSL=false", "root", "root");
							String delete = "delete from tb_info where tbname='" + CreateCustomProject.tb_n.getText()
									+ "';";
							PreparedStatement pdelete = con.prepareStatement(delete);
							pdelete.executeUpdate();
							con.close();
						} catch (ClassNotFoundException e2) {

							e2.printStackTrace();
						} catch (SQLException e2) {

							e2.printStackTrace();
						}

					}

				}

			}
		}

	}

	private boolean GuessView(String tbname) {

		boolean gv = false;
		if (ShowPanel.maintab.getTabCount() > 0) {
			for (int i = 0; i < ShowPanel.maintab.getTabCount(); i++) {
				if (ShowPanel.maintab.getTitleAt(i).equalsIgnoreCase(tbname)) {
					gv = true;
				}
			}

		}
		return gv;

	}

	private int getTabOfString(String tbname) {

		int get = 0;
		if (ShowPanel.maintab.getTabCount() > 0) {
			for (int i = 0; i < ShowPanel.maintab.getTabCount(); i++) {
				if (ShowPanel.maintab.getTitleAt(i).equalsIgnoreCase(tbname)) {
					get = i;
				}
			}

		}
		return get;

	}

	private void passion(String tbname, String type) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:9906/guiproject?useSSL=false", "root",
					"root");

			String sql = "select password from tb_info where tbname='" + tbname + "';";
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			String password = "";
			if (rs.next()) {
				password = rs.getString(1);
			}

			if (password.equals("")) {
				if (type.equalsIgnoreCase("open")) {
					// updateAddTable(tbname);
					GuessOpen();
					if (GuessView(tbname)) {
						int getI = getTabOfString(tbname);
						ShowPanel.maintab.setSelectedIndex(getI);
					} else {
						updateTableData(tbname);
					}
				}
				if (type.equalsIgnoreCase("add")) {

					// dataAdd(tbname,index);
					GuessOpen();

				}
			} else {

				JPasswordField tx = new JPasswordField(10);
				JRadioButton show = new JRadioButton("Show");
				show.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						if (show.isSelected()) {
							tx.setEchoChar((char) 0);
						} else {
							tx.setEchoChar('x');
						}
					}
				});
				JLabel ll = new JLabel("This table has password");
				ll.setVerticalAlignment(SwingConstants.CENTER);
				Object[] obj = { "Please Enter Password : ", tx, show };
				if (JOptionPane.showConfirmDialog(null, obj, "This table has password !", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
					@SuppressWarnings("deprecation")
					String getpass = tx.getText();
					if (getpass.equalsIgnoreCase(password)) {
						if (type.equalsIgnoreCase("open")) {
							GuessOpen();
							if (GuessView(tbname)) {
								int getI = getTabOfString(tbname);
								ShowPanel.maintab.setSelectedIndex(getI);
							} else {
								updateTableData(tbname);
							}
						}
						if (type.equalsIgnoreCase("add")) {
							GuessOpen();
							// dataAdd(tbname,index);

						}
					} else {
						GuessOpen();
						JOptionPane.showMessageDialog(null, "Incorrect Password !");

					}
				} else {
					GuessOpen();
				}
			}
			GuessOpen();
		} catch (ClassNotFoundException e) {
			//
			e.printStackTrace();
			GuessOpen();
		} catch (SQLException e) {
			//
			e.printStackTrace();
			GuessOpen();
		}
		GuessOpen();
	}

	void updateTableData(String tbname) {
		dis = new DisplayScreen(tbname);

		dis_ary.add(dis);

		ShowPanel.maintab.addTab(tbname, dis);
		ShowPanel.maintab.setSelectedComponent(dis);

		// guessNeedSelectOrNot(tbname);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//
		if (e.getSource() == this.noop) {
			ShowPanel.create.doClick();
		}

		if (e.getSource() == this.npop) {
			ShowPanel.open.doClick();
		}
		if (e.getSource() == maintab) {
			int i = ShowPanel.maintab.getSelectedIndex();
			if (e.getButton() == MouseEvent.BUTTON1) {

			}
			if (e.getButton() == MouseEvent.BUTTON3) {
				if (ShowPanel.maintab.getTabCount() < 1) {
					JOptionPane.showMessageDialog(null, "No Table Open !");
				} else {
					if (JOptionPane.showConfirmDialog(maintab.getSelectedComponent(),
							"Close " + ShowPanel.maintab.getTitleAt(i) + " tables ?", "Setting",
							JOptionPane.PLAIN_MESSAGE, JOptionPane.YES_NO_CANCEL_OPTION) == JOptionPane.YES_OPTION) {
						CloseTab(i);
					}
				}
			}
		}
	}

	private void CloseTab(int i) {
		//
		dis_ary.remove(i);
		tb_arys.remove(i);
		ShowPanel.maintab.remove(i);
		GuessOpen();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//
		if (e.getSource() == add) {
			ShowPanel.add.setBackground(new Color(0, 102, 225));
		}
		if (e.getSource() == newp) {
			ShowPanel.newp.setBackground(new Color(0, 102, 225));
		}
		if (e.getSource() == open) {
			ShowPanel.open.setBackground(new Color(0, 102, 225));
		}
		if (e.getSource() == refresh) {
			ShowPanel.refresh.setBackground(new Color(0, 102, 225));
		}
		if (e.getSource() == search_btn) {
			ShowPanel.search_btn.setBackground(new Color(0, 102, 225));
		}
		if (e.getSource() == create) {
			ShowPanel.create.setBackground(new Color(0, 102, 225));
		}
		if (e.getSource() == noop) {
			this.noop.setForeground(new Color(255, 255, 255));
		}
		if (e.getSource() == npop) {
			this.npop.setForeground(new Color(255, 255, 255));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//
		if (e.getSource() == add) {
			ShowPanel.add.setBackground(new Color(54, 33, 89));
		}
		if (e.getSource() == newp) {
			ShowPanel.newp.setBackground(new Color(54, 33, 89));
		}
		if (e.getSource() == open) {
			ShowPanel.open.setBackground(new Color(54, 33, 89));
		}
		if (e.getSource() == refresh) {
			ShowPanel.refresh.setBackground(new Color(54, 33, 89));
		}
		if (e.getSource() == search_btn) {
			ShowPanel.search_btn.setBackground(new Color(54, 33, 89));
		}
		if (e.getSource() == create) {
			ShowPanel.create.setBackground(new Color(54, 33, 89));
		}
		if (e.getSource() == this.noop) {
			this.noop.setForeground(new Color(0, 0, 51));
		}
		if (e.getSource() == this.npop) {
			this.npop.setForeground(new Color(0, 0, 51));
		}
	}

}
