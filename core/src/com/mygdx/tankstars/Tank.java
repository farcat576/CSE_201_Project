package com.mygdx.tankstars;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public abstract class Tank {
    protected Projectile weapons[];
    protected int health;
    protected Sprite img;
    protected String description;
    protected Vector2 position;

    public Tank(Projectile weapons[],int health,Texture img,String description){
        this.weapons=weapons;
        this.health=health;
        this.img=new Sprite(img);
        this.description=description;
    }

    public void draw(SpriteBatch batch){
        this.img.setPosition(this.position.x,this.position.y);
        this.img.draw(batch);
    }
}
