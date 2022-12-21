package com.mygdx.tankstars.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
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
    private OrthographicCamera cam;
    private FitViewport gameport;

    private final float radius=50;



    public StartScreen(TankStars game){
        this.game=game;
        this.cam = new OrthographicCamera();
        this.gameport = new FitViewport(TankStars.V_WIDTH ,TankStars.V_HEIGHT,this.cam);
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {
                int renderY = Gdx.graphics.getHeight() - y;
                if (Vector2.dst(Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .5f, x, renderY) < radius) {
                    game.setScreen(new SelectScreen(game));
                }
                if (Vector2.dst(Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .4f, x, renderY) < radius) {
                    game.setScreen(new FileScreen(game));
                }
                if (Vector2.dst(Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .3f, x, renderY) < radius) {
                    game.setScreen(new ShopScreen(game));
                }
                if (Vector2.dst(Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .2f, x, renderY) < radius) {
                    game.setScreen(new SettingsScreen(game));
                }
                if (Vector2.dst(Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .1f, x, renderY) < radius) {
                    game.dispose();
                    System.exit(0);
                }
                return true;
            }
        });
    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        game.batch.begin();
        game.font.getData().setScale(5);
        game.font.draw(game.batch, "Tank Stars!", Gdx.graphics.getWidth() * .35f, Gdx.graphics.getHeight() * .75f);
        game.font.getData().setScale(1);
        game.font.draw(game.batch, "Click this to play.", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .5f);
        game.font.draw(game.batch, "Click this to load a saved game.", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .4f);
        game.font.draw(game.batch, "Click this to open the shop.", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .3f);
        game.font.draw(game.batch, "Click this to open settings.", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .2f);
        game.font.draw(game.batch, "Click this to exit.", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .1f);

//        this.Circle.begin(ShapeRenderer.ShapeType.Filled);
//        this.Circle.circle(50,50,20);
//        this.Circle.end();

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
        Gdx.input.setInputProcessor(null);
    }
}
