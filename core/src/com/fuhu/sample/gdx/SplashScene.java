package com.fuhu.sample.gdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.fuhu.gdx.scene.Scene;
import com.fuhu.gdx.scene.SceneLayer;

/**
 * Created by sabrinakuo on 2016/4/28.
 */
public class SplashScene extends Scene {

    SceneLayer layer;

    public SplashScene() {
        Pixmap bgPix = new Pixmap((int)getWidth(), (int)getHeight(), Pixmap.Format.RGBA8888);
        bgPix.setColor(Color.WHITE);
        bgPix.fillRectangle(0, 0, bgPix.getWidth(), bgPix.getHeight());
        Image bkgImage = new Image(manage(new Texture(bgPix)));
        bgPix.dispose();

        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(
                Gdx.files.internal("Roboto-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 130;
        parameter.color = Color.BLACK;

        BitmapFont font = manage(fontGenerator.generateFont(parameter));
        for (TextureRegion region : font.getRegions()){
            region.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        fontGenerator.dispose();

        Label.LabelStyle style = new Label.LabelStyle(font, Color.BLACK);
        Label label = new Label("Fuhu", style);
        label.setPosition( (getWidth() - label.getPrefWidth()) / 2, (getHeight() - label.getPrefHeight()) / 2);

        layer = new SceneLayer();
        layer.setSize(getWidth(), getHeight());
        layer.addActor(bkgImage);
        layer.addActor(label);
        addLayer(layer);
    }

    @Override
    public void enter() {
        layer.addAction(Actions.sequence(Actions.delay(2f), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        getGame().setScene(new LoadingScene(new MenuScene()));
                    }
                })

        ));
        super.enter();
    }
}
