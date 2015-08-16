package com.matterickson.sidescroller.enemy;

import java.awt.Point;

import com.matterickson.sidescroller.GameObject;
import com.matterickson.sidescroller.Player;

public abstract class Enemy extends GameObject {
	
	public Enemy(int x, int y, int w, int h, String file, int drawWidth, int drawHeight) {
		super(x, y, w, h, file, drawWidth, drawHeight, false);
	}
	
	@Override
	protected Point rightSideHit(GameObject p, Point s){
		if(p instanceof Player && !this.isDead){
			p.damage();
		}
		
		return super.rightSideHit(p, s);
	}
	
	@Override
	protected Point leftSideHit(GameObject p, Point s){
		if(p instanceof Player && !this.isDead){
			p.damage();
		}
		
		return super.leftSideHit(p, s);
	}

	@Override
	protected Point topSideHit(GameObject o, Point speed){
		if(o instanceof Player && !this.isDead){	
			speed.y = -15;
			this.damage();
		}
		
		return speed;
	}
	
	@Override
	public void damage(){
		super.damage();
		this.applyGravity = true;
		this.speed = new Point(0, 0);
	}
	
	public Point ranIntoPlatform(GameObject o, Point speed){
		speed.x = -speed.x;
		this.facingRight = !this.facingRight;

		return speed;
	}
	
}
