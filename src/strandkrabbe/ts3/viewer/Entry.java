package strandkrabbe.ts3.viewer;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JPanel;

public class Entry extends JPanel {

	private static final long serialVersionUID = -4144854534781428109L;

	private User user;
	private Connection conn;
	private UI base;

	public Entry(UI base, User u, Connection conn) {
		this.user = u;
		this.base = base;
		this.conn = conn;
		this.initComponents();
		this.addEvents();
		this.setData();
	}

	private void initComponents() {

		value_name = new javax.swing.JLabel();
		jPanel1 = new javax.swing.JPanel();
		jLabel3 = new javax.swing.JLabel();

		setBackground(new java.awt.Color(250, 250, 250));
		setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 1, 3, 3));
		setMaximumSize(new java.awt.Dimension(3200, 28));
		setMinimumSize(new java.awt.Dimension(350, 28));
		setPreferredSize(new java.awt.Dimension(350, 28));

		value_name.setForeground(new java.awt.Color(0, 0, 250));
		value_name.setText("<client_nickname>");
		value_name.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 5));

		jPanel1.setBackground(new java.awt.Color(0, 200, 0));

		jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
		jLabel3.setForeground(new java.awt.Color(255, 255, 255));
		jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel3.setText("Open Chat");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(value_name, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel1,
								javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(value_name, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
				.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE));
	}// </editor-fold>

	// Variables declaration - do not modify
	private javax.swing.JLabel jLabel3;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JLabel value_name;
	// End of variables declaration
	
	private void setData()	{
		this.value_name.setText(this.user.getDisplayName());
	}

	private void addEvents() {
		if (this.user.getClid() == null) {
			this.jPanel1.setBackground(new Color(250, 0, 0));
			this.jPanel1.setVisible(false);
		} else {
			this.jPanel1.setBackground(new Color(0, 200, 0));
		}
		this.jPanel1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String clid = user.getClid();
				if (clid != null) {
					try {
						Entry.this.conn.request("sendtextmessage targetmode=1 target=" + clid + " msg=__");
						Entry.this.base.setMessage(
								"Chat open send for " + StringAnalystic.replaceSpacer(Entry.this.user.getName()));
					} catch (IOException e1) {
						Entry.this.base.setMessage("Failed to open chat " + e1.getMessage());
					}
				}
			}
		});
	}

}
