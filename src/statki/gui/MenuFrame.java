package statki.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.Media;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class MenuFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	

	JPanel titlePanel, menuPanel, shipPanel;
	JButton buttonAi, buttonVs;
	JLabel etykieta, trash1, trash2, trash3, trash4, trash5, title;
	Color menuColor, titleColor;
	final String inFileName = "amberkys.wav";
	static MenuFrame menuFrame;
	Sound musicLabel;
	JButton lang;
	boolean lChooser;
	int m=1;
	

	public MenuFrame(int mm) throws IOException {
		m=mm;
		this.setSize(650, 600);
		
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        
        titlePanel = new JPanel();
        menuPanel = new JPanel();
        shipPanel = new JPanel();
     
        
        
        titlePanel.setLayout(new GridLayout(2,1));
        menuPanel.setLayout(new GridLayout(9,1));
        
        
        
        
      
     
        ImageIcon icon = new ImageIcon("zdjecia/stateczek1.png");
        this.setIconImage(icon.getImage());

        ImageIcon iconu = new ImageIcon("zdjecia/statek.gif");
		JLabel label = new JLabel();
		label.setIcon(iconu);
	
		shipPanel.add(label);

        menuColor = new Color(224, 224, 224);
        titleColor = new Color(169, 169, 169);
        
        
        
        
        etykieta = new JLabel("Menu", SwingConstants.CENTER);
        etykieta.setFont(new Font("Serif", Font.PLAIN, 20));
        
        
        trash1 = new JLabel();
        trash2 = new JLabel();
        trash3 = new JLabel();
        trash4 = new JLabel();
        trash5 = new JLabel();
        
        buttonAi = new JButton("Przeciwko AI");
        buttonVs = new JButton("Przeciwko graczowi");
       
                
        buttonAi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameFrame gameframe = new GameFrame(true, m, musicLabel.audioClip, musicLabel.n);
				gameframe.setLocationRelativeTo(null);
				gameframe.setVisible(true);
				menuFrame.dispose();
			}
		});
        
        buttonVs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameFrame gameframe = new GameFrame(false, m, musicLabel.audioClip, musicLabel.n);
				gameframe.setLocationRelativeTo(null);
				gameframe.setVisible(true);
				menuFrame.dispose();
			}
		});
       
        
        musicLabel = new Sound(0);
        
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
            	Runtime.getRuntime().exit(1);
            }
        });


        
        
        title = new JLabel("Gra w Statki", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.PLAIN, 28));


        
        
        menuPanel.setBackground(menuColor);
        titlePanel.setBackground(titleColor);
        
        titlePanel.add(title);
        
        
        lang = new JButton("Język");
        
        
        lang.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(m % 2 == 0)
				{
					lChooser = true;
					title.setText("Gra w Statki");
					lang.setText("Język");
					buttonAi.setText("Przeciwko AI");
					buttonVs.setText("Przeciwko graczowi");
					musicLabel.playbutton.setText("Włącz muzykę");
					musicLabel.pausebutton.setText("Wyłącz muzykę");
					menuFrame.setVisible(false);
					menuFrame.setVisible(true);
					m++;
				}
				else if(m % 2 == 1)
				{
					lChooser = false;
					title.setText("Battleships");
					lang.setText("Language");
					buttonAi.setText("Against AI");
					buttonVs.setText("Against player");
					musicLabel.playbutton.setText("Turn on the music");
					musicLabel.pausebutton.setText("Turn off the music");
					menuPanel.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);
					menuFrame.setVisible(false);
					menuFrame.setVisible(true);
					m++;
				}
			}
		});

        
        
        menuPanel.add(etykieta);
        menuPanel.add(trash1);
        menuPanel.add(buttonVs);
        menuPanel.add(trash2);
        menuPanel.add(buttonAi);
        menuPanel.add(trash3);
        menuPanel.add(lang);
        menuPanel.add(trash4);
        menuPanel.add(musicLabel);
        
       
        
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(menuPanel, BorderLayout.EAST);
        this.add(shipPanel, BorderLayout.WEST);
        
		
	}	
	
	public static void main(String[] args) throws IOException {
		menuFrame = new MenuFrame(1);
		
		final String inFileName = "zdjecia/amberkys.wav";

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                Sound p = new Sound(0);
                p.play(inFileName);
            }
        });
       
		menuFrame.setLocationRelativeTo(null);
		menuFrame.setResizable(false);
		menuFrame.setVisible(true);
	}
		

	
}
