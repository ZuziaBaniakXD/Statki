package statki.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class BoardPanel extends JPanel {
	private final int baseWindowSize = 300;
	private int size = 25;
	private List<Box> boxes;
	private List<Ship> ships;
	private String playerText;
	
	public BoardPanel(String playerText) {
		this.playerText = playerText;
		int x = 0;
		
		boxes = new ArrayList<>();
        for(int i = 0; i < 11; i++, x += size)
        {
        	int y = 0;
        	for(int j = 0; j < 11; j++, y += size)
        	{
        		if(i == 0 && j == 0)
        		{
        			continue;
        		}
        		else if(j == 0)
        		{
        			char letter = (char)(64 + i);
        			Box b = new Box(x, y, size, letter + "");
            		boxes.add(b);
        		}
        		else if(i == 0)
        		{
        			Box b = new Box(x, y, size, j + "");
            		boxes.add(b);
        		}
        		else
        		{
        			Box b = new Box(x, y, size);
            		boxes.add(b);
        		}
        		
        	}
        }
        ships = new ArrayList<Ship>();
        ships.add(new Ship(100, 100, 25, 2, true));
        ships.add(new Ship(150, 100, 25, 3, false));
	}
	
	private double getScale()
	{
		double scaleX = getWidth() / (double)baseWindowSize;
		double scaleY = getHeight() / (double)baseWindowSize;
		return scaleX < scaleY ? scaleX : scaleY;
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        for(int i = 0; i < boxes.size(); i++)
        {
        	boxes.get(i).draw(g, getScale());
        }
        for(int i = 0; i < ships.size(); i++)
        {
        	ships.get(i).draw(g, getScale());
        }
        g.setColor(Color.black);
        g.drawString(playerText, (int)(60 * getScale()), (int)(290 * getScale()));
    }
}
