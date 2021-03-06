package snake_Ofri;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Snake_Panel extends JPanel{

	private ArrayList<Snake_Rect> rectList = new ArrayList<Snake_Rect>();
	private Snake_Rect rect1;
	private Snake_Apple apple;
	private int score = 0, size = 10;
	private Snake_Frame frame;
	private boolean updated;
	
	private Timer panelTimer = new Timer(40, new TimerAction());
	
	public Snake_Panel(Snake_Frame frame)
	{
		setBackground(Color.WHITE);
		this.frame = frame;
		this.updated = false;
		
		for(int i=0; i<3; i++)
		{
			if(i==0)
				rectList.add(new Snake_Rect(0, 0, size, null));
			else
				rectList.add(new Snake_Rect(0, 0, size, rectList.get(i-1)));
		}
		
		rect1 = rectList.get(0);
		
		apple = newApple();
		
		JButton reset = new JButton("reset");
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(rectList.size() == 3)
					rectList.add(null);
				while(rectList.size() > 3)
				{
					rect1.setX(0);
					rect1.setY(0);
					rect1.setDirection('r');
					rectList.remove(rectList.size()-1);
					rect1.setActive(true);
					score = 0;
				}
			}
		});
		reset.setBackground(Color.GREEN);
		reset.setFont(new Font("AR CHRISTY", Font.PLAIN, 15));
		reset.setForeground(Color.RED);
		this.add(reset);
		
		InputMap im = this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = this.getActionMap();
		
		im.put(KeyStroke.getKeyStroke("RIGHT"), "right");
		am.put("right", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(rect1.getDirection() != 'l' && updated)
				{
					rect1.setDirection('r');
					updated = false;
				}	
			}
		});
		
		im.put(KeyStroke.getKeyStroke("LEFT"), "left");
		am.put("left", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(rect1.getDirection() != 'r' && updated)
				{
					rect1.setDirection('l');
					updated = false;
				}
					
			}
		});
		
		im.put(KeyStroke.getKeyStroke("UP"), "up");
		am.put("up", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(rect1.getDirection() != 'd' && updated)
				{
					rect1.setDirection('u');
					updated = false;
				}		
			}
		});
		
		im.put(KeyStroke.getKeyStroke("DOWN"), "down");
		am.put("down", new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(rect1.getDirection() != 'u' && updated)
				{
					rect1.setDirection('d');
					updated = false;
				}
					
			}
		});
	
		panelTimer.start();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		for(Snake_Rect r:rectList)
			r.paintRect(g);
		
		apple.paintApple(g);
		
		Font font = new Font("AR DELANEY", Font.PLAIN, 30);
		String str = "Score: " + score;
		
		g.setFont(font);
		g.setColor(Color.BLUE);
		g.drawString(str, frame.getWidth()/2 - g.getFontMetrics(font).stringWidth(str)/2,  frame.getHeight()-40);
		updated = true;
	}
	
	public void updatePanel()
	{
		for(int i=rectList.size()-1; i>=0; i--)
			rectList.get(i).RectUpdate();
		
		if(rect1.getBounds().intersects(apple.getBounds()))
		{
			apple = newApple();
			rectList.add(new Snake_Rect(rectList.get(rectList.size()-1).getX(), rectList.get(rectList.size()-1).getY(), size, rectList.get(rectList.size()-1)));
			score ++;
		}
		
		if(rect1.getX() > 590 || rect1.getY() > 560 || rect1.getX() < 0 || rect1.getY() < 0)
			rect1.setActive(false);
		
		for(int i=1; i<rectList.size()-1; i++)
			if(rect1.getBounds().intersects(rectList.get(i).getBounds()))
				rect1.setActive(false);
		
		repaint();
	}
	
	public Snake_Apple newApple()
	{
		int xa = (int)(Math.random()*591/size)*size;
		int ya = (int)(Math.random()*561/size)*size;
		
		return new Snake_Apple(xa, ya, size);
	}
	
	private class TimerAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(rect1.isActive())
				updatePanel();
		}
	}
}
