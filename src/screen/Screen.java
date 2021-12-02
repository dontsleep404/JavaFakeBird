package screen;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import entity.Background;
import entity.Base;
import entity.Bird;
import entity.Pump;
import entity.Score;

@SuppressWarnings("serial")
public class Screen extends JPanel implements MouseListener, KeyListener{
	Background background;
	Bird bird;
	Base base;
	ArrayList<Pump> pumps;
	Score score;
	Image imgOver = new ImageIcon("sprites/gameover.png").getImage();
	boolean isOver = false;
	final int spacing = 200;
	public void newGame() {
		score = new Score(800, 500, 0);
		isOver = false;
		background = new Background(800,500);
		bird = new Bird();
		base = new Base(450);
		pumps = new ArrayList<>();
		int spa = 200;
		int fx = 500;
		for(int i = 0 ; i < 6;i++) {
			pumps.add(new Pump(fx, spacing));
			fx+=spa;
		}
	}
	public void gameOver() {
		isOver = true;
	}
	public Screen() {
		setFocusable(true);
	    requestFocus();
	    requestFocusInWindow();
		addMouseListener(this);
		addKeyListener(this);		
		setVisible(true);
		newGame();
		Timer update = new Timer(1000/60, new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();				
			}
		});
		update.start();
		Timer draw = new Timer(1000/20, new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				onUpdate();				
			}
		});
		draw.start();		
	}
	public void onUpdate() {
		background.update();		
		if(!isOver) {			
			bird.update();
			for(Pump p : pumps) {
				p.update();
			}
			base.update();
			if(!pumps.isEmpty()) {
				if(pumps.get(0).getPosX() <= -pumps.get(0).getW()) {
					pumps.remove(0);
					pumps.add(new Pump(pumps.get(pumps.size()-1).getPosX()+200, spacing));
				}
			}
			if(bird.getPosY() + bird.getH() >= base.getPosY()) {
				isOver = true;
			}
			if(bird.getPosX()+bird.getW() >= pumps.get(0).getPosX() && !pumps.get(0).isPass()) {				
				if(bird.getPosX() > pumps.get(0).getPosX() + pumps.get(0).getW()) {
					pumps.get(0).setPass(true);
					score.setScore(score.getScore()+12);
				}
				if(bird.getPosY()<=pumps.get(0).getTopY() || bird.getPosY()+bird.getH() >= pumps.get(0).getBotY()) {
					isOver = true;
				}
			}	
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		smoothPaint(g2d);		
		background.draw(g2d);
		if(!isOver) {
			for(Pump p : pumps) {
				p.draw(g2d);
			}			
			bird.draw(g2d);	
			score.draw(g2d);
		}else {
			int x = (getWidth() - imgOver.getWidth(null))/2;
			int y = (getHeight() - imgOver.getHeight(null))/2;
			g2d.drawImage(imgOver, x, y, imgOver.getWidth(null), imgOver.getHeight(null), null);
		}
		base.draw(g2d);
		g2d.dispose();
	}
	public void smoothPaint(Graphics2D g2d) {
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);            
        g2d.setStroke(new BasicStroke(6,BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
	}
	public Dimension getPreferredSize() {
        return new Dimension(800, 500);
    }
	@Override
	public void keyTyped(KeyEvent e) {		
	}

	@Override
	public void keyPressed(KeyEvent e) {		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(isOver) {
				newGame();
			}else {
				bird.onFly();	
			}					
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {		
	}

	@Override
	public void mouseReleased(MouseEvent e) {		
	}

	@Override
	public void mouseEntered(MouseEvent e) {		
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
