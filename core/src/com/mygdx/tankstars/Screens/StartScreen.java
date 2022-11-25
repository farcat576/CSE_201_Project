package com.mygdx.tankstars.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.tankstars.Scenes.HUD;
import com.mygdx.tankstars.TankStars;

public class StartScreen implements Screen {
    private TankStars game;
    private Texture img;
    private OrthographicCamera cam;
    private FitViewport gameport;


    public StartScreen(TankStars game){
        this.game=game;
        this.img=new Texture(Gdx.files.internal("Start_Menu.png"));
        this.cam = new OrthographicCamera();
        this.gameport = new FitViewport(TankStars.V_WIDTH ,TankStars.V_HEIGHT,cam);
    }
    @Override
    public void show() {

        this.Circle = new ShapeRenderer();
    }


    @Override
    public void render(float delta) {
//        if(Gdx.input.justTouched()) {
//            game.setScreen(new PlayScreen((TankStars) game));
//            this.dispose();
//        }
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();


        this.Circle.begin(ShapeRenderer.ShapeType.Filled);
        this.Circle.circle(50,50,20);
        this.Circle.end();
//
        game.batch.draw(this.img,0,0,this.cam.viewportWidth,this.cam.viewportHeight);
        game.batch.end();
    }

    private ShapeRenderer Circle;


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
        this.Circle.dispose();
        this.img.dispose();
    }
}
