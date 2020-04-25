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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import dragonball.controller.gameGUI;


public class fighterOptions extends JPanel
{
	//private gameView GameView;
	private JTextField fighterField;
	private JLabel fighterName;
	private JLabel raceLabel;
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
	private JComboBox fighters;
	private JButton selectFighter;
	private JButton worldMode;
	private JList unlockedAttacks;
	private JList fightersAttacks;
	private JButton addAttackBtn;

	
	public fighterOptions(gameGUI gui)
	{

	
		setLayout(null);
		
		backgroundImage = new ImageIcon ("header.jpg");
		HeaderLabel = new JLabel (backgroundImage);		    
		HeaderLabel.setBounds(0,0, 1200, 1080);
		
		okBtn = new JButton("Add");
		okBtn.setBounds(140, 200, 75, 30);
		okBtn.setActionCommand("Add");
		okBtn.addActionListener(gui);
		//okBtn.addActionListener(l); // worldGUI

		
		fighterName = new JLabel("Fighter name: ");
		fighterName.setBounds(50, 50, 100, 25);
		
		fighterField = new JTextField();
		fighterField.setBounds(140, 50, 100, 25);
		
		
		raceLabel = new JLabel("Race: ");
		raceLabel.setBounds(50, 75, 50, 25);
		
		Earthling = new JRadioButton("Earthling");
		Earthling.setActionCommand("Earthling");
		Earthling.setBounds(140, 75, 100, 25);
		
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
		Frieza.setBounds(140, 100, 100, 25);
		raceIcon = new ImageIcon("Frieza.png");
		picLabel = new JLabel(raceIcon);
		picLabel.setBounds(300, 225, 184, 235);
		picLabel.setVisible(false);
		
		
		
		
		//Majin = new JRadioButton("Majin", new ImageIcon("Majin.png"));
		Majin = new JRadioButton("Majin");
		Majin.setActionCommand("Majin");
		Majin.setBounds(140, 125, 100, 25);
		//Namekian = new JRadioButton("Namekian" , new ImageIcon("Namekian.png"));
		Namekian = new JRadioButton("Namekian");
		Namekian.setActionCommand("Namekian");
		Namekian.setBounds(140, 150, 100,25);
		//Saiyan = new JRadioButton("Saiyan" , new ImageIcon("Saiyan.png"));
		Saiyan = new JRadioButton("Saiyan");
		Saiyan.setActionCommand("Saiyan");
		Saiyan.setBounds(140, 175, 100, 25);
		
		raceGroup = new ButtonGroup();
		raceGroup.add(Earthling);
		raceGroup.add(Frieza);
		raceGroup.add(Majin);
		raceGroup.add(Namekian);
		raceGroup.add(Saiyan);
		
		fighters = new JComboBox<>();
		fighters.setSelectedIndex(-1);
		fighters.setBounds(50, 250, 150, 25);
		
		selectFighter = new JButton("Select this fighter");
		selectFighter.setBounds(50, 275, 150, 30);
		selectFighter.setActionCommand("Select this fighter");
		selectFighter.addActionListener(gui);
		
		worldMode = new JButton("Back to world");
		worldMode.setBounds(10, 10, 150, 30);
		worldMode.setActionCommand("Back to world mode");
		worldMode.addActionListener(gui);
		
		unlockedAttacks = new JList<>();
		unlockedAttacks.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		unlockedAttacks.setLayoutOrientation(JList.VERTICAL);
		unlockedAttacks.setVisibleRowCount(-1);
		unlockedAttacks.setBounds(50, 350, 100, 150);
		

		fightersAttacks = new JList<>();
		fightersAttacks.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		fightersAttacks.setLayoutOrientation(JList.VERTICAL);
		fightersAttacks.setVisibleRowCount(-1);
		fightersAttacks.setBounds(250, 350, 100, 150);
		
		addAttackBtn = new JButton("Add attack");
		addAttackBtn.setActionCommand("Add attack");
		addAttackBtn.addActionListener(gui);
		addAttackBtn.setBounds(150, 415, 100, 30);
		
		
		
		//add(newF,BorderLayout.NORTH);
		add(addAttackBtn);
		add(unlockedAttacks);
		add(fightersAttacks);
		add(worldMode);
		add(selectFighter);
		add(fighters);
		add(fighterName);
		add(fighterField);
		add(raceLabel);
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
        java.net.URL imgURL = fighterOptions.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
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
			JOptionPane.showMessageDialog(this, "you must choose a fighter");
		}
		return null;
	}
	public String getFighterName() {
		try
		{
			return fighterField.getText();
		}catch(NullPointerException e)
		{
			JOptionPane.showMessageDialog(this, "you must choose a name");
		}
		return null;
	}

	public void setFighterName(JLabel fighterName) {
		this.fighterName = fighterName;
	}
	
	public void setFightersList(String [] a)
	{
		fighters.removeAllItems();
		for(int i = 0 ; i<a.length ; i++)
			fighters.addItem(a[i]);
	}
	
	public int getFighterIndex()
	{
		return fighters.getSelectedIndex();
	}
	
	public void setUnlockedAttacks(String [] a)
	{
		unlockedAttacks.removeAll();
		unlockedAttacks.setListData(a);
	}
	public void setFighterAttack(String [] a)
	{
		fightersAttacks.removeAll();
		fightersAttacks.setListData(a);
	}
	
	public String getAttackName()
	{
		return (String)unlockedAttacks.getSelectedValue();
	}
	public int getFightersAttackSize()
	{
		return fightersAttacks.getModel().getSize();
	}
	public int getUnlockedAttacks()
	{
		return unlockedAttacks.getModel().getSize();
	}
	
	public String getFighterAttack()
	{
		return (String) fightersAttacks.getSelectedValue();
	}


	
	/*
	public static void main(String [] args)
	{
		newFighter nf = new newFighter();
		nf.setVisible(true);
		gameView g = new gameView(new gameGUI());
		g.add(nf);
		
	}*/

	

}
