package statki.gui;

import java.awt.Color;
import java.awt.Graphics;

public class ShipBox extends Box {
	private Color color;
	
	public ShipBox(int x, int y, int size, Color color)
	{
		super(x, y, size);
		this.color = color;
	}
	
	public void draw(Graphics g, double scale)
	{
		g.setColor(color);
		g.fillRect((int)(x*scale), (int)(y*scale), (int)(size*scale), (int)(size*scale));
	}
}
