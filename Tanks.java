/******************************************************
In The Name of God
Project of ICP- Tanks Game
Written By: Benyamin Delshad
610393093
University of Tehran
Winter 2015 - zemestan 1393
******************************************************/

import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/******************************************************
Classes Line:
1. Tanks: 39
2. start_frame: 49
3. main_frame: 236
4. end_frame: 388
5. dashboard_panel: 427
6. map_loader: 619
7. wind_panel: 1243
8. arrows: 1293
9. tank_head: 1333
10. tank_information: 1360
11. coin_information: 1419
12. bullet: 1439 

1474 Lines!
******************************************************/

class Tanks {
	public static void main(String[] args) throws IOException, InterruptedException
	{
		start_frame startframe = new start_frame();
		//main_frame mainframe = new main_frame();
		//end_frame endframe = new end_frame(1);
	}
}


class start_frame extends JFrame implements ActionListener {
	JLabel welcome; 
	JButton start, exit;
	tank_head tankpic; 
	
	
	JLabel[] gamelevel;
	JLabel gamelevellabel;
	JButton changelevel;
	
	JLabel[] gamemode;
	JLabel gamemodelabel;
	JButton changemode;
	

	JLabel[] gamemap;
	JLabel gamemaplabel;
	JButton changemap;
	JTextField custommap;
	
	int mode_game, level_game, map_game;
	
	main_frame mainframe;

	start_frame() throws IOException {
		super("Tanks!");
		setBounds(200, 150, 340, 500);
		setVisible(true);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLayout(null);
		setResizable(false);
		
		welcome = new JLabel("Welcome to Tanks Game!");
		welcome.setBounds(75, 20, 200, 20);
		add(welcome);
		
		tankpic = new tank_head();
		tankpic.setLocation(145, 50);
		add(tankpic);
		
		gamemodelabel = new JLabel("Type of Game:");
		gamemodelabel.setBounds(20, 110, 150, 20);
		
		gamemode = new JLabel[2];
		gamemode[0] = new JLabel("Player1 VS CPU");
		gamemode[1] = new JLabel("Player1 VS Player2");
		
		gamemode[0].setBounds(50,130 , 150, 20);
		gamemode[1].setBounds(50, 150, 150, 20);
		
		gamemode[0].setForeground(Color.red);
		mode_game = 0;
		
		changemode = new JButton("Change!");
		changemode.setBounds(200, 120, 100, 30);
		changemode.addActionListener(this);
		changemode.setActionCommand("changemode");
		
		add(gamemodelabel);
		add(gamemode[0]);
		add(gamemode[1]);
		add(changemode);
		
		gamelevellabel = new JLabel("Level of Game:");
		gamelevellabel.setBounds(20, 190, 150, 20);
		gamelevel = new JLabel[3];	
		gamelevel[0] = new JLabel("Easy");
		gamelevel[1] = new JLabel("Normal");
		gamelevel[2] = new JLabel("Hard");
		
		gamelevel[0].setBounds(50, 210, 150, 20);
		gamelevel[1].setBounds(50, 230, 150, 20);
		gamelevel[2].setBounds(50, 250, 150, 20);
		
		gamelevel[0].setForeground(Color.red);
		level_game = 0;
		
		changelevel = new JButton("Change!");
		changelevel.setBounds(200, 210, 100, 30);
		changelevel.addActionListener(this);
		changelevel.setActionCommand("changelevel");
		
		
		add(gamelevellabel);
		add(gamelevel[0]);
		add(gamelevel[1]);
		add(gamelevel[2]);
		add(changelevel);
		

		gamemaplabel = new JLabel("Map of Game:");
		gamemaplabel.setBounds(20, 280, 150, 20);
		
		gamemap = new JLabel[3];	
		gamemap[0] = new JLabel("Map #1");
		gamemap[1] = new JLabel("Map #2");
		gamemap[2] = new JLabel("Custom:");
		
		gamemap[0].setBounds(50, 300, 150, 20);
		gamemap[1].setBounds(50, 320, 150, 20);
		gamemap[2].setBounds(50, 340, 150, 20);
		
		gamemap[0].setForeground(Color.red);
		map_game = 0;
		
		changemap = new JButton("Change!");
		changemap.setBounds(200, 290, 100, 30);
		changemap.addActionListener(this);
		changemap.setActionCommand("changemap");
		
		custommap = new JTextField();
		custommap.setBounds(50, 360, 250, 20);
		custommap.setEditable(false);
		
		add(gamemaplabel);
		add(gamemap[0]);
		add(gamemap[1]);
		add(gamemap[2]);
		add(changemap);
		add(custommap);
	

		start = new JButton("Start!");
		start.setBounds(50, 420, 80, 40);
		start.setActionCommand("start");
		start.addActionListener(this);
		add(start);
		
		exit = new JButton ("Exit!");
		exit.setBounds(210, 420, 80, 40);
		exit.setActionCommand("exit");
		exit.addActionListener(this);
		add(exit);
		
		repaint();
	}

	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("changemode")) {
			gamemode[mode_game].setForeground(Color.black);
			mode_game++;
			mode_game %= 2;
			gamemode[mode_game].setForeground(Color.red);
			
		}
		if(arg0.getActionCommand().equals("changelevel")) {
			gamelevel[level_game].setForeground(Color.black);
			level_game++;
			level_game %= 3;
			gamelevel[level_game].setForeground(Color.red);
		}
		if(arg0.getActionCommand().equals("changemap")) {
			if(map_game == 2)
				custommap.setEditable(false);
			gamemap[map_game].setForeground(Color.black);
			map_game++;
			map_game %= 3;
			gamemap[map_game].setForeground(Color.red);
			if(map_game == 2)
				custommap.setEditable(true);
		}
		if(arg0.getActionCommand().equals("start")) {
			this.setVisible(false);
			System.out.println(custommap.getText() );
			try {
				if(map_game == 2)
					mainframe = new main_frame(mode_game, level_game, map_game, custommap.getText());
				else
					mainframe = new main_frame(mode_game, level_game, map_game, "DefultMap!");
					
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(arg0.getActionCommand().equals("exit")) {
			System.exit(0);
		}
	}
	
}




class main_frame extends JFrame implements ActionListener {
	dashboard_panel dashboard1, dashboard2;
	wind_panel wind;
	JButton fire;
	map_loader gameplan;
	int turn;
	int gamemode, gamelevel, gamemap;
	String mapfile;
	main_frame(int mode_game, int level_game, int map_game, String mapaddress) throws IOException {
		super("Tanks!");
		setBounds(20, 15, 800, 720);
		setVisible(true);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLayout(null);
		setResizable(false);
		
		gamemode = mode_game; // 0: 1 players, 1: 2 players
		gamelevel = level_game; // 0: easy 1: normal 2: hard
		gamemap = map_game; // 0 & 1 : my maps 2: user map 
		mapfile = mapaddress; // address of user map!
		
		wind = new wind_panel(650, 520, 50);
		add(wind);
		wind.repaint();
		
		fire = new JButton("Fire!!!");
		fire.setBounds(660, 640, 120, 50);
		fire.setActionCommand("fire");
		fire.addActionListener(this);
		add(fire);
		if(gamemap != 2)
			gameplan = new map_loader("finalmap" + Integer.toString(gamemap) + ".txt", wind, gamemode, gamelevel);
		else
			gameplan = new map_loader(mapfile , wind, gamemode, gamelevel);
		gameplan.setLocation(0, 0);
		add(gameplan);
		
		dashboard1 = new dashboard_panel(0, 520, gameplan, 1);
		dashboard2 = new dashboard_panel(0, 520, gameplan, 2);
		add(dashboard1);
		add(dashboard2);
		
		turn = 1;
		dashboard1.setVisible(true);
		
		repaint();
		
		
	}

	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("fire")) {
			if(gamemode == 1)
			{
				dashboard1.update();
				dashboard2.update();
				boolean flag = true;
				if( (turn == 1 && gameplan.tank1.typeofbullet == 2 && gameplan.tank1.numberofbullet2 <= 0)
				 || (turn == 2 && gameplan.tank2.typeofbullet == 2 && gameplan.tank2.numberofbullet2 <= 0))
					flag = false;
				
				if(flag && turn == 1) {
					dashboard1.setVisible(false);
					
					if(gameplan.tank1.typeofbullet == 1) {							
						try {
							gameplan.firebullet1(turn);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
		
					} else
					{
						gameplan.tank1.numberofbullet2--;
						dashboard1.bullet2number.setText(Integer.toString(gameplan.tank1.numberofbullet2) );
						try {
							gameplan.firebullet2(turn);
							
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					dashboard2.setVisible(true);
				}
				else if (flag) {
					dashboard2.setVisible(false);
					if(gameplan.tank2.typeofbullet == 1) {
						try {
							gameplan.firebullet1(turn);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else
					{
						gameplan.tank2.numberofbullet2--;
						dashboard2.bullet2number.setText(Integer.toString(gameplan.tank2.numberofbullet2) );
						try {
							gameplan.firebullet2(turn);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					dashboard1.setVisible(true);
				}
				
				if(flag)
					turn = 3 - turn;
				dashboard1.update();
				dashboard2.update();
				
				repaint();
			}
			if(gamemode == 0) {
				dashboard1.update();
				dashboard2.update();
				boolean flag = true;
				if( (turn == 1 && gameplan.tank1.typeofbullet == 2 && gameplan.tank1.numberofbullet2 <= 0))
					flag = false;
				if(flag) {
					dashboard1.update();
					
					if(gameplan.tank1.typeofbullet == 1) {							
						try {
							gameplan.firebullet1(turn);
						} catch (InterruptedException e) {
							//e.printStackTrace();
						}
		
					} else
					{
						gameplan.tank1.numberofbullet2--;
						dashboard1.bullet2number.setText(Integer.toString(gameplan.tank1.numberofbullet2) );
						try {
							gameplan.firebullet2(turn);
							
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				dashboard1.update();
				dashboard1.repaint();
								
			}
		
		}
		
	}
	
}


class end_frame extends JFrame implements ActionListener 
{
	int winner;
	String message;
	JLabel winnerlabel, me;
	JButton exitbutton;
	
	end_frame(int winner_val)
	{
		super("End of Game!");
		setBounds(200, 150, 350, 170);
		setVisible(true);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLayout(null);
		setResizable(false);
		
		winner = winner_val;
		message = new String("Player" + Integer.toString(winner) + " Wins!");
		winnerlabel = new JLabel(message);
		winnerlabel.setForeground(Color.red);
		winnerlabel.setBounds(125, 0, 100, 50);
		add(winnerlabel);
		exitbutton = new JButton("exit!");
		exitbutton.setBounds(125, 70, 100, 30);
		exitbutton.addActionListener(this);
		add(exitbutton);
		me = new JLabel("Written By: Benyamin Delshad, Winter 2015");
		me.setForeground(Color.gray);
		me.setBounds(10, 140, 350, 20);
		add(me);
		//repaint();
	}

	public void actionPerformed(ActionEvent arg0) {
		System.exit(0);
	}
}


class dashboard_panel extends JPanel implements ActionListener , ChangeListener {
	JSlider force, degree;
	JLabel forcelabel, degreelabel, degreelabel2, coinlabel, typeofbulletlabel, bullet2label, healthlabel, playerlabel;
	JTextField  degreeshow, typeofbullet, coin, bullet2number, health;
	JButton changetypeofbullet, buybullet2;
	map_loader gameplan;
	int index;
	
	dashboard_panel(int x, int y, map_loader map, int dashboard_index)  {
		setLayout(null);
		setBounds(x, y, 650, 200);
		setVisible(false);
		gameplan = map;
		
		index = dashboard_index;
		
		playerlabel = new JLabel();
		if(index == 1)
			playerlabel.setText("player1");
		else
			playerlabel.setText("player2");
		playerlabel.setForeground(Color.blue);
		playerlabel.setBounds(0, 30,70, 20 );
		add(playerlabel);
		
		forcelabel = new JLabel("Force:");
		forcelabel.setBounds(10,0,70,30);
		add(forcelabel);
		
		force = new JSlider (JSlider.HORIZONTAL,0, 100, 50 );
		force.setMajorTickSpacing(5);
		force.setMinorTickSpacing(1);
		force.setBounds(80, 0, 550, 50);
		force.setPaintTicks(true);
		force.setPaintLabels( true);
		force.addChangeListener(this);
		force.setName("force");
		add(force);
		
		degreelabel = new JLabel ("Degree:");
		degreelabel.setBounds(10,50,70,30);
		add(degreelabel);
		
		degreeshow = new JTextField("90");
		degreeshow.setEditable(false);
		degreeshow.setBounds(80, 100, 30, 30);
		add(degreeshow);
		
		degreelabel2 = new JLabel ("Degree:");
		degreelabel2.setBounds(10,100,70,30);
		add(degreelabel2);
		
		degree = new JSlider (JSlider.HORIZONTAL,0, 180, 90 );
		degree.setMajorTickSpacing(10);
		degree.setMinorTickSpacing(1);
		degree.setBounds(80, 50, 550, 50);
		degree.setPaintTicks(true);
		degree.setPaintLabels( true);
		degree.addChangeListener(this);
		degree.setName("degree");
		add(degree);
		
		
		healthlabel = new JLabel("Health:");
		healthlabel.setBounds(120, 100, 55, 30);
		//add(healthlabel);
		
		health = new JTextField("100");
		health.setEditable(false);
		health.setBounds(175, 100, 30, 30);
		//add(health);
		
		
		typeofbulletlabel = new JLabel("Type of Bullet:");
		typeofbulletlabel.setBounds(220, 100, 110, 30);
		add(typeofbulletlabel);
		
		typeofbullet = new JTextField("#1");
		typeofbullet.setEditable(false);
		typeofbullet.setBounds(330, 100, 30, 30);
		add(typeofbullet);
		
		changetypeofbullet = new JButton("Change Type of Bullet!");
		changetypeofbullet.setBounds(370, 100, 250, 30);
		changetypeofbullet.addActionListener(this);
		changetypeofbullet.setActionCommand("changetypeofbullet");
		add(changetypeofbullet);
		
		coinlabel = new JLabel("Coins:");
		coinlabel.setBounds(10, 140, 70, 30);
		//add(coinlabel);
		
		coin = new JTextField("3");
		coin.setEditable(false);
		coin.setBounds(80, 140, 30, 30);
		//add(coin);
		
		bullet2label = new JLabel("Number of Bullet #2:");
		bullet2label.setBounds(150, 140, 180, 30);
		add(bullet2label);
		
		bullet2number = new JTextField("0");
		bullet2number.setEditable(false);
		bullet2number.setBounds(330, 140, 30, 30);
		add(bullet2number);
		
		buybullet2 = new JButton("Buy a Bullet #2 with 1 coin!");
		buybullet2.setBounds(370, 140, 250, 30);
		buybullet2.setActionCommand("buybullet2");
		buybullet2.addActionListener(this);
		add(buybullet2);
		
	}
	
	public void setrotatelule(int degree_val) {
		if(index == 1)
			gameplan.rotatelule("rotatelule1", degree_val);
		else
			gameplan.rotatelule("rotatelule2", degree_val);
	}
	
	public void update() {
		if(index == 1) {
			coin.setText(Integer.toString(gameplan.tank1.coins));
			health.setText(Integer.toString(gameplan.tank1.health));
		}
		else {
			coin.setText(Integer.toString(gameplan.tank2.coins));
			health.setText(Integer.toString(gameplan.tank2.health));
		}
		repaint();
	}
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getActionCommand().equals("changetypeofbullet")) {
			if(typeofbullet.getText().equals("#1")) {
				typeofbullet.setText("#2");
				if(index == 1)
					gameplan.tank1.typeofbullet = 2;
				else
					gameplan.tank2.typeofbullet = 2;
			}
			else {
				typeofbullet.setText("#1");
				if(index == 1)
					gameplan.tank1.typeofbullet = 1;
				else
					gameplan.tank2.typeofbullet = 1;
			}
		}
		if(arg0.getActionCommand().equals("buybullet2") && Integer.parseInt(coin.getText()) > 0) {
			bullet2number.setText( new Integer(Integer.parseInt(bullet2number.getText() ) + 1).toString() );
			coin.setText(new Integer(Integer.parseInt(coin.getText()) - 1).toString() );
			int cointmp = (Integer.parseInt(coin.getText() ) );
			if(index == 1) {
				gameplan.tank1.coins--;
				gameplan.tank1.numberofbullet2++;
				gameplan.coin1.setText("coin player1: " + Integer.toString(gameplan.tank1.coins));
			}
			else {
				gameplan.tank2.coins--;
				gameplan.tank2.numberofbullet2++;
				gameplan.coin2.setText("coin player2: " + Integer.toString(gameplan.tank2.coins));
			}
		}
		repaint();
		
	}

	
	public void stateChanged(ChangeEvent arg0) {
		JSlider source;
		source = (JSlider) arg0.getSource();
		if(source.getName().equals("degree") ) {
			degreeshow.setText(new Integer(source.getValue()).toString() );
			setrotatelule(source.getValue());
			if(index == 1)
				gameplan.tank1.degree = degree.getValue();
			else
				gameplan.tank2.degree = degree.getValue();
		}
		if(source.getName().equals("force")) {
			if(index == 1)
				gameplan.tank1.force = force.getValue();
			else
				gameplan.tank2.force = force.getValue();
		}
		
	}
}



class map_loader extends JPanel implements Runnable {
	char[][] map;
	coin_information[] coininfo;
	int row, column, degree1, degree2, numberofcoins;
	String nameinput;
	tank_information tank1, tank2;
	bullet bullet1, bullet2;
	boolean activebullet1, activebullet2;
	Thread bullet_animation;
	String Threadname;
	int turn;
	wind_panel wind;
	int zamin[]; // zamin[x] = y --> (x, y) marze zamin o hava 
	JLabel health1, health2, coin1, coin2;
	int winner, gamemode, gamelevel;
	end_frame endf;
	Clip firebullet1_music, firebullet2_music, accidentzamin_music;
	Random randomlevelgame;
	
	BufferedImage bullet1_img; // = ImageIO.read(new File("bullet.png"));
	BufferedImage bullet2_img; // = ImageIO.read(new File("bullet2.png"));
	BufferedImage img; // = ImageIO.read(new File("wallpaper.png"));
	BufferedImage[] image; // = new BufferedImage[10];
	BufferedImage special_0; // = ImageIO.read(new File("Sand_Tile_Flat_Edge_a.png"));
	BufferedImage luletankt; // = ImageIO.read(new File("luletankpic.png"));
	BufferedImage luletankT; // = ImageIO.read(new File("luletankpic2.png"));
	
	
	map_loader(String mapname, wind_panel windname, int mode_game, int level_game) throws IOException {
		wind = windname;
		setLayout(null);
		winner = 0;
		
		gamemode = mode_game; // 0: 1 players, 1: 2 players
		gamelevel = level_game; // 0: easy 1: normal 2: hard
		randomlevelgame = new Random();
	
		tank1 = new tank_information();
		tank2 = new tank_information();
		
		health1 = new JLabel("Health player1: " + Integer.toString(tank1.health));
		health2 = new JLabel("Health player2: " + Integer.toString(tank2.health));
		health1.setBounds(5, 0, 150, 20);
		health2.setBounds(5, 20, 150, 20);
		health1.setForeground(Color.red);
		health2.setForeground(Color.red);
		add(health1);
		add(health2);
		
		numberofcoins = 0;
		Scanner scan = new Scanner(new File(mapname));
		column = scan.nextInt();
		row = scan.nextInt();
		map = new char[row][column];
		
		coin1 = new JLabel("coin player1: " + Integer.toString(tank1.coins));
		coin2 = new JLabel("coin player2: " + Integer.toString(tank2.coins));
		coin1.setBounds(4 * column - 120, 0, 120, 20);
		coin2.setBounds(4 * column - 120, 20, 120, 20);
		coin1.setForeground(Color.yellow);
		coin2.setForeground(Color.yellow);
		add(coin1);
		add(coin2);
		
		
		String tmpline = new String();
		tmpline = scan.nextLine();
		for(int i = 0;scan.hasNextLine() &&  i < row; ++i) {
			tmpline = scan.nextLine();
			for(int j = 0; j < tmpline.length(); ++j) {
				map[i][j] = tmpline.charAt(j);
				if(map[i][j] == 'c' || map[i][j] == 'C')
					numberofcoins++;
			}
		}
		
		//System.out.println(numberofcoins);
		zamin = new int[4 * column];
		coininfo = new coin_information[numberofcoins];
		//System.out.println(numberofcoins);
		for(int i = 0; i < numberofcoins; ++i)
			coininfo[i] = new coin_information();
		int counter_coin = 0;
		for(int i = 0; i < row; ++i) {
			for(int j = 0; j < column; ++j) {
				if(map[i][j] == 't') {
					tank1.set_information(i, j, degree1);
					if(i < row - 1 && map[i + 1][j] == '2')
						tank1.set_degree_tank(45);
					else if (i < row && map[i + 1][j] == '1')
						tank1.set_degree_tank(360 - 45);
					else
						tank1.set_degree_tank(0);
				}
				if(map[i][j] == 'T') {
					tank2.set_information(i, j, degree2);
					if(i < row - 1 && map[i + 1][j] == '2')
						tank2.set_degree_tank(45);
					else if (i < row && map[i + 1][j] == '1')
						tank2.set_degree_tank(360 - 45);
					else
						tank2.set_degree_tank(0);
				}
				if(map[i][j] == 'c' || map[i][j] == 'C')
					coininfo[counter_coin++].set_coin_location(4 * j - 8, 4 * i - 16);
				if(map[i][j] == '0'&& ( i == 0 || (map[i - 1][j] < '0' || map[i - 1][j] > '2') ) )
					for(int k = 0; k < 4; ++k)
						zamin[4 * j + k] = 4 * i;
				if(map[i][j] == '1') {
					zamin[4 * j + 0] = 4 * i + 0;
					zamin[4 * j + 1] = 4 * i + 1;
					zamin[4 * j + 2] = 4 * i + 2;
					zamin[4 * j + 3] = 4 * i + 3;
				}
					
				if(map[i][j] == '2') {
					zamin[4 * j + 0] = 4 * i + 3;
					zamin[4 * j + 1] = 4 * i + 2;
					zamin[4 * j + 2] = 4 * i + 1;
					zamin[4 * j + 3] = 4 * i + 0;
				}
				if(map[i][j] == '3' || map[i][j] == '4') {
					for(int k = 0; k < 4; ++k)
						zamin[4 * j + k] = 4 * i;
				}
				
			}
		}
		if(tank1.degreetank == 0)
			for(int i = tank1.Xtank; i < tank1.Xtank + tank1.widthtank; i++)
				zamin[i] -= tank1.heighttank / 2;
		else
			for(int i = tank1.Xtank + tank1.widthtank / 2 - (int)(Math.sqrt(2.0) / 2 * tank1.widthtank); i < tank1.Xtank + tank1.widthtank / 2 + (int) (Math.sqrt(2.0) / 2 * tank1.widthtank); ++i )
				zamin[i] -= tank1.heighttank;
		if(tank2.degreetank == 0)
			for(int i = tank2.Xtank; i < tank2.Xtank + tank2.widthtank; i++)
				zamin[i] -= tank2.heighttank / 2;
		else
			for(int i = tank2.Xtank + tank2.widthtank / 2 - (int)(Math.sqrt(2.0) / 2 * tank2.widthtank); i < tank2.Xtank + tank2.widthtank / 2 + (int) (Math.sqrt(2.0) / 2 * tank2.widthtank); ++i )
				zamin[i] -= tank2.heighttank;
		
		setSize( 4 * column, 4 * row);
		
		nameinput = new String("setmap");
		degree1 = 90;
		degree2 = 90;
		
		tank1.set_lule_ends(degree1);
		tank2.set_lule_ends(degree2);
		bullet1 = new bullet( 25 ,1,  1);
		bullet2 = new bullet( 40 ,2,  5 );
		
		activebullet1 = false;
		activebullet2 = false;
		
		String Threadname = new String();

		bullet1_img = ImageIO.read(new File("bullet.png"));
		bullet2_img = ImageIO.read(new File("bullet2.png"));
		img = ImageIO.read(new File("wallpaper.png"));
		image = new BufferedImage[10];
		image[0] = ImageIO.read(new File("Sand_Tile.png")); // 0
		image[1] = ImageIO.read(new File("Sand_Tile_Slope_down.png")); // 1
		image[2] = ImageIO.read(new File("Sand_Tile_Slope_Up.png")); // 2
		image[3] = ImageIO.read(new File("Sand_Tile_Slope_num4.png")); // 3
		image[4] = ImageIO.read(new File("Sand_Tile_Slope_num3.png")); // 4
		image[5] = ImageIO.read(new File("Coin_Front.png")); // c
		image[6] = ImageIO.read(new File("tankpic.png")); // t
		image[7] = ImageIO.read(new File("tankpic2.png")); // T
		special_0 = ImageIO.read(new File("Sand_Tile_Flat_Edge_a.png"));
		luletankt = ImageIO.read(new File("luletankpic.png"));
		luletankT = ImageIO.read(new File("luletankpic2.png"));
		
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		try {
			doDrawing(g);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void doDrawing(Graphics g) throws IOException {
		Graphics2D g2d = (Graphics2D )g;
		/*
		BufferedImage bullet1_img = ImageIO.read(new File("bullet.png"));
		BufferedImage bullet2_img = ImageIO.read(new File("bullet2.png"));
		BufferedImage img = ImageIO.read(new File("wallpaper.png"));
		BufferedImage[] image = new BufferedImage[10];
		image[0] = ImageIO.read(new File("Sand_Tile.png")); // 0
		image[1] = ImageIO.read(new File("Sand_Tile_Slope_down.png")); // 1
		image[2] = ImageIO.read(new File("Sand_Tile_Slope_Up.png")); // 2
		image[3] = ImageIO.read(new File("Sand_Tile_Slope_num4.png")); // 3
		image[4] = ImageIO.read(new File("Sand_Tile_Slope_num3.png")); // 4
		image[5] = ImageIO.read(new File("Coin_Front.png")); // c
		image[6] = ImageIO.read(new File("tankpic.png")); // t
		image[7] = ImageIO.read(new File("tankpic2.png")); // T
		BufferedImage special_0 = ImageIO.read(new File("Sand_Tile_Flat_Edge_a.png"));
		BufferedImage luletankt = ImageIO.read(new File("luletankpic.png"));
		BufferedImage luletankT = ImageIO.read(new File("luletankpic2.png"));
		*/
		/*
		AffineTransform tmp = new AffineTransform();
		tmp.translate(400, 70);
		tmp.rotate(Math.PI / 2);
		tmp.translate(-400, -70);
		*/
		
		g2d.drawImage(img, 0, 0, 4 * column, 4 * row, null);
		
		AffineTransform normalstate = g2d.getTransform(); //new AffineTransform();
		AffineTransform rotatelule1 = new AffineTransform();
		AffineTransform rotatelule2 = new AffineTransform();
		AffineTransform rotatetank1 = new AffineTransform();
		AffineTransform rotatetank2 = new AffineTransform();
		// setmap begin:
		
		for(int i = 0;i < row ; ++i)
			for(int j = 0; j < column; ++j) {
				if(map[i][j] - '0' >= 0 && map[i][j] - '0' <= 4)
					if(map[i][j] == '0'&& ( i == 0 || (map[i - 1][j] < '0' || map[i - 1][j] > '2') ) )
						g2d.drawImage( special_0 , 4 * j, 4 * i, 4, 4, null);
					else
						g2d.drawImage( image[(int)(map[i][j] - '0') ], 4 * j, 4 * i, 4, 4, null);
			}
			
		for(int i = 0; i < row; i++)
		{
			for(int j = 0; j < column; ++j)
			{
	
				if(map[i][j] == 't') {
					//g2d.drawImage(image[6], 4 * j - 20, 4 * i - 20, 44, 24, null);
					rotatetank1.translate(tank1.Xtank + tank1.widthtank / 2 + tank1.tmpX , tank1.Ytank + tank1.heighttank / 2 + tank1.tmpY);
					rotatetank1.rotate(-Math.toRadians((double) tank1.degreetank));
					//g2d.drawImage(image[6], tank1.Xtank, tank1.Ytank, tank1.widthtank, tank1.heighttank, null);
					g2d.setTransform(rotatetank1);
					g2d.drawImage(image[6], -tank1.widthtank / 2, -tank1.heighttank / 2, tank1.widthtank, tank1.heighttank, null);
					g2d.setTransform(normalstate);
					
					
					//rotatelule1.translate(4 * j + 2, 4 * i - 20  + 4 );
					rotatelule1.translate(tank1.Xlule + tank1.tmpluleX, tank1.Ylule  + 4  + tank1.tmpluleY);
					
					rotatelule1.rotate(-Math.toRadians((double)(degree1 + tank1.degreetank)));
					g2d.setTransform(rotatelule1);
					//g2d.drawImage(luletankt, 0 , -4, 40, 8, null);
					g2d.drawImage(luletankt, 0 , -tank1.heightlule / 2, tank1.widthlule, tank1.heightlule, null);
					g2d.setTransform(normalstate);					
				}
				if(map[i][j] == 'T') {
					//g2d.drawImage(image[7], 4 * j - 20, 4 * i - 20, 44, 24, null);
					rotatetank2.translate(tank2.Xtank + tank2.widthtank / 2 + tank2.tmpX, tank2.Ytank + tank2.heighttank / 2 + tank2.tmpY);
					rotatetank2.rotate(-Math.toRadians((double) tank2.degreetank));
					//g2d.drawImage(image[7],tank2.Xtank, tank2.Ytank, tank2.widthtank, tank2.heighttank, null);
					g2d.setTransform(rotatetank2);
					g2d.drawImage(image[7], -tank2.widthtank / 2, -tank2.heighttank / 2, tank2.widthtank, tank2.heighttank, null);
					g2d.setTransform(normalstate);
					
					
					
					//rotatelule2.translate(4 * j + 2, 4 * i - 20  + 4 );
					rotatelule2.translate(tank2.Xlule + tank2.tmpluleX, tank2.Ylule  + 4  + tank2.tmpluleY);

					rotatelule2.rotate(-Math.toRadians((double)(degree2 + tank2.degreetank)));
					g2d.setTransform(rotatelule2);
					//g2d.drawImage(luletankT, 0 , -4, 40, 8, null);
					g2d.drawImage(luletankT , 0 ,-tank2.heightlule / 2, tank2.widthlule, tank2.heightlule, null);
					g2d.setTransform(normalstate);
											
				}
				/*if(map[i][j] == 'c' || map[i][j] == 'C') {
					g2d.drawImage(image[5], 4 * j - 8, 4 * i - 16, 20, 20, null);
				}*/
		
					
			}
		}
	
		for(int i = 0; i < numberofcoins; ++i)
			if(coininfo[i].visible)
				g2d.drawImage(image[5], coininfo[i].Xcoin, coininfo[i].Ycoin, 20, 20, null);
		
		if(activebullet1) {
			//System.out.println(":|");
			g2d.drawImage(bullet1_img, bullet1.Xbullet, bullet1.Ybullet, bullet1.widthbullet, bullet1.heightbullet, null);
		}
		if(activebullet2) {
			g2d.drawImage(bullet2_img, bullet2.Xbullet, bullet2.Ybullet, bullet2.widthbullet, bullet2.heightbullet, null);
		}
		//repaint();
		
	// end setmap! 
		
	}
	
	void rotatelule(String name, int degree_val) {
		//System.out.println("hello in rotatelule");
		nameinput = name;
		if(nameinput.equals("rotatelule1")) {
			degree1 = degree_val;
			tank1.degree = degree1;
			tank1.set_lule_ends(tank1.degree);
		}
		if(nameinput.equals("rotatelule2")) {
			degree2 = degree_val;
			tank2.degree = degree2;
			tank2.set_lule_ends(tank2.degree);
		}
		
		repaint();

	}
	double zaribkhata() {
		double tmp = randomlevelgame.nextDouble();
		if(gamelevel == 0) {
			tmp *= 0.5;
			tmp += 0.5;
		}
		if(gamelevel == 1) {
			tmp *= 0.3;
			tmp += 0.7;
			
		}
		if(gamelevel == 2) {
			tmp *= 0.05;
			tmp += 0.95;
		}
		return tmp;
	}
	void player2_automatic_move() throws InterruptedException { // hush masnuE
		/*while(tank2.coins > 0) {
			tank2.coins--;
			coin2.setText("coin player2: " + Integer.toString(tank2.coins));
			tank2.numberofbullet2++;
		}*/
		double Vi, TETA, Vx, Vy, X, Y, Vw, X0, Y0;
		int deg = 180, forc = 100, mass = 1, best_deg = 135, best_forc = 0;
		int min_dis = 1000 * 1000;
		
		for(int i = 90; i <  180 + 90; i++)
			for(int j = 100; j >= 0; j--) {
				//System.out.println(i + " " + j);
				deg = i % 180;
				tank2.degree = deg;
				rotatelule("rotatelule2", tank2.degree);
				forc = j;
				Vw = (double) wind.currentwindspeed;
				X0 = (double)tank2.Xluleend + 1;
				Y0 = (double) tank2.Yluleend - 1;				
				Vi = (double)((double)(forc) / mass);
				TETA  = (double)(deg + tank2.degreetank);
				Vx = Vi * Math.cos(Math.toRadians(TETA)) + (double)Vw;
				Vy = Vi * Math.sin(Math.toRadians(TETA));
				for(int k = tank1.Xtank; k < tank1.Xtank + tank1.widthtank; k++) {
					X = (double)k;
					Y = -(-5.0 * /*0.16 **/ (X - X0) * (X - X0) / (Vx * Vx) + Vy * (X - X0)/* 0.4*/ /  Vx) + Y0;
					if((X - X0) / Vx < 0) break; // t < 0 mishe dar inja!
					if((int)Y < zamin[(int)X] && (tank1.Ytank + zamin[(int) X] / 2) - (int) Y < min_dis ) {
						min_dis = (tank1.Ytank + zamin[(int) X] / 2) - (int) Y;
						best_deg = deg;
						best_forc = forc;
					}
				}
					
				
			}
	
		
		tank2.degree = best_deg;
		rotatelule("rotatelule2", tank2.degree);
		best_forc *= zaribkhata();
		tank2.force = best_forc;
		firebullet1(2);
	}
	
	void firebullet1(int turn_in) throws InterruptedException {
		activebullet1 = true;
		turn = turn_in;
		Threadname = "bullet1";
		bullet_animation = new Thread(this, Threadname);
		bullet_animation.start();
	}
	
	void firebullet2(int turn_in) throws InterruptedException {
		activebullet2 = true;
		turn = turn_in;
		Threadname = "bullet2";
		bullet_animation = new Thread(this, Threadname);
		bullet_animation.start();
	}

	
	public void run() {
		if(bullet_animation.getName().equals("bullet1")) {
			// seda:
			Clip firebullet1_music;
			try {
				firebullet1_music = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, AudioSystem.getAudioInputStream(new File("cannon2.wav")).getFormat()));
				firebullet1_music.open(AudioSystem.getAudioInputStream(new File("cannon2.wav")));
				firebullet1_music.start();
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}			
			// seda end!
			
			long time = System.currentTimeMillis();
			int wind_value = wind.currentwindspeed;
			wind.setnewspeed();
			int X0, Y0;
			if(turn == 1) {
				X0 = tank1.Xluleend + 1;
				Y0 = tank1.Yluleend - 1;
			}
			else
			{
				X0 = tank2.Xluleend + 1;
				Y0 = tank2.Yluleend - 1;
			}
			while(true) {
				if(turn == 1)
					bullet1.calc_move_bullet((System.currentTimeMillis() - time) / 500.0, tank1.force, tank1.degree, tank1.degreetank,wind_value/*wind.currentwindspeed*/ , X0, Y0);
				else
					bullet1.calc_move_bullet((System.currentTimeMillis() - time) / 500.0, tank2.force, tank2.degree, tank2.degreetank,wind_value/*wind.currentwindspeed*/ , X0, Y0);
				
				if(bullet1.Xbullet  < 0 || bullet1.Xbullet + bullet1.widthbullet  > zamin.length)
					break;
				
				if(bullet1.Ybullet + bullet1.heightbullet  >= zamin[bullet1.Xbullet + bullet1.widthbullet / 2]) {
					int Xfire_start = bullet1.Xbullet - 4;
					int Xfire_end = bullet1.Xbullet + bullet1.widthbullet + 4;
					if((tank1.Xtank < Xfire_end && tank1.Xtank + tank1.widthtank > Xfire_end)
					|| (tank1.Xtank < Xfire_start && tank1.Xtank + tank1.widthtank > Xfire_start)) {
						if(tank1.health <= bullet1.damage) {
							tank1.health = 0;
							winner = 2;
						}
						else
							tank1.health -= bullet1.damage;
						health1.setText("Health player1: " + Integer.toString(tank1.health) );
					}
					if((tank2.Xtank < Xfire_end && tank2.Xtank + tank2.widthtank > Xfire_end)
					|| (tank2.Xtank < Xfire_start && tank2.Xtank + tank2.widthtank > Xfire_start)) {
						if(tank2.health <= bullet1.damage) {
							tank2.health = 0;
							winner = 1;
						}
						else
							tank2.health -= bullet1.damage;
						//tank2.health -= bullet1.damage;
						health2.setText("Health player2: " + Integer.toString(tank2.health) );
					}	
					break;
					
				}
				
				//repaint();
				
				for(int j = 0; j < numberofcoins; ++j) {
					if(coininfo[j].visible && coininfo[j].check_bullet_accident(bullet1.Xbullet + bullet1.widthbullet / 2, bullet1.Ybullet + bullet1.heightbullet / 2)) {
						coininfo[j].visible = false;
						if(turn == 1) {
							tank1.coins++;
							coin1.setText("coin player1: " + Integer.toString(tank1.coins));
						}
						else {
							tank2.coins++;
							coin2.setText("coin player2: " + Integer.toString(tank2.coins));
						}
					}
				}
				
				repaint();
				try {
					Thread.sleep(33);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			activebullet1 = false;
			repaint();
		}
		if(bullet_animation.getName().equals("bullet2")) {
			
			try {
				firebullet2_music = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, AudioSystem.getAudioInputStream(new File("Explosion3.WAV")).getFormat()));
				firebullet2_music.open(AudioSystem.getAudioInputStream(new File("Explosion3.WAV")));
				firebullet2_music.start();
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
			long time = System.currentTimeMillis();
			int wind_value = wind.currentwindspeed;
			wind.setnewspeed();
			int X0, Y0;
			if(turn == 1) {
				X0 = tank1.Xluleend + 1;
				Y0 = tank1.Yluleend - 1;
			}
			else
			{
				X0 = tank2.Xluleend + 1;
				Y0 = tank2.Yluleend - 1;
			}
			while(true) {
				if(turn == 1)
					bullet2.calc_move_bullet((System.currentTimeMillis() - time) / 500.0, tank1.force, tank1.degree, tank1.degreetank,wind_value/*wind.currentwindspeed*/ , X0, Y0);
				else
					bullet2.calc_move_bullet((System.currentTimeMillis() - time) / 500.0, tank2.force, tank2.degree, tank2.degreetank,wind_value/*wind.currentwindspeed*/ , X0, Y0);
				
				if(bullet2.Xbullet  < 0 || bullet2.Xbullet + bullet2.widthbullet  > zamin.length)
					break;
				if(bullet2.Ybullet + bullet2.heightbullet  >= zamin[bullet2.Xbullet + bullet2.widthbullet / 2]) {
					
					int Xfire_start = bullet2.Xbullet;
					int Xfire_end = bullet2.Xbullet + bullet2.widthbullet;
					if(bullet2.jahat.equals("right")) {
						Xfire_start -= 4;
						Xfire_end += 8;
					}
					else {
						Xfire_start -= 8;
						Xfire_end += 4;
					}
					
					if((tank1.Xtank < Xfire_end && tank1.Xtank + tank1.widthtank > Xfire_end)
					|| (tank1.Xtank < Xfire_start && tank1.Xtank + tank1.widthtank > Xfire_start)) {
						if(tank1.health <= bullet2.damage) {
							tank1.health = 0;
							winner = 2;
						}
						else
							tank1.health -= bullet2.damage;
						//tank1.health -= bullet2.damage;
						health1.setText("Health player1: " + Integer.toString(tank1.health) );
					}
					if((tank2.Xtank < Xfire_end && tank2.Xtank + tank2.widthtank > Xfire_end)
					|| (tank2.Xtank < Xfire_start && tank2.Xtank + tank2.widthtank > Xfire_start)) {
						if(tank2.health <= bullet2.damage) {
							tank2.health = 0;
							winner = 1;
						}
						else
							tank2.health -= bullet2.damage;
						//tank2.health -= bullet2.damage;
						health2.setText("Health player2: " + Integer.toString(tank2.health) );
					}	
					
					break;
				}

				for(int j = 0; j < numberofcoins; ++j) {
					if(coininfo[j].visible && coininfo[j].check_bullet_accident(bullet2.Xbullet + bullet2.widthbullet / 2, bullet2.Ybullet + bullet2.heightbullet / 2)) {
						coininfo[j].visible = false;
						if(turn == 1) {
							tank1.coins++;
							coin1.setText("coin player1: " + Integer.toString(tank1.coins));
						}
						else {
							tank2.coins++;
							coin2.setText("coin player2: " + Integer.toString(tank2.coins));
						}
					}
				}
				
				
				repaint();
				
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			activebullet2 = false;
		}
		//wind.setnewspeed();
		if(winner != 0)
			endf = new end_frame(winner);
		else {
			try {
				if(turn == 1 && gamemode == 0) {
					player2_automatic_move();
					repaint();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	
	}
	/*void play_accidentzamin_music() {
		try {
			accidentzamin_music = (Clip) AudioSystem.getLine(new DataLine.Info(Clip.class, AudioSystem.getAudioInputStream(new File("Explos.WAV")).getFormat()));
			accidentzamin_music.open(AudioSystem.getAudioInputStream(new File("Explosion3.WAV")));
			accidentzamin_music.start();
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}*/
	
}


class wind_panel extends JPanel{
	JLabel windlabel;
	JTextField windspeed; 
	arrows arrow;
	int maxwindspeed, currentwindspeed;
	Random randspeed;
	
	wind_panel(int x, int y, int Maxwindspeed) {
		setLayout(null);
		setBounds(x, y, 120, 110);
		
		windlabel = new JLabel("Wind:");
		windlabel.setBounds(10, 20, 50, 20);
		add(windlabel);
		
		windspeed = new JTextField("0 Km/h");
		windspeed.setEditable(false);
		windspeed.setBounds(20, 40 ,90 , 20);
		add(windspeed);
		
		arrow = new arrows();
		arrow.setLocation(10, 60);
		add(arrow);
		//revalidate();
		
		maxwindspeed = Maxwindspeed;
		randspeed = new Random();
		currentwindspeed = 0;
		setnewspeed();
	}
	void setnewspeed() {
		int randspeedtmp = randspeed.nextInt(2 * maxwindspeed + 1) - maxwindspeed;
		currentwindspeed = randspeedtmp;
		String speedtmp = new String(); 
		if(randspeedtmp >= 0) {
			arrow.changepicture("right");
			speedtmp = ( new Integer(randspeedtmp).toString() + " Km/h");
		}
		else {
			arrow.changepicture("left");
			speedtmp = ( new Integer(-1 * randspeedtmp).toString() + " Km/h");
		}
		windspeed.setText(speedtmp);
		repaint();
	}
	

}


class arrows extends JPanel{
	File right, left;
	String namearrow;
	arrows() {
		right = new File("RightArrow.png");
		left = new File("LeftArrow.png");
		namearrow = new String("right");
		setSize(100, 50);
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			doDrawing(g, namearrow);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void doDrawing(Graphics g, String name) throws IOException {
		Graphics2D g2d = (Graphics2D )g;
		BufferedImage img;
		if(name.equals("right")) {
			img = ImageIO.read(right);
			g2d.drawImage(img, 0, 0, 100, 50, null);
		}
		else {
			img = ImageIO.read(left);
			g2d.drawImage(img, 0, 0, 100, 50, null);
			
		}
		
	}
	void changepicture(String name) {
		namearrow = name;
		repaint();
	}

}


class tank_head extends JPanel{

	tank_head() throws IOException {
		
        setSize(45, 20);
	}
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    try {
			doDrawing(g);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void doDrawing(Graphics g) throws IOException {
        Graphics2D g2d = (Graphics2D) g;
        BufferedImage img_tank = ImageIO.read(new File("tank.png"));
        g2d.drawImage(img_tank, 0, 0, 45, 20, null);
	}
	
  
	

}



class tank_information {
	
	int heighttank, widthtank, Xtank, Ytank;
	int heightlule, widthlule, Xlule, Ylule, Xluleend, Yluleend;
	int health, force, degree, coins, numberofbullet2, typeofbullet;
	int degreetank;
	int tmpX, tmpY, tmpluleX, tmpluleY;
	
	tank_information() {
		heighttank = 24;
		widthtank = 44;
		heightlule = 8;
		widthlule = 40;
		health = 100;
		force = 50;
		degree = 90;
		coins = 3;
		numberofbullet2 = 0;
		typeofbullet = 1;
		tmpX = tmpY = tmpluleX = tmpluleY = 0;
		// Xtank, Ytank Xlule, Ylule, Xluleend, Yluleend 
	}
	void set_information(int i, int j, int degree_val) {
		degree = degree_val;
		Xtank = 4 * j - 20;
		Ytank = 4 * i - 20;
		Xlule = 4 * j + 2;
		Ylule = 4 * i - 20;
		Xluleend = (int) ((double)Xlule + (double) widthlule * Math.cos( Math.toRadians((double) degree) ) );
		Yluleend = (int) ((double)Ylule - (double) widthlule * Math.sin( Math.toRadians((double) degree) ) );
		
		
	}
	void set_lule_ends(int degree_val){
		degree = degree_val;
		Xluleend = (int) ((double)Xlule + (double) widthlule * Math.cos( Math.toRadians((double) (degree + degreetank)) ) );
		Yluleend = (int) ((double)Ylule - (double) widthlule * Math.sin( Math.toRadians((double) (degree + degreetank)) ) );
		
	}
	void set_degree_tank(int degree_val) {
		degreetank = degree_val;
		if(degreetank == 45) {
			tmpX = -1;
			tmpY = -2;
			tmpluleX = -6;
			tmpluleY = 0;
		}
		if(degreetank == 360 - 45) {
			tmpX = +1;
			tmpY = -2;
			tmpluleX = +6;
			tmpluleY = +0;
		}
	}
	
	
}


class coin_information {
	int heightcoin, widthcoin, Xcoin, Ycoin;
	boolean visible;
	coin_information() {
		heightcoin = widthcoin = 20;
		visible = true;
	}
	void set_coin_location(int x, int y){
		Xcoin = x;
		Ycoin = y;
	}
	boolean check_bullet_accident(int x, int y) {
		if(x >= Xcoin && x <= Xcoin + widthcoin && y >= Ycoin && y <= Ycoin + heightcoin)
			return true;
		else
			return false;
	}
}


class bullet {
	
	int damage, type, mass;
	int Xbullet, Ybullet, widthbullet, heightbullet;
	double Vi, TETA, Vx, Vy, X, Y;
	String jahat;
	
	bullet(int damage_val, int type_val, int mass_val) {
		damage = damage_val;
		type = type_val;
		mass = mass_val;
		widthbullet = 8;
		heightbullet = 8;
		jahat = new String(); // baraye bullet e 2 e!
		
	}
	void set_bullet_location(int x, int y) {
		Xbullet = x;
		Ybullet = y;
	}
	void calc_move_bullet(double time, int force, int TETAi, int TETAH, int Vw, int X0, int Y0) {
		Vi = (double)((double)(force) / mass);
		TETA  = (double)(TETAi + TETAH);
		Vx = Vi * Math.cos(Math.toRadians(TETA)) + (double)Vw;
		Vy = Vi * Math.sin(Math.toRadians(TETA));
		X = Vx * time + (double)X0;
		Y = -1.0 * ((double)(-5 * time * time) + (double)(Vy * time)) + (double)Y0;
		Xbullet = (int) X;
		Ybullet = (int) Y;
		if(/*Xbullet > X0*/ Vx * time > 0)
			jahat = "right";
		if(/*Xbullet < X0*/ Vx * time < 0)
			jahat = "left";
	}
}

