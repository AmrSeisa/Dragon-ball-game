package dragonball.view;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import dragonball.controller.gameGUI;

public class upgradeFighter extends JPanel
{
	private ImageIcon background;
	private JLabel header;
	private JButton worldM;
	private JLabel fightersA;
	private JRadioButton maxHealthPoints;
	private JRadioButton physicalDamage;
	private JRadioButton blastDamage;
	private JRadioButton maxKi;
	private JRadioButton maxStamina;
	private ButtonGroup upgradeGroup;
	private JButton upgrade;
	private JLabel chooseA;
	
	public upgradeFighter(gameGUI gui)
	{
		setLayout(null);
		background = new ImageIcon("worldHeader.jpg");
		header = new JLabel(background);
		header.setSize(1200, 1080);
		
		worldM= new JButton("Back to world");
		worldM.setBounds(10, 10, 150, 30);
		worldM.setActionCommand("Back to world");
		worldM.addActionListener(gui);
		
		chooseA = new JLabel("Choose one of the following to upgrade");
		chooseA.setBounds(20, 50, 300, 30);
		
		maxHealthPoints = new JRadioButton("Max health");
		maxHealthPoints.setActionCommand("H");
		maxHealthPoints.setBounds(20, 80, 150, 25);
		
		blastDamage = new JRadioButton("Blast Damage");
		blastDamage.setActionCommand("B");
		blastDamage.setBounds(20, 105, 150, 25);
		
		physicalDamage = new JRadioButton("Physical Damage");
		physicalDamage.setActionCommand("P");
		physicalDamage.setBounds(20, 130, 150, 25);
		
		maxKi = new JRadioButton("Max Ki");
		maxKi.setActionCommand("K");
		maxKi.setBounds(20, 155, 150, 25);
		
		maxStamina = new JRadioButton("Max Stamina");
		maxStamina.setActionCommand("S");
		maxStamina.setBounds(20, 180, 150, 25);
		
		upgrade = new JButton("Upgrade");
		upgrade.setActionCommand("Upgrade");
		upgrade.addActionListener(gui);
		upgrade.setBounds(20, 205, 100, 25);
		
		upgradeGroup = new ButtonGroup();
		upgradeGroup.add(maxHealthPoints);
		upgradeGroup.add(blastDamage);
		upgradeGroup.add(physicalDamage);
		upgradeGroup.add(maxKi);
		upgradeGroup.add(maxStamina);
		
		
		fightersA = new JLabel("default");
		fightersA.setBounds(300, 50, 300, 150);
		fightersA.setLayout(null);
		
		
		add(fightersA);
		add(upgrade);
		add(physicalDamage);
		add(maxKi);
		add(maxStamina);
		add(blastDamage);
		add(maxHealthPoints);
		add(chooseA);
		add(worldM);
		add(header);

	}
	
	public void setFighterA(String fName,int maxH , int phys , int blast , int maxK , int maxS,int absPoints)
	{
		String s = "<html>Fighter Name: " + fName +"<br>Max Health points: "+ maxH + "<br>Physical Damage: " + phys+"<br>Blast Damage: "+ blast +"<br>Max Ki: "+ maxK + "<br>Max Stamina: "+ maxS+"<br>Ability points: "+ absPoints+"</html>";
		fightersA.setText(s);
		
	}
	
	public String getUpgrade()
	{
		try
		{
			return (String)upgradeGroup.getSelection().getActionCommand();
		}catch(NullPointerException e)
		{
			JOptionPane.showMessageDialog(this, "You must choose an attribute to upgrade");
		}return null;
	}
	

}
