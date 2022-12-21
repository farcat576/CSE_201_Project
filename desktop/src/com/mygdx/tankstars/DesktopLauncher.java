package com.mygdx.tankstars;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.mygdx.tankstars.TankStarsCS;
import com.mygdx.tankstars.TankStars;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setWindowedMode(1200, 720);
		config.useVsync(true);
		config.setTitle("TankStarsCSE201");
		new Lwjgl3Application(new TankStars(), config);
	}
}
