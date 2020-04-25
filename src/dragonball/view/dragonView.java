package dragonball.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import dragonball.controller.gameGUI;

public class dragonView extends JPanel 
{
	private ImageIcon background;
	private JLabel header;
	private JList wishes;
	private JButton worldMode;
	private JButton choose;
	
	public dragonView(gameGUI gui)
	{
		setLayout(null);
		background = new ImageIcon("dragonHeader.jpg");
		header = new JLabel(background);
		header.setSize(1200, 1080);
		
		wishes = new JList<>();
		wishes.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		wishes.setLayoutOrientation(JList.VERTICAL);
		wishes.setVisibleRowCount(-1);
		wishes.setBounds(50, 100, 200, 150);
		
		choose = new JButton("Choose wish");
		choose.setBounds(50, 250, 150, 30);
		choose.setActionCommand("Choose wish");
		choose.addActionListener(gui);
		
		worldMode = new JButton("Back to world");
		worldMode.setBounds(10, 10, 150, 30);
		worldMode.setActionCommand("Back to world mode Dragon");
		worldMode.addActionListener(gui);
		
		add(wishes);
		add(worldMode);
		add(choose);
		add(header);
		
	}
	
	public void setWishess(String[] w)
	{
		wishes.removeAll();
		wishes.setListData(w);
	}
	
	public int getWishh()
	{
		return  wishes.getSelectedIndex();
	}
	public String getWishToString()
	{
		return (String)wishes.getSelectedValue();
	}

}
