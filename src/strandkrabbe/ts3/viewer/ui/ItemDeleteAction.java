package strandkrabbe.ts3.viewer.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import strandkrabbe.ts3.viewer.UserDB;

public class ItemDeleteAction implements EntryButtonAction {
	
	private final UserDB db;
	private final UI ui;
	private BufferedImage icon;
	
	public ItemDeleteAction(UI u,UserDB db)	{
		this.ui = u;
		this.db = db;
		try	{
			InputStream ii = this.getClass().getResourceAsStream("/strandkrabbe/ts3/viewer/res/del.png");
			if (ii == null)
				throw new IOException();
			this.icon = ImageIO.read(ii);
		}	catch (IOException ex)	{
			this.icon = new BufferedImage(64, 64, BufferedImage.TYPE_INT_RGB);
			Graphics g = this.icon.createGraphics();
			g.setColor(new Color(250,10,10));
			g.fillRect(0, 0, 64, 64);
			g.dispose();
		}
	}
	
	@Override
	public BufferedImage getIcon() {
		return this.icon;
	}
	@Override
	public void onClick(String value) {
		int sep = value.indexOf(UI.UID_SEPERATOR);
		if (sep >= 0)	{
			String uid = value.substring(0, sep).strip();
			if (uid.length() == 28 && uid.endsWith("="))	{
				db.remove(uid);
				ui.setMessage("Removed " + uid);
			}
		}
	}
	
}
