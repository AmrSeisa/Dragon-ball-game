package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dragonball.controller.gameGUI;


public class newFighter extends JPanel
{
	//private gameView GameView;
	private JTextField nameField;
	private JTextField fighterField;
	private JLabel nameLabel;
	private JLabel fighterName;
	private JLabel raceLabel;
	private JLabel newF;
	private JLabel picLabel;
	private JButton okBtn;
	private JRadioButton Earthling;
	private JRadioButton Frieza;
	private JRadioButton Majin;
	private JRadioButton Namekian;
	private JRadioButton Saiyan;
	private ButtonGroup raceGroup;
	private JLabel HeaderLabel;
	private ImageIcon backgroundImage;
	private ImageIcon raceIcon;

	
	public newFighter(gameGUI gui)
	{

	
		setLayout(null);
		
		backgroundImage = new ImageIcon ("header.jpg");
		HeaderLabel = new JLabel (backgroundImage);		    
		HeaderLabel.setBounds(0,0, 1200, 1080);
		
		okBtn = new JButton("Start");
		okBtn.setBounds(200, 325, 75, 30);
		okBtn.setActionCommand("start");
		okBtn.addActionListener(gui);
		//okBtn.addActionListener(l); // worldGUI

		
		nameLabel = new JLabel("Name: ");
		nameLabel.setBounds(150, 150, 50, 25);

		nameField = new JTextField("player");
		nameField.setBounds(200, 150, 100, 25);
		
		fighterName = new JLabel("Fighter: ");
		fighterName.setBounds(150, 175, 70, 25);
		
		fighterField = new JTextField("figter");
		fighterField.setBounds(200, 175, 100, 25);
		
		
		raceLabel = new JLabel("Race: ");
		raceLabel.setBounds(150, 200, 50, 25);
		
		Earthling = new JRadioButton("Earthling");
		Earthling.setActionCommand("Earthling");
		Earthling.setBounds(200, 200, 100, 25);
		
		/*
		raceIcon = new ImageIcon("Earthlings.png");
		picLabel = new JLabel(raceIcon);
		picLabel.setBounds(300, 225, 184, 235);
		picLabel.setVisible(false);  
		
		//Icon
		
		
		Earthling.addItemListener(new ItemListener() {
			
			@Override			
			public void itemStateChanged(ItemEvent e) {
				picLabel.setVisible(true);
				
			}
		});*/
		
		//Frieza = new JRadioButton("Frieza", new ImageIcon("Frieza.png"));
		Frieza = new JRadioButton("Frieza");
		Frieza.setActionCommand("Frieza");
		Frieza.setBounds(200, 225, 100, 25);
		raceIcon = new ImageIcon("Frieza.png");
		picLabel = new JLabel(raceIcon);
		picLabel.setBounds(300, 225, 184, 235);
		picLabel.setVisible(false);
		
		
		
		
		//Majin = new JRadioButton("Majin", new ImageIcon("Majin.png"));
		Majin = new JRadioButton("Majin");
		Majin.setActionCommand("Majin");
		Majin.setBounds(200, 250, 100, 25);
		//Namekian = new JRadioButton("Namekian" , new ImageIcon("Namekian.png"));
		Namekian = new JRadioButton("Namekian");
		Namekian.setActionCommand("Namekian");
		Namekian.setBounds(200, 275, 100,25);
		//Saiyan = new JRadioButton("Saiyan" , new ImageIcon("Saiyan.png"));
		Saiyan = new JRadioButton("Saiyan");
		Saiyan.setActionCommand("Saiyan");
		Saiyan.setBounds(200, 300, 100, 25);
	
		
		raceGroup = new ButtonGroup();
		raceGroup.add(Earthling);
		raceGroup.add(Frieza);
		raceGroup.add(Majin);
		raceGroup.add(Namekian);
		raceGroup.add(Saiyan);
		
		
		//add(newF,BorderLayout.NORTH);
		add(nameLabel);
		add(fighterName);
		add(fighterField);
		add(raceLabel);
		add(nameField);
		add(Earthling);
		add(Frieza);
		add(Majin);
		add(Namekian);
		add(Saiyan);
		add(picLabel);
		add(okBtn);
		add(HeaderLabel);

		
		
		
		//this.setVisible(true);
		
	}
	
	protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = newFighter.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
        }
	
	public String getName()
	{
		try{
			return nameField.getText();
		}catch(NullPointerException e)
		{
			JOptionPane.showMessageDialog(this, "Enter your name");
		}return null;
	}
	
	public JPanel getPanel() {
		return this;
	}
	
	public String getRace()
	{
		try
		{
			return raceGroup.getSelection().getActionCommand();
		}catch(NullPointerException e)
		{
			JOptionPane.showMessageDialog(this, "You must choose a fighter");
		}return null;
	}
	public String getFighterName() {
		try
		{
			return fighterField.getText();
		}catch(NullPointerException e)
		{
			JOptionPane.showMessageDialog(this, "you must set a name to the fighter");
		}return null;
	}

	public void setFighterName(JLabel fighterName) {
		this.fighterName = fighterName;
	}

	/*
	public void startGame()
	{
		new newFighter(new gameGUI());
	}*/



	
	/*
	public static void main(String [] args)
	{
		newFighter nf = new newFighter();
		nf.setVisible(true);
		gameView g = new gameView(new gameGUI());
		g.add(nf);
		
	}*/

	

}
