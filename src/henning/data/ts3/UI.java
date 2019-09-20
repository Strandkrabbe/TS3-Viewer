package henning.data.ts3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.swing.JFrame;

public class UI extends JFrame {

	private static final long serialVersionUID = 5725756400454418636L;

	private UserDB db;
	private Connection conn;
	private Properties conf;

	public UI(Properties p) throws NumberFormatException, UnknownHostException, IOException {
		this.conf = p;
		conn = new Connection(this.conf.getProperty("server", "127.0.0.1"),
				Integer.valueOf(this.conf.getProperty("port", "25639")));
		String key = this.conf.getProperty("key");
		conn.doAuth(key);
		db = new UserDB(new File("./user.db"));
		this.conf = p;
		initComponents();
		this.addEvents();
		this.setVisible(true);
	}

	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		value_state = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		value_port = new javax.swing.JLabel();
		value_addr = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		input_tab = new javax.swing.JTextField();
		jLabel9 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();
		jPanel1 = new javax.swing.JPanel();
		jPanel2 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		output_known = new javax.swing.JTextArea();
		jPanel3 = new javax.swing.JPanel();
		jScrollPane2 = new javax.swing.JScrollPane();
		output_online = new javax.swing.JTextArea();
		jLabel3 = new javax.swing.JLabel();
		value_msg = new javax.swing.JLabel();

		setBackground(new java.awt.Color(255, 255, 255));
//		setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255), 2));
		setMinimumSize(new java.awt.Dimension(800, 500));

		jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
		jLabel1.setForeground(new java.awt.Color(250, 0, 0));
		jLabel1.setText("TS3_Viewer");

		jLabel2.setForeground(new java.awt.Color(250, 0, 0));
		jLabel2.setText("Status:");

		value_state.setForeground(new java.awt.Color(0, 0, 250));
		value_state.setText("Disconnected");

		jLabel4.setForeground(new java.awt.Color(250, 0, 0));
		jLabel4.setText("Port:");

		value_port.setForeground(new java.awt.Color(0, 0, 255));
		value_port.setText("6666");

		value_addr.setForeground(new java.awt.Color(0, 0, 255));
		value_addr.setText("127.0.0.1");

		jLabel7.setForeground(new java.awt.Color(250, 0, 0));
		jLabel7.setText("Client-Server:");

		jLabel8.setForeground(new java.awt.Color(250, 0, 0));
		jLabel8.setText("Handler-ID (Tab):");

		input_tab.setForeground(new java.awt.Color(0, 0, 250));
		input_tab.setText("0");
		input_tab.setBorder(javax.swing.BorderFactory.createCompoundBorder(
				javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 250)),
				javax.swing.BorderFactory.createEmptyBorder(2, 3, 2, 2)));

		jLabel9.setForeground(new java.awt.Color(250, 0, 0));
		jLabel9.setText("Known Users");

		jLabel10.setForeground(new java.awt.Color(250, 0, 0));
		jLabel10.setText("Online Users");

		jPanel1.setBackground(new java.awt.Color(245, 245, 255));
		jPanel1.setLayout(new java.awt.GridLayout(1, 2));

		jPanel2.setBackground(new java.awt.Color(240, 240, 255));

		jScrollPane1.setBorder(null);
		jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		output_known.setEditable(false);
		output_known.setColumns(20);
		output_known.setForeground(new java.awt.Color(0, 0, 255));
		output_known.setRows(5);
		output_known.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 250)));
		jScrollPane1.setViewportView(output_known);

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
						.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
						.addContainerGap()));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
						.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
						.addContainerGap()));

		jPanel1.add(jPanel2);

		jPanel3.setBackground(new java.awt.Color(240, 240, 255));

		jScrollPane2.setBorder(null);
		jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		output_online.setEditable(false);
		output_online.setColumns(20);
		output_online.setForeground(new java.awt.Color(0, 0, 255));
		output_online.setRows(5);
		output_online.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 250)));
		jScrollPane2.setViewportView(output_online);

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel3Layout.createSequentialGroup().addContainerGap()
						.addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
						.addContainerGap()));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel3Layout.createSequentialGroup().addContainerGap()
						.addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
						.addContainerGap()));

		jPanel1.add(jPanel3);

		jLabel3.setForeground(new java.awt.Color(250, 0, 0));
		jLabel3.setText("Msg:");

		value_msg.setForeground(new java.awt.Color(180, 0, 180));
		value_msg.setText("< - >");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this.getContentPane());
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(layout.createSequentialGroup().addComponent(jLabel2)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(value_state).addGap(18, 18, 18).addComponent(jLabel3)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(value_msg)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jLabel7)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(value_addr).addGap(18, 18, 18).addComponent(jLabel4)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(value_port))
						.addGroup(layout.createSequentialGroup().addComponent(jLabel1)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jLabel8)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(input_tab, javax.swing.GroupLayout.PREFERRED_SIZE, 167,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addComponent(jLabel9)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jLabel10)))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel1)
						.addComponent(jLabel8).addComponent(input_tab, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel9)
						.addComponent(jLabel10))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel2)
						.addComponent(value_state).addComponent(jLabel4).addComponent(value_port)
						.addComponent(value_addr).addComponent(jLabel7).addComponent(jLabel3).addComponent(value_msg))
				.addContainerGap()));
	}// </editor-fold>

	// Variables declaration - do not modify
	private javax.swing.JTextField input_tab;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTextArea output_known;
	private javax.swing.JTextArea output_online;
	private javax.swing.JLabel value_addr;
	private javax.swing.JLabel value_msg;
	private javax.swing.JLabel value_port;
	private javax.swing.JLabel value_state;

	private class ScanResult {

		public final List<String> visibleUIDs;
		public final List<String> onlineUIDs;

		public ScanResult() throws IOException {
			this.visibleUIDs = new ArrayList<String>();
			this.onlineUIDs = new ArrayList<>();
			List<Map<String, String>> clientquery = UI.this.conn.request("clientlist -uid", null);
			for (Map<String, String> u : clientquery) {
				String uid = u.get("client_unique_identifier");
				this.visibleUIDs.add(uid);
				if (UI.this.conf.get("learn").equals("1") && !UI.this.db.contains(uid)) {
					UI.this.db.add(uid, u.get("client_nickname"), false);
				}
			}
			List<Map<String, String>> all = UI.this.db.getAllUDIs();
			for (Map<String, String> u : all) {
				String uid = u.get("uid");
				String res = UI.this.conn.request("clientgetids cluid=" + uid);
				if (!(res.contains("error id=") && !res.contains("error id=0"))) {
					onlineUIDs.add(uid);
				}
			}
		}
		
		private List<String> knownList = null;
		private List<String> onlineNames = null;
		
		private void compute()	{
			this.knownList = new ArrayList<>();
			this.onlineNames = new ArrayList<>();
			List<Map<String,String>> all = UI.this.db.getAllUDIs();
			for (Map<String,String> u : all)	{
				String uid = u.get("uid");
				if (this.onlineUIDs.contains(uid))	{
					String name = u.get("name");
					if (!this.visibleUIDs.contains(uid))
						name = "*" + name + "*";
					this.onlineNames.add(name);
				}	else	{
					this.knownList.add(u.get("name"));
				}
			}
		}
		
		public List<String> getKnownNames()	{
			if (knownList == null || onlineNames == null)	{
				compute();
			}
			return this.knownList;
		}
		public List<String> getOnlineNames()	{
			if (knownList == null || onlineNames == null)	{
				compute();
			}
			return this.onlineNames;
		}

	}

	private void setMessage(String msg)	{
		if (msg == null)
			msg = "< = >";
		this.value_msg.setText(msg);
	}

	public void update() {
		try {
			ScanResult scan = new ScanResult();
			List<String> known = scan.getKnownNames();
			List<String> online = scan.getOnlineNames();
			StringBuilder knownBuilder = new StringBuilder();
			for (int C = 0;C < known.size();C++)	{
				if (C != 0)
					knownBuilder.append('\n');
				knownBuilder.append(known.get(C));
			}
			StringBuilder onlineBuilder = new StringBuilder();
			for (int C = 0;C < online.size();C++)	{
				if (C != 0)
					onlineBuilder.append('\n');
				onlineBuilder.append(online.get(C));
			}
			this.output_known.setText(knownBuilder.toString());
			this.output_online.setText(onlineBuilder.toString());
			this.value_addr.setText(this.conn.ip);
			this.value_port.setText(this.conn.port + "");
			this.value_state.setText("Connected");
		} catch (Exception ex) {
			this.setMessage("Failed to scan!!");
			ex.printStackTrace();
			this.value_state.setText("Error");
		}
	}
	
	public static final long SLEEP_TIME = 45000;
	private boolean isActive = true;
	
	public void run()	{
		this.isActive = true;
		while (isActive)	{
			this.update();
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e) {}
		}
	}

	private void addEvents()	{
		this.input_tab.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try	{
					int hid = Integer.valueOf(input_tab.getText());
					UI.this.conn.switchHandler(hid);
				}	catch (NumberFormatException ex)	{
					UI.this.setMessage("Invalid number");
				} catch (IOException e1) {
					UI.this.setMessage("Failed to switch");
					e1.printStackTrace();
				}
			}
		});
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				UI.this.setVisible(false);
				UI.this.dispose();
				try {
					UI.this.conn.close();
				} catch (IOException e1) {
				}
				System.exit(0);
			}
		});
	}
	
}
