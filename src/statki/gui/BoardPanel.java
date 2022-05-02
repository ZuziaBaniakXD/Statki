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
	private Board board;
	private boolean showShips;
	
	public BoardPanel(String playerText, boolean showShips) {
		this.showShips = showShips;
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
        			Box b = new Box(x, y, size, letter + ""); //dodawanie liter 
            		boxes.add(b);
        		}
        		else if(i == 0)
        		{
        			Box b = new Box(x, y, size, j + ""); //dodawanie cyfr
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
//        ships.add(new Ship(100, 100, 25, 2, true));
//        ships.add(new Ship(150, 100, 25, 3, false));
        board = new Board();
        for(int i = 0; i < board.getShips().size(); i++)
        {
        	ShipLogic sl = board.getShips().get(i); //pobieram pobjedyczny statek z listy pod danym indeksem i
        	int blockSize = 25;
        	Ship s = new Ship((1+sl.getRow()) * blockSize, (1+sl.getCol()) * blockSize, blockSize, sl.getSize(), sl.isVertical());
        	ships.add(s);
        }
	}
	
	private double getScale()
	{
		double scaleX = getWidth() / (double)baseWindowSize;
		double scaleY = getHeight() / (double)baseWindowSize;
		return scaleX < scaleY ? scaleX : scaleY; //jeśli warunek jest prawdxiwy zwroc scaleX a jesli falszywy to scaleY
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        for(int i = 0; i < boxes.size(); i++)
        {
        	boxes.get(i).draw(g, getScale());
        }
        if(showShips)
        {
            for(int i = 0; i < ships.size(); i++)
            {
            	ships.get(i).draw(g, getScale());
            }
        }
        g.setColor(Color.black);
        g.drawString(playerText, (int)(60 * getScale()), (int)(290 * getScale()));
    }
	
}
