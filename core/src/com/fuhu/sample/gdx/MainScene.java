package com.fuhu.sample.gdx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.fuhu.gdx.scene.Scene;
import com.fuhu.gdx.scene.SceneLayer;

/**
 * Created by sabrinakuo on 2016/4/29.
 */
public class MainScene extends Scene {

    public MainScene(){
        Pixmap bgPix = new Pixmap((int)getWidth(), (int)getHeight(), Pixmap.Format.RGBA8888);
        bgPix.setColor(Color.WHITE);
        bgPix.fillRectangle(0, 0, bgPix.getWidth(), bgPix.getHeight());
        Image bkgImage = new Image(manage(new Texture(bgPix)));
        bgPix.dispose();

        SceneLayer layer = new SceneLayer();
        layer.setSize(getWidth(), getHeight());
        layer.addActor(bkgImage);

        addLayer(layer);
    }
}
