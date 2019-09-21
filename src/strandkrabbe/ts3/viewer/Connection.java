package strandkrabbe.ts3.viewer;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("deprecation")
public class Connection implements Closeable{
	
	private Socket sock;
	private PrintStream out;
	private DataInputStream in;
	public final String ip;
	public final int port;
	
	public Connection(String ip,int port) throws UnknownHostException, IOException	{
		this.ip = ip;
		this.port = port;
		sock = new Socket(ip, port);
		sock.setSoTimeout(1000);
		out = new PrintStream(new DataOutputStream(sock.getOutputStream()));
		in = new DataInputStream(sock.getInputStream());
	}
	public void doAuth(String key) throws IOException	{
		request("auth apikey=" + key); 
		request("clientnotifyregister schandlerid=0 event=notifyclientids");
	}
	public void switchHandler(int i) throws IOException	{
		request("clientnotifyunregister");
		request("clientnotifyregister schandlerid=" + i + " event=notifyclientids");
	}
	public List<Map<String,String>> request(String cmd,Map<String,String> args) throws IOException	{
		List<Map<String,String>> a = new ArrayList<Map<String,String>>();
		a.add(args);
		String res;
		if (args != null)
			res = this.request(cmd + " " + StringAnalystic.mapToString(a));
		else
			res = this.request(cmd);
		a = StringAnalystic.convertMap(StringAnalystic.cutError(res));
		if (a.size() == 0)
			a.add(new HashMap<String, String>());
		a.get(0).put(" error ","" + (!StringAnalystic.nonerrorString(res)));
		return a;
	}
	synchronized String request(String tw) throws IOException	{
		write(tw);
		return readAll();
	}
	private void write(String tw) throws IOException	{
		in.skip(in.available());
		out.println(tw);
		out.flush();
		Log.debug(tw);
	}
	private String readAll() throws IOException	{
		String finalS = "";
		boolean flagged = false;
		while (flagged == false)	{
			try	{
			String nl = in.readLine();
			Log.debug(nl);
			if (nl.contains("error id="))	{
				flagged = true;
				finalS += " " + nl;
			}	else	{
				finalS += nl;
			}
			}	catch (SocketTimeoutException ex)	{
				break;
			}
		}
		return finalS;
	}
	
	public void close() throws IOException	{
		this.sock.close();
	}
	
}
