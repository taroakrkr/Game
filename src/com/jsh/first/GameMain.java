package com.jsh.first;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;



public class GameMain extends JFrame implements Runnable, KeyListener {
	private BufferedImage bi = null;
	private ArrayList enListN = null;
	private ArrayList enListS = null;
	private ArrayList enListE = null;
	private ArrayList enListW = null;
	private boolean left = false, right = false, up = false, down = false;
	private boolean start = false, end = false;
	private int w = 500, h = 500, x = 130, y = 450, xw = 20, xh = 20;
	private long sTime;
	private long eTime;

	public GameMain() {
		bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		enListN = new ArrayList();
		enListS = new ArrayList();
		enListE = new ArrayList();
		enListW = new ArrayList();                                                                                               
		this.addKeyListener(this);
		this.setSize(w, h);
		this.setTitle("GameMaining Game");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public void run() {
		try {
			int enCnt = 0;
			while (true) {
				Thread.sleep(10);

				if (start) {
					if (enCnt > 1000) {
						enCreate();
						enCnt = 0;
					}
					enCnt += 10;
					keyControl();
					crashChk();
				}
				draw();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enCreate() {
		for (int i = 0; i < 3; i++) {
			double nx = Math.random() * (w - xw);
			double ny = Math.random() * 50;
			Enemy enN = new EnemyN((int) nx, (int) ny);
			enListN.add(enN);
			double sx = Math.random() * (w - xw);
			double sy = Math.random() * 50 + h-50;
			Enemy enS = new EnemyS((int) sx, (int) sy);
			enListS.add(enS);
			double ex = Math.random() * 50 + w -50;
			double ey = Math.random() * (h - xh);
			Enemy enE = new EnemyE((int) ex, (int) ey);
			enListW.add(enE);
			double wx = Math.random() * 50;
			double wy = Math.random() * (h - xh);
			Enemy enW = new EnemyW((int) wx, (int) wy);
			enListW.add(enW);
		}
	}

	public void crashChk() {
		Graphics g = this.getGraphics();
		Polygon p = null;
		for (int i = 0; i < enListN.size(); i++) {
			Enemy e = (Enemy) enListN.get(i);
			int[] xpoints = { x, (x + xw), (x + xw), x };
			int[] ypoints = { y, y, (y + xh), (y + xh) };
			p = new Polygon(xpoints, ypoints, 4);
			if (p.intersects((double) e.x, (double) e.y, (double) e.w, (double) e.h)) {
				enListN.remove(i);
				start = false;
				end = true;
				eTime = System.currentTimeMillis();
			}
		}
		for (int i = 0; i < enListS.size(); i++) {
			Enemy e = (Enemy) enListS.get(i);
			int[] xpoints = { x, (x + xw), (x + xw), x };
			int[] ypoints = { y, y, (y + xh), (y + xh) };
			p = new Polygon(xpoints, ypoints, 4);
			if (p.intersects((double) e.x, (double) e.y, (double) e.w, (double) e.h)) {
				enListS.remove(i);
				start = false;
				end = true;
				eTime = System.currentTimeMillis();
			}
		}
		for (int i = 0; i < enListE.size(); i++) {
			Enemy e = (Enemy) enListE.get(i);
			int[] xpoints = { x, (x + xw), (x + xw), x };
			int[] ypoints = { y, y, (y + xh), (y + xh) };
			p = new Polygon(xpoints, ypoints, 4);
			if (p.intersects((double) e.x, (double) e.y, (double) e.w, (double) e.h)) {
				enListE.remove(i);
				start = false;
				end = true;
				eTime = System.currentTimeMillis();
			}
		}
		for (int i = 0; i < enListW.size(); i++) {
			Enemy e = (Enemy) enListW.get(i);
			int[] xpoints = { x, (x + xw), (x + xw), x };
			int[] ypoints = { y, y, (y + xh), (y + xh) };
			p = new Polygon(xpoints, ypoints, 4);
			if (p.intersects((double) e.x, (double) e.y, (double) e.w, (double) e.h)) {
				enListW.remove(i);
				start = false;
				end = true;
				eTime = System.currentTimeMillis();
			}
		}
	}

	public void draw() {
		Graphics gs = bi.getGraphics();
		gs.setColor(Color.white);
		gs.fillRect(0, 0, w, h);
		gs.setColor(Color.black);
		gs.drawString("Enemy 객체수 : " + (enListN.size()+enListS.size()), 200, 50);
		gs.drawString("시간 : " + (System.currentTimeMillis()-sTime)/1000, 200, 100);
		gs.drawString("게임시작 : Enter", 200, 90);

		if (end) {
			gs.drawString("G A M E     O V E R", 200, 250);
			gs.drawString((eTime-sTime)/1000+ "초", 240, 300);
		}

		gs.fillRect(x, y, xw, xh);
		
		gs.setColor(Color.black);
		for (int i = 0; i < enListN.size(); i++) {
			Enemy e = (Enemy) enListN.get(i);
			gs.fillRect(e.x, e.y, e.w, e.h);
			if (e.y > h)
				enListN.remove(i);
			e.moveEn();
		}
		for (int i = 0; i < enListS.size(); i++) {
			Enemy e = (Enemy) enListS.get(i);
			gs.fillRect(e.x, e.y, e.w, e.h);
			if (e.y < 0)
				enListS.remove(i);
			e.moveEn();
		}
		for (int i = 0; i < enListE.size(); i++) {
			Enemy e = (Enemy) enListE.get(i);
			gs.fillRect(e.x, e.y, e.w, e.h);
			if (e.x < 0)
				enListE.remove(i);
			e.moveEn();
		}
		for (int i = 0; i < enListW.size(); i++) {
			Enemy e = (Enemy) enListW.get(i);
			gs.fillRect(e.x, e.y, e.w, e.h);
			if (e.x > w)
				enListW.remove(i);
			e.moveEn();
		}

		Graphics ge = this.getGraphics();
		ge.drawImage(bi, 0, 0, w, h, this);
	}

	public void keyControl() {
		if (0 < x) {
			if (left)
				x -= 3;
		}
		if (w > x + xw) {
			if (right)
				x += 3;
		}
		if (25 < y) {
			if (up)
				y -= 3;
		}
		if (h > y + xh) {
			if (down)
				y += 3;
		}
	}

	public void keyPressed(KeyEvent ke) {
		switch (ke.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		case KeyEvent.VK_ENTER:
			start = true;
			end = false;
			sTime=System.currentTimeMillis();
			break;
		}
	}

	public void keyReleased(KeyEvent ke) {
		switch (ke.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		}
	}

	public void keyTyped(KeyEvent ke) {
	}

	public static void main(String[] args) {
		Thread t = new Thread(new GameMain());
		t.start();
	}
}







abstract class Enemy {
	int x;
	int y;
	int w = 10;
	int h = 10;
	
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public abstract void moveEn();
}

class EnemyN extends Enemy {
	public EnemyN(int x, int y) {
		super(x, y);
	}
	@Override
	public void moveEn() {
		y++;
	}
}
class EnemyS extends Enemy {
	public EnemyS(int x, int y) {
		super(x, y);
	}
	@Override
	public void moveEn() {
		y--;
	}
}
class EnemyE extends Enemy {
	public EnemyE(int x, int y) {
		super(x, y);
	}
	@Override
	public void moveEn() {
		x--;
	}
}
class EnemyW extends Enemy {
	public EnemyW(int x, int y) {
		super(x, y);
	}
	@Override
	public void moveEn() {
		x++;
	}
}