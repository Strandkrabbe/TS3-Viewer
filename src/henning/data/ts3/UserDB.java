package henning.data.ts3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class UserDB {
	
	private File dbFile;
	private List<Map<String,String>> data;
	private boolean temp = false;
	private boolean created = false;
	
	public UserDB(File db)	{
		try	{
		if (db.exists() == false)
			create(db);
		}	catch (IOException iox)	{
			Log.warn("Cannot save db using temporary database!");
			temp = true;
		}
		dbFile = db;
		if (temp == false)
			try {
				data = StringAnalystic.convertMap(new String(Files.readAllBytes(dbFile.toPath())));
			} catch (IOException e) {
				Log.error("Error while reading userdb using temporary db!");
				data = new ArrayList<Map<String,String>>();
				temp = true;
			}
		else
			data = new ArrayList<Map<String,String>>();
		if (created)	{
			Map<String,String> fs = new HashMap<String,String>();
//			fs.put("uid", "OlM8j+fLWWSwMD/lBBHLqTQtOiE=");
//			fs.put("name", "Strandkrabbe_ARM");
//			fs.put("state", "0");
			data = new ArrayList<Map<String,String>>();
			data.add(fs);
		}
	}
	
	private void create(File f) throws IOException	{
		f.createNewFile();
		created = true;
	}
	
	private void clean(List<Map<String,String>> m)	{
		ListIterator<Map<String,String>> mi = m.listIterator();
		while (mi.hasNext())	{
			Map<String,String> e = mi.next();
			if (e == null || !e.containsKey("uid") || !e.containsKey("name"))
				mi.remove();
			if (!e.containsKey("state"))
				e.put("state", "0");
		}
	}
	
	public void save()	{
		if (temp)
			return;
		try {
			this.clean(data);
			Files.write(dbFile.toPath(), StringAnalystic.mapToString(data).getBytes(), StandardOpenOption.WRITE);
		} catch (IOException e) {
			Log.error("Unexcpected error while writing to userdb");
		}
	}
	
	public void add(String uid,String name,boolean recode)	{
		if (recode)
			name = StringAnalystic.replcesSpaceWithS(name);
		Map<String,String> ti = new HashMap<String,String>();
		ti.put("uid", uid);
		ti.put("name", name);
		ti.put("state", "0");
		data.add(ti);
		save();
	}
	public boolean contains(String uid)	{
		for (Map<String,String> m : data)	{
			String uids = m.get("uid");
			if (uids != null && uids.equals(uid))
				return true;
		}
		return false;
	}
	public List<Map<String,String>> getAllUDIs()	{
		return data;
	}
}
