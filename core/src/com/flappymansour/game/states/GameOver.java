package com.flappymansour.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.flappymansour.game.AdHandler;
import com.flappymansour.game.flappyMansour;

import java.util.Random;

/**
 * Created by Raggi on 5/26/2017.
 */

public class GameOver extends State  {

    private BitmapFont font;
    private Texture background;

private int score;


private Music sound;
    public GameOver(GameStateManager gsm, int score) {


        super(gsm);

        cam.setToOrtho(false);
        Random s= new Random();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(25,25);
        sound = Gdx.audio.newMusic(Gdx.files.internal("sounds/" + s.nextInt(18)+ ".mp3"));

        sound.play();
        this.score = score;


        background = new Texture("gameover.png");

    }

    public void play()
    {
        sound.play();
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched())
        {
            gsm.set(new PlayState(gsm));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){

            gsm.set(new MenuState(gsm));

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

        font.draw(sb, String.valueOf(score), Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);


        sb.end();
    }


    @Override
    public void dispose() {
        background.dispose();

sound.dispose();



    }
}
