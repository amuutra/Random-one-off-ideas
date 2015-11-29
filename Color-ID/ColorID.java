package colorGetter;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ColorID implements KeyListener {

	private Robot robot = new Robot();
	private JFrame mainFrame;
	private JPanel startPanel;
	private int numberOfColors; 
	private JLabel instructionLabel;
	
	public ColorID() throws AWTException {

	}
	
	public void showUI() {
		
		mainFrame = new JFrame("Color-ID");
		mainFrame.setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.Y_AXIS));
		
		startPanel = new JPanel();
		instructionLabel = new JLabel("Point the mouse at something and hit Space" + "     (" + numberOfColors + "/10)" );
		
		startPanel.add(instructionLabel);	
		mainFrame.add(startPanel);
	
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.addKeyListener(this);	
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);	
		
	}
	
	public static void main(String[] args) throws AWTException {
		ColorID start = new ColorID();
		start.showUI();
	}
	
	public void showSelectedColor(Color color) {

		JPanel colorPanel = new JPanel();

		int currentRed = color.getRed();
		int currentGreen = color.getGreen();
		int currentBlue = color.getBlue();
		
		String hexRed = Integer.toHexString(currentRed);
		String hexGreen = Integer.toHexString(currentGreen);
		String hexBlue = Integer.toHexString(currentBlue);
		
		JLabel redLabel = new JLabel("Red: " + currentRed);
		JLabel greenLabel = new JLabel("Green: " + currentGreen);
		JLabel blueLabel = new JLabel("Blue: " + currentBlue);
		JLabel hexCode = new JLabel("#" + hexRed + "" + hexGreen + "" + hexBlue);
			
		JButton colorDisplay = new JButton();
		colorDisplay.setText("\n" + "\n" + "\n");
		colorDisplay.setBackground(color);
		colorDisplay.setEnabled(false);
		
		JButton closeButton = new JButton("(X)");
		closeButton.setForeground(Color.RED);
		closeButton.setBorderPainted(false);
		closeButton.setFocusable(false);
		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainFrame.remove(colorPanel);
				mainFrame.pack();
				numberOfColors = numberOfColors - 1;
				instructionLabel.setText("Point the mouse at something and hit Space" + "     (" + numberOfColors + "/10)" );
			}});
		
		colorPanel.add(redLabel);
		colorPanel.add(greenLabel);
		colorPanel.add(blueLabel);
		colorPanel.add(hexCode);
		colorPanel.add(colorDisplay);
		colorPanel.add(closeButton);
		
		mainFrame.add(colorPanel);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);

	}
	
	public Color getColorAt(int x, int y) {
		return robot.getPixelColor(x,  y);
	}

	@Override
	public void keyTyped(KeyEvent evt) {
		
		if (Character.isSpaceChar(evt.getKeyChar()) && numberOfColors<10) {
			int x = (int) MouseInfo.getPointerInfo().getLocation().getX();
			int y = (int) MouseInfo.getPointerInfo().getLocation().getY();		
			showSelectedColor(getColorAt(x, y));
			numberOfColors = numberOfColors + 1;
			instructionLabel.setText("Point the mouse at something and hit Space" + "     (" + numberOfColors + "/10)" );
        }
		
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
	}

}