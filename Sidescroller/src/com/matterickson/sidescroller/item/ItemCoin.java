package com.matterickson.sidescroller.item;

import java.awt.Point;

import com.matterickson.sidescroller.GameObject;
import com.matterickson.sidescroller.Player;
import com.matterickson.sidescroller.World;

public class ItemCoin extends Item {

	public ItemCoin(int x, int y) {
		super(x, y, 35, 35, "res/coin_gold.png", 35, 35);
		this.applyGravity = false;
	}
	
	@Override
	protected Point rightSideHit(GameObject o, Point speed){
		if(o instanceof Player){
			this.isDead = true;
		}
		return speed;
	}
	
	@Override
	protected Point leftSideHit(GameObject o, Point speed){
		if(o instanceof Player){
			this.isDead = true;
		}
		return speed;
	}
	
	@Override
	protected Point topSideHit(GameObject o, Point speed){
		if(o instanceof Player){
			this.isDead = true;
		}
		return speed;
	}
	
	@Override
	protected Point bottomSideHit(GameObject o, Point speed){
		if(o instanceof Player){
			this.isDead = true;
		}
		return speed;
	}
	
	@Override
	public void update(World w){
		if(this.isDead){
			w.getGameObjects().remove(this);
			w.getItems().remove(this);
			w.getPlayer().addCoin();
		}
	}

}
