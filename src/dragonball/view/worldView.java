package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JToolBar;

import dragonball.controller.gameGUI;
import dragonball.model.world.World;

public class worldView extends JPanel
{

	private JLabel myLabel;
	private JLabel HeaderLabel;
	private JMenuBar saveBar;
	private JButton saveBtn;
	private ImageIcon backgroundImage;
	private JLabel l;
	private JLabel map;
	private JLabel playerInfo;	
	private JButton newFighterBtn;
	private JButton upgradeBtn;
	
	public worldView(gameGUI gui)
	{
		
		
		backgroundImage = new ImageIcon ("worldHeader.jpg");
		HeaderLabel = new JLabel (backgroundImage);		    
		HeaderLabel.setSize(1200,1080);
		
		setLayout(null);
		
		this.map = new JLabel();
		map.setSize(600,800);
		map.setLayout(new GridLayout(10,10));
		
		playerInfo = new JLabel("default");
		playerInfo.setBounds(700, 0, 600, 100);
		playerInfo.setLayout(null);
		
		
		
		
		for(int i = 0 ; i<100 ; i++)
		{
			JLabel l = new JLabel(new ImageIcon("cell.jpg"));
			l.setName(i+"");
			if(i==0)
				l.setIcon(new ImageIcon("foe.jpg"));
			if(i==99)
				l.setIcon(new ImageIcon("daz1-02 copy.jpg"));
			
				
			map.add(l);
			
		}
		
		saveBar = new JMenuBar();
		saveBtn = new JButton("Save game");
		saveBar.add(saveBtn);
		//info.add(saveBar,BorderLayout.PAGE_START);
		
		myLabel = new JLabel();
		myLabel.setBounds(700, 300, 100, 20);
		myLabel.setLayout(null);
		
		newFighterBtn = new JButton("Fighters properties");
		newFighterBtn.setBounds(700, 300, 150, 23);
		newFighterBtn.setActionCommand("Fighters properties");
		newFighterBtn.addActionListener(gui);
		newFighterBtn.setFocusable(false);
		
		upgradeBtn = new JButton("Upgrade Fighter");
		upgradeBtn.setBounds(700, 325, 150, 23);
		upgradeBtn.setActionCommand("Upgrade Fighter");
		upgradeBtn.addActionListener(gui);
		upgradeBtn.setFocusable(false);
		
				
//		add(myLabel);
		add(upgradeBtn);
		add(newFighterBtn);
		add(playerInfo);
		add(map,BorderLayout.WEST);
		add(HeaderLabel);
		//add(menuBar(),BorderLayout.NORTH);
	}
	/*
	 private JMenuBar menuBar() {
			//JPanel menuPanel = new JPanel();
			setLayout(new FlowLayout());
			
			JMenuBar menuBar = new JMenuBar();
			JMenu menu = new JMenu("My Menu");
			//menu.setMnemonic(KeyEvent.VK_A);
			menu.getAccessibleContext().setAccessibleDescription("My Items");
			menuBar.add(menu);
			//add your items to the menu

			return menuBar;
		  }
	*/
	public JLabel getPlayerLabel(int iz)
	{
		return (JLabel) getComponent(iz);
	}
	
	public void movePlayer(int iz,int xz)
	{
		JLabel y = new JLabel();
		y = (JLabel) map.getComponent(iz);
		y.setIcon(new ImageIcon("cell.jpg"));
		JLabel m = new JLabel();
		m = (JLabel) map.getComponent(xz);
		m.setIcon(new ImageIcon("daz1-02 copy.jpg"));
	}
	
	public void setPlayerInfo(String playerName , String fighterName , int level , int senzuBeans , int DragonBalls)
	{
		String s = "<html>Player name: " + playerName +"<br>Current fighter name: " + fighterName + "<br>Level: " + level + "<br>Senzu beans: "  + senzuBeans + "<br>Dragon Ball :" + DragonBalls +"</html>" ;
		playerInfo.setText(s);
		
	}
	
	
	
	
}
