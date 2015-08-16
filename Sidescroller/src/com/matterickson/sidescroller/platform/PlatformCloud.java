package com.matterickson.sidescroller.platform;

import java.awt.Point;

import com.matterickson.sidescroller.GameObject;

public class PlatformCloud extends Platform {

	public PlatformCloud(int x, int y, int blocksWide) {
		super(x, y, 70, 54, "res/cloud.png", blocksWide, 1);
	}
	
	public PlatformCloud(int x, int y, int blocksWide, int blocksTall) {
		super(x, y, 70, 54, "res/cloud.png", blocksWide, blocksTall);
	}
	
	@Override
	public Point topSideHit(GameObject object, Point newSpeed) {
		newSpeed = super.topSideHit(object, newSpeed);
		//how to update location live
		object.setLocation(object.getX()+newSpeed.x, object.getY()+newSpeed.y);
		
		//makes the character bounce up
		newSpeed.y = -10;
		return newSpeed;
	}

}