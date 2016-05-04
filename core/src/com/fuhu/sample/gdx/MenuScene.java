package com.fuhu.sample.gdx;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.fuhu.gdx.scene.Scene;
import com.fuhu.gdx.scene.SceneLayer;
import com.fuhu.gdx.scene.transition.BottomInTransition;
import com.fuhu.gdx.scene.transition.TopOutTransition;
import com.fuhu.gdx.ui.SimpleButton;

/**
 * Created by sabrinakuo on 2016/4/28.
 */
public class MenuScene extends Scene {

    SceneLayer btnLayer;

    @Override
    public void loadResources(AssetManager assetManager) {
        assetManager.load("landing_background.png", Texture.class);
        assetManager.load("landing_button_play.png", Texture.class);
    }

    @Override
    public void loadResourcesComplete(AssetManager assetManager) {
        Texture bkg = assetManager.get("landing_background.png");
        setBackgroundImage(new Image(bkg));

        Texture playBtnTex = assetManager.get("landing_button_play.png");
        SimpleButton playButton = new SimpleButton(new TextureRegionDrawable(new TextureRegion(playBtnTex)));
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Scene newScene = new MainScene();
                setInTransition(new BottomInTransition(newScene, 0.2f));
                setOutTransition(new TopOutTransition(MenuScene.this, 0.2f));
                getGame().pushScene(newScene);
//                getGame().pushScene(new MainScene());
            }
        });

        btnLayer = new SceneLayer();
        btnLayer.addActor(playButton);
        playButton.setPosition((getWidth() - playButton.getWidth()) / 2, 170);
        addLayer(btnLayer);
    }

    @Override
    public void unloadResources(AssetManager assetManager) {
        assetManager.unload("landing_background.png");
        assetManager.unload("landing_button_play.png");
        btnLayer.remove();
    }
}
