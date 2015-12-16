package pongPakk;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Pong extends JPanel implements KeyListener {

	private JFrame parentFrame;
	private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
	private int gameWidth;
	private int gameHeight;
	private int[] leftPaddle;
	private int[] rightPaddle;
	private int ballPositionX;
	private int ballPositionY;
	private int[][] completeBoard;
	private int leftPaddlePosition = 5;
	private int rightPaddlePosition = 7;
	private int paddleSize = 8;
	private int gameSpeed = 50;
	private Timer taimer;

	public Pong(JFrame raam) {
		this.setBackground(Color.BLACK);
		parentFrame = raam;
		parentFrame.addKeyListener(this);
		setScreenSize();
		setupGame();	
	}
	
	public void setupGame() {
		
		gameHeight = gameHeight/10;
		gameWidth = gameWidth/10;
		
		leftPaddle = new int[gameHeight];
		rightPaddle = new int[gameHeight];
		completeBoard = new int[gameHeight][gameWidth];
		
		ballPositionX = gameWidth/2;
		ballPositionY = gameHeight/2;
		
		taimer = new Timer();
		BallTimer a = new BallTimer(completeBoard);
		a.setBallHeight(ballPositionY);
		a.setBallWidth(ballPositionX);
		a.setGameHeight(gameHeight);
		a.setGameWidth(gameWidth);
		taimer.scheduleAtFixedRate(a, 0, gameSpeed);
		
		for(int j = 0; j < gameWidth; j++) {
			for(int i = 0; i < gameHeight; i++) {
				
				if(j == leftPaddlePosition) {

					for(int i2 = gameHeight/3; i2<gameHeight/3 + paddleSize; i2++) {
						leftPaddle[i2] = 1;
						completeBoard[i2][j] = 1;
					}
					
				}
				
				if(j == gameWidth - rightPaddlePosition) {
					
					for(int i3 = gameHeight/3; i3<gameHeight/3 + paddleSize; i3++) {
						rightPaddle[i3] = 1;
						completeBoard[i3][j] = 1;
					}
					
				}	
			}	
		}
	}
			
	public void paintComponent(Graphics g) {
		super.paintComponent(g);	
				
		for(int j = 0; j < gameWidth; j++) {
			for(int i = 0; i < gameHeight; i++) {
		
		if(completeBoard[i][j] == 1) {
			g.setColor(Color.WHITE);
			g.fillRect(j * 10, i * 10, 10, 10);
		}
		
		if(completeBoard[i][j] == 3) {
			g.setColor(Color.GREEN);
			g.fillRect(j * 10, i * 10, 10, 10);
		}
				
			}
		}	
		this.repaint();
	}

	@Override
	public void keyPressed(KeyEvent evt) {

		int keyCode = evt.getKeyCode();
		
		if(evt.getKeyChar() == 'w' || evt.getKeyChar() == 'W' ) {
			moveLeftPaddleUp();
		}
		
		if(evt.getKeyChar() == 's' || evt.getKeyChar() == 'S' ) {
			moveLeftPaddleDown();
		}
		
		if(keyCode == KeyEvent.VK_UP) {
	     	moveRightPaddleUp();
        }
		
		if(keyCode == KeyEvent.VK_DOWN) {
	     	moveRightPaddleDown();
        }
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
		
	public void setScreenSize() {
		
		gameWidth = screenWidth/2;
		gameHeight = screenHeight/2;
		
		while(!((gameWidth%10) == 0)) {
			gameWidth = gameWidth + 1;
		}
		
		while(!((gameHeight%10) == 0)) {
			gameHeight = gameHeight + 1;
		}
		
		parentFrame.setSize(gameWidth, gameHeight);
		this.setSize(parentFrame.getSize());
		
	}
	
	public void moveLeftPaddleUp() {
		
		if (leftPaddle[0] == 0) {
			
			for(int i = 0; i < gameHeight; i++) {
				
				if(leftPaddle[i] == 1) {
					leftPaddle[i] = 0;
					leftPaddle[i - 1] = 1;
					completeBoard[i][5] = 0;
					completeBoard[i - 1][5] = 1;
				}			
			}
			
		}
		this.repaint();
    	}          
	
	public void moveLeftPaddleDown() {
		
		if (leftPaddle[leftPaddle.length - 4] == 0) {
			
		for(int i = gameHeight - 1; i >= 0; i--) {
			
				if(leftPaddle[i] == 1) {
					leftPaddle[i] = 0;
					leftPaddle[i + 1] = 1;
					completeBoard[i][5] = 0;
					completeBoard[i + 1][5] = 1;		
				}
		}
		}		
		this.repaint();
	}
	
	public void moveRightPaddleUp() {	
		
		if (rightPaddle[0] == 0) {
			
			for(int i = 0; i < gameHeight; i++) {	
				
				if(rightPaddle[i] == 1) {
					rightPaddle[i] = 0;
					rightPaddle[i - 1] = 1;
					completeBoard[i][gameWidth - rightPaddlePosition] = 0;
					completeBoard[i - 1][gameWidth - rightPaddlePosition] = 1;

				}	
			}
		}
		this.repaint();
	}
	
	public void moveRightPaddleDown() {
		
		if (rightPaddle[leftPaddle.length - 4] == 0) {
			
		for(int i = gameHeight - 1; i >= 0; i--) {		
				
			if(rightPaddle[i] == 1) {
					rightPaddle[i] = 0;
					rightPaddle[i + 1] = 1;
					completeBoard[i][gameWidth - 7] = 0;
					completeBoard[i + 1][gameWidth - 7] = 1;
			}				
		}
		}
		this.repaint();
	}

	public static void main(String[] args) {

	JFrame raam = new JFrame();		
	Pong paneel = new Pong(raam);
	raam.add(paneel, BorderLayout.CENTER);
	
	raam.setLocationRelativeTo(null);
	raam.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	raam.setResizable(false);
	raam.setVisible(true);
	
	}

}