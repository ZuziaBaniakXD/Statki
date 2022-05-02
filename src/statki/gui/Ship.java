package statki.gui;

import java.awt.Color;
import java.awt.Graphics;

public class Ship implements Drawable {
	private int x;
	private int y;
	private int segmentCount;
	private boolean isVertical;
	private int segmentSize;

	public Ship(int x, int y, int segmentSize, int segmentCount, boolean isVertical) {
		super();
		this.x = x;
		this.y = y;
		this.segmentCount = segmentCount;
		this.isVertical = isVertical;
		this.segmentSize = segmentSize;
	}


	@Override
	public void draw(Graphics g, double scale) {
		Color c1 = new Color(51,204,255);
		g.setColor(c1);
		if(isVertical)
		{
			g.fillRect((int)(scale*x), (int)(scale*y), (int)(segmentSize*scale), (int)(segmentSize * segmentCount * scale));
		}
		else
		{
			g.fillRect((int)(scale*x), (int)(scale*y), (int)(segmentSize * segmentCount * scale), (int)(segmentSize*scale));
		}
		
	}
	

}
