package statki.gui;

import java.util.ArrayList;
import java.util.Random;

public class Board { //reprezentuje czesc logiczna statkow, przechowujemy tylko liczobwo informacje o pozycjach stakow oraz trafieniu itp
	private int[][] board;
	private ArrayList<ShipLogic> ships;
	public static final int BOARD_SIZE = 10;
	private static final int EMPTY_FIELD = 0;
	public static final int MISS_FIELD = -1;
	public static final int HIT_FIELD = -2;
	public static final int FAIL = -3;
	public static final int SNAKE_ISLAND = 1000;
	
	public Board() {
		board = new int[BOARD_SIZE][BOARD_SIZE];
		ships = new ArrayList<>();
		
		Random rand = new Random();
		int sizes[] = {4, 3, 3, 2, 2, 2, 1, 1, 1 }; 
		for(int i = 0; i < sizes.length; i++)
		{
			int size = sizes[i];
			ShipLogic ship = null;
			do
			{
				//wejscie w petle nastepuje przed sprawdzeniem warunku, tworzy sie losowy statek
				ship = new ShipLogic(rand.nextInt(BOARD_SIZE), rand.nextInt(BOARD_SIZE), size, rand.nextBoolean());
			}
			while(!placeShip(ship)); //jesli nie uda sie dodac statku to wracamy na poczatek petli 
									 //i losujemy kolejny statek
		}
		
		//losujemy miejsce na wyspe wezy
		ShipLogic ship = null;
		do
		{
			//wejscie w petle nastepuje przed sprawdzeniem warunku, tworzy sie losowy statek
			ship = new ShipLogic(rand.nextInt(BOARD_SIZE), rand.nextInt(BOARD_SIZE), 1, rand.nextBoolean(), true);
		}
		while(!placeShip(ship, SNAKE_ISLAND));
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
		if(ship.getCol() < 0 
				|| ship.getCol() >= BOARD_SIZE 
				|| ship.getRow() < 0 
				|| ship.getRow() >= BOARD_SIZE
				|| ship.getSize() < 0 
				|| ship.getSize() > BOARD_SIZE 
				|| (!ship.isVertical() && ship.getRow() + ship.getSize() - 1 >= BOARD_SIZE)
				|| (ship.isVertical() && ship.getCol() + ship.getSize() - 1 >= BOARD_SIZE))
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
		return placeShip(ship, ship.getSize());
	}
	
	public boolean placeShip(ShipLogic ship, int symbol)
	{
		if(!canPlaceShip(ship))
		{
			return false;
		}
		
		if(ship.isVertical()) //jesli jest horyzontalny
		{
			for(int i = ship.getCol(); i < ship.getCol() + ship.getSize(); i++)
			{
				board[ship.getRow()][i] = symbol;
			}
		}
		else //jesli jest wertykalny
		{
			for(int i = ship.getRow(); i < ship.getRow() + ship.getSize(); i++)
			{
				board[i][ship.getCol()] = symbol;
			}
		}
		ships.add(ship);
		return true;
	}
	
	private ShipLogic getShip(int i, int j)
	{
		for(int k = 0; k < ships.size(); k++)
		{
			var ship = ships.get(k);
			if(ship.getRow() == i && ship.getCol() == j)
			{
				return ship;
			}
		}
		return null;
	}
	
	private void removeShip(ShipLogic ship)
	{
		if(ship.isVertical()) //jesli jest horyzontalny
		{
			for(int i = ship.getCol(); i < ship.getCol() + ship.getSize(); i++)
			{
				board[ship.getRow()][i] = EMPTY_FIELD;
			}
		}
		else //jesli jest wertykalny
		{
			for(int i = ship.getRow(); i < ship.getRow() + ship.getSize(); i++)
			{
				board[i][ship.getCol()] = EMPTY_FIELD;
			}
		}
		ships.remove(ship);
		
	}
	
	public boolean move(int sourceI, int sourceJ, int destinationI, int destinationJ) throws Exception
	{
		//1. Pobiera statek z listy
		//2. Jesli statek nie istnieje to rzuca wyjatkiem i przerywa operacje
		//3. Usuwa statek z planszy
		//4. Tworzy nowy statek ale o nowych wspolrzednych i probuje go dodac na plansze, jesli uda sie dodac to zwroc tru,.e
		//5. Jesli nie uda sie dodac to dodaj z powrotem stary statek na plansze i zwroc false
		var ship = getShip(sourceI, sourceJ);
		if(ship == null)
		{
			throw new Exception("No ship on pos: " + sourceI + " " + sourceJ);
		}
		
		removeShip(ship);
		ShipLogic newShip = new ShipLogic(destinationI, destinationJ, ship.getSize(), ship.isVertical());
		if(placeShip(newShip))
		{
			return true;
		}
		else
		{
			placeShip(ship);
			return false;
		}
	}

	public int[][] getBoard() {
		return board;
	}

	public ArrayList<ShipLogic> getShips() {
		return ships;
	}

	public boolean toggleOrientation(int i, int j) throws Exception {
		var ship = getShip(i, j);
		if(ship == null)
		{
			throw new Exception("No ship on pos: " + i + " " + j);
		}
		
		removeShip(ship);
		ShipLogic newShip = new ShipLogic(ship.getRow(), ship.getCol(), ship.getSize(), !ship.isVertical());
		if(placeShip(newShip))
		{
			return true;
		}
		else
		{
			placeShip(ship);
			return false;
		}
	}
	
	public int attack(int i, int j)
	{
		//i, j - indeksy komorek do strzalu
		if(i < 0 || i >= BOARD_SIZE || j < 0 || j >= BOARD_SIZE)
		{
			return FAIL; //fail jesli nie poprawne indeksy
		}
		
		var state = board[i][j]; //pobierz co jest pod komorka (pudlo, statek, puste itp)
		if(state == SNAKE_ISLAND)
		{
			board[i][j] = HIT_FIELD; //ustaw ze pudlo
			return SNAKE_ISLAND;
		}
		if(state == EMPTY_FIELD) //jesli pole puste
		{
			board[i][j] = MISS_FIELD; //ustaw ze pudlo
			return MISS_FIELD;
		}
		else if(state > 0) //jesli w polu jest statek
		{
			board[i][j] = HIT_FIELD; //ustaw ze pole trafione
			return HIT_FIELD;
		}
		
		return FAIL;
	}
	
	public boolean hasAnyShip()
	{
		for(int i = 0; i < BOARD_SIZE; i++)
		{
			for(int j = 0; j < BOARD_SIZE; j++)
			{
				if(board[i][j]> 0) //jesli jest statek to znaczy ze na planszy jest jakikolwiek statek (true)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public int[] attackRandomShip()
	{
		for(int i = 0; i < BOARD_SIZE; i++)
		{
			for(int j = 0; j < BOARD_SIZE; j++)
			{
				if(board[i][j]> 0 && board[i][j] != SNAKE_ISLAND) 
				{
					attack(i,j);
					return new int[] {i, j};
				}
			}
		}
		return null;
	}
}
