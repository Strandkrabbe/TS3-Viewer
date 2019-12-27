package strandkrabbe.ts3.viewer.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class StringEntry extends JPanel {

	private static final long serialVersionUID = 2839530576275464021L;
	public static final Color FG = new Color(20, 20, 250);
	public static final Color BG = new Color(235, 235, 240);

	private String value;
	private EntryButtonAction[] buttons = new EntryButtonAction[3];

	public StringEntry(String value) {
		this.value = value;
		this.initComponents();
		this.setValue(this.value);
	}

	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel() {
			private static final long serialVersionUID = 7897264286905768541L;

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				draw(g, this, 2);
			}
		};
		jPanel2 = new javax.swing.JPanel() {
			private static final long serialVersionUID = 1291993035361312614L;

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				draw(g, this, 1);
			}
		};
		jLabel2 = new javax.swing.JLabel();
		jPanel3 = new javax.swing.JPanel() {
			private static final long serialVersionUID = -5966740940763930527L;

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				draw(g, this, 0);
			}
		};

		setBackground(new java.awt.Color(250, 250, 250));
		setMinimumSize(new java.awt.Dimension(350, 28));
		setPreferredSize(new java.awt.Dimension(350, 28));

		jPanel1.setBackground(new java.awt.Color(250, 20, 20));
		jPanel1.setForeground(new java.awt.Color(250, 20, 20));

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 28, Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 0, Short.MAX_VALUE));

		jPanel2.setBackground(new java.awt.Color(250, 250, 20));

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 28, Short.MAX_VALUE));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 0, Short.MAX_VALUE));

		jLabel2.setForeground(new java.awt.Color(20, 20, 250));
		jLabel2.setText("<value>");

		jPanel3.setBackground(new java.awt.Color(20, 250, 20));

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 28, Short.MAX_VALUE));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 0, Short.MAX_VALUE));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap()
						.addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE).addComponent(jPanel3,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
	}// </editor-fold>

	// Variables declaration - do not modify
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	// End of variables declaration

	public void setValue(String value) {
		this.jLabel2.setText(value);
		Dimension d = this.getLayout().minimumLayoutSize(this);
		this.setMinimumSize(d);
		this.setPreferredSize(d);
	}

	public void setGreenButton(EntryButtonAction ba) {
		this.buttons[0] = ba;
		updateButtons();
	}

	public void setYellowButton(EntryButtonAction ba) {
		this.buttons[1] = ba;
		updateButtons();
	}

	public void setRedButton(EntryButtonAction ba) {
		this.buttons[2] = ba;
		updateButtons();
	}

	private void updateButtons() {
		this.jPanel1.repaint();
		this.jPanel2.repaint();
		this.jPanel3.repaint();
		this.removeListeners(jPanel1);
		this.removeListeners(jPanel2);
		this.removeListeners(jPanel3);
		jPanel3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (buttons[0] != null)
					buttons[0].onClick(value);
			}
		});
		jPanel2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (buttons[1] != null)
					buttons[1].onClick(value);
			}
		});
		jPanel1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (buttons[2] != null)
					buttons[2].onClick(value);
			}
		});
	}
	private void removeListeners(JPanel p)	{
		MouseListener[] ll = p.getMouseListeners();
		for (MouseListener ml : ll)	{
			p.removeMouseListener(ml);
		}
	}

	public void draw(Graphics g, JPanel p, int index) {
		if (this.buttons[index] != null) {
			BufferedImage icon = this.buttons[index].getIcon();
			if (icon != null) {
				g.drawImage(icon, 3, 3, p.getWidth() - 6, p.getHeight() - 6, p);
				return;
			}
		}
		g.setColor(this.getBackground());
		g.fillRect(0, 0, p.getWidth(), p.getHeight());
	}

}
