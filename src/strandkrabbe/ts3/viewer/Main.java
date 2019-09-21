package strandkrabbe.ts3.viewer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class Main {
	
	private static Connection conn;
	private static Properties conf;
	private static UserDB userdb;
	
	private static void createConf(File f) throws FileNotFoundException, IOException	{
		f.createNewFile();
		Properties np = new Properties();
		np.setProperty("IP", "127.0.0.1");
		np.setProperty("port", "25639");
		np.setProperty("key", "?");
		np.setProperty("learn", "1");
		np.setProperty("ui", "true");
		np.store(new FileOutputStream(f), "Teamspeak ClientQuery interface data");
		Log.warn("New config file created! Program will exit. Please edit the file and fill in the apikey");
		Log.warn("of your teamspeak ClienQuery plugin. You can find it in Extras > Options > Plugins > ClientQuery > Preferences");
		System.exit(0);
	}
	
	static boolean active = true;
	
	public static void main(String[] args) throws IOException {
		File confd = new File("./conf.properties");
		File userdbf = new File("./user.db");
		if (!confd.exists())	{
			Log.warn("Found no config file creating new...");
			createConf(confd);
		}
		conf = new Properties();
		conf.load(new FileInputStream(confd));
		if (!userdbf.exists())	{
			Log.warn("Found no user database creating new...");
		}
		new Thread(Main::commands).start();
		if (conf.getProperty("ui","true").equalsIgnoreCase("true"))	{
			UI ui = new UI(conf);
			ui.run();
		}	else	{
			userdb = new UserDB(userdbf);
			Log.setDebug(false);
			Log.info("Statring...");
			conn = new Connection(conf.getProperty("ip"), Integer.valueOf(conf.getProperty("port")));
			conn.doAuth(conf.getProperty("key"));
			for (Map<String,String> m : userdb.getAllUDIs())	{
				m.put("state", "0");
			}
			userdb.save();
			Log.info("Started!");
			int learn = 0;
			while (active)	{
				for (int C = 0;C < userdb.getAllUDIs().size();C++)	{
					String rep = conn.request("clientgetids cluid=" + userdb.getAllUDIs().get(C).get("uid"));
					if (rep.contains("error id=1281") && userdb.getAllUDIs().get(C).get("state").equals("1"))	{
						Log.info(StringAnalystic.replaceSpacer(userdb.getAllUDIs().get(C).get("name")) + " is now offline");
						userdb.getAllUDIs().get(C).put("state", "0");
						userdb.save();
					}	else if (userdb.getAllUDIs().get(C).get("state").equals("0") && rep.contains("error id=0"))	{
						String clid = StringAnalystic.convertMap(rep).get(0).get("clid");
						Log.info(StringAnalystic.replaceSpacer(userdb.getAllUDIs().get(C).get("name")) + " is now online with clid=" + clid);
						userdb.getAllUDIs().get(C).put("state","1");
						userdb.save();
					}
				}
				if (learn == 0 && conf.getProperty("learn", "0").equals("1"))	{
					List<Map<String,String>> res = StringAnalystic.convertMap(conn.request("clientlist -uid"));
					if (res == null)	continue;
					for (Map<String,String> user : res)	{
						if (user == null || user.get("client_unique_identifier") == null)	continue;
						if (!user.get("client_unique_identifier").endsWith("="))	{
							user.put("client_unique_identifier", user.get("client_unique_identifier") + "=");
						}
						if (!userdb.contains(user.get("client_unique_identifier")))	{
							userdb.add(user.get("client_unique_identifier"), user.get("client_nickname"),false);
							Log.info("Added user to db: " + user.get("client_nickname") + " as " + user.get("client_unique_identifier"));
						}
					}
				}
				if (learn == 6)	{
					learn = 0;
				}	else	learn++;
				try {
					Thread.sleep(15000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static void commands()	{
		Scanner Sc = new Scanner(System.in);
		while (active)	{
			try	{
				String cmd = Sc.nextLine();
				String[] args = cmd.split(" ");
				if (args[0].equalsIgnoreCase("exit"))	{
					conn.request("quit");
					active = false;
				}
				if (args[0].equalsIgnoreCase("openchat"))	{
					int clid = Integer.valueOf(args[1]);
					conn.request("sendtextmessage targetmode=1 target=" + clid + " msg=__start__");
				}
				if (args[0].equalsIgnoreCase("raw"))	{
					StringBuilder b = new StringBuilder();
					for (int C = 1;C < args.length;C++)	{
						if (C == 1)
							b.append(' ');
						b.append(args[C]);
					}
					String res = conn.request(b.toString());
					Log.info(res);
				}
			}	catch (Exception e)	{
				e.printStackTrace();
			}
		}
		Sc.close();
		Log.info("Exiting...");
	}
	
}
