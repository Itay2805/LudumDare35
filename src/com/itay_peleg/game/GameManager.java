package com.itay_peleg.game;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import com.itay_peleg.engine.AbstractGame;
import com.itay_peleg.engine.GameContainer;
import com.itay_peleg.engine.util.Log;
import com.itay_peleg.game.states.MenuState;

public class GameManager extends AbstractGame {
	
	private MusicManager music;
	
	public void init() {
		music = new MusicManager();
		Assets.init();
		push(new MenuState());
	}

	public void update(GameContainer gc) {
		music.update(gc);
		peek().update(gc);
	}

	public void render(GameContainer gc, Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
		peek().render(gc, g);
	}
	
	public static void main(String[] args) {
		try {
			Log.setPrintStream(new PrintStream(new File("game.log")));
		} catch (FileNotFoundException e) {
			Log.error("GameContainer", "Could not create log file.");
			e.printStackTrace();
		}
		GameContainer gc = new GameContainer(new GameManager());
		gc.setHeight(480 * 2).setWidth(640 * 2);
		gc.setTitle("Shape Bender");
		gc.start();
	}
}