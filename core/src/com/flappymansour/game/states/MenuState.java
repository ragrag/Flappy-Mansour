package com.flappymansour.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flappymansour.game.AdHandler;
import com.flappymansour.game.flappyMansour;

import java.util.Random;

/**
 * Created by Raggi on 5/26/2017.
 */

public class MenuState extends State {

    private Texture background;

private Music sound;


    public MenuState(GameStateManager gsm) {

        super(gsm);

        cam.setToOrtho(false);
        Random s= new Random();
        sound = Gdx.audio.newMusic(Gdx.files.internal("sounds/menu/" + s.nextInt(3)+ ".mp3"));
        sound.play();
        background = new Texture("bg.png");

    }

    @Override
  public void handleInput() {
    if(Gdx.input.justTouched())
    {
        gsm.set(new PlayState(gsm));

    }
    }

    @Override
    public void update(float dt) {
        handleInput();


    }


    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        sb.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());




        sb.end();
    }


    @Override
    public void dispose() {
        background.dispose();



    }

}
