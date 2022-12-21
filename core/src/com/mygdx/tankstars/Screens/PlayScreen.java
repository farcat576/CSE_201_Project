package com.mygdx.tankstars.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.tankstars.*;
import com.mygdx.tankstars.Scenes.HUD;

public class PlayScreen implements Screen{
    private TankStars game;
    private HUD hud;
    private OrthographicCamera cam;
    private Viewport gameport;

    private TmxMapLoader mapLoader;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;


    private Box2DDebugRenderer debugRenderer;
    private World box2dWorld;

    private Tank tank1;

    private TANK_TYPE tank1Type;
    private TANK_TYPE tank2Type;

    private Tank tank2;
    private Tank currentTank;
    private Level level;

    public PlayScreen(TankStars game){
        this.game=game;
        this.cam = new OrthographicCamera();
        this.gameport = new FitViewport(TankStars.V_WIDTH / TankStars.PPM ,TankStars.V_HEIGHT / TankStars.PPM,cam);
        this.hud = new HUD(game.batch);

        this.mapLoader = new TmxMapLoader();
        this.tiledMap = this.mapLoader.load("M3.tmx");
        this.orthogonalTiledMapRenderer = new OrthogonalTiledMapRenderer(this.tiledMap, 1/TankStars.PPM);

        this.cam.position.set( this.gameport.getWorldWidth()/2, this.gameport.getWorldHeight()/2 , 0 );

        box2dWorld = new World(new Vector2(0,-10),true);
        debugRenderer = new Box2DDebugRenderer();

        this.level = new Level(box2dWorld,this.tiledMap);

        box2dWorld.setContactListener(new CollisionListener());

        this.tank1Type = TANK_TYPE.ABRAMS;
        this.tank2Type = TANK_TYPE.FROST;
        this.setTank1();
        this.setTank2();

        this.currentTank = tank1;
        currentTank.setFuel(100);

        //this.texture1 = new Texture(Gdx.files.internal("Abrams_transparent.png"));
        //this.sprite1 = new Sprite(texture1,0,0, 555,305);

        //sprite1.setPosition(0,250);
        //sprite1.setSize(111,61);
        //this.tankposition = 0;
        //this.tankposition2 = 0;

        //this.texture1 = new Texture(Gdx.files.internal("Abrams_transparent.png"));
        //this.sprite1 = new Sprite(texture1,0,0, 555,305);

        //this.tanktexture2 = new Texture(Gdx.files.internal("Frost_transparent.png"));
        //this.tank2 = new Sprite(tanktexture2,0,0, 499,282);

        //sprite1.setPosition(0,250);
        //sprite1.setSize(111,61);

        //tank2.setPosition( this.game.V_WIDTH - 200, 250);
        //tank2.setSize(100,57);
        //tank2.flip(true,false);

        //this.tankposition2 = this.game.V_WIDTH-200;


    }

    private void setTank1(){
        Sprite tankSprite;
        tankSprite = tankSetup(tank1Type);
        this.tank1= new Tank(this.box2dWorld,100,tankSprite,14,250);
    }

    private Sprite tankSetup(TANK_TYPE s){
        Texture tankTexture;
        Sprite tankSprite;

        switch (s){
            case ABRAMS:
                tankTexture = new Texture(Gdx.files.internal("Abrams_transparent.png")) ;
                tankSprite = new Sprite(tankTexture , 0,0,555,305);
                tankSprite.setSize(83,45);
                break;
            case BURATINO:
                tankTexture = new Texture(Gdx.files.internal("Buratino_Transparent.png")) ;
                tankSprite = new Sprite(tankTexture , 0,0,555,305);
                tankSprite.setSize(111,61);
                break;
            case FROST:
                tankTexture = new Texture(Gdx.files.internal("Frost_transparent.png")) ;
                tankSprite = new Sprite(tankTexture , 0,0,499,282);
                tankSprite.setSize(75  ,42);
                break;
            case BLAZER:
                tankTexture = new Texture(Gdx.files.internal("Frost_transparent.png")) ;
                tankSprite = new Sprite(tankTexture , 0,0,499,282);
                tankSprite.setSize(100/TankStars.PPM,57/TankStars.PPM);
                break;
            case HELIOS:
                tankTexture = new Texture(Gdx.files.internal("Frost_transparent.png")) ;
                tankSprite = new Sprite(tankTexture , 0,0,499,282);
                tankSprite.setSize(100/TankStars.PPM,57/TankStars.PPM);
            default:
                tankTexture = new Texture(Gdx.files.internal("Frost_transparent.png")) ;
                tankSprite = new Sprite(tankTexture , 0,0,499,282);
                tankSprite.setSize(100/TankStars.PPM,57/TankStars.PPM);

        }
        return tankSprite;
    }

    private void setTank2(){
        Texture tankTexture;
        Sprite tankSprite = tankSetup(tank2Type);
        tankSprite.flip(true,false);
        this.tank2= new Tank(this.box2dWorld,100,tankSprite,TankStars.V_WIDTH-100,250);
    }

    @Override
    public void show() {

    }
    public void handleInput(){

    }

    private float speed = 1000;

    public void update(float delta){
        //if(Gdx.input.isKeyPressed(Input.Keys.A)) {
        //    if(this.tankposition > 0)
        //    this.tankposition -= (int)(delta*speed);
        //}

//        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) ) {
//            tank1.box2dBody.setAngularVelocity(-speed);
//        }else {
//            tank1.box2dBody.setAngularVelocity(0f);
//        }
 if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
//            TiledMapTileLayer tlayer = (TiledMapTileLayer) tiledMap.getLayers().get(1);
//            StaticTiledMapTile st = new StaticTiledMapTile(
//                    new TextureRegion(new Texture("tr.png"), 10,10));
//
//            TiledMapTileLayer.Cell f = new TiledMapTileLayer.Cell();
//            f.setTile(st);
//            tlayer.setCell(randomx++,randomy,f);
            // deletion logic

            if( this.currentTank == tank1) {
                this.currentTank = tank2;
            }else {
                this.currentTank = tank1;
            }
     this.currentTank.setFuel(100);


        }

        hud.setFuel(currentTank.getFuel());

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)  ) {
            currentTank.moveLeft();
            hud.setHealthA(randomx++);
        } else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            currentTank.moveRight();
            hud.setHealthB(randomy++);
        }else{
            currentTank.stop();
        }
        box2dWorld.step(1/60f,6,2);

        cam.update();
        orthogonalTiledMapRenderer.setView(cam);

    }

    private static int randomy = 0;
    private static int randomx = 0;

    @Override
    public void render(float delta) {


        this.update(Gdx.graphics.getDeltaTime());
        this.cam.update();

        orthogonalTiledMapRenderer.setView(this.cam);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        orthogonalTiledMapRenderer.render();
        debugRenderer.render(box2dWorld,cam.combined);

        game.batch.setProjectionMatrix(this.hud.stage.getCamera().combined);
        this.hud.stage.draw();

        game.batch.begin();

        tank1.draw(this.game.batch);
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
        tank1.disposeTank();
        //texture1.dispose();
    }
}
