package com.itay_peleg.game.gameobjects;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.itay_peleg.engine.GameContainer;
import com.itay_peleg.engine.components.GameObject;
import com.itay_peleg.game.Assets;
import com.itay_peleg.game.states.FinalState;
import com.itay_peleg.game.states.ScoreState;
import com.itay_peleg.game.util.Map;

public class Player extends GameObject {
	
	public static enum Shape {
		BALL, X, TRIANGLE, STICK;
	}
	
	private double dx, dy;
	
	private final double MOVE_SPEED = 0.5 / 2;
	private final double MAX_SPEED = 2 / 2;
	private final double MAX_FALLING_SPEED = 14 / 2;
	private final double STOP_SPEED = 0.44 / 2;
	private final double JUMP_START = -14 / 2;
	private final double GRAVITY = 0.64 / 2;
	
	private double speed_boost = 0;
	private double jump_boost = 0;
	
	private double speed_boost_timer = -1;
	private double jump_boost_timer = -1;
	private double shift_timer = -1;
	private double stick_timer = -1;
	
	private boolean jumping = false, falling = true;
	
	private Shape shape;
	
	private Map map;
			
	public Player(Map map) {
		shape = Shape.BALL;
		this.map = map;
		w = 30;
		h = 30;
		x = (64 + 36) / 2;
		y = 864 / 2;
		tag = "player";
	}
	
	String name = null;

	public void update(GameContainer gc) {
		if((gc.getInput().isButtonPressed(MouseEvent.BUTTON1) || gc.getInput().isKeyPressed(KeyEvent.VK_Z)) && shift_timer == -1) {
			switch(shape) {
			case BALL:
				shape = Shape.TRIANGLE;
				break;
			case TRIANGLE:
				shape = Shape.X;
				break;
			case X:
				shape = Shape.BALL;
				break;
			case STICK:
				break;
			}
		}
		int tile = map.getTile((int)(x / map.getTileSize()), (int)(y / map.getTileSize() + 1));
		switch(tile) {
		case 3:
			jump_boost_timer = 3;
			jump_boost = -6 / 2;
			break;
		case 7:
			speed_boost_timer = 3;
			speed_boost = 1.25 / 2;
			break;
		case 4:
			shape = Shape.BALL;
			map.nextLevel(gc);
			speed_boost = 0;
			speed_boost_timer = -1;
			jump_boost = 0;
			jump_boost_timer = 0;
			dx = 0;
			dy = 0;
			y = 864 / 2;
			x = (64 + 36) / 2;
			return;
		case 2:
			shift_timer = 1.5;
			break;
		case 8:
			shape = Shape.STICK;
			stick_timer = 3;
			break;
		case 9:
			gc.getGame().setState(new ScoreState(map));
			break;
		}
		
		tile = map.getTile((int)(x / map.getTileSize()), (int)(y / map.getTileSize()));
		switch(tile) {
		case 4:
			shape = Shape.BALL;
			map.nextLevel(gc);
			speed_boost = 0;
			speed_boost_timer = -1;
			jump_boost = 0;
			jump_boost_timer = 0;
			dx = 0;
			dy = 0;
			y = 864 / 2;
			x = (64 + 36) / 2;
			return;
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
			name = "/level_res/level_" + map.getLevel() + "_dialog_" + (tile - 10 + 1) + ".png";
			break;
		case 18:
			name = "/level_res/level_" + map.getLevel() + "_scroll.png";
			break;
		case 9:
			gc.getGame().setState(new ScoreState(map));
			break;
		case 23:
			gc.getGame().setState(new FinalState(gc.getGame().peek(), map));;
			break;
		}
		
		if(gc.getInput().isKey(KeyEvent.VK_SPACE) || gc.getInput().isKey(KeyEvent.VK_UP) || gc.getInput().isKey(KeyEvent.VK_W)) {
			jumping = true;
		}else {
			jumping = false;
		}
		if(gc.getInput().isKey(KeyEvent.VK_A) || gc.getInput().isKey(KeyEvent.VK_LEFT)) {
			dx -= MOVE_SPEED + speed_boost;
			if(dx > -MAX_SPEED) {
				dx = -MAX_SPEED;
			}
		}else if(gc.getInput().isKey(KeyEvent.VK_D) || gc.getInput().isKey(KeyEvent.VK_RIGHT)) {
			dx += MOVE_SPEED + speed_boost;
			if(dx < MAX_SPEED) {
				dx = MAX_SPEED;
			}
		}else {
			if(dx > 0) {
				tile = map.getTile((int)(x / map.getTileSize()), (int)(y / map.getTileSize() + 1));
				dx -= STOP_SPEED;
				if(dx < 0) {
					dx = 0;
				}
				if(tile == 22) {
					dx = 0;
					speed_boost_timer = -1;
					speed_boost = 0;
				}
			}else if(dx < 0) {
				tile = map.getTile((int)(x / map.getTileSize()), (int)(y / map.getTileSize() + 1));
				dx += STOP_SPEED;
				if(dx > 0) {
					dx = 0;
				}
				if(tile == 22) {
					dx = 0;
					speed_boost_timer = -1;
					speed_boost = 0;
				}
			}
		}
		if(jumping && !falling) {
			dy = JUMP_START + jump_boost;
			falling = true;
			jumping = false;
		}
		if(falling) {
			dy += GRAVITY;
			if(dy > MAX_FALLING_SPEED) {
				dy = MAX_FALLING_SPEED;
			}
		}else {
			dy = 0;
		}
		int currCol = map.getColTile((int)x);
		int currRow = map.getRowTile((int)y);
		double tox = (x + dx);
		double toy = (y + dy);
		double tempX = x;
		double tempY = y;
		calulateCorners(x, toy);
		if(dy < 0) {
			if(topLeft || topRight) {
				dy = 0;
				tempY = (currRow * map.getTileSize() + h / 2);
			}else {
				tempY += dy;
			}
		}
		if(dy > 0) {
			if(bottomLeft || bottomRight) {
				dy = 0;
				falling = false;
				tempY = ((currRow + 1) * map.getTileSize() - h / 2);
			}else {
				tempY += dy;
			}
		}
		calulateCorners(tox, y);
		if(dx < 0) {
			if(topLeft || bottomLeft) {
				dx = 0;
				tempX = (currCol * map.getTileSize() + w / 2);
			}else {
				tempX += dx;
			}
		}
		if(dx > 0) {
			if(topRight || bottomRight) {
				dx = 0;
				tempX = ((currCol + 1) * map.getTileSize() - w / 2);
			}else {
				tempX += dx;
			}
		}
		if(!falling) {
			calulateCorners(x, y + 1);
			if((!bottomLeft && !bottomRight) || (!bottomLeft && !bottomRight)) {
				falling = true;
			}
		}
		
		if(jump_boost_timer > 0) {
			jump_boost_timer -= gc.getFrameRate();
		}else {
			jump_boost_timer = -1;
			jump_boost = 0;
		}
		
		if(speed_boost_timer > 0) {
			speed_boost_timer -= gc.getFrameRate();
		}else {
			speed_boost_timer = -1;
			speed_boost = 0;
		}
		
		if(shift_timer > 0) {
			shift_timer -= gc.getFrameRate();
		}else {
			shift_timer = -1;
		}
		
		if(stick_timer > 0) {
			stick_timer -= gc.getFrameRate();
		}else {
			stick_timer = -1;
			if(shape == Shape.STICK) shape = Shape.BALL; 
		}
		
		x = (float)tempX;
		y = (float)tempY;
		
		map.setTransX((int) ((x + w / 2) - gc.getWidth() / 2));
	}
	
	private boolean topLeft, topRight, bottomLeft, bottomRight;
	
	private void calulateCorners(double x, double y) {
		int leftTile = map.getColTile((int) Math.round(x - w / 2));
		int rightTile = map.getColTile((int) Math.round(x + w / 2) - 1);
		int topTile = map.getRowTile((int) Math.round(y - h / 2));
		int bottomTile = map.getRowTile((int) Math.round(y + h / 2) - 1);
		
		int topLeftVal = map.getTile(leftTile, topTile);
		int topRightVal = map.getTile(rightTile, topTile);
		int bottomLeftVal = map.getTile(leftTile, bottomTile);
		int bottomRightVal =  map.getTile(rightTile, bottomTile);
				
		topLeft = topLeftVal != 0 && topLeftVal != 10 && topLeftVal != 11 && topLeftVal != 12 && topLeftVal != 13 && topLeftVal != 14 && topLeftVal != 18 && topLeftVal != 9 && topLeftVal != 4 && topLeftVal != 19 && topLeftVal != 20 && topLeftVal != 21 && topLeftVal != 23;
		topRight = topRightVal != 0 && topRightVal != 10 && topRightVal != 11 && topRightVal != 12 && topRightVal != 13 && topRightVal != 14 && topRightVal != 18 && topRightVal != 9 && topRightVal != 4 && topRightVal != 19 && topRightVal != 20 && topRightVal != 21 && topRightVal != 23;
		bottomLeft = bottomLeftVal != 0 && bottomLeftVal != 10 && bottomLeftVal != 11 && bottomLeftVal != 12 && bottomLeftVal != 13 && bottomLeftVal != 14 && bottomLeftVal != 18 && bottomLeftVal != 9 && bottomLeftVal != 4 && bottomLeftVal != 19 && bottomLeftVal != 20 && bottomLeftVal != 21 && bottomLeftVal != 23;
		bottomRight = bottomRightVal != 0 && bottomRightVal != 10 && bottomRightVal != 11 && bottomRightVal != 12 && bottomRightVal != 13 && bottomRightVal != 14 && bottomRightVal != 18 && bottomRightVal != 9 && bottomRightVal != 4 && bottomRightVal != 19 && bottomRightVal != 20 && bottomRightVal != 21 && bottomRightVal != 23;
		
		switch(shape) {
		case BALL:
			topLeft = topLeft && topLeftVal != 17;
			topRight = topRight && topRightVal != 17;
			bottomLeft = bottomLeft && bottomLeftVal != 17;
			bottomRight = bottomRight && bottomRightVal != 17;
			break;
		case TRIANGLE:
			topLeft = topLeft && topLeftVal != 15;
			topRight = topRight && topRightVal != 15;
			bottomLeft = bottomLeft && bottomLeftVal != 15;
			bottomRight = bottomRight && bottomRightVal != 15;
			break;
		case X:
			topLeft = topLeft && topLeftVal != 16;
			topRight = topRight && topRightVal != 16;
			bottomLeft = bottomLeft && bottomLeftVal != 16;
			bottomRight = bottomRight && bottomRightVal != 16;
			break;
		case STICK:
			topLeft = topLeft && topLeftVal != 15 && topLeftVal != 16 && topLeftVal != 17;
			topRight = topRight && topRightVal != 15 && topRightVal != 16 && topRightVal != 17;
			bottomLeft = bottomLeft && bottomLeftVal != 15 && bottomLeftVal != 16 && bottomLeftVal != 17;
			bottomRight = bottomRight && bottomRightVal != 15 && bottomRightVal != 16 && bottomRightVal != 17;
			break;
		}
	}
	
	public void render(GameContainer gc, Graphics g) {
		switch(shape) {
		case BALL:
			g.drawImage(Assets.player_ball, map.getTransX() + (int) x - 16, map.getPlayerY() + (int) y - 48, (int)w, (int)h, null);
			break;
		case TRIANGLE:
			g.drawImage(Assets.player_tirangle, map.getTransX() + (int) x - 16, map.getPlayerY() + (int) y - 48, (int)w, (int)h, null);
			break;
		case X:
			g.drawImage(Assets.player_x, map.getTransX() + (int) x - 16, map.getPlayerY() + (int) y - 48, (int)w, (int)h, null);
			break;
		case STICK:
			g.drawImage(Assets.player_stick, map.getTransX() + (int) x - 16, map.getPlayerY() + (int) y - 48, (int)w, (int)h, null);
		}
		if(shift_timer > 0) {
			g.drawImage(Assets.cant_shift,  10, 10, 32, 32, null);
		}
		if(jump_boost_timer > 0) {
			g.drawImage(Assets.JumpTile,  10 + 10 + 32, 10, 32, 32, null);
		}
		if(speed_boost_timer > 0) {
			g.drawImage(Assets.SpeedTile,  10 + 10 + 32 + 10 + 32, 10, 32, 32, null);
		}
		if(name != null) {
			BufferedImage img = Assets.loadImage(name);
			g.drawImage(img,  gc.getWidth() / 2 - img.getWidth() / 2, gc.getHeight() / 2 - img.getHeight() / 2, null);
			name = null;
		}
	}

	public void dispose() {}

}
