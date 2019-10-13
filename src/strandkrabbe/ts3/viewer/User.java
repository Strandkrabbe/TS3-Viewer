package strandkrabbe.ts3.viewer;

public class User {
	
	private String name;
	private String displayName;
	private final String uid;
	private String clid;
	private String cid;
	
	public User(String name,String displayName,String uid,String clid,String cid)	{
		this.name = name;
		this.displayName = StringAnalystic.replaceSpacer(displayName);
		this.uid = uid;
		this.clid = clid;
		this.cid = cid;
	}
	public User(String name,String displayName,String uid,String clid)	{
		this(name, displayName, uid, clid, null);
	}
	public User(String name,String displayName,String uid)	{
		this(name, displayName, uid, null, null);
	}
	
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = StringAnalystic.replaceSpacer(displayName);
	}
	public String getClid() {
		return clid;
	}
	public void setClid(String clid) {
		this.clid = clid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public String getUid() {
		return uid;
	}
	
}
