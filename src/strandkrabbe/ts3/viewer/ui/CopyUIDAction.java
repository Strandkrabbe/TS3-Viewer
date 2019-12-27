package strandkrabbe.ts3.viewer.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class CopyUIDAction implements EntryButtonAction {

	private final UI ui;
	private BufferedImage icon;
	
	public CopyUIDAction(UI u)	{
		this.ui = u;
		try	{
			InputStream ii = this.getClass().getResourceAsStream("/strandkrabbe/ts3/viewer/res/copy.png");
			if (ii == null)
				throw new IOException();
			this.icon = ImageIO.read(ii);
		}	catch (IOException ex)	{
			this.icon = new BufferedImage(64, 64, BufferedImage.TYPE_INT_RGB);
			Graphics g = this.icon.createGraphics();
			g.setColor(new Color(10,250,250));
			g.fillRect(0, 0, 64, 64);
			g.dispose();
		}
	}
	
	@Override
	public void onClick(String value) {
		int sepindex = value.indexOf(UI.UID_SEPERATOR);
		if (sepindex >= 0)	{
			String uid = value.substring(0, sepindex).strip();
			StringSelection sel = new StringSelection(uid);
			Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
			c.setContents(sel, null);
			this.ui.setMessage("Copied UID");
		}
	}

	@Override
	public BufferedImage getIcon() {
		return this.icon;
	}
	
	
	
}
