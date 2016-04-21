package com.itay_peleg.engine.gui.builtin;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.itay_peleg.engine.GameContainer;
import com.itay_peleg.engine.gui.AbstractGUI;

public class Button extends AbstractGUI {
	
	private int x, y, w, h;
	
	private String text = "";
	private Font textFont = new Font("Cochin", Font.BOLD, 30);
	private Color textColor = Color.WHITE;
	private BufferedImage buttonImage = null;
	private Color color = Color.BLACK;
	private Color backgroundColor = Color.WHITE;
	private boolean background = true;
	
	public Button(String tag, int x, int y, int w, int h) {
		this.tag = tag;
		this.text = tag;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	public void init(GameContainer gc) {}

	public void update(GameContainer gc) {
		interacted = false;
		if(gc.getInput().isButtonPressed(MouseEvent.BUTTON1)) {
			if(gc.getInput().getMouseX() > x && gc.getInput().getMouseX() < x + w && gc.getInput().getMouseY() > y && gc.getInput().getMouseY() < y + h ) {
				interacted = true;
			}
		}
	}

	public void render(GameContainer gc, Graphics g) {
		Color c = g.getColor();
		if(buttonImage == null) {
			if(background) {
				g.setColor(backgroundColor);
				g.fillRect(x, y, w, h);
			}
			g.setColor(color);
			g.drawRect(x, y, w, h);
			g.setFont(textFont);
			g.setColor(textColor);
			g.drawString(text, x + 5, y + h / 2 + 15);
		}else {
			g.drawImage(buttonImage, x, y, w, h, null);
			g.setFont(textFont);
			g.setColor(textColor);
			g.drawString(text, x + w / 2 - (30 * text.length()) / 2, y + h / 2 + 30);
		}
		g.setColor(c);
	}
	
	// Getters and Setters

	public String getText() {
		return text;
	}

	public Button setText(String text) {
		this.text = text;
		return this;
	}

	public Image getButtonImage() {
		return buttonImage;
	}

	public Button setButtonImage(BufferedImage buttonImage) {
		this.buttonImage = buttonImage;
		return this;
	}

	public Font getTextFont() {
		return textFont;
	}

	public Button setTextFont(Font textFont) {
		this.textFont = textFont;
		return this;
	}

	public Color getTextColor() {
		return textColor;
	}

	public Button setTextColor(Color textColor) {
		this.textColor = textColor;
		return this;
	}

	public Color getColor() {
		return color;
	}

	public Button setColor(Color color) {
		this.color = color;
		return this;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public Button setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
		return this;
	}

	public boolean isBackground() {
		return background;
	}

	public Button setBackground(boolean background) {
		this.background = background;
		return this;
	}
	
}
