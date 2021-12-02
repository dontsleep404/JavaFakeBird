package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Date;

import javax.swing.ImageIcon;

public class Bird implements IRender{
	int preX;
	int preY;
	int posX;
	int posY;	
	int birdStage = 0;
	float motionY = 0;
	long nowTime;
	long preTime;
	Image birdImage[];
	float vec = 1.5f;
	int w = 34;
	int h = 24;
	public Bird() {
		nowTime = (new Date()).getTime();
		preTime = nowTime;
		posX = 50;
		posY = 100;
		birdImage = new Image[3];
		birdImage[0] = new ImageIcon("sprites/yellowbird-downflap.png").getImage();
		birdImage[1] = new ImageIcon("sprites/yellowbird-midflap.png").getImage();
		birdImage[2] = new ImageIcon("sprites/yellowbird-upflap.png").getImage();
	}	
	public void onFly() {
		motionY = -10*vec;
	}
	@Override
	public void update() {		
		preTime = nowTime;		
		motionY+=vec;
		preY = posY;
		posY += motionY;
		nowTime = (new Date()).getTime();
	}
	@Override
	public void draw(Graphics2D g2d) {
		float paraticks = (float) (((new Date()).getTime() - nowTime)/Math.max(nowTime - preTime,0.01));
		Image img;
		if(motionY == 0) {
			img = birdImage[1];
		}else if(motionY > 0) {
			img = birdImage[2];
		}else {
			img = birdImage[0];
		}
		int bx = posX;
		int by = (int) (preY + (posY - preY)*paraticks);
		//g2d.drawImage(img, bx, by, null);
		g2d.drawImage(img, bx, by, w, h, null);
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	
}
