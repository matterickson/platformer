package com.matterickson.sidescroller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import com.matterickson.sidescroller.enemy.Enemy;
import com.matterickson.sidescroller.enemy.EnemyFlyer;
import com.matterickson.sidescroller.enemy.EnemyRiverSnake;
import com.matterickson.sidescroller.enemy.EnemySlime;
import com.matterickson.sidescroller.item.Item;
import com.matterickson.sidescroller.item.ItemCoin;
import com.matterickson.sidescroller.item.ItemMushroom;
import com.matterickson.sidescroller.item.ItemPlant;
import com.matterickson.sidescroller.item.ItemRock;
import com.matterickson.sidescroller.platform.Platform;
import com.matterickson.sidescroller.platform.PlatformBlock;
import com.matterickson.sidescroller.platform.PlatformBridge;
import com.matterickson.sidescroller.platform.PlatformGround;
import com.matterickson.sidescroller.platform.PlatformWater;

public class World implements ActionListener {
	private Timer t; //world timer
	private ArrayList<GameObject> gameObjects; //all the platforms, enemies, items, and players in the world
	private ArrayList<Platform> platforms; //all platforms
	private ArrayList<Enemy> enemies; //all enemies
	private ArrayList<Item> items; //all items
	private Player player; //the player

	private Point mapOffset; //used for scrolling
	public int drawnObjects; //tracks # of drawn objects per update (for debugger menu)

	public World(){		
		initObjects();
		
		t = new Timer(20, this);
		t.start();
		
		mapOffset = new Point(0, 0);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		for(int i = 0; i < gameObjects.size(); i++){
			gameObjects.get(i).update(this);
		}
	}
	
	public void draw(Graphics g){
		drawnObjects=0;
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 900, 720);
		
		for(int i = 0; i < enemies.size(); i++){
			if(enemies.get(i).distanceFromObject(player)<800){
				enemies.get(i).draw(g, this.mapOffset);
				drawnObjects++;
			}
		}
		
		player.draw(g, this.mapOffset);
		drawnObjects++;
		
		for(int i = 0; i < items.size(); i++){
			if(items.get(i).distanceFromObject(player)<800){
				items.get(i).draw(g, this.mapOffset);
				drawnObjects++;
			}
		}
		
		for(int i = 0; i < platforms.size(); i++){
			platforms.get(i).draw(g, this.mapOffset);
			drawnObjects++;
		}
	}
	
	private void initObjects() {
		gameObjects = new ArrayList<GameObject>();
		initEnemies();
		initItems();
		initPlatforms();
	}
	
	private void initPlatforms(){
		platforms = new ArrayList<Platform>();
		
		//first section
		addPlatform(new PlatformBlock(100, 400, 5, 4));
		addPlatform(new PlatformBlock(450, 540, 4, 2));
		addPlatform(new PlatformBlock(730, 400, 11, 4));
		addPlatform(new PlatformWater(450, 495, 4));
		addPlatform(new PlatformBlock(1500, 470, 5, 3));
		addPlatform(new PlatformBlock(2020, 540, 5, 2));
		addPlatform(new PlatformGround(2090, 260, 12, 1));

		//water across the bottom
		addPlatform(new PlatformWater(-330, 700, 100));
		addPlatform(new PlatformBlock(-400, 745, 100, 3));
		addPlatform(new PlatformBlock(-400, 45, 1, 10));


		//bridges
		addPlatform(new PlatformBridge(2580, 660, 12));
	}
	
	public void initEnemies(){
		enemies = new ArrayList<Enemy>();
		
		//constructor: x, y, facingRight
		addEnemy(new EnemyRiverSnake(650,450,false));

		addEnemy(new EnemySlime(1300,300,false));
		addEnemy(new EnemySlime(1500,300,false));
		
		addEnemy(new EnemyFlyer(1980,220,false));
	}
	
	public void initItems(){
		items = new ArrayList<Item>();
		
		addItem(new ItemPlant(200, 300));
		addItem(new ItemRock(310, 300));
		addItem(new ItemCoin(860, 330));
		addItem(new ItemCoin(910, 330));
		addItem(new ItemCoin(960, 330));

		addItem(new ItemMushroom(1160, 300));
		addItem(new ItemPlant(1140, 300));
		addItem(new ItemPlant(1190, 300));
		
		addItem(new ItemCoin(1290, 330));
		addItem(new ItemCoin(1340, 330));
		addItem(new ItemCoin(1390, 330));
		
		addItem(new ItemCoin(2210, 180));
		addItem(new ItemCoin(2260, 185));
		addItem(new ItemCoin(2310, 190));
		addItem(new ItemCoin(2360, 185));
		addItem(new ItemCoin(2410, 180));
		addItem(new ItemCoin(2460, 185));
		addItem(new ItemCoin(2510, 190));
		addItem(new ItemCoin(2560, 185));
		addItem(new ItemCoin(2610, 180));
		addItem(new ItemCoin(2660, 185));
		addItem(new ItemCoin(2710, 190));
		addItem(new ItemCoin(2760, 185));
		addItem(new ItemCoin(2810, 180));

	}

	public void addPlayer(Player p){
		player = p;
		gameObjects.add(p);
	}
	
	public void addPlatform(Platform p){
		gameObjects.add(p);
		platforms.add(p);
	}
	
	private void addEnemy(Enemy e) {
		gameObjects.add(e);
		enemies.add(e);
	}
	
	private void addItem(Item i){
		gameObjects.add(i);
		items.add(i);
	}
	
	public ArrayList<GameObject> getGameObjects(){
		return gameObjects;
	}
	
	public ArrayList<Platform> getPlatforms(){
		return platforms;
	}
	
	public ArrayList<Enemy> getEnemies(){
		return enemies;
	}
	
	public ArrayList<Item> getItems(){
		return items;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public void updateMapOffset(int x, int y){
		this.mapOffset.x += x;
		this.mapOffset.y += y;
	}
	
	public Point getMapOffset(){
		return this.mapOffset;
	}
	
	public void reset(){
		mapOffset = new Point(0, 0);
		
		initObjects();
		addPlayer(player);
	}
}
