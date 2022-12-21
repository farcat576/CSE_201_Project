package com.mygdx.tankstars;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;

public class Tank extends Sprite {

    public World game_world;;
    public Body box2dBody;
    protected int health;
    protected Sprite img;
    protected Vector2 position;

    public Tank(World world,int health,Sprite img,
                float x_position,float y_position){
        this.game_world = world;
        this.health=health;
        this.img=img;
        this.defineTank(x_position/TankStars.PPM,y_position/TankStars.PPM);
        this.position = new Vector2(0,0);
        this.position.x = x_position ;
        this.position.y = y_position ;
        img.setPosition(x_position,y_position);

    }

    public void draw(SpriteBatch batch){
        this.position.x = this.box2dBody.getPosition().x * TankStars.PPM;
        this.position.y = this.box2dBody.getPosition().y * TankStars.PPM;
        this.img.setPosition(this.position.x,this.position.y);
        Gdx.app.log(String.valueOf(img.getX()),"x");
        Gdx.app.log(String.valueOf(img.getY()),"y");
        this.img.draw(batch);
    }

    private CircleShape tank_shape;
    public void defineTank(float x_pos,float y_pos){
        BodyDef abrams_def = new BodyDef();
        abrams_def.position.set(x_pos ,y_pos );
        abrams_def.type = BodyDef.BodyType.DynamicBody;
        box2dBody = game_world.createBody(abrams_def);

        FixtureDef fdef = new FixtureDef();
        tank_shape = new CircleShape();
        tank_shape.setRadius(radius/TankStars.PPM);
        fdef.shape = tank_shape;

        fdef.friction=10000f;
        box2dBody.createFixture(fdef);
    }

    private static float tankSpeed = 6f ;

    public void moveRight(){
        if( (box2dBody.getPosition().x + (this.getWidth()/2/TankStars.PPM)
        < TankStars.V_WIDTH/ TankStars.PPM )
        && this.Fuel > 0 ){
            box2dBody.setAngularVelocity(-this.tankSpeed);
//            this.position = box2dBody.getPosition();
            this.Fuel--;
        }else {
            box2dBody.setAngularVelocity(0f);
        }
    }
public void moveLeft(){
        if( (box2dBody.getPosition().x - (this.getWidth()/2/TankStars.PPM)
                > 0f ) && (this.Fuel>0)) {
            box2dBody.setAngularVelocity(this.tankSpeed);
//            this.position = box2dBody.getPosition();
            this.Fuel--;
        }else {
            box2dBody.setAngularVelocity(0f);
        }
    }
    public static float radius=15;

    public void setFuel(int fuel){
        this.Fuel = fuel;
    }
    private int Fuel;
    public int getFuel(){
        return Fuel;
    }

    @Override
    public float getWidth() {
        return this.radius;
    }



    public void disposeTank(){
        tank_shape.dispose();
    };

    public void stop() {
        box2dBody.setAngularVelocity(0f);
    }
}
