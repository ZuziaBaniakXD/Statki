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
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MenuFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	

	JPanel titlePanel, menuPanel, shipPanel;
	JButton buttonAi, buttonVs;
	JLabel etykieta, trash1, trash2, title;
	Color menuColor, titleColor;
	
	

	public MenuFrame() throws HeadlessException, IOException {
		
		this.setSize(800, 600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        
        titlePanel = new JPanel();
        menuPanel = new JPanel();
        shipPanel = new JPanel();
        
        
        titlePanel.setLayout(new GridLayout(2,1));
        menuPanel.setLayout(new GridLayout(7,1));
        
        
    
   
        BufferedImage img = null;
        try {

        img = ImageIO.read(new File("image/stateczek.jpg"));
      
        Image newImage = img.getScaledInstance(640, 490, Image.SCALE_SMOOTH);

        JLabel label = new JLabel(new ImageIcon(newImage));
        shipPanel.add(label);
        }
        catch (IOException e) {
        	
        }

        menuColor = new Color(224, 224, 224);
        titleColor = new Color(169, 169, 169);
        
        
        
        
        etykieta = new JLabel("Menu", SwingConstants.CENTER);
        etykieta.setFont(new Font("Serif", Font.PLAIN, 20));
        
        
        trash1 = new JLabel();
        trash2 = new JLabel();
        
        
        buttonAi = new JButton("Przeciwko AI");
        buttonVs = new JButton("Przeciwko graczowi");
        
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
        
        
        
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(menuPanel, BorderLayout.EAST);
        this.add(shipPanel, BorderLayout.WEST);
        
		
	}
	
	
	public static void main(String[] args) throws HeadlessException, IOException {
		MenuFrame menuFrame = new MenuFrame();
		menuFrame.setVisible(true);
	}
		

	
}