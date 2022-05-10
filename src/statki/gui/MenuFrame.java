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

public class MenuFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	

	JPanel titlePanel, menuPanel, shipPanel;
	JButton buttonAi, buttonVs;
	JLabel etykieta, trash1, trash2, trash3, trash4, trash5, title;
	Color menuColor, titleColor;
	JRadioButton buttonSound;
	int a;
	
	
	

	public MenuFrame() throws HeadlessException, IOException {
		
		this.setSize(650, 600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        
        titlePanel = new JPanel();
        menuPanel = new JPanel();
        shipPanel = new JPanel();
        
        
        
        titlePanel.setLayout(new GridLayout(2,1));
        menuPanel.setLayout(new GridLayout(9,1));
        
        
        
        
      
     
        ImageIcon icon = new ImageIcon("zdjecia/stateczek1.png"); //za mała ikona, w internecie nie ma za bardzo rozwiązań
        this.setIconImage(icon.getImage());

    
  
        ImageIcon iconu = new ImageIcon("zdjecia/statek.gif");
		JLabel label = new JLabel();
		label.setIcon(iconu);
	
      // Image newImage = img.getScaledInstance(640, 490, Image.SCALE_SMOOTH);
     
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
        buttonSound = new JRadioButton("Muzyka");
        
        buttonAi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameFrame gameframe = new GameFrame();
				gameframe.setLocationRelativeTo(null);
				gameframe.setVisible(true);
				setVisible(false);
			}
		});
        
        buttonVs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameFrame gameframe = new GameFrame();
				gameframe.setLocationRelativeTo(null);
				gameframe.setVisible(true);
				setVisible(false);
			}
		});
       
       // buttonSound.addActionListener(new ActionListener() {
        	
        	//public void actionPerformed(ActionEvent arg0) {
				//	try {
					//	Sound sound = new Sound();
				//	} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				//		
				//		e.printStackTrace();
				//	}
			
        //	}
      //  });
        
        
        buttonSound.setBackground(menuColor);
        title = new JLabel("Gra w Statki", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.PLAIN, 28));


        
        
        menuPanel.setBackground(menuColor);
        titlePanel.setBackground(titleColor);
        
        titlePanel.add(title);
        
        menuPanel.add(etykieta);
        menuPanel.add(trash1);
        menuPanel.add(buttonVs);
        menuPanel.add(trash2);
        menuPanel.add(buttonAi);
        menuPanel.add(trash3);
        menuPanel.add(trash4);
        menuPanel.add(trash5);
        menuPanel.add(buttonSound);
        
        
        
        
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(menuPanel, BorderLayout.EAST);
        this.add(shipPanel, BorderLayout.WEST);
        
		
	}	
	
	public static void main(String[] args) throws HeadlessException, IOException {
		MenuFrame menuFrame = new MenuFrame();
		menuFrame.setLocationRelativeTo(null);
		menuFrame.setResizable(false);
		menuFrame.setVisible(true);
	}
		

	
}
