package statki.gui;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameFrame extends JFrame {

	public GameFrame() throws HeadlessException {
		setSize(800, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		JPanel upPanel = new JPanel();
		JButton setShipsButton = new JButton("Ustaw");
		upPanel.add(setShipsButton);
		JButton playButton = new JButton("Zagraj");
		upPanel.add(playButton);
		JButton MusicButton = new JButton("Wlacz dzwiek");
		upPanel.add(MusicButton);
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
			}
		});
	}


}
