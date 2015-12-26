package com.pallasli.javastudy;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;

@SuppressWarnings("serial")
public class AppletUtils extends Applet {
	Image img;
	MediaTracker tr;

	@Override
	public void paint(Graphics g) {
		tr = new MediaTracker(this);
		img = getImage(getCodeBase(), "demoimg.gif");
		tr.addImage(img, 0);
		g.drawImage(img, 0, 0, this);
	}
}
