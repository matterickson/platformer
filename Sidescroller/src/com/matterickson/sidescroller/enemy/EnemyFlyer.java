package com.matterickson.sidescroller.enemy;

import java.awt.Graphics;
import java.awt.Point;

import com.matterickson.sidescroller.World;

public class EnemyFlyer extends Enemy {
	//this enemy flies in circles
	double angle;

	public EnemyFlyer(int x, int y, boolean facingRight) {
		super(x, y, 69, 31, "res/flyer.png", 69, 31);
		this.facingRight=facingRight;
		
		this.applyGravity = false;
		angle = 0;
	}
	
	@Override
	public void update(World w){
		super.update(w);
		if(this.isDead && this.distanceFromObject(w.getPlayer())>800){
			w.getGameObjects().remove(this);
			w.getEnemies().remove(this);
		}
	}
	
	@Override
	public void draw(Graphics g, Point offset){
		if(!this.isDead){
			if(this.facingRight){
				this.picture.update(40);
				this.picture.draw(g, this.getLocationMinusOffset(offset), this.getWidth(), this.getHeight(), 0);
			}else{
				this.picture.update(40);
				this.picture.draw(g, this.getLocationMinusOffset(offset), this.getWidth(), this.getHeight(), 1);
			}
		}else{
			this.picture.reset();
			this.picture.draw(g, this.getLocationMinusOffset(offset), this.getWidth(), this.getHeight(), 1);
		}
	}	
	
	@Override
	public void move(World w){
		if(applyGravity){
			speed.y += 1;
		}

		this.speed = this.checkCollision(w.getGameObjects());
		
		if(!this.isDead){
			angle = (angle - Math.PI/64)%(2*Math.PI);
			speed.x = (int) (Math.sin(angle)*6);
			speed.y = (int) (Math.cos(angle)*6);
		}
		
		loc.x += speed.x;
		loc.y += speed.y;
	}
}
