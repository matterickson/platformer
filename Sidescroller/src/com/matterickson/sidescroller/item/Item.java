package com.matterickson.sidescroller.item;

import com.matterickson.sidescroller.GameObject;

public abstract class Item extends GameObject {

	public Item(int x, int y, int w, int h, String filename, int drawWidth, int drawHeight) {
		super(x, y, w, h, filename, drawWidth, drawHeight, false);
	}

}
