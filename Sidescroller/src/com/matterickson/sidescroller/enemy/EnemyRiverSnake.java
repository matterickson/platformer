package com.matterickson.sidescroller.enemy;

import java.awt.Graphics;
import java.awt.Point;

import com.matterickson.sidescroller.GameObject;
import com.matterickson.sidescroller.Player;
import com.matterickson.sidescroller.World;

public class EnemyRiverSnake extends Enemy {
	//this enemy grows taller over time
	private int maxHeight, growDelay, growCounter;
	
	public EnemyRiverSnake(int x, int y, boolean facingRight) {
		super(x, y, 53, 146, "res/snakeSlime.png", 53, 146);
		this.speed.x = facingRight?2:-2;
		this.facingRight=facingRight;
		this.height = 50;
		this.maxHeight = 146;
		this.growDelay = 2;
		this.growCounter = 0;
	}
	
	@Override
	protected Point topSideHit(GameObject o, Point speed){
		super.topSideHit(o, speed);
		if(o instanceof Player){	
			speed.y = -15;
		}
		return speed;
	}
	
	@Override
	public void update(World w){
		super.update(w);
		if(!this.isDead){
			if(growCounter > growDelay){
				growCounter = 0;
				if(height < maxHeight){
					height++;
					this.loc.y--;
				}
			}else{
				growCounter++;
			}
		}
	}
	
	@Override
	public void draw(Graphics g, Point offset){
		if(!this.isDead){
			this.picture.update(150);
			this.picture.draw(g, this.getLocationMinusOffset(offset), this.getWidth(), this.getHeight(), 0);
		}else{
			this.picture.reset();
			this.picture.draw(g, this.getLocationMinusOffset(offset), this.getWidth(), this.getHeight(), 1);
		}
		
		super.draw(g, offset);
	}
}
