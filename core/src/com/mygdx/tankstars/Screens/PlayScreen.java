package com.mygdx.tankstars.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.tankstars.Scenes.HUD;
import com.mygdx.tankstars.TankStars;

public class PlayScreen implements Screen{
    private TankStars game;
    private HUD hud;
    private OrthographicCamera cam;
    private Viewport gameport;

    private TmxMapLoader mapLoader;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;

    public PlayScreen(TankStars game){
        this.game=game;
        this.cam = new OrthographicCamera();
        this.gameport = new FitViewport(TankStars.V_WIDTH ,TankStars.V_HEIGHT,cam);
        this.hud = new HUD(game.batch);

        this.mapLoader = new TmxMapLoader();
        this.tiledMap = this.mapLoader.load("M2.tmx");
        this.orthogonalTiledMapRenderer = new OrthogonalTiledMapRenderer(this.tiledMap);

        this.cam.position.set( this.gameport.getWorldWidth()/2, this.gameport.getWorldHeight()/2 , 0 );


        this.texture1 = new Texture(Gdx.files.internal("Abrams_transparent.png"));
        this.sprite1 = new Sprite(texture1,0,0, 555,305);

        sprite1.setPosition(0,250);
        sprite1.setSize(111,61);
        this.tankposition = 0;
        this.tankposition2 = 0;

        this.texture1 = new Texture(Gdx.files.internal("Abrams_transparent.png"));
        this.sprite1 = new Sprite(texture1,0,0, 555,305);

        this.tanktexture2 = new Texture(Gdx.files.internal("Frost_transparent.png"));
        this.tank2 = new Sprite(tanktexture2,0,0, 499,282);

        sprite1.setPosition(0,250);
        sprite1.setSize(111,61);

        tank2.setPosition( this.game.V_WIDTH - 200, 250);
        tank2.setSize(100,57);
        tank2.flip(true,false);

        this.tankposition2 = this.game.V_WIDTH-200;


    }
    private Texture tanktexture2;
    private Sprite tank2;

    private int tankposition ;
    private int tankposition2 ;

    private Sprite sprite1;
    private Texture texture1;


    @Override
    public void show() {

    }

    public void handleInput(){

    }

    private float speed = 1000;

    public void update(float delta){
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            if(this.tankposition > 0)
            this.tankposition -= (int)(delta*speed);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            if(this.tankposition2 < game.V_WIDTH - 111)
            this.tankposition += (int)(delta*speed);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if(this.tankposition2 > 0 )
            this.tankposition2 -= (int)(delta*speed);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if(this.tankposition2 < game.V_WIDTH - 100)
                this.tankposition2 += (int)(delta*speed);
        }



    }

    @Override
    public void render(float delta) {

        this.update(Gdx.graphics.getDeltaTime());

        this.cam.update();
        orthogonalTiledMapRenderer.setView(this.cam);


        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        orthogonalTiledMapRenderer.render();





        game.batch.setProjectionMatrix(this.hud.stage.getCamera().combined);
        this.hud.stage.draw();

        game.batch.begin();
        sprite1.setPosition(this.tankposition,250);
        sprite1.draw(this.game.batch);

        tank2.setPosition(this.tankposition2,250);
        tank2.draw(this.game.batch);

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

        texture1.dispose();
    }
}
