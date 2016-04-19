package com.itay_peleg.game.util;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.itay_peleg.engine.GameContainer;
import com.itay_peleg.engine.util.Log;
import com.itay_peleg.game.Assets;
import com.itay_peleg.game.states.ScoreState;

public class Map {
	
	private int w, h, playerX, playerY;
	private int tileSize = 64;
	
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
						g.drawImage(Assets.basicTile, transX + x * tileSize, transY + y * tileSize, null);
						break;
					case 2:
						g.drawImage(Assets.bendingBlockTile, transX + x * tileSize, transY + y * tileSize, null);
						break;
					case 3:
						g.drawImage(Assets.JumpTile, transX + x * tileSize, transY + y * tileSize, null);
						break;
					case 4:
						g.drawImage(Assets.NextLevelTile, transX + x * tileSize, transY + y * tileSize, null);
						break;
					case 5:
						g.drawImage(Assets.SlowTile, transX + x * tileSize, transY + y * tileSize, null);
						break;
					case 6:
						g.drawImage(Assets.SmallTile, transX + x * tileSize, transY + y * tileSize, null);
						break;
					case 7:
						g.drawImage(Assets.SpeedTile, transX + x * tileSize, transY + y * tileSize, null);
						break;
					case 8:
						g.drawImage(Assets.stick, transX + x * tileSize, transY + y * tileSize, null);
						break;
					case 9:
						g.drawImage(Assets.spike, transX + x * tileSize, transY + y * tileSize, null);
						break;
					case 10:
					case 11:
					case 12:
					case 13:
					case 14:	
						g.drawImage(Assets.sign, transX + x * tileSize, transY + y * tileSize, null);
						break;
					case 15:
						g.drawImage(Assets.gate_triangle, transX + x * tileSize, transY + y * tileSize, null);
						break;
					case 16:
						g.drawImage(Assets.gate_x, transX + x * tileSize, transY + y * tileSize, null);
						break;
					case 17:
						g.drawImage(Assets.gate_ball, transX + x * tileSize, transY + y * tileSize, null);
						break;
					case 18:
						g.drawImage(Assets.scroll, transX + x * tileSize, transY + y * tileSize, null);
						break;
					case 19:
						g.drawImage(Assets.npc_ball, transX + x * tileSize, transY + y * tileSize, null);
						break;
					case 20:
						g.drawImage(Assets.npc_x, transX + x * tileSize, transY + y * tileSize, null);
						break;
					case 21:
						g.drawImage(Assets.npc_tirangle, transX + x * tileSize, transY + y * tileSize, null);
						break;
					case 22:
						g.drawImage(Assets.stopTile, transX + x * tileSize, transY + y * tileSize, null);
						break;
					case 23:
						g.drawImage(Assets.outsider, transX + x * tileSize, transY + y * tileSize, null);
						break;
				}
			}
		}
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
