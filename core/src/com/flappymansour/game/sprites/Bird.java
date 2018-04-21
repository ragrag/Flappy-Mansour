package com.flappymansour.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

/**
 * Created by Raggi on 5/26/2017.
 */

public class Bird {

private Vector3 position;
private Vector3 velocity;

private Rectangle bounds;


private Animation birdAnimation;
    private Texture texture;
private static final int Gravity = -16;
    public int movement = 110;
private int score=0;


    Sound flap;

    public Bird(int x, int y)
    {
     position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);

        flap = Gdx.audio.newSound(Gdx.files.internal("sounds/20.ogg"));


        texture = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(texture),3,0.5F);
        bounds = new Rectangle(x,y,texture.getWidth()/3,texture.getHeight());
    }

public void update (float dt)
{
birdAnimation.update(dt);

if (position.y>0) {
    velocity.add(0, Gravity, 0);
}
    velocity.scl(dt);

    position.add(movement*dt,velocity.y,0);
if (position.y <0)
{
 position.y=0;
}
    velocity.scl(1/dt);
bounds.setPosition(position.x, position.y);
}



    public Vector3 getPosition() {
        return position;
    }

public Rectangle getBounds()
{
    return bounds;
}

    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }




    public void jump()
    {

        velocity.y = 250;
      flap.play();


    }



public void dispose()
{

    texture.dispose();
    flap.dispose();
}







}
