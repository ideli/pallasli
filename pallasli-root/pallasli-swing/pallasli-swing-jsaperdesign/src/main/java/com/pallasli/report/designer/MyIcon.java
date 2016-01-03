package com.pallasli.report.designer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.Icon;

public class MyIcon implements Icon {
	BufferedImage bufferedImage;

	public MyIcon(String path) {
		try {
			System.out.println(path);
			path = Main.class.getResource(path).getPath();
			InputStream in = new FileInputStream(new File(path));
			this.bufferedImage = ImageIO.read(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getIconHeight() {
		return 20;
	}

	@Override
	public int getIconWidth() {
		return 20;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		g.setColor(Color.RED);
		g.drawRect(0, 0, 25, 25);
		g.drawImage(this.bufferedImage, 0, 0, 20, 20, null);
	}
}
