package statki.gui;

import java.awt.Color;
import java.awt.Graphics;

public class Ship implements Drawable {
	private int x;
	private int y;
	private int segmentCount;
	private boolean isVertical;
	private int segmentSize;
	private Color color = new Color(51,204,255);

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
		
		g.setColor(color);
		if(isVertical)
		{
			g.fillRect((int)(scale*x), (int)(scale*y), (int)(segmentSize*scale), (int)(segmentSize * segmentCount * scale));
		}
		else
		{
			g.fillRect((int)(scale*x), (int)(scale*y), (int)(segmentSize * segmentCount * scale), (int)(segmentSize*scale));
		}
		
	}
	
	public boolean containsPoint(int posX, int posY, double scale)
	{
		//lewy gorny rog
		int minX = (int)(scale*x);
		int minY = (int)(scale*y);
		int maxX;
		int maxY;
		if(isVertical)
		{
			maxX = minX + (int)(segmentSize*scale);
			maxY = minY + (int)(segmentSize * segmentCount * scale);
		}
		else
		{
			maxX = minX + (int)(segmentSize * segmentCount * scale);
			maxY = minY + (int)(segmentSize*scale);
		}
		return posX >= minX && posX <= maxX && posY >= minY && posY <= maxY;
	}
	
	public void select()
	{
		color = Color.orange;
	}
	
	public void deselect()
	{
		color = new Color(51,204,255);
	}
	
	public void move(int destX, int destY)
	{
		x = destX;
		y = destY;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}


	public void setX(int x) {
		this.x = x;
	}


	public void setY(int y) {
		this.y = y;
	}
	
	
}
