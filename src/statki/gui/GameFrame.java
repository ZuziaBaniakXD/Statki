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
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameFrame extends JFrame  {
	private static final int SET_SHIPS = 1;
	private static final int PLAY_GAME = 2;
	private int gameMode;
	
	public GameFrame() throws HeadlessException {
		gameMode = SET_SHIPS;
	
		setSize(800, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		JPanel upPanel = new JPanel();
		JButton setShipsButton = new JButton("Ustaw");
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
		
		BoardPanel p1 = new BoardPanel("TWOJA PLANSZA");
		BoardPanel p2 = new BoardPanel("PLANSZA PRZECIWNIKA");
		bottomPanel.add(p1);
		bottomPanel.add(p2);
		
		add(bottomPanel, BorderLayout.CENTER);
		
		setShipsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				p1.showShips();
				playButton.setEnabled(true);
			}
		});
		
		playButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gameMode = PLAY_GAME;
				setShipsButton.setEnabled(false);
				playButton.setEnabled(false);
				
				p1.setBoardMode(BoardPanel.LOCKED);
				p2.setBoardMode(BoardPanel.ATTACK);
			}
		});
		
		p2.setAttackListener(new AttackListener() {
			
			@Override
			public void attackPerformed() {
				// TODO Auto-generated method stub
				Random rand = new Random();
				while(p1.attack(rand.nextInt(0,  Board.BOARD_SIZE), rand.nextInt(0, Board.BOARD_SIZE)) == Board.FAIL)
				{
				}
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
            	Runtime.getRuntime().exit(1);
            }
        });

	}


}
