package com.matterickson.sidescroller.platform;

import java.awt.Graphics;
import java.awt.Point;

import com.matterickson.sidescroller.GameObject;

public class PlatformWater extends Platform {

	public PlatformWater(int x, int y, int blocksWide) {
		super(x, y, 70, 45, "res/water.png", blocksWide, 1);
	}
	
	public PlatformWater(int x, int y, int blocksWide, int blocksTall) {
		super(x, y, 70, 45, "res/water.png", blocksWide, blocksTall);
	}
	
	@Override
	public Point topSideHit(GameObject object, Point newSpeed) {
		object.damage();
		return newSpeed;
	}
	
	@Override
	public void draw(Graphics g, Point offset){
		this.picture.update(25);
		this.picture.draw(g, this.getLocationMinusOffset(offset), this.getWidth(), this.getHeight(), 0);		
	}

}
