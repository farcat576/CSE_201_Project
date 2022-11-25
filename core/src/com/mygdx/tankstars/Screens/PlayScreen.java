package com.mygdx.tankstars.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.tankstars.Scenes.HUD;
import com.mygdx.tankstars.TankStars;

public class PlayScreen implements Screen{
    private TankStars game;
    private HUD hud;
    private OrthographicCamera cam;
    private Viewport gameport;


    public PlayScreen(TankStars game){
        this.game=game;
        this.cam = new OrthographicCamera();
        this.gameport = new FitViewport(TankStars.V_WIDTH ,TankStars.V_HEIGHT,cam);
        this.hud = new HUD(game.batch);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(this.hud.stage.getCamera().combined);
        this.hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gameport.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
