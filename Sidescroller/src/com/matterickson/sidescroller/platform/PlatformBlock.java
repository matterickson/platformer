package com.matterickson.sidescroller.platform;

public class PlatformBlock extends Platform {

	public PlatformBlock(int x, int y, int blocksWide) {
		super(x, y, 70, 70, "res/block.png", blocksWide, 1);
	}
	
	public PlatformBlock(int x, int y, int blocksWide, int blocksTall) {
		super(x, y, 70, 70, "res/block.png", blocksWide, blocksTall);
	}

}
