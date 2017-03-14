package spaceShooter_Ofri;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Space_Bullet {

	private int x, y, size;
	private String d;
	private boolean active;
	private Ellipse2D bullet;
	private Space_Panel panel;
	
	Space_Bullet(int x, int y, String direction, int size, Space_Panel panel)
	{
		this.x = x + 5*size - 5;//Sets the x of bullet to the middle of the player
		this.d = direction;
		this.size = size;
		this.panel = panel;
		if(d == "up")//sets the y of bullet away from the player
			this.y = y - 7*size;//y - the size of the head
		else
			this.y = y + 7*size + 10*size;//y + the size of head + body
		active = false;
	}
	
	/*Paints the bullet*/
	public void paintBullet(Graphics g)
	{	
		Graphics2D g2d = (Graphics2D)g;
		
		bullet = new Ellipse2D.Double(x, y, 10, 10);
		g2d.setColor(Color.GREEN); //Sets the color to green
		g2d.draw(bullet);
	}
	
	/*Updates*/
	public void bulletUpdate()
	{
		if(d == "up")
			y -= 3;
		else
			y += 3;
		
		bullet = new Ellipse2D.Double(x, y, 10, 10);
		
		if(d == "up" && bullet.intersects(panel.getPlayer("p2").getBounds()))
		{
			panel.getPlayer("p2").damageHealth();
			
			this.active = false;
		}	
		else if(d == "down" && bullet.intersects(panel.getPlayer("p1").getBounds()))
		{
			panel.getPlayer("p1").damageHealth();
			this.active = false;
		}
			
		if(y > panel.getHeight() || y < 0)//If the bullet gets out of the screen
		{
			this.active = false;
		}
	}
	
	public void activated()
	{
		this.active = true;
	}
	
	public boolean isActive()
	{
		return active;
	}

	public void setX(int x) {
		this.x = x + 5*size - 5;
	}

	public void setY(int y) {
		if(d == "up")
			this.y = y - 7*size;
		else
			this.y = y + 7*size + 10*size;
	}
}
