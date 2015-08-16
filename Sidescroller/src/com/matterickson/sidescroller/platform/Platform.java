package com.matterickson.sidescroller.platform;

import java.awt.Point;

import com.matterickson.sidescroller.GameObject;
import com.matterickson.sidescroller.enemy.Enemy;

public abstract class Platform extends GameObject {
	
	public Platform(int x, int y, int w, int h, String filename, int blocksWide, int blocksTall) {
		super(x, y, w, h, filename, blocksWide*w, blocksTall*h, true);
		
		this.applyGravity = false;
	}

	@Override
	protected Point rightSideHit(GameObject o, Point speed){
		if(o instanceof Enemy){
			((Enemy)o).ranIntoPlatform(this, speed);
		}else{
			speed.x = this.getRight() - o.getX();
		}
		return speed;
	}
	
	@Override
	protected Point leftSideHit(GameObject o, Point speed){
		if(o instanceof Enemy){
			((Enemy)o).ranIntoPlatform(this, speed);
		}else{
			speed.x = this.getLocation().x - o.getWidth() - o.getX();
		}
		return speed;
	}
	
	@Override
	protected Point topSideHit(GameObject o, Point speed){
		speed.y = this.getLocation().y - o.getHeight() - o.getY();
		return speed;
	}
	
	@Override
	protected Point bottomSideHit(GameObject o, Point speed){
		speed.y = this.getBottom() - o.getY();
		return speed;
	}
	
}
