package statki.gui;

import java.awt.Color;
import java.awt.Graphics;

public class Box implements Drawable {
	private int x;
	private int y;
	private int size;
	private String text;
	
	public Box(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.text = null;
	}
	
	public Box(int x, int y, int size, String text) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.text = text;
	}
	
	public void draw(Graphics g, double scale)
	{
		if(text != null)
		{
			g.setColor(Color.cyan);
			g.fillRect((int)(x*scale), (int)(y*scale), (int)(size*scale), (int)(size*scale));
			g.setColor(Color.black);
			int sizeX;
			if(text.length() == 1)
			{
				sizeX = x+size/3;
			}
			else
			{
				sizeX = x+size/5;
			}
			g.drawString(text, (int)(sizeX*scale), (int)((y+size/2)*scale));
			
		}

		g.setColor(Color.black);
		g.drawRect((int)(x*scale), (int)(y*scale), (int)(size*scale), (int)(size*scale));
	}
}
