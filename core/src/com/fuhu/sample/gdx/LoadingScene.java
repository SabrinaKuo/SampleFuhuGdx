package com.fuhu.sample.gdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.fuhu.gdx.scene.Scene;
import com.fuhu.gdx.scene.SceneLayer;

/**
 * Created by sabrinakuo on 2016/4/28.
 */
public class LoadingScene extends com.fuhu.gdx.scene.loading.LoadingScene {
    private int dotsIndex = 0;
    String dots[] = {".", "..", "..."};
    BitmapFont font;
    String loadingStr = "Loading";
    Label label;
    private float totalElapsedTime;

    public LoadingScene() {
        super();
    }

    public LoadingScene(Scene targetScene){
        super(targetScene);
    }

    private void setUI() {
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("Roboto-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.color = Color.WHITE;
        parameter.size = 50;
        font = fontGenerator.generateFont(parameter);
        fontGenerator.dispose();

        Label.LabelStyle style = new Label.LabelStyle(font, Color.WHITE);
        Label label = new Label(loadingStr + dots[dotsIndex], style);

        SceneLayer layer = new SceneLayer();
        layer.addActor(label);
        layer.setAlign(Align.bottomLeft);
        layer.setSize(getWidth(), getHeight());
        this.addLayer(layer);

    }

    @Override
    public void render(float elapsedSeconds) {
        if(totalElapsedTime >= 0.5f) {
            dotsIndex = (dotsIndex + 1) % dots.length;
            label.setText(loadingStr + dots[dotsIndex]);
            totalElapsedTime = 0;

        }else {
            totalElapsedTime += elapsedSeconds;
        }
        super.render(elapsedSeconds);
    }

    @Override
    public void loadResources(AssetManager assetManager) {
        setUI();
    }

    @Override
    public void unloadResources(AssetManager assetManager) {
        super.unloadResources(assetManager);
    }
}
