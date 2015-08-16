package com.matterickson.sidescroller.platform;


public class PlatformGround extends Platform {

	public PlatformGround(int x, int y, int blocksWide) {
		super(x, y, 70, 70, "res/ground.png", blocksWide, 1);
	}
	
	public PlatformGround(int x, int y, int blocksWide, int blocksTall) {
		super(x, y, 70, 70, "res/ground.png", blocksWide, blocksTall);
	}

}
