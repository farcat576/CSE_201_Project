package com.mygdx.tankstars;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.EllipseMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.physics.box2d.*;

public class Level {
    private World box2dWorld;
    private TiledMap tmap;
    public Level(World world, TiledMap tiledMap) {
        this.box2dWorld = world;


        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        CircleShape cshape = new CircleShape();
        FixtureDef fdef = new FixtureDef();
        Body body ;

        for(MapObject object: tiledMap.getLayers().get(2).getObjects().getByType(EllipseMapObject.class) ){
            Ellipse ellipse = ( (EllipseMapObject) object).getEllipse();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set( (ellipse.x + ellipse.width/2) / TankStars.PPM
                    , ( ellipse.y + ellipse.height /2 ) / TankStars.PPM) ;

            body = box2dWorld.createBody(bdef);
            cshape.setRadius( ellipse.width/2 / TankStars.PPM);
            fdef.shape = cshape;
            body.createFixture(fdef);
        }

    }
}
