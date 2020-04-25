package dragonball.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import dragonball.view.battleView;
import dragonball.view.dragonView;
import dragonball.view.fighterOptions;
import dragonball.view.newFighter;
import dragonball.view.upgradeFighter;
import dragonball.view.worldView;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dragonball.model.attack.Attack;
import dragonball.model.battle.BattleEvent;
import dragonball.model.battle.BattleListener;
import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.dragon.Dragon;
import dragonball.model.dragon.DragonWishType;
import dragonball.model.exceptions.DuplicateAttackException;
import dragonball.model.exceptions.MaximumAttacksLearnedException;
import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.NotASaiyanException;
import dragonball.model.exceptions.NotEnoughAbilityPointsException;
import dragonball.model.exceptions.NotEnoughKiException;
import dragonball.model.exceptions.NotEnoughSenzuBeansException;
import dragonball.model.exceptions.UnknownAttackTypeException;
import dragonball.model.battle.*;
import dragonball.model.game.Game;
import dragonball.model.game.GameListener;
import dragonball.view.gameView;

public class gameGUI implements ActionListener , GameListener
{
	private Game game;
	//private World world;
	private gameView GameView;
	private Battle battle;
	private newFighter nf;
	private worldView worldView;
	private battleView battleView;
	private String playerName;
	private String activeFighterName;
	private String startRace;
	private int currentPos;
	private int l;
	private fighterOptions fighterOptions;
	private upgradeFighter upgradeFighter;
	private dragonView dragonView;
	private Dragon dragons;
	
	
	public gameGUI() throws MissingFieldException, UnknownAttackTypeException
	{
		GameView = new gameView(this);
		GameView.setVisible(true);
		game = new Game();
		game.setListener(this);
		nf = new newFighter(this);
		battleView = new battleView(this);
			
		worldView = new worldView(this);
		
		fighterOptions = new fighterOptions(this);
		
		upgradeFighter = new upgradeFighter(this);
		
		dragonView = new dragonView(this);
		
//		worldView.setPlayerInfo(game.getPlayer().getName(), game.getPlayer().getActiveFighter().getName(), game.getPlayer().getActiveFighter().getLevel(), game.getPlayer().getSenzuBeans(),game.getPlayer().getDragonBalls());
		
		
//		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
//		addWindowListener(new WindowAdapter() {
//		    public void windowClosing(WindowEvent ev) {
//		    int confirm = JOptionPane.showOptionDialog(start,
//		        "Are you sure to end this game?",
//		        "Exit", JOptionPane.YES_NO_OPTION,
//		        JOptionPane.QUESTION_MESSAGE,null, null, null);
//		if (confirm == JOptionPane.YES_OPTION) {
//		    System.exit(1);
//		}
//		      }            
//		});
//		
		
		GameView.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(worldView.isVisible())
				{
				if(e.getKeyCode() == KeyEvent.VK_UP && game.getWorld().getPlayerRow()!=0) 
				{
					
					
					int iz = game.getWorld().getPlayerRow()*10 + game.getWorld().getPlayerColumn();
					game.getWorld().moveUp();
					currentPos = game.getWorld().getPlayerRow()*10 + game.getWorld().getPlayerColumn();
					worldView.movePlayer(iz,currentPos);					
				}else
					if(e.getKeyCode() == KeyEvent.VK_DOWN && game.getWorld().getPlayerRow()!=9)
					{
						int iz = game.getWorld().getPlayerRow()*10 + game.getWorld().getPlayerColumn();
						game.getWorld().moveDown();
						currentPos = game.getWorld().getPlayerRow()*10 + game.getWorld().getPlayerColumn();
						worldView.movePlayer(iz,currentPos);		
					}else
					if(e.getKeyCode() == KeyEvent.VK_RIGHT && game.getWorld().getPlayerColumn()!=9)
						{
						int iz = game.getWorld().getPlayerRow()*10 + game.getWorld().getPlayerColumn();
						game.getWorld().moveRight();
						currentPos = game.getWorld().getPlayerRow()*10 + game.getWorld().getPlayerColumn();
						worldView.movePlayer(iz,currentPos);		
						
					}else
						if(e.getKeyCode() == KeyEvent.VK_LEFT &&  game.getWorld().getPlayerColumn()!=0)
						{
							
							int iz = game.getWorld().getPlayerRow()*10 + game.getWorld().getPlayerColumn();
							game.getWorld().moveLeft();
							currentPos = game.getWorld().getPlayerRow()*10 + game.getWorld().getPlayerColumn();
							worldView.movePlayer(iz,currentPos);		
						}
				}}
		});
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("New Game"))
		{
			GameView.getStart().setVisible(false);
			GameView.add(nf.getPanel());
			//nf = new newFighter();
		}
		if(e.getActionCommand().equals("start")&&nf.getRace()!=null&&nf.getName()!=null&&nf.getFighterName()!=null)
		{
			this.playerName = nf.getName();
			
			this.startRace = nf.getRace();
			
			this.activeFighterName = nf.getFighterName();
			nf.setVisible(false);
			GameView.add(worldView);
			game.getPlayer().setName(this.playerName);
			game.getPlayer().createFighter(this.startRace.charAt(0), activeFighterName);
			worldView.setPlayerInfo(game.getPlayer().getName(), game.getPlayer().getActiveFighter().getName(), game.getPlayer().getActiveFighter().getLevel(), game.getPlayer().getSenzuBeans(),game.getPlayer().getDragonBalls());
			JOptionPane.showMessageDialog(GameView, "World");

		}
		if(e.getActionCommand().equals("Attack"))
		{
			try {
				if(battleView.getAttackIndex()!=-1)
				{				
					battle.attack(battle.getAssignedAttacks().get(battleView.getAttackIndex()));
				}
			} catch (NotEnoughKiException e1) {
				// TODO Auto-generated catch block
				battleView.setMove1("You don't have enough Ki");
//				e1.printStackTrace();
				
			}
			
		}
		if(e.getActionCommand().equals("Block"))
		{
			battle.block();
		}
		if(e.getActionCommand().equals("Use"))
		{
			if(game.getPlayer().getSenzuBeans()>=1)
			{
			try {
				battle.use(game.getPlayer(), Collectible.SENZU_BEAN);
			} catch (NotEnoughSenzuBeansException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}else
				battleView.setMove1("you don't enough senzu beans!!");
		}
		if(e.getActionCommand().equals("Fighters properties"))
		{
			worldView.setVisible(false);
			fighterOptions.setVisible(true);
			
			String [] x = new String [game.getPlayer().getFighters().size()];
			 for(int i = 0 ; i< game.getPlayer().getFighters().size() ; i++)
			 {
				 x[i] = game.getPlayer().getFighters().get(i).getName();
			 }
			 fighterOptions.setFightersList(x);
			 
			 String [] a = new String[game.getPlayer().getSuperAttacks().size()+game.getPlayer().getUltimateAttacks().size()];
			 if(game.getPlayer().getSuperAttacks()!=null)
			 {
				 for(int i = 0 ; i<game.getPlayer().getSuperAttacks().size() ; i++)
					 a[i] = game.getPlayer().getSuperAttacks().get(i).getName();
			 }
			 if(game.getPlayer().getUltimateAttacks()!=null)
			 {
				 int j = 0;
				 for(int i = game.getPlayer().getSuperAttacks().size() ; i<a.length ;i++)
				 {
					 
					 a[i] = game.getPlayer().getUltimateAttacks().get(j).getName();
					 j++;
				 }
			 }
			 if(a!=null)
			 	fighterOptions.setUnlockedAttacks(a);
			 
				if(game.getPlayer().getActiveFighter()!=null)
				{
					String [] k = new String[game.getPlayer().getActiveFighter().getSuperAttacks().size()+game.getPlayer().getActiveFighter().getUltimateAttacks().size()];
		
				 if(game.getPlayer().getActiveFighter().getSuperAttacks()!=null)
				 {
					 for(int i = 0 ; i< game.getPlayer().getActiveFighter().getSuperAttacks().size();i++)
						 k[i] = game.getPlayer().getActiveFighter().getSuperAttacks().get(i).getName();
				 }
				 if(game.getPlayer().getActiveFighter().getUltimateAttacks()!=null)
				 {
					 int j = 0;
					 for(int i = game.getPlayer().getActiveFighter().getSuperAttacks().size() ; i<game.getPlayer().getActiveFighter().getUltimateAttacks().size();i++)
					 {
						 k[i] = game.getPlayer().getActiveFighter().getUltimateAttacks().get(j).getName();
						 j++;
					 }
				 }
				 if(k!=null)
					 fighterOptions.setFighterAttack(k);
				}			
			GameView.add(fighterOptions);
			
			
		}
		if(e.getActionCommand().equals("Add")&&fighterOptions.getRace()!=null&fighterOptions.getFighterName()!=null)
		{
			game.getPlayer().createFighter(fighterOptions.getRace().charAt(0), fighterOptions.getFighterName());
			String [] x = new String [game.getPlayer().getFighters().size()];
			 for(int i = 0 ; i< game.getPlayer().getFighters().size() ; i++)
			 {
				 x[i] = game.getPlayer().getFighters().get(i).getName();
			 }
			 fighterOptions.setFightersList(x);
			 
				if(game.getPlayer().getActiveFighter()!=null)
				{
					String [] k = new String[game.getPlayer().getActiveFighter().getSuperAttacks().size()+game.getPlayer().getActiveFighter().getUltimateAttacks().size()];
		
				 if(game.getPlayer().getActiveFighter().getSuperAttacks()!=null)
				 {
					 for(int i = 0 ; i< game.getPlayer().getActiveFighter().getSuperAttacks().size();i++)
						 k[i] = game.getPlayer().getActiveFighter().getSuperAttacks().get(i).getName();
				 }
				 if(game.getPlayer().getActiveFighter().getUltimateAttacks()!=null)
				 {
					 int j = 0;
					 for(int i = game.getPlayer().getActiveFighter().getSuperAttacks().size() ; i<game.getPlayer().getActiveFighter().getUltimateAttacks().size();i++)
					 {
						 k[i] = game.getPlayer().getActiveFighter().getUltimateAttacks().get(j).getName();
						 j++;
					 }
				 }
				 if(k!=null)
					 fighterOptions.setFighterAttack(k);
				}
			 
		}
		if(e.getActionCommand().equals("Select this fighter"))
		{
			game.getPlayer().setActiveFighter(game.getPlayer().getFighters().get(fighterOptions.getFighterIndex()));
//			System.out.println(game.getPlayer().getActiveFighter().getName());
			
			if(game.getPlayer().getActiveFighter()!=null)
			{
				String [] k = new String[game.getPlayer().getActiveFighter().getSuperAttacks().size()+game.getPlayer().getActiveFighter().getUltimateAttacks().size()];
	
			 if(game.getPlayer().getActiveFighter().getSuperAttacks()!=null)
			 {
				 for(int i = 0 ; i< game.getPlayer().getActiveFighter().getSuperAttacks().size();i++)
					 k[i] = game.getPlayer().getActiveFighter().getSuperAttacks().get(i).getName();
			 }
			 if(game.getPlayer().getActiveFighter().getUltimateAttacks()!=null)
			 {
				 int j = 0;
				 for(int i = game.getPlayer().getActiveFighter().getSuperAttacks().size() ; i<game.getPlayer().getActiveFighter().getUltimateAttacks().size();i++)
				 {
					 k[i] = game.getPlayer().getActiveFighter().getUltimateAttacks().get(j).getName();
					 j++;
				 }
			 }
			 if(k!=null)
				 fighterOptions.setFighterAttack(k);
			}
		}
		if(e.getActionCommand().equals("Back to world mode"))
		{
			fighterOptions.setVisible(false);
			worldView.setVisible(true);
			GameView.add(worldView);
			JOptionPane.showMessageDialog(GameView, "World");
		}
		if(e.getActionCommand().equals("Add attack"))
		{
			
				for(int i = 0 ; i <game.getPlayer().getSuperAttacks().size();i++)
				{
					if(fighterOptions.getAttackName().equals(game.getPlayer().getSuperAttacks().get(i).getName()))
						{
							try {
								game.getPlayer().assignAttack(game.getPlayer().getActiveFighter(), game.getPlayer().getSuperAttacks().get(i), null);
//								System.out.println( game.getPlayer().getSuperAttacks().get(i).getName());
							} catch (MaximumAttacksLearnedException e1) {
							// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (DuplicateAttackException e1) {
							// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(GameView, game.getPlayer().getSuperAttacks().get(i).getName()+ " is already learned by this fighter");
//								e1.printStackTrace();
							} catch (NotASaiyanException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							}
						}	
				}
				for(int i = 0 ; i <game.getPlayer().getUltimateAttacks().size();i++)
				{
					if(fighterOptions.getAttackName().equals(game.getPlayer().getUltimateAttacks().get(i).getName()))
					{
						try {
							game.getPlayer().assignAttack(game.getPlayer().getActiveFighter(), game.getPlayer().getUltimateAttacks().get(i), null);
							System.out.println(game.getPlayer().getUltimateAttacks().get(i).getName());
						} catch (MaximumAttacksLearnedException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(GameView, "Maximum attacks learned you must switch attack");
							for(int j = 0 ; i<game.getPlayer().getActiveFighter().getUltimateAttacks().size();j++)
							{
								if(fighterOptions.getFighterAttack().equals(game.getPlayer().getActiveFighter().getUltimateAttacks().get(j).getName()))
									try {
										game.getPlayer().assignAttack(game.getPlayer().getActiveFighter(), game.getPlayer().getUltimateAttacks().get(j),game.getPlayer().getActiveFighter().getUltimateAttacks().get(j));
									} catch (MaximumAttacksLearnedException e2) {
										// TODO Auto-generated catch block
										e2.printStackTrace();
									} catch (DuplicateAttackException e2) {
										// TODO Auto-generated catch block
										e2.printStackTrace();
									} catch (NotASaiyanException e2) {
										// TODO Auto-generated catch block
										e2.printStackTrace();
									}
							}	
						} catch (DuplicateAttackException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(GameView, game.getPlayer().getUltimateAttacks().get(i).getName()+ " is already learned by this fighter");
						} catch (NotASaiyanException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}	
				}
				if(game.getPlayer().getActiveFighter()!=null)
				{
					String [] k = new String[game.getPlayer().getActiveFighter().getSuperAttacks().size()+game.getPlayer().getActiveFighter().getUltimateAttacks().size()];
		
				 if(game.getPlayer().getActiveFighter().getSuperAttacks()!=null)
				 {
					 for(int i = 0 ; i< game.getPlayer().getActiveFighter().getSuperAttacks().size();i++)
						 k[i] = game.getPlayer().getActiveFighter().getSuperAttacks().get(i).getName();
				 }
				 if(game.getPlayer().getActiveFighter().getUltimateAttacks()!=null)
				 {
					 int j = 0;
					 for(int i = game.getPlayer().getActiveFighter().getSuperAttacks().size() ; i<game.getPlayer().getActiveFighter().getUltimateAttacks().size();i++)
					 {
						 k[i] = game.getPlayer().getActiveFighter().getUltimateAttacks().get(j).getName();
						 j++;
					 }
				 }
				 if(k!=null)
					 fighterOptions.setFighterAttack(k);
				}
			
			}
				
//		if(game.getPlayer().getActiveFighter()!=null)
//			{
//				String [] k = new String[game.getPlayer().getActiveFighter().getSuperAttacks().size()+game.getPlayer().getActiveFighter().getUltimateAttacks().size()];
//	
//			 if(game.getPlayer().getActiveFighter().getSuperAttacks()!=null)
//			 {
//				 for(int i = 0 ; i< game.getPlayer().getActiveFighter().getSuperAttacks().size();i++)
//					 k[i] = game.getPlayer().getActiveFighter().getSuperAttacks().get(i).getName();
//			 }
//			 if(game.getPlayer().getActiveFighter().getUltimateAttacks()!=null)
//			 {
//				 int j = 0;
//				 for(int i = game.getPlayer().getActiveFighter().getSuperAttacks().size() ; i<game.getPlayer().getActiveFighter().getUltimateAttacks().size();i++)
//				 {
//					 k[i] = game.getPlayer().getActiveFighter().getUltimateAttacks().get(j).getName();
//					 j++;
//				 }
//			 }
//			 if(k!=null)
//				 fighterOptions.setFighterAttack(k);
//			}
		
		if(e.getActionCommand().equals("Upgrade Fighter"))
		{
			worldView.setVisible(false);
			upgradeFighter.setVisible(true);
			upgradeFighter.setFighterA(game.getPlayer().getActiveFighter().getName(), game.getPlayer().getActiveFighter().getMaxHealthPoints(), game.getPlayer().getActiveFighter().getPhysicalDamage(), game.getPlayer().getActiveFighter().getBlastDamage(), game.getPlayer().getActiveFighter().getMaxKi(), game.getPlayer().getActiveFighter().getMaxStamina(),game.getPlayer().getActiveFighter().getAbilityPoints());
			GameView.add(upgradeFighter);
		}
		if(e.getActionCommand().equals("Back to world"))
		{
			upgradeFighter.setVisible(false);
			worldView.setVisible(true);
			GameView.add(worldView);
			JOptionPane.showMessageDialog(GameView, "World");
		}
		if(e.getActionCommand().equals("Upgrade"))
		{
			if(upgradeFighter.getUpgrade()!=null)
			{			
				try {
				game.getPlayer().upgradeFighter(game.getPlayer().getActiveFighter(), upgradeFighter.getUpgrade().charAt(0));
			} catch (NotEnoughAbilityPointsException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(GameView, "You don't have enough ability points");
			}
			}
			upgradeFighter.setFighterA(game.getPlayer().getActiveFighter().getName(), game.getPlayer().getActiveFighter().getMaxHealthPoints(), game.getPlayer().getActiveFighter().getPhysicalDamage(), game.getPlayer().getActiveFighter().getBlastDamage(), game.getPlayer().getActiveFighter().getMaxKi(), game.getPlayer().getActiveFighter().getMaxStamina(),game.getPlayer().getActiveFighter().getAbilityPoints());
		}
		if(e.getActionCommand().equals("Back to world mode Dragon"))
		{
			dragonView.setVisible(false);
			worldView.setVisible(true);
			GameView.add(worldView);
			JOptionPane.showMessageDialog(GameView, "World");
		}
		if(e.getActionCommand().equals("Choose wish"))
		{
			game.getPlayer().chooseWish(dragons.getWishes()[dragonView.getWishh()]);
//			System.out.println(dragons.getWishes()[dragonView.getWishh()]);
			JOptionPane.showMessageDialog(GameView, "you've got " + dragonView.getWishToString());
			dragonView.setVisible(false);
			worldView.setVisible(true);
			GameView.add(worldView);
			JOptionPane.showMessageDialog(GameView, "World");
			
		}
	}

	public String getActiveFighterName() {
		return activeFighterName;
	}

	public void setActiveFighterName(String activeFighterName) {
		this.activeFighterName = activeFighterName;
	}

	@Override
	public void onDragonCalled(Dragon dragon) {
		// TODO Auto-generated method stub
		this.dragons = dragon;
		worldView.setVisible(false);
		dragonView.setVisible(true);
		String [] w =new String [dragon.getWishes().length];
		for(int i = 0 ; i<w.length ;i++)
		{
//			if(dragon.getWishes()[i].getType().equals(DragonWishType.SUPER_ATTACK))
			switch(dragon.getWishes()[i].getType())
			{
			case SUPER_ATTACK : w[i] = dragon.getWishes()[i].getSuperAttack().getName();
			break;
			case ULTIMATE_ATTACK : w[i] =  dragon.getWishes()[i].getUltimateAttack().getName();
			break;
			case SENZU_BEANS: w[i] = dragon.getWishes()[i].getSenzuBeans() + " Senzu beans";
			break;
			case ABILITY_POINTS : w[i] = dragon.getWishes()[i].getAbilityPoints() + " Ability points";
			break;
			}
		}
		dragonView.setWishess(w);
		GameView.add(dragonView);
		JOptionPane.showMessageDialog(GameView, "Choose one of the following wishes","Choose Wish",JOptionPane.INFORMATION_MESSAGE,new ImageIcon("start.gif"));
	}

	@Override
	public void onCollectibleFound(Collectible collectible) 
	{
		// TODO Auto-generated method stub
		//JOptionPane noti = new JOptionPane();
		if(collectible == Collectible.SENZU_BEAN)
		{
		JOptionPane.showMessageDialog(GameView, "now you have " + game.getPlayer().getSenzuBeans() + " Senzu beans","senzu bean" , JOptionPane.INFORMATION_MESSAGE , new ImageIcon("Senzu_Bean.png"));
		worldView.setPlayerInfo(game.getPlayer().getName(), game.getPlayer().getActiveFighter().getName(), game.getPlayer().getActiveFighter().getLevel(), game.getPlayer().getSenzuBeans(),game.getPlayer().getDragonBalls());
		}
		else
			if(collectible == Collectible.DRAGON_BALL)
			{
				JOptionPane.showMessageDialog(GameView, "now you have " + game.getPlayer().getDragonBalls() +" Dragon Balls","Dragon Ball" , JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Esferas_de_una_estrella.png")); //set Icon
				worldView.setPlayerInfo(game.getPlayer().getName(), game.getPlayer().getActiveFighter().getName(), game.getPlayer().getActiveFighter().getLevel(), game.getPlayer().getSenzuBeans(),game.getPlayer().getDragonBalls());
			}
		}
	
	@Override
	public void onBattleEvent(BattleEvent e) {//mesg beydkhol onBattleEvent fe game
		// TODO Auto-generated method stub
		battle = (Battle)e.getSource();
//		battle.setListener(this);
		switch(e.getType())
		{
		case STARTED :
			worldView.setVisible(false);
//			battleView.setFighterInfo(activeFighterName, game.getPlayer().getActiveFighter().getLevel() , game.getPlayer().getActiveFighter().getMaxHealthPoints() , game.getPlayer().getActiveFighter().getHealthPoints() , game.getPlayer().getActiveFighter().getMaxKi() , game.getPlayer().getActiveFighter().getKi() , game.getPlayer().getActiveFighter().getMaxStamina() , game.getPlayer().getActiveFighter().getStamina());
//			battleView.setOpponentInfo(((Fighter)battle.getFoe()).getName(), ((Fighter)battle.getFoe()).getLevel(), ((Fighter)battle.getFoe()).getMaxHealthPoints(), ((Fighter)battle.getFoe()).getHealthPoints(), ((Fighter)battle.getFoe()).getMaxKi(), ((Fighter)battle.getFoe()).getKi(), ((Fighter)battle.getFoe()).getMaxStamina(), ((Fighter)battle.getFoe()).getStamina());
			l = ((PlayableFighter)battle.getMe()).getLevel();
			String [] b = new String[battle.getAssignedAttacks().size()];
			for(int i = 0 ; i<battle.getAssignedAttacks().size();i++)
				b[i] = battle.getAssignedAttacks().get(i).getName();
			battleView.setAttacks(b);
			battleView.setVisible(true);
			GameView.add(battleView);
			//battleView.setHealthTxt()
			break;
			
		case ATTACK :
			if(battle.getAttacker().equals(battle.getMe()))
			{		
				battleView.setMove1("Attack by " + e.getAttack().getName());
				battleView.setMove2(null);
			}
			else
			{
				battleView.setMove2("Attack by " + e.getAttack().getName());
				//battleView.setMove1(null);
			}
			break;
		case NEW_TURN:
			if(battle.getAttacker().equals(battle.getFoe()))
			{
				battleView.setTurn("Opponent Turn");
				try {
					battle.play();
				} catch (NotEnoughKiException e1) {
					// TODO Auto-generated catch block
					battle.block();
				}
			}else
				battleView.setTurn("your turn");
			//battleView.setMove2()
			battleView.setOpponentInfo(((Fighter)battle.getFoe()).getName(), ((Fighter)battle.getFoe()).getLevel(), ((Fighter)battle.getFoe()).getMaxHealthPoints(), ((Fighter)battle.getFoe()).getHealthPoints(), ((Fighter)battle.getFoe()).getMaxKi(), ((Fighter)battle.getFoe()).getKi(), ((Fighter)battle.getFoe()).getMaxStamina(), ((Fighter)battle.getFoe()).getStamina());
			battleView.setFighterInfo(activeFighterName, game.getPlayer().getActiveFighter().getLevel() , game.getPlayer().getActiveFighter().getMaxHealthPoints() , game.getPlayer().getActiveFighter().getHealthPoints() , game.getPlayer().getActiveFighter().getMaxKi() , game.getPlayer().getActiveFighter().getKi() , game.getPlayer().getActiveFighter().getMaxStamina() , game.getPlayer().getActiveFighter().getStamina());
			break;
			
		case BLOCK:
			if(battle.getAttacker().equals(battle.getMe()))
			{
				battleView.setMove1("Block");
			}else{
				battleView.setMove2("Block");
			}
			break;
			
		case USE:
			if(battle.getAttacker().equals(battle.getMe()))
				battleView.setMove1("Using senzu bean");
			else
				battleView.setMove2("Using senzu bean");
			
			break;
			
		case ENDED:
			if(e.getWinner()== game.getPlayer().getActiveFighter())
				{
					if(((NonPlayableFighter)battle.getFoe()).isStrong())
					{
						String skills = "";
						for(Attack superA:((NonPlayableFighter)battle.getFoe()).getSuperAttacks())
							skills+=superA.getName()+"\n";
						for(Attack ultimateA:((NonPlayableFighter)battle.getFoe()).getUltimateAttacks())
							skills+=ultimateA.getName()+"\n";
				
						JOptionPane.showMessageDialog(GameView,"You have defeated the Boss" +"\nYour xp: "+((NonPlayableFighter)battle.getFoe()).getLevel()*5+"\nGained skills: " + skills,"You've won",JOptionPane.PLAIN_MESSAGE);
						
						battleView.setVisible(false);
//						worldView.setVisible(true);
						worldView = new worldView(this);
//						worldView.movePlayer(currentPos, 99);
//						System.out.println(currentPos);
//						currentPos = 99;
						game.getWorld().resetPlayerPosition();
						GameView.add(worldView);
						worldView.setPlayerInfo(game.getPlayer().getName(), game.getPlayer().getActiveFighter().getName(), game.getPlayer().getActiveFighter().getLevel(), game.getPlayer().getSenzuBeans(),game.getPlayer().getDragonBalls());
						
					}else
					{
//					String [] a = new String[((NonPlayableFighter)battle.getFoe()).getSuperAttacks().size()+((NonPlayableFighter)battle.getFoe()).getUltimateAttacks().size()];
//					for(int i = 0 ; i<((NonPlayableFighter)battle.getFoe()).getSuperAttacks().size();i++)
//						a[i] = ((NonPlayableFighter)battle.getFoe()).getSuperAttacks().get(i).getName();
//					for(int i = 0 ; i<((NonPlayableFighter)battle.getFoe()).getUltimateAttacks().size();i++)
//						a[i] = ((NonPlayableFighter)battle.getFoe()).getUltimateAttacks().get(i).getName();
//					
					
					String skills = "";
					for(Attack superA:((NonPlayableFighter)battle.getFoe()).getSuperAttacks())
						skills+=superA.getName()+"\n";
					for(Attack ultimateA:((NonPlayableFighter)battle.getFoe()).getUltimateAttacks())
						skills+=ultimateA.getName()+"\n";
			
					JOptionPane.showMessageDialog(GameView,"You have won" +"\nYour xp: "+((NonPlayableFighter)battle.getFoe()).getLevel()*5+"\nGained skills: " + skills,"You've won",JOptionPane.PLAIN_MESSAGE);
					
					
					
					battleView.setVisible(false);
					worldView.setVisible(true);
					worldView.setPlayerInfo(game.getPlayer().getName(), game.getPlayer().getActiveFighter().getName(), game.getPlayer().getActiveFighter().getLevel(), game.getPlayer().getSenzuBeans(),game.getPlayer().getDragonBalls());
					GameView.add(worldView);
					if(l!= ((PlayableFighter)battle.getMe()).getLevel())
						JOptionPane.showMessageDialog(GameView,"Congratulation" +"\nNow your level: "+((PlayableFighter)battle.getMe()).getLevel()+"\nYour new target XP: " + ((PlayableFighter)battle.getMe()).getTargetXp()+"\n+2 ability points","Level up!!",JOptionPane.PLAIN_MESSAGE);
					else
						JOptionPane.showMessageDialog(GameView, "World");
					
					}
				
				}
			else
				if(e.getWinner() == battle.getFoe())
				{
					battleView.setVisible(false);
//					worldView.setVisible(true);
					worldView = new worldView(this);
					worldView.movePlayer(currentPos, 99);
//					System.out.println(currentPos);
//					currentPos = 99;
					game.getWorld().resetPlayerPosition();
					GameView.add(worldView);
					worldView.setPlayerInfo(game.getPlayer().getName(), game.getPlayer().getActiveFighter().getName(), game.getPlayer().getActiveFighter().getLevel(), game.getPlayer().getSenzuBeans(),game.getPlayer().getDragonBalls());
					JOptionPane.showMessageDialog(GameView, "You've lost");
				}
					
			
		}
			
	}
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getStartRace() {
		return startRace;
	}

	public void setStartRace(String startRace) 
	{
		this.startRace = startRace;
	}

	
	public static void main (String [] args) throws MissingFieldException, UnknownAttackTypeException
	{
		new gameGUI();
	}

}