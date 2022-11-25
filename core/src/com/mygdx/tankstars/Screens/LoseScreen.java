package com.mygdx.tankstars.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.tankstars.TankStars;

public class LoseScreen implements Screen {
    private TankStars game;
    private Texture img;
    private OrthographicCamera cam;
    private FitViewport gameport;


    public LoseScreen(TankStars game){
        this.game=game;
        this.img=new Texture(Gdx.files.internal("Lose_Menu.png"));
        this.cam = new OrthographicCamera();
        this.gameport = new FitViewport(TankStars.V_WIDTH ,TankStars.V_HEIGHT,cam);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()) {
            game.setScreen(new PlayScreen((TankStars) game));
            this.dispose();
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(this.img,0,0);
        game.batch.end();
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
        this.img.dispose();
    }
}
