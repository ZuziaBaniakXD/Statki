package statki.gui;

import java.util.ArrayList;
import java.util.Random;

public class Board { //reprezentuje czesc logiczna statkow, przechowujemy tylko liczobwo informacje o pozycjach stakow oraz trafieniu itp
	private int[][] board;
	private ArrayList<ShipLogic> ships;
	public static final int BOARD_SIZE = 10;
	private final int EMPTY_FIELD = 0;
	
	public Board() {
		board = new int[BOARD_SIZE][BOARD_SIZE];
		ships = new ArrayList<>();
		
		Random rand = new Random();
		int sizes[] = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1 };
		for(int i = 0; i < sizes.length; i++)
		{
			int size = sizes[i];
			ShipLogic ship = null;
			do
			{
				//wejscie w petle nastepuje przed sprawdzeniem warunku, tworzy sie losowy statek
				ship = new ShipLogic(rand.nextInt(BOARD_SIZE), rand.nextInt(BOARD_SIZE), size, rand.nextBoolean());
			}
			while(!placeShip(ship)); //jesli nie uda sie dodac statku to wracamy na poczatek petli i losujemy kolejny statek
		}
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
				|| ship.getSize() < 0 || ship.getSize() > BOARD_SIZE || ship.getRow() + ship.getSize() - 1 >= BOARD_SIZE
				|| ship.getCol() + ship.getSize() - 1 >= BOARD_SIZE)
		{
			return false;
		}
		
		if(ship.isVertical()) //jesli jest wertykalny
		{
			for(int i = ship.getCol(); i < ship.getCol() + ship.getSize(); i++)
			{
				if(!isEmptyFieldArround(ship.getRow(), i))
				{
					return false;
				}
			}
		}
		else //jesli jest horyzontalny
		{
			for(int i = ship.getRow(); i < ship.getRow() + ship.getSize(); i++)
			{
				if(!isEmptyFieldArround(i, ship.getCol()))
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	public boolean placeShip(ShipLogic ship)
	{
		if(!canPlaceShip(ship))
		{
			return false;
		}
		
		if(ship.isVertical()) //jesli jest horyzontalny
		{
			for(int i = ship.getCol(); i < ship.getCol() + ship.getSize(); i++)
			{
				board[ship.getRow()][i] = ship.getSize();
			}
		}
		else //jesli jest wertykalny
		{
			for(int i = ship.getRow(); i < ship.getRow() + ship.getSize(); i++)
			{
				board[i][ship.getCol()] = ship.getSize();
			}
		}
		ships.add(ship);
		return true;
	}

	public int[][] getBoard() {
		return board;
	}

	public ArrayList<ShipLogic> getShips() {
		return ships;
	}
}
