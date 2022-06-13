package statki.gui;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameFrame extends JFrame implements GameOverListener  {
	private static final int SET_SHIPS = 1;
	private static final int PLAY_GAME = 2;
	private int gameMode;
	private boolean withComputer;
	private BoardPanel p1;
	private BoardPanel p2;
	
	public GameFrame(boolean withComputer) {
		gameMode = SET_SHIPS;
		this.withComputer = withComputer; //trzyma informacje czy gramy z komputerem (true) czy z innym graczem (false)
		setSize(800, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		
		JPanel upPanel = new JPanel();
		JButton setShipsButton = new JButton(withComputer ? "Ustaw" : "Ustaw - Gracz 1");
		upPanel.add(setShipsButton);
		JButton playButton = new JButton("Zagraj");
		playButton.setEnabled(false);
		upPanel.add(playButton);
		
		JButton MusicButton1 = new JButton("Włacz muzykę"); 
		JButton MusicButton2 = new JButton("Wyłącz muzykę");
		
		
		//upPanel.add(MusicButton1);
		//upPanel.add(MusicButton2);
		add(upPanel, BorderLayout.NORTH);
		
		JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
		
		p1 = new BoardPanel(withComputer ? "TWOJA PLANSZA" : "Gracz 1");
		p2 = new BoardPanel(withComputer ? "PLANSZA PRZECIWNIKA" : "Gracz 2");
		bottomPanel.add(p1);
		bottomPanel.add(p2);
		
		add(bottomPanel, BorderLayout.CENTER);
		
		setShipsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(withComputer)
				{
					p1.showShips();
					playButton.setEnabled(true);
				}
				else
				{
					//jesli gracz nr kliknal w ustaw to daj mu opcje zakonczenia ustawiania
					if(setShipsButton.getText().contains("1") && setShipsButton.getText().contains("Ustaw"))
					{
						setShipsButton.setText("Zakoncz ustawianie - Gracz 1");
						p1.showShips();
					} //jesli gracz 1 kliknal w zakonczenie ustawiania to nadaj opcje ustawienai stakow dla drugiego gracza
					else if(setShipsButton.getText().contains("1") && setShipsButton.getText().contains("Zakoncz"))
					{
						setShipsButton.setText("Ustaw - Gracz 2");
						p1.hideShips();
					}//jesli drugi gracz klikal w opcje ustawienia statkow to daj mu opcje zakonczenia ustawiania
					else if(setShipsButton.getText().contains("2") && setShipsButton.getText().contains("Ustaw"))
					{
						setShipsButton.setText("Zakoncz ustawianie - Gracz 2");
						p2.showShips();
					}//jesli drugi gracz zakonczyl ustawianie to aktywuj przycisk rozpoczenia gry
					else if(setShipsButton.getText().contains("2") && setShipsButton.getText().contains("Zakoncz"))
					{
						setShipsButton.setEnabled(false);
						playButton.setEnabled(true);
						p2.hideShips();
					}
				}
			}
		});
		
		playButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gameMode = PLAY_GAME;
				setShipsButton.setEnabled(false);
				playButton.setEnabled(false);

				p1.setBoardMode(BoardPanel.LOCKED); //w przypadku gru z komputerem plansza nr 1 wyswietla nasze statki, w przypadku gry z graczem plansza nr 1 jest zablokowana poniewaz najpierw strzela gracz nr 1 w plansze gracza nr 2
				p2.setBoardMode(BoardPanel.ATTACK);

			}
		});
		
		//w przypadku gry z komputerem powinnimy dodac akcje ktora wykonuje strzal komputera od razu po strzale gracza
		if(withComputer)
		{
			p2.setAttackListener(new AttackListener() {
				
				@Override
				public void attackPerformed() {
					// TODO Auto-generated method stub
					Random rand = new Random();
					//strzelaj tak dlugo az trafi albo w pudlo albo w statek
					while(p1.attack(rand.nextInt(0,  Board.BOARD_SIZE), rand.nextInt(0, Board.BOARD_SIZE)) == Board.FAIL)
					{
					}
				}
			});
		}
		else
		{
			p1.setAttackListener(new AttackListener() {
				
				@Override
				public void attackPerformed() {
					//po strzale w plansze nr 1 bedziemy oddawac strzal do planszy nr 2
					p1.setBoardMode(BoardPanel.LOCKED);
					p2.setBoardMode(BoardPanel.ATTACK);
				}
			});
			
			p2.setAttackListener(new AttackListener() {
				
				@Override
				public void attackPerformed() {
					//po strzale w plansze nr 2 bedziemy oddawac strzal do planszy nr 1
					p1.setBoardMode(BoardPanel.ATTACK);
					p2.setBoardMode(BoardPanel.LOCKED);
				}
			});
		}
		
		
		this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
            	Runtime.getRuntime().exit(1);
            }
        });
			
		p1.setGameOverListener(this);
		p2.setGameOverListener(this);
	}

	@Override
	public void gameOverPerformed(BoardPanel failedBoard) {
		String winner = "";
		if(failedBoard == p1)
		{
			winner = (withComputer ? "komputer" : "gracz nr 2");
		}
		else if(failedBoard == p2)
		{
			winner = (withComputer ? "gracz" : "gracz nr 1");
		}
		JOptionPane.showMessageDialog(this, "Koniec gry, wygrywa " + winner);
		MenuFrame menu;
		try {
			menu = new MenuFrame();
			menu.setVisible(true);
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dispose();
	}


}
