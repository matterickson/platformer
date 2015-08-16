package com.matterickson.sidescroller;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Spritesheet {
	private Image sheet;
	private int framesPerRow, frameWidth, frameHeight;
	private int curFrame, counter, frameDelay;
	private boolean tile;
	
	public Spritesheet(String path, int frameWidth, int frameHeight, boolean til) throws IOException{
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		this.tile = til;
		
		this.curFrame = 0;
		this.sheet = ImageIO.read(new File(path));
		this.framesPerRow = (new ImageIcon(sheet)).getIconWidth() / frameWidth;
	}
	
	public void update(int delay){
		frameDelay = delay;
		if (counter == frameDelay){
			curFrame = (curFrame + 1) % framesPerRow;
		}
		
		counter = (counter + 1) % (frameDelay + 1);
	}
	
	public void setTile(boolean t){
		tile = t;
	}
	
	public void draw(Graphics g, Point l, int w, int h, int row){
		if (tile) {
            for (int x = 0; x < w; x += frameWidth) {
                for (int y = 0; y < h; y += frameHeight) {
            		g.drawImage(sheet, l.x+x, l.y+y, Math.min(l.x+x+frameWidth, l.x+w), Math.min(l.y+y+frameHeight, l.y+h), curFrame*frameWidth, row*frameHeight, (curFrame+1)*frameWidth, (row+1)*frameHeight, null);
                }
            }
        } else {
    		g.drawImage(sheet, l.x, l.y, l.x+w, l.y+h, curFrame*frameWidth, row*frameHeight, (curFrame+1)*frameWidth, (row+1)*frameHeight, null);
        }
	
	}
	
	public void reset(){
		this.curFrame = 0;
	}
}
