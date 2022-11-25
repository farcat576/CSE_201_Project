package com.mygdx.tankstars.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.tankstars.TankStars;

public class HUD {
    public Stage stage;
    private Viewport viewport;
    private int healthA;
    private int healthB;

    private Label healthALabel;
    private Label healthBLabel;

    public HUD(SpriteBatch sb){
        this.healthA=100;
        this.healthB=100;
        this.viewport=new FitViewport(TankStars.V_WIDTH,TankStars.V_HEIGHT,new OrthographicCamera());
        this.stage = new Stage(this.viewport,sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        healthALabel = new Label(String.format("%06d", healthA), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        healthBLabel = new Label(String.format("%06d", healthB), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(healthALabel).expandX().padTop(10);
        table.add(healthBLabel).expandX().padTop(10);
        stage.addActor(table);
    }
}
