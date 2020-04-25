package dragonball.view;

import javafx.scene.paint.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

import dragonball.controller.gameGUI;
import dragonball.model.attack.Attack;
import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.UnknownAttackTypeException;

public class battleView extends JPanel
{
	private ImageIcon background;
	private JLabel header;
	private JTextArea fighterInfo;
	private JTextArea opponentInfo;
	private JTextArea move1;
	private JTextArea move2;
	private JButton attack;
	private JButton block;
	private JButton use;
	private JList attacks;
	private JTextArea turn;
	
	public battleView(gameGUI gui) throws MissingFieldException, UnknownAttackTypeException
	{
		
		 
		setLayout(null);
		background = new ImageIcon("battleHeader.jpg");
		header = new JLabel(background);
		header.setSize(1200, 1080);
		
		fighterInfo = new JTextArea();
		fighterInfo.setBounds(10, 50, 200, 150);
		//fighterInfo.setBackground(Color.TURQUOISE);
		opponentInfo = new JTextArea();
		opponentInfo .setBounds(1000, 50, 200, 150);
		
		move1 = new JTextArea();
		move1.setBounds(400, 550, 200, 100);
		
		move2 = new JTextArea();
		move2.setBounds(800, 550, 200, 100);
		
		turn = new JTextArea();
		turn.setBounds(600, 200, 100,100);
		
		attack = new JButton("Attack");
		attack.setBounds(10, 550, 75, 30);
		attack.setActionCommand("Attack");
		attack.addActionListener(gui);
		
		block = new JButton("Block");
		block.setBounds(10, 580, 75, 30);
		block.setActionCommand("Block");
		block.addActionListener(gui);
		
		use = new JButton("Use");
		use.setBounds(10, 610, 75, 30);
		use.setActionCommand("Use");
		use.addActionListener(gui);
		
		attacks = new JList<>();
		attacks.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		attacks.setLayoutOrientation(JList.VERTICAL);
		attacks.setVisibleRowCount(-1);
		attacks.setBounds(88, 550, 100, 200);
		
		
		add(turn);
		add(move1);
		add(move2);
		add(attack);
		add(use);
		add(block);
		add(attacks);
		add(opponentInfo);
		add(fighterInfo);
		add(header);
	}
	
	public void setFighterInfo(String name , int level , int maxHealthPoints , int healthPoints , int maxKi , int currentKi , int maxStamia , int currentStamina)
	{
		fighterInfo.setText("Name: " + name +"\nLevel: "+level+"\nMax Health points: " + maxHealthPoints + "\nHealth points: " + healthPoints +
				"\nMax ki: " + maxKi + "\nKi: " + currentKi+"\nMax Stamina: " + maxStamia + "\nStamina:" + currentStamina);
	}

	public JTextArea getFighterInfo() {
		return fighterInfo;
	}
	
	public void setOpponentInfo(String name , int level , int maxHealthPoints , int healthPoints , int maxKi , int currentKi , int maxStamia , int currentStamina)
	{
		opponentInfo.setText("Name: " + name +"\nLevel: "+level+"\nMax Health points: " + maxHealthPoints + "\nHealth points: " + healthPoints +
				"\nMax ki: " + maxKi + "\nKi: " + currentKi+"\nMax Stamina: " + maxStamia + "\nStamina:" + currentStamina);
	}

	public void setFighterInfo(JTextArea fighterInfo) {
		this.fighterInfo = fighterInfo;
	}
	
	public void setAttacks(String[] a)
	{
		attacks.setListData(a);
	}
	
	public int getAttackIndex()
	{
		return attacks.getSelectedIndex();
	}
	
	public void setMove1(String m)
	{
		move1.setText(m);
	}
	public void setMove2(String m)
	{
		move2.setText(m);
	}
	
	public void setTurn(String m)
	{
		turn.setText(m);
	}
	

}
