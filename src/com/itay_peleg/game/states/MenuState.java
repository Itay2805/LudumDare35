package com.itay_peleg.game.states;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.itay_peleg.engine.GameContainer;
import com.itay_peleg.engine.components.State;
import com.itay_peleg.engine.gui.GUIContainer;
import com.itay_peleg.engine.gui.builtin.Button;
import com.itay_peleg.game.Assets;
import com.itay_peleg.game.util.Save;

public class MenuState extends State {
	
	private GUIContainer gui = new GUIContainer();
	private BufferedImage bg; 
	
	/* Too lazy to do it smart XD */
	private Button level_1 = new Button("1", 10 * 1 + 130 * 0, 500, 130, 130);
	private Button level_2 = new Button("2", 10 * 2 + 130 * 1, 500, 130, 130);
	private Button level_3 = new Button("3", 10 * 3 + 130 * 2, 500, 130, 130);
	private Button level_4 = new Button("4", 10 * 4 + 130 * 3, 500, 130, 130);
	private Button level_5 = new Button("5", 10 * 5 + 130 * 4, 500, 130, 130);
	private Button level_6 = new Button("6", 10 * 6 + 130 * 5, 500, 130, 130);
	private Button level_7 = new Button("7", 10 * 7 + 130 * 6, 500, 130, 130);
	private Button level_8 = new Button("8", 10 * 8 + 130 * 7, 500, 130, 130);
	private Button level_9 = new Button("9", 10 * 9 + 130 * 8, 500, 130, 130);
	
	private Button level_10 = new Button("10", 10 * 1 + 130 * 0, 500 + 10 * 2 + 130 * 1, 130, 130);
	private Button level_11 = new Button("11", 10 * 2 + 130 * 1, 500 + 10 * 2 + 130 * 1, 130, 130);
	private Button level_12 = new Button("12", 10 * 3 + 130 * 2, 500 + 10 * 2 + 130 * 1, 130, 130);
	private Button level_13 = new Button("13", 10 * 4 + 130 * 3, 500 + 10 * 2 + 130 * 1, 130, 130);
	private Button level_14 = new Button("14", 10 * 5 + 130 * 4, 500 + 10 * 2 + 130 * 1, 130, 130);
	private Button level_15 = new Button("15", 10 * 6 + 130 * 5, 500 + 10 * 2 + 130 * 1, 130, 130);
	private Button level_16 = new Button("16", 10 * 7 + 130 * 6, 500 + 10 * 2 + 130 * 1, 130, 130);
	private Button level_17 = new Button("17", 10 * 8 + 130 * 7, 500 + 10 * 2 + 130 * 1, 130, 130);
	private Button level_18 = new Button("18", 10 * 9 + 130 * 8, 500 + 10 * 2 + 130 * 1, 130, 130);

	public MenuState() {
		bg = Assets.loadImage("/ui/background.png");
		
		int level = Save.load();
		if(level >= 0) gui.addGUIObject(level_1.setButtonImage(Assets.button));
		if(level >= 1) gui.addGUIObject(level_2.setButtonImage(Assets.button));
		if(level >= 2) gui.addGUIObject(level_3.setButtonImage(Assets.button));
		if(level >= 3) gui.addGUIObject(level_4.setButtonImage(Assets.button));
		if(level >= 4) gui.addGUIObject(level_5.setButtonImage(Assets.button));
		if(level >= 5) gui.addGUIObject(level_6.setButtonImage(Assets.button));
		if(level >= 6) gui.addGUIObject(level_7.setButtonImage(Assets.button));
		if(level >= 7) gui.addGUIObject(level_8.setButtonImage(Assets.button));
		if(level >= 8) gui.addGUIObject(level_9.setButtonImage(Assets.button));
		if(level >= 9) gui.addGUIObject(level_10.setButtonImage(Assets.button));
		if(level >= 10) gui.addGUIObject(level_11.setButtonImage(Assets.button));
		if(level >= 11) gui.addGUIObject(level_12.setButtonImage(Assets.button));
		if(level >= 12) gui.addGUIObject(level_13.setButtonImage(Assets.button));
		if(level >= 13) gui.addGUIObject(level_14.setButtonImage(Assets.button));
		if(level >= 14) gui.addGUIObject(level_15.setButtonImage(Assets.button));
		if(level >= 15) gui.addGUIObject(level_16.setButtonImage(Assets.button));
		if(level >= 16) gui.addGUIObject(level_17.setButtonImage(Assets.button));
		if(level >= 17) gui.addGUIObject(level_18.setButtonImage(Assets.button));
	}
	
	public void update(GameContainer gc) {
		gui.update(gc);
		int level = Save.load();
		if(level_1.isInteracted() && level >= 0) { gc.getGame().setState(new PlayState(0)); Assets.click.play(); }
		if(level_2.isInteracted() && level >= 1) { gc.getGame().setState(new PlayState(1)); Assets.click.play(); }
		if(level_3.isInteracted() && level >= 2) { gc.getGame().setState(new PlayState(2)); Assets.click.play(); }
		if(level_4.isInteracted() && level >= 3) { gc.getGame().setState(new PlayState(3)); Assets.click.play(); }
		if(level_5.isInteracted() && level >= 4) { gc.getGame().setState(new PlayState(4)); Assets.click.play(); }
		if(level_6.isInteracted() && level >= 5) { gc.getGame().setState(new PlayState(5)); Assets.click.play(); }
		if(level_7.isInteracted() && level >= 6) { gc.getGame().setState(new PlayState(6)); Assets.click.play(); }
		if(level_8.isInteracted() && level >= 7) { gc.getGame().setState(new PlayState(7)); Assets.click.play(); }
		if(level_9.isInteracted() && level >= 8) { gc.getGame().setState(new PlayState(8)); Assets.click.play(); }
		if(level_10.isInteracted() && level >= 9) { gc.getGame().setState(new PlayState(9)); Assets.click.play(); }
		if(level_11.isInteracted() && level >= 10) { gc.getGame().setState(new PlayState(10)); Assets.click.play(); }
		if(level_12.isInteracted() && level >= 11) { gc.getGame().setState(new PlayState(11)); Assets.click.play(); }
		if(level_13.isInteracted() && level >= 12) { gc.getGame().setState(new PlayState(12)); Assets.click.play(); }
		if(level_14.isInteracted() && level >= 13) { gc.getGame().setState(new PlayState(13)); Assets.click.play(); }
		if(level_15.isInteracted() && level >= 14) { gc.getGame().setState(new PlayState(14)); Assets.click.play(); }
		if(level_16.isInteracted() && level >= 15) { gc.getGame().setState(new PlayState(15)); Assets.click.play(); }
		if(level_17.isInteracted() && level >= 16) { gc.getGame().setState(new PlayState(16)); Assets.click.play(); }
		if(level_18.isInteracted() && level >= 17) { gc.getGame().setState(new PlayState(17)); Assets.click.play(); }
	}

	public void render(GameContainer gc, Graphics g) {
		g.drawImage(bg, 0, 0, gc.getWidth(), gc.getHeight(), null);
		gui.render(gc, g);
	}

	public void dispose() {}

}
