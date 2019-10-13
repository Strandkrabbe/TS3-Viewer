package strandkrabbe.ts3.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
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
		jScrollPane3 = new javax.swing.JScrollPane();
		panel_known = new javax.swing.JPanel();
		jPanel3 = new javax.swing.JPanel();
		jScrollPane4 = new javax.swing.JScrollPane();
		panel_online = new javax.swing.JPanel();
		jLabel3 = new javax.swing.JLabel();
		value_msg = new javax.swing.JLabel();

		setBackground(new java.awt.Color(255, 255, 255));
//		setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255), 2));
		setMinimumSize(new java.awt.Dimension(900, 500));
		setPreferredSize(new java.awt.Dimension(900, 500));

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

		jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
		jScrollPane3.setForeground(new java.awt.Color(20, 20, 255));
		jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		panel_known.setBackground(new java.awt.Color(255, 255, 255));
		panel_known.setLayout(new javax.swing.BoxLayout(panel_known, javax.swing.BoxLayout.Y_AXIS));
		jScrollPane3.setViewportView(panel_known);

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(
				jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout
						.createSequentialGroup().addContainerGap().addComponent(jScrollPane3).addContainerGap()));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
						.addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
						.addContainerGap()));

		jPanel1.add(jPanel2);

		jPanel3.setBackground(new java.awt.Color(240, 240, 255));

		jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));
		jScrollPane4.setForeground(new java.awt.Color(20, 20, 255));
		jScrollPane4.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		panel_online.setBackground(new java.awt.Color(255, 255, 255));
		panel_online.setLayout(new javax.swing.BoxLayout(panel_online, javax.swing.BoxLayout.Y_AXIS));
		jScrollPane4.setViewportView(panel_online);

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(
				jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout
						.createSequentialGroup().addContainerGap().addComponent(jScrollPane4).addContainerGap()));
		jPanel3Layout.setVerticalGroup(
				jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout
						.createSequentialGroup().addContainerGap().addComponent(jScrollPane4).addContainerGap()));

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
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JScrollPane jScrollPane4;
	private javax.swing.JPanel panel_known;
	private javax.swing.JPanel panel_online;
	private javax.swing.JLabel value_addr;
	private javax.swing.JLabel value_msg;
	private javax.swing.JLabel value_port;
	private javax.swing.JLabel value_state;
	// End of variables declaration

	private class ScanResult {

		public final List<String> visibleUIDs;
		public final List<String> onlineUIDs;
		public final List<String> onlineCLIDs;
		public final List<String> visibleCIDs;

		public ScanResult() throws IOException {
			this.visibleUIDs = new ArrayList<String>();
			this.onlineUIDs = new ArrayList<>();
			this.visibleCIDs = new ArrayList<>();
			this.onlineCLIDs = new ArrayList<>();
			List<Map<String, String>> clientquery = UI.this.conn.request("clientlist -uid", null);
			for (Map<String, String> u : clientquery) {
				String uid = u.get("client_unique_identifier");
				this.visibleUIDs.add(uid);
				this.visibleCIDs.add(u.get("cid"));
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
					String clid = StringAnalystic.convertMap(res).get(0).get("clid");
					onlineCLIDs.add(clid);
				}
			}
		}

		private List<User> knownList = null;
		private List<User> onlineNames = null;

		private void compute() {
			this.knownList = new ArrayList<>();
			this.onlineNames = new ArrayList<>();
			Map<String, String> channel_names = this.getChannelNames();
			List<Map<String, String>> all = UI.this.db.getAllUDIs();
			for (Map<String, String> u : all) {
				String uid = u.get("uid");
				int onlinei = this.onlineUIDs.indexOf(uid);
				if (onlinei >= 0) {
					String name = u.get("name");
					User user = new User(name, null, uid, this.onlineCLIDs.get(onlinei));
					int uidi = this.visibleUIDs.indexOf(uid);
					if (uidi < 0)
						name = "*" + name + "*";
					else {
						String cid = this.visibleCIDs.get(uidi);
						String cname = channel_names.get(cid);
						if (cname == null)
							name += " <?>";
						else
							name += " <" + cname + ">";
						user.setCid(cid);
					}
					user.setDisplayName(name);
					this.onlineNames.add(user);
				} else {
					String name = u.get("name");
					User user = new User(name, name, uid);
					this.knownList.add(user);
				}
			}
		}

		private Map<String, String> getChannelNames() {
			Map<String, String> names = new HashMap<>();
			try {
				List<Map<String, String>> res = UI.this.conn.request("channellist", null);
				for (Map<String, String> channel : res) {
					if (channel.get("channel_flag_are_subscribed").equals("1")
							&& channel.get("total_clients").equals("0"))
						continue;
					String cid = channel.get("cid");
					String name = channel.get("channel_name");
					names.put(cid, name);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return names;
		}

		public List<User> getKnownNames() {
			if (knownList == null || onlineNames == null) {
				compute();
			}
			return this.knownList;
		}

		public List<User> getOnlineNames() {
			if (knownList == null || onlineNames == null) {
				compute();
			}
			return this.onlineNames;
		}

	}

	public void setMessage(String msg) {
		if (msg == null)
			msg = "< = >";
		this.value_msg.setText(msg);
	}

	public void update() {
		try {
			ScanResult scan = new ScanResult();
			this.panel_known.removeAll();
			this.panel_online.removeAll();
			List<User> kn = scan.getKnownNames();
			List<User> on = scan.getOnlineNames();
			for (User u : kn)	{
				Entry e = new Entry(this, u, conn);
				this.panel_known.add(e);
			}
			for (User u : on)	{
				Entry e = new Entry(this, u, conn);
				this.panel_online.add(e);
			}
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

	public void run() {
		this.isActive = true;
		while (isActive) {
			this.update();
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
				isActive = false;
			}
		}
	}

	private void addEvents() {
		this.input_tab.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int hid = Integer.valueOf(input_tab.getText());
					UI.this.conn.switchHandler(hid);
				} catch (NumberFormatException ex) {
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
