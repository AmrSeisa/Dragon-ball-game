package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.sun.glass.events.WindowEvent;

import dragonball.controller.gameGUI;

public class gameView extends JFrame
{
	//private JFrame mainFrame;
	private ImageIcon backgroundImage;
	private JPanel start;
	private JLabel label;
	private JButton newGame;
	private JButton loadGame;
//	private gameGUI gameG;
	public gameView(gameGUI gui)
	{
		
		setTitle("Dragon Ball");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1200,1080);
		start = new JPanel();
		start.setLayout(null);
		//gameG = new gameGUI();
		
		backgroundImage = new ImageIcon ("header.jpg");
		label = new JLabel (backgroundImage);		    
		label.setBounds(0,0, 1200, 1080);
		
		newGame = new JButton("New Game");
		newGame.setBounds(150, 150, 100, 50);
		newGame.setActionCommand("New Game");
		newGame.addActionListener(gui);
		start.add(newGame);
		
		loadGame = new JButton("Load Game");
		loadGame.setBounds(150, 200, 100, 50);
		loadGame.setActionCommand("Load Game");
		loadGame.addActionListener(gui);
		start.add(loadGame);
		
		
//		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
//		addWindowListener(new WindowAdapter() {
//		    public void windowClosing(WindowEvent ev) {
//		    int confirm = JOptionPane.showOptionDialog(this,
//		        "Are you sure to end this game?",
//		        "Exit", JOptionPane.YES_NO_OPTION,
//		        JOptionPane.QUESTION_MESSAGE,null, null, null);
//		if (confirm == JOptionPane.YES_OPTION) {
//		    System.exit(1);
//		}
//		      }            
//		});
//		

		start.add(label);
	    add(start);
		
	}
	/*
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("New Game"))
		{
			newGame.setVisible(false);
			loadGame.setVisible(false);
			JTextField jtf = new JTextField();
			jtf.setBounds(150, 250, 100, 50);
			start.add(jtf);
			
		}
		
	}*/
	
	public JPanel getStart() 
	{
		return start;
	}


	public void setStart(JPanel panel) 
	{
		start.setVisible(false);
		this.start = panel;
	}

	
	
	public static void main(String []args) 
	{
		//gameView g = new gameView();
		//g.setVisible(true);
		
	
	}
}
