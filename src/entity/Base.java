package entity;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Base implements IRender{
	int posY;
	int fx = 0;
	Image base = new ImageIcon("sprites/base.png").getImage();
	public Base(int posY) {
		this.posY = posY;
	}
	@Override
	public void update() {
		fx-=5;
	}

	@Override
	public void draw(Graphics2D g2d) {
		for(int x = fx;x<=800;x+=base.getWidth(null)) {
			g2d.drawImage(base, x, posY, null);
		}		
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	

}
