package com.itay_peleg.game.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.itay_peleg.engine.GameContainer;
import com.itay_peleg.engine.util.Log;
import com.itay_peleg.game.Assets;
import com.itay_peleg.game.states.ScoreState;

public class Map {
	
	private int w, h, playerX, playerY;
	private int tileSize = 32;
	
	private int[][] map;
	private int level = 0;
	
	private int transX, transY;
	
	public void load() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/level_" + level + ".map")));
			w = Integer.parseInt(in.readLine());
			h = Integer.parseInt(in.readLine());
			playerX = Integer.parseInt(in.readLine());
			playerY = Integer.parseInt(in.readLine());
			map = new int[w][h];
			for(int y = 0; y < h; y++) {
				String line = in.readLine();
				String[] tokens = line.split(" ");
				for(int x = 0; x < w; x++) {
					map[x][y] = Integer.parseInt(tokens[x]);
				}
			}
			in.close();
		}catch (Exception e) {
			Log.error("MapLoader", e.getMessage());
			e.printStackTrace();
		}
		Log.info("MapLoader", "The map level_" + level + ".map has been loaded!");
	}
	
	public int getLevel() {
		return level;
	}
	
	public void nextLevel(GameContainer gc) {
		level++;
		int save = Math.max(level, Save.load());
		Save.save(save);
		if(level == 18) {
			gc.getGame().setState(new ScoreState(this));
		}else {
			load();
		}
	}
		
	public void render(GameContainer gc, Graphics g) {
		for(int x = 0; x < w; x++) {
			for(int y = 0; y < h; y++) {
				int tile = map[x][y];
				switch(tile) {
					case 0:
						g.setColor(new Color(0xff0094FF));
						g.fillRect(transX + x * tileSize, transY + y * tileSize, tileSize, tileSize);
						break;
					case 1:
						draw(Assets.basicTile, x, y, g);
						break;
					case 2:
						draw(Assets.bendingBlockTile, x, y, g);
						break;
					case 3:
						draw(Assets.JumpTile, x, y, g);
						break;
					case 4:
						draw(Assets.NextLevelTile, x, y, g);
						break;
					case 5:
						draw(Assets.SlowTile, x, y, g);
						break;
					case 6:
						draw(Assets.SmallTile, x, y, g);
						break;
					case 7:
						draw(Assets.SpeedTile, x, y, g);
						break;
					case 8:
						draw(Assets.stick, x, y, g);
						break;
					case 9:
						draw(Assets.spike, x, y, g);
						break;
					case 10:
					case 11:
					case 12:
					case 13:
					case 14:	
						draw(Assets.sign, x, y, g);
						break;
					case 15:
						draw(Assets.gate_triangle, x, y, g);
						break;
					case 16:
						draw(Assets.gate_x, x, y, g);
						break;
					case 17:
						draw(Assets.gate_ball, x, y, g);
						break;
					case 18:
						draw(Assets.scroll, x, y, g);
						break;
					case 19:
						draw(Assets.npc_ball, x, y, g);
						break;
					case 20:
						draw(Assets.npc_x, x, y, g);
						break;
					case 21:
						draw(Assets.npc_tirangle, x, y, g);
						break;
					case 22:
						draw(Assets.stopTile, x, y, g);
						break;
					case 23:
						draw(Assets.outsider, x, y, g);
						break;
				}
			}
		}
	}
	
	private void draw(BufferedImage img, int x, int y, Graphics g) {
		g.drawImage(img, transX + x * tileSize, transY + y * tileSize, tileSize, tileSize, null);
	}
	
	// Getters And Setters
	
	public int getH() {
		return h;
	}
	
	public int getW() {
		return w;
	}
	
	public int getTileSize() {
		return tileSize;
	}
	
	public int getColTile(int x) {
		return x / tileSize;
	}
	
	public int getRowTile(int y) {
		return y / tileSize;
	}
	
	public int getTile(int x, int y) {
		return map[x][y];
	}
	
	public int getPlayerX() {
		return playerX * tileSize;
	}
	
	public int getPlayerY() {
		return playerY * tileSize;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void moveTransX(int x) {
		transX += -x;
	}
	
	public void setTransX(int x) {
		transX = -x;
	}
	
	public void setTransY(int y) {
		transY = -y;
	}
	
	public int getTransX() {
		return transX;
	}
	
	public int getTransY() {
		return transY;
	}
	
}
