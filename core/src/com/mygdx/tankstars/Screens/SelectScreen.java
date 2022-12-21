package com.mygdx.tankstars.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.tankstars.TankStars;

public class SelectScreen implements Screen {
    private TankStars game;
    private OrthographicCamera cam;
    private FitViewport gameport;
    private final float radius=50;
    private static int descriptions[]={0,1,2,3,4};
    private static PlayScreen screen;
    private static int chosing=0;


    public SelectScreen(TankStars game){
        this.game=game;
        this.cam = new OrthographicCamera();
        this.gameport = new FitViewport(TankStars.V_WIDTH ,TankStars.V_HEIGHT,cam);

        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);
        // Do the array
        screen = new PlayScreen(game);
        chosing=0;
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {
                int t1=0;
                int renderY = Gdx.graphics.getHeight() - y;
                for (int i:descriptions) {
                    if (Vector2.dst(Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .1f * (6 - i), x, renderY) < radius) {
                        if(chosing==0) {
                            screen.setTank1(i);
                            chosing=1;
                        }
                        else{
                            screen.setTank2(i);
                            game.setScreen(screen);
                        }
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        String tanks[] = {"Abrams","Frost","Buratino","Helios","Blazer"};
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        for(int i:descriptions){
            game.font.draw(game.batch, "Click this to play "+tanks[i], Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .1f*(6-i));
        }
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

    }
}
