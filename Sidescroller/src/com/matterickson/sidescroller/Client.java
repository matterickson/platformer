package com.matterickson.sidescroller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Client extends JPanel implements KeyListener {
	private JFrame frame;
	private Player player;
	private World world;
	private boolean debug;

	public static void main(String[] args) {
		Client c = new Client();
	}
	
	public Client(){
		debug = false;

		world = new World();
		player = new Player(100, 100);
		world.addPlayer(player);
				
		frame = new JFrame("Sidescroller");
		frame.add(this);
		frame.setSize(900, 720);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.addKeyListener(this);
	}
	
	@Override
	public void paint(Graphics g) {
		world.draw(g);
		if(debug){
			g.setFont(new Font("Courier New", 0, 12));
			g.setColor(Color.red);
			g.drawString("Player Information   Coins: "+player.getCoinCount(), 10, 30);
			g.drawString("absolute x: "+player.getLocation().x+", absolute y: "+player.getLocation().y, 10, 44);
			g.drawString("screen x: "+player.getLocationMinusOffset(world.getMapOffset()).x+", screen y: "+player.getLocationMinusOffset(world.getMapOffset()).y, 10, 58);
			g.drawString("speed x: "+player.speed.x+", speed y: "+player.speed.y+", onFloor: "+player.onFloor, 10, 72);
			
			g.drawString("World Information", 10, 86);
			g.drawString("gameObjects: "+world.getGameObjects().size(), 10, 100);
			g.drawString("drawnObjects: "+world.drawnObjects, 10, 114);
			g.drawString("enemies: "+world.getEnemies().size(),	10, 128);
			g.drawString("platforms: "+world.getPlatforms().size(), 10, 144);
		}
		repaint();
	}
	
	@Override
	//updates the screen, implements double buffering
	public void update(Graphics g) {
		Graphics offg;
		Image offscreen = null;

		offscreen = createImage(this.getWidth(), this.getHeight());
		offg = offscreen.getGraphics();
		
		paint(offg);
		g.drawImage(offscreen, 0, 0, this);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W){
			player.jump();
		}else if(e.getKeyCode() == KeyEvent.VK_A){
			player.moveLeft();
		}else if(e.getKeyCode() == KeyEvent.VK_D){
			player.moveRight();
		}else if(e.getKeyCode() == KeyEvent.VK_F3){
			debug = !debug;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {	
		if(e.getKeyCode() == KeyEvent.VK_A){
			player.stopLeft();
		}else if(e.getKeyCode() == KeyEvent.VK_D){
			player.stopRight();
		}else if(e.getKeyCode() == KeyEvent.VK_W){
			player.stopJumping();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {	}
}
