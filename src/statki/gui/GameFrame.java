package statki.gui;

import java.awt.BorderLayout;
import java.awt.Color;
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

public class GameFrame extends JFrame implements GameOverListener, SnakeIslandAttackListener  {
	private static final int SET_SHIPS = 1;
	private static final int PLAY_GAME = 2;
	private int gameMode;
	private boolean withComputer;
	private BoardPanel p1;
	private BoardPanel p2;
	String tust,tzak,t1, t2, t3, t4, t5, t6, t7, t8, t9, t11, t12, t13, t14, t15, t16;
	Sound musicLabel;
	int M;
	
	
	
	public GameFrame(boolean withComputer, int N, Clip audioClip, int SS) {
		gameMode = SET_SHIPS;
		this.withComputer = withComputer; //trzyma informacje czy gramy z komputerem (true) czy z innym graczem (false)
		setSize(800, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		musicLabel = new Sound(1);
		musicLabel.audioClip = audioClip;
		musicLabel.n = SS;
		
		
		
		M=N;
		
		if(N % 2 == 1)
		{
		tust = "Ustaw";
		t1 = "Ustaw - Gracz 1";
		t2 = "Zagraj";
		t3 = "TWOJA PLANSZA";
		t4 = "Gracz 1";
		t5 = "PLANSZA PRZECIWNIKA";
		t6 = "Gracz 2";
		t7 = "Zakończ ustawianie - Gracz 1";
		tzak = "Zakończ";
		t8= "Ustaw - Gracz 2";
		t9 = "Zakończ ustawianie - Gracz 2";
		t11 = "Komputer";
		t12 = "Gracz nr. 2";
		t13 = "Gracz";
		t14 = "Gracz nr. 1";
		t15 = "Koniec gry, wygrywa ";
		t16 = "";
		musicLabel.playbutton.setText("Włącz muzykę");
		musicLabel.pausebutton.setText("Wyłącz muzykę");
		}
		
		if(N % 2 == 0)
		{
		tust = "Set ships";
		t1 = "Set ships - Player 1";
		t2 = "Play";
		t3 = "YOUR BOARD";
		t4 = "Player 1";
		t5 = "OPPONENT'S BOARD";
		t6 = "Player 2";
		t7 = "Complete setting - Player 1";
		tzak = "Complete";
		t8= "Set ships - Player 2";
		t9 = "Complete setting - Player 2";
		t11 = "Computer";
		t12 = "Player 2";
		t13 = "Player";
		t14 = "Player 1";
		t15 = "End of the game, ";
		t16 = " wins";
		musicLabel.playbutton.setText("Turn on the music");
		musicLabel.pausebutton.setText("Turn off the music");

		}

		
		JPanel upPanel = new JPanel();
		JButton setShipsButton = new JButton(withComputer ? tust : t1);
		
		upPanel.add(setShipsButton);
		JButton playButton = new JButton(t2);
		playButton.setEnabled(false);
		upPanel.add(playButton);
		
		
		
		upPanel.add(musicLabel);
		
		
		add(upPanel, BorderLayout.NORTH);
		
		JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
		
		p1 = new BoardPanel(withComputer ? t3 : t4);
		p2 = new BoardPanel(withComputer ? t5 : t6);
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
					if(setShipsButton.getText().contains("1") && setShipsButton.getText().contains(tust))
					{
						setShipsButton.setText(t7);
						p1.showShips();
					} //jesli gracz 1 kliknal w zakonczenie ustawiania to nadaj opcje ustawienai stakow dla drugiego gracza
					else if(setShipsButton.getText().contains("1") && setShipsButton.getText().contains(t7))
					{
						setShipsButton.setText(t8);
						p1.hideShips();
					}//jesli drugi gracz klikal w opcje ustawienia statkow to daj mu opcje zakonczenia ustawiania
					else if(setShipsButton.getText().contains("2") && setShipsButton.getText().contains(tust))
					{
						setShipsButton.setText(t9);
						p2.showShips();
					}//jesli drugi gracz zakonczyl ustawianie to aktywuj przycisk rozpoczenia gry
					else if(setShipsButton.getText().contains("2") && setShipsButton.getText().contains(tzak))
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
		p1.setSnakeIslandAttackListener(this);
		p2.setSnakeIslandAttackListener(this);
	}

	@Override
	public void gameOverPerformed(BoardPanel failedBoard) {
		String winner = "";
		if(failedBoard == p1)
		{
			winner = (withComputer ? t11 : t12);
		}
		else if(failedBoard == p2)
		{
			winner = (withComputer ? t13 : t14);
		}
		JOptionPane.showMessageDialog(this, t15 + winner + t16);
		MenuFrame menuFrame;
		try {
			menuFrame = new MenuFrame(M);
			menuFrame.setLocationRelativeTo(null);
			menuFrame.setResizable(false);
			menuFrame.setVisible(true);
			
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dispose();
	}

	@Override
	public void snakeIslandAttackPerformed(BoardPanel defenderBoard) {
		if(p1 != defenderBoard)
		{
			p1.attackRandomShip();
		}
		else if(p2 != defenderBoard)
		{
			p2.attackRandomShip();
		}
		JOptionPane.showMessageDialog(this, "Zaatakowano wyspe wezy!");
	}


}
