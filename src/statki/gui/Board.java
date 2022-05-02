package statki.gui;

import java.util.ArrayList;

public class Board { //reprezentuje czesc logiczna statkow, przechowujemy tylko liczobwo informacje o pozycjach stakow oraz trafieniu itp
	private int[][] board;
	private ArrayList<ShipLogic> ships;
	private final int BOARD_SIZE = 10;
	private final int EMPTY_FIELD = 0;
	
	public Board() {
		board = new int[BOARD_SIZE][BOARD_SIZE];
//				{
//				{ 0, 0, 0, 3, 3, 3, 0, 0, 0, 0 },
//				{ 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
//				{ 0, 0, 0, 0, 0, 0, 3, 0, 0, 0 },
//				{ 0, 1, 0, 0, 1, 0, 3, 0, 2, 0 },
//				{ 0, 0, 0, 0, 0, 0, 3, 0, 2, 0 },
//				{ 4, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
//				{ 4, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
//				{ 4, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
//				{ 4, 0, 0, 2, 2, 0, 0, 2, 2, 0 },
//				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
//				};
		ships = new ArrayList<>();
//		ships.add(new ShipLogic(1, 1, 1, false));
//		ships.add(new ShipLogic(1, 3, 1, false));
//		ships.add(new ShipLogic(2, 5, 1, false));
//		ships.add(new ShipLogic(4, 3, 1, false));
//		
//		ships.add(new ShipLogic(8, 3, 2, true));
//		ships.add(new ShipLogic(7, 8, 2, false));
//		ships.add(new ShipLogic(3, 8, 2, false));
//		
//		ships.add(new ShipLogic(3, 0, 3, false));
//		ships.add(new ShipLogic(6, 2, 3, true));
//		
//		ships.add(new ShipLogic(0, 5, 4, true));
		
	}
	
	private boolean isEmptyFieldArround(int x, int y)
	{
		for(int i = x - 1; i <= x + 1; i++)
		{
			for(int j = y - 1; j <= y + 1; j++)
			{
				if(i >= 0 && i < BOARD_SIZE && j >= 0 && j < BOARD_SIZE && board[i][j] != EMPTY_FIELD)
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean canPlaceShip(ShipLogic ship)
	{
		if(ship.getCol() < 0 || ship.getCol() >= BOARD_SIZE || ship.getRow() < 0 || ship.getRow() >= BOARD_SIZE
				|| ship.getSize() < 0 || ship.getSize() > BOARD_SIZE || ship.getRow() + ship.getSize() < BOARD_SIZE
				|| ship.getCol() + ship.getSize() < BOARD_SIZE)
		{
			return false;
		}
		
		if(!ship.isVertical()) //jesli jest horyzontalny
		{
			for(int i = ship.getCol(); i <= ship.getCol() + ship.getSize(); i++)
			{
				if(!isEmptyFieldArround(i, ship.getRow()))
				{
					return false;
				}
			}
		}
		else //jesli jest wertykalny
		{
			for(int i = ship.getRow(); i <= ship.getRow() + ship.getSize(); i++)
			{
				if(!isEmptyFieldArround(ship.getCol(), i))
				{
					return false;
				}
			}
		}
		
		return true;
	}

	public int[][] getBoard() {
		return board;
	}

	public ArrayList<ShipLogic> getShips() {
		return ships;
	}
}
