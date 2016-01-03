package paintingPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PaintingPanel extends JPanel implements MouseListener, MouseMotionListener {

	private int mousePressedX;
	private int mousePressedY;
	private boolean dragging = false;
	private JButton deletePicture;
	
	public PaintingPanel() {
		this.setPreferredSize(new Dimension(300,300));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		deletePicture = new JButton("Delete picture");
		this.add(deletePicture);
		
		deletePicture.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				paintAgain();
			}
		});
		
	}
	
	public void paintAgain() {
		this.repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}

	@Override
	public void mouseDragged(MouseEvent evt) {

		if(dragging) {
			this.getGraphics().drawLine(mousePressedX, mousePressedY, evt.getX(), evt.getY());
			mousePressedX = evt.getX();
			mousePressedY = evt.getY();
		}	
	}

	@Override
	public void mouseMoved(MouseEvent evt) {	
	}

	@Override
	public void mouseClicked(MouseEvent evt) {
	}

	@Override
	public void mouseEntered(MouseEvent evt) {
	}

	@Override
	public void mouseExited(MouseEvent evt) {	
	}

	@Override
	public void mousePressed(MouseEvent evt) {
		mousePressedX = evt.getX();
		mousePressedY = evt.getY();
		dragging = true;
	}

	@Override
	public void mouseReleased(MouseEvent evt) {
		dragging = false;
		deletePicture.repaint();
	}
	
	public static void main(String[] args) {
		JFrame mainFrame = new JFrame();
		PaintingPanel panel = new PaintingPanel();
		
		mainFrame.add(panel);
		
		mainFrame.pack();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
}
