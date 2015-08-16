package com.matterickson.sidescroller;

import java.awt.Graphics;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import com.matterickson.sidescroller.platform.Platform;

public abstract class GameObject {
	protected Point loc;
	protected Point speed;
	protected int width, height;
	protected boolean applyGravity;
	protected boolean facingRight;
	protected boolean onFloor;
	protected boolean isDead;
	protected Spritesheet picture;
	
	public GameObject(int x, int y, int w, int h, String filename){
		this(x, y, w, h, filename, w, h, false);
	}
	
	public GameObject(int x, int y, int w, int h, String filename, int drawWidth, int drawHeight, boolean tile){
		this.applyGravity = true;
		this.speed = new Point(0, 0);
		
		this.width = drawWidth;
		this.height = drawHeight;
		this.loc = new Point(x, y);	
		
		this.facingRight = true;
		this.isDead = false;
		
		try {
			this.picture = new Spritesheet(filename, w, h, tile);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	//note: picture.update(int updatedelay) is req to animate
	public void draw(Graphics g, Point offset){
		picture.draw(g, new Point(loc.x-offset.x, loc.y-offset.y), width, height, 0);
	}
	
	protected void update(World world){	
		move(world);
	}
	
	protected void move(World world){
		if(applyGravity){
			speed.y += 1;
		}
		
		if(!(this instanceof Platform)){
			this.speed = this.checkCollision(world.getGameObjects());
		}
		
		if(this.speed.y != 0){
			this.onFloor = false;
		}
		
		loc.x += speed.x;
		loc.y += speed.y;
	}
	
	protected Point checkCollision(ArrayList<GameObject> gameObjects) {
		Point newSpeed = new Point(speed);
		for(int i = 0; i < gameObjects.size(); i++){
			GameObject object = gameObjects.get(i);
			
			if(object != this){
				//coming on from the left side
				if (this.getRight() <= object.getLocation().x && this.getRight() + speed.x > object.getLocation().x){
					if (this.loc.y < object.getBottom() && this.getBottom() > object.getLocation().y){
						//hitting wall from left side
						newSpeed = object.leftSideHit(this, newSpeed);
					} else if (this.getBottom() <= object.getLocation().y && this.getBottom() + speed.y > object.getBottom()){
						//landing down from above and left
						newSpeed = object.topSideHit(this, newSpeed);
						this.onFloor = true;
					} else if (this.loc.y >= object.getBottom() && this.loc.y + speed.y < object.getBottom()){
						//hit head on roof of platform from left
						newSpeed = object.bottomSideHit(this, newSpeed);
					}
					
				//coming on from the right side
				} else if (this.loc.x >= object.getRight() && this.loc.x + speed.x < object.getRight()){
					if (this.loc.y < object.getBottom() && this.getBottom() > object.getLocation().y){
						//hitting wall from right side
						newSpeed = object.rightSideHit(this, newSpeed);
					} else if (this.getBottom() <= object.getLocation().y && this.getBottom() + speed.y > object.getLocation().y){
						//landing down from above and right
						newSpeed = object.topSideHit(this, newSpeed);
						this.onFloor = true;
					} else if (this.loc.y >= object.getBottom() && this.loc.y + speed.y < object.getBottom()){
						//hit head on roof of platform from right
						newSpeed = object.bottomSideHit(this, newSpeed);
					}
				}
				
				if (newSpeed.y > 0){
					if (this.getBottom() <= object.getLocation().y && this.getBottom() + speed.y > object.getLocation().y){
						if(this.getRight() + newSpeed.x > object.getLocation().x && this.loc.x + newSpeed.x < object.getRight()){
							//land on platform
							newSpeed = object.topSideHit(this, newSpeed);
							this.onFloor = true;
						}
					}
				} else{
					if (this.loc.y >= object.getBottom() && this.loc.y + speed.y < object.getBottom()){
						if(this.getRight() + newSpeed.x > object.getLocation().x && this.loc.x + newSpeed.x < object.getRight()){
							//hit head
							newSpeed = object.bottomSideHit(this, newSpeed);
						}
					}	
				}
			}	
		}
		return newSpeed;
	}
	
	protected Point rightSideHit(GameObject o, Point speed){
		return speed;
	}
	
	protected Point leftSideHit(GameObject o, Point speed){
		return speed;
	}
	
	protected Point topSideHit(GameObject o, Point speed){
		return speed;
	}
	
	protected Point bottomSideHit(GameObject o, Point speed){
		return speed;
	}
	
	public Point getLocation(){
		return this.loc;
	}
	
	public Point getLocationMinusOffset(Point o){
		return new Point(this.loc.x-o.x, this.loc.y-o.y);
	}
	
	public int getX(){
		return this.loc.x;
	}
	
	public int getY(){
		return this.loc.y;
	}

	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public int getRight(){
		return this.loc.x + this.width;
	}
	
	public int getBottom(){
		return this.loc.y + this.height; 
	}
	
	public void setLocation(int x, int y){
		this.loc = new Point(x, y);
	}
	
	public void setX(int x){
		this.loc.x = x;
	}
	
	public void setY(int y){
		this.loc.y = y;
	}
	
	public void damage() {
		this.isDead = true;
	}
	
	protected double distanceFromObject(GameObject o){
		double x = Math.abs(this.loc.x - o.getX());
		double y = Math.abs(this.loc.y - o.getY());
		return Math.sqrt(x*x+y*y);
	}
}
