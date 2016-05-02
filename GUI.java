import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GUI extends Thread implements KeyListener, ActionListener {
	private JFrame frame;
	private JPanel[][] display;
	private Grid grid; 
	
	private int playerKey = Grid.USERKEY_D;
	
	private MenuItem menuItemStart = new MenuItem("Start/Restart");
	private MenuItem menuItemAbout = new MenuItem("About");
	private MenuItem menuItemHelp = new MenuItem("Help");
	private MenuItem menuItemQuit = new MenuItem("Quit");
	
	public static void main(String[] args) {
		new GUI();
	}
	
	
	
	
	
	public GUI() {	
		//The Grid object plays the actual game
		this.grid = null;
		//Create a JFrame for the game
		this.frame = new JFrame("Tetris");
		
		
		//Create the menu
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu");;
		menuBar.add(menu);
		this.menuItemStart.addActionListener(this);
		this.menuItemAbout.addActionListener(this);
		this.menuItemHelp.addActionListener(this);
		this.menuItemQuit.addActionListener(this);
		menu.add(menuItemStart);
		menu.add(menuItemAbout);
		menu.add(menuItemHelp);
		menu.add(menuItemQuit);
		

		
		
		
		this.frame.setMenuBar( menuBar );
		
		this.frame.setLayout(new GridLayout(25, 10));
		
		this.display = new JPanel[10][25];
		
		for (int i=0; i<10; i++) {
			for (int j=0; j<25; j++) {
				this.display[i][j] = new JPanel();
				this.display[i][j].setSize( new Dimension(20, 20));
				if ( (i+j)%2==0) 
					this.display[i][j].setBackground( Color.BLUE);
				else 
					this.display[i][j].setBackground( Color.GREEN);
			}
		}
		
		
		for (int j=0; j<25; j++) {
			for (int i=0; i<10; i++) {
			
				this.frame.add( this.display[i][j] );
				
			}
		}
		
		//The Grid object plays the actual game
		this.grid=null;
		
		this.frame.addKeyListener(this);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setSize(300, 500);
		this.frame.setVisible(true);
		this.frame.repaint();
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	




	private boolean updateMenu(Menu m, String s) {
		String s0 = m.getLabel();
		return (!s.equals(s0));
	}

	public void run() {
		int delayTime = 160;
		
		for (int i=0; i<10; i++)
			for (int j=0; j<25; j++)
				this.display[i][j].setBackground(Color.BLACK);
		
		while (true) {
			
			
			this.frame.setTitle("score " + this.grid.getScore() + " | lines " + this.grid.getLines() + " | level " + this.grid.getLevel() );
			
			for (int i=0; i<5; i++) {
				for (int j=0; j<5; j++) {
					Color c0 = this.display[i+3][j].getBackground();
					Color c = this.grid.getPreviewColor(j, i);
					if (!c0.equals(c)) 
						this.display[i+3][j].setBackground(c);
				}
			}
			
			
			
			
			for (int i=0; i<10; i++) for (int j=0; j<20; j++) {
				
				Color c0 = this.display[i][j+5].getBackground();
				Color c = this.grid.getBlockColor(j, i);
				if (!c0.equals(c)) 
					this.display[i][j+5].setBackground(c);
			}
			this.frame.setForeground(Color.DARK_GRAY);
			
			this.grid.move(this.playerKey);
			this.playerKey = Grid.USERKEY_D;
			try {
				Thread.sleep(delayTime);
			} catch (InterruptedException e) {
				return;
			}
			
		}
	}













	public void keyTyped(KeyEvent e) {
		// Nothing
	}

	
	
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()== KeyEvent.VK_RIGHT)
            this.playerKey = Grid.USERKEY_R;
		
        else if(e.getKeyCode()== KeyEvent.VK_LEFT)
        	this.playerKey = Grid.USERKEY_L;
		
        else if(e.getKeyCode()== KeyEvent.VK_DOWN)
        	this.playerKey = Grid.USERKEY_D;
		
        else if(e.getKeyCode()== KeyEvent.VK_UP)
        	this.playerKey = Grid.USERKEY_U;
		
		
	}

	
	public void keyReleased(KeyEvent e) {
		
	}




	
	
	
	
	
	
	
	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == menuItemStart) {
			this.grid = new Grid(10, 20);
			if (!this.isAlive())
				this.start();
			
		} else if (e.getSource() == menuItemAbout) {
			System.out.println("ABOUT");
			
		} else if (e.getSource() == menuItemHelp) {
			System.out.println("HELP");
			
		} else if (e.getSource() == menuItemQuit) {
			this.frame.setVisible(false);
			this.frame.dispose();
		}
	}
	
	
	
}
