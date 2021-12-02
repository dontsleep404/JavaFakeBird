package entity;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Background implements IRender{
	Image img = new ImageIcon("sprites/background-day.png").getImage();
	int fx = 0;
	int w;
	int h;
	public Background(int w, int h) {
		this.w = w;
		this.h = h;
	}
	public void draw(Graphics2D g2d) {
		for(int x=fx;x<=w;x+=img.getWidth(null)) {
			g2d.drawImage(img,x,0, null);
		}
		
	}
	public void update() {
		fx--;		
	}
}
