package com.matterickson.sidescroller.enemy;

import java.awt.Graphics;
import java.awt.Point;

import com.matterickson.sidescroller.World;

public class EnemySlime extends Enemy {
	//this is a basic enemy
	
	public EnemySlime(int x, int y, boolean facingRight) {
		super(x, y, 43, 32, "res/slime.png", 43, 32);
		this.speed.x = facingRight?3:-3;
		this.facingRight=facingRight;
	}
	
	@Override
	public void update(World w){
		super.update(w);
		if(this.isDead){
			if(height > 0){
				height--;
			}else{
				w.getGameObjects().remove(this);
				w.getEnemies().remove(this);
			}
		}
	}
	
	@Override
	public void draw(Graphics g, Point offset){
		if(!this.isDead){
			this.picture.update(40);
			this.picture.draw(g, this.getLocationMinusOffset(offset), this.getWidth(), this.getHeight(), facingRight? 0 : 1);
		}else{
			this.picture.reset();
			this.picture.draw(g, this.getLocationMinusOffset(offset), this.getWidth(), this.getHeight(), facingRight? 0 : 1);
		}
	}
}
