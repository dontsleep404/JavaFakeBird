package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Date;

import javax.swing.ImageIcon;

public class Pump implements IRender{
	int posX;
	int preX;
	int topY;
	int botY;
	int space;	
	long nowTime;
	long preTime;
	private boolean pass = false;
	Image pump = new ImageIcon("sprites/pipe-green.png").getImage();
	public Pump(int posX, int space) {
		nowTime = (new Date()).getTime();
		preTime = nowTime;
		this.space = space;
		this.posX = posX;preX = posX;
		this.topY = (int) (Math.random()*200) + 50;
		this.botY = topY+space;
	}
	public void update() {		
		preTime = nowTime;
		preX = posX;
		posX -=5;
		nowTime = (new Date()).getTime();
	}

	@Override
	public void draw(Graphics2D g2d) {
		float paraticks = (float) (((new Date()).getTime() - nowTime)/Math.max(nowTime - preTime,0.01));
		int px = (int) (preX + (posX - preX)*paraticks);
		g2d.drawImage(pump, px, topY, 52, -320, null);
		g2d.drawImage(pump, px, botY, 52, 320, null);
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getTopY() {
		return topY;
	}
	public void setTopY(int topY) {
		this.topY = topY;
	}
	public int getBotY() {
		return botY;
	}
	public void setBotY(int botY) {
		this.botY = botY;
	}
	public int getW() {
		return 52;
	}
	public boolean isPass() {
		return pass;
	}
	public void setPass(boolean pass) {
		this.pass = pass;
	}

}
