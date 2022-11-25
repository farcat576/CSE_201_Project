package com.mygdx.tankstars;

import com.mygdx.tankstars.Screens.PlayScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.tankstars.Screens.StartScreen;

public class TankStars extends Game {
	public SpriteBatch batch;
	public static final int V_WIDTH = 1200;
	public static final int V_HEIGHT = 720;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new StartScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
