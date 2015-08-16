package com.matterickson.sidescroller;

import java.awt.Graphics;
import java.awt.Point;

import com.matterickson.sidescroller.enemy.Enemy;

public class Player extends GameObject {
	private int moveSpeed, jumpSpeed;
	private boolean movingRight, movingLeft, jumping;
	private Point startLoc;
	int coinCount;

	public Player(int x, int y) {
		super(x, y, 72, 104, "res/player.png");
		moveSpeed = 7;
		jumpSpeed = -20;
		movingRight = movingLeft = false;
		facingRight = true;
		startLoc = new Point(x, y);
		coinCount = 0;
	}

	public void jump() {
		this.jumping = true;
	}

	public void moveLeft() {
		this.movingLeft = true;
		this.facingRight = false;
	}

	public void moveRight() {
		this.movingRight = true;
		this.facingRight = true;
	}

	public void stopLeft() {
		this.movingLeft = false;
	}

	public void stopRight() {
		this.movingRight = false;
	}
	
	public void stopJumping() {
		this.jumping = false;
	}

	@Override
	public void draw(Graphics g, Point mapOffset) {
		if (this.onFloor == true) {
			if (speed.x != 0) {
				picture.update(10);
				picture.draw(g, new Point(loc.x - mapOffset.x, loc.y - mapOffset.y), width, height, (speed.x>0)?0:1);
			} else {
				picture.draw(g, new Point(loc.x - mapOffset.x, loc.y - mapOffset.y), width, height, facingRight ? 0 : 1);
				picture.reset();
			}
		} else {
			picture.reset();
			if (speed.x != 0) {
				picture.draw(g, new Point(loc.x - mapOffset.x, loc.y - mapOffset.y), width, height, (speed.x>0)?2:3);
			} else {
				picture.draw(g, new Point(loc.x - mapOffset.x, loc.y - mapOffset.y), width, height,	facingRight ? 2 : 3);
			}
		}
	}

	@Override
	protected void move(World w) {
		super.move(w);
		if ((this.getLocationMinusOffset(w.getMapOffset()).x > 600 && this.speed.x > 0)
				|| (this.getLocationMinusOffset(w.getMapOffset()).x < 200) && this.speed.x < 0) {
			w.updateMapOffset(this.speed.x, 0);
		}

		if (this.getLocationMinusOffset(w.getMapOffset()).y < 100
				|| this.getLocationMinusOffset(w.getMapOffset()).y > 300) {
			w.updateMapOffset(0, this.speed.y);
		}
		
		if (this.onFloor == true && jumping) {
			this.speed.y = jumpSpeed;
			this.onFloor = false;
		}

		this.speed.x = (movingLeft ? -moveSpeed : 0) + (movingRight ? moveSpeed : 0);
	}
	
	@Override
	public void update(World w){
		super.update(w);
		if(this.isDead){
			this.reset();
			w.reset();
		}
	}
	
	protected void reset() {
		this.loc = new Point(this.startLoc.x, this.startLoc.y);
		this.isDead = false;
		this.coinCount = 0;
	}
	
	protected Point rightSideHit(GameObject o, Point speed){
		if(o instanceof Enemy && !o.isDead){
			this.damage();
		}
		
		return super.rightSideHit(o, speed);
	}
	
	protected Point leftSideHit(GameObject o, Point speed){
		if(o instanceof Enemy && !o.isDead){
			this.damage();
		}
		
		return super.leftSideHit(o, speed);
	}
	
	protected Point topSideHit(GameObject o, Point speed){
		if(o instanceof Enemy && !o.isDead){
			this.damage();
		}
		
		return super.topSideHit(o, speed);
	}

	public void addCoin() {
		coinCount++;
	}
	
	public int getCoinCount(){
		return coinCount;
	}
}
