package com.matterickson.sidescroller.platform;

import java.awt.Point;

import com.matterickson.sidescroller.GameObject;

public class PlatformSpikes extends Platform {

	public PlatformSpikes(int x, int y, int blocksWide) {
		super(x, y, 70, 35, "res/spikes.png", blocksWide, 1);
	}

	@Override
	public Point topSideHit(GameObject object, Point newSpeed) {
		object.damage();
		return super.topSideHit(object, newSpeed);
	}
}
