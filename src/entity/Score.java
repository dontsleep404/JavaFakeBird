package entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Score implements IRender{
	int score;
	int w;
	int h;
	int ow;
	Image[] nums;
	public Score(int w, int h, int score) {
		this.score = 0;
		this.w = w;
		this.h = h;
		nums = new Image[10];
		for(int i = 0 ; i <= 9;i++) {
			nums[i] = new ImageIcon("sprites/"+i+".png").getImage();			
		}
		ow = nums[0].getWidth(null);
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	@Override
	public void update() {
		
	}
	@Override
	public void draw(Graphics2D g2d) {
		ArrayList<Image> im = new ArrayList<>();
		int s = score;
		if(s == 0) {
			im.add(nums[0]);
		}
		while(s > 0) {
			im.add(0,nums[s % 10]);
			s/=10;
		}
		int fx = (w - ow*im.size())/2;
		int y = 50;
		for(Image i : im) {
			g2d.drawImage(i, fx, y, null);
			fx+=ow;
		}
	}
	
}
