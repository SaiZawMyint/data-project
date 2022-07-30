package com.ProJ;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

public class Screen extends JPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1454273097788449265L;

	JPanel topPanel, mainPanel, no;

	// imp
	JTabbedPane maintab;
	JScrollPane mainsc;

	JLabel tt, td, name_title, roll, Class_s, grade, tabletitle, tb_de, noopen, noop, npop, npc, nclick, nn;

	public Screen() {
		// TODO Auto-generated constructor stub

		this.setBackground(new Color(0, 153, 153));
		this.setLayout(new BorderLayout());
		topPanel = new JPanel();

		mainPanel = new JPanel();

		no = new JPanel();

		maintab = new JTabbedPane();

		GuessOpen();

		this.add(topPanel, BorderLayout.NORTH);
		this.add(mainPanel, BorderLayout.CENTER);
	}

	private void GuessOpen() {
		// TODO Auto-generated method stub
		if (this.maintab.getTabCount() < 1) {
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
			noop.setForeground(Color.blue);

			nn = new JLabel("to Create project or click");
			nn.setFont(new Font("Arial Rounded MT", Font.PLAIN, 15));
			nn.setVerticalAlignment(SwingConstants.BOTTOM);
			nn.setForeground(Color.white);
			npop = new JLabel("Here");
			npop.setFont(new Font("Arial Rounded MT", Font.ITALIC, 15));
			npop.setCursor(new Cursor(Cursor.HAND_CURSOR));
			npop.addMouseListener(this);
			npop.setForeground(Color.blue);

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
			this.maintab.setVisible(false);
			this.topPanel.add(this.no, BorderLayout.NORTH);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
