package com.flappymansour.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;
/**
 * Created by Raggi on 5/26/2017.
 */

public class Tube {


   public static final int TUBE_WIDTH =44;

    private Rectangle boundsTop, boundsBot;
    private static final int Fluct = 120;
    private static final int TUBE_GAP = 100;
    private static final int Lowest_op = 107;
    private Texture topTube, bottomTube;
    private Vector2 posTopTube, posBotTube;
    private Random rand;

    public Tube(float x,int t)
    {

        topTube = new Texture("tubes/top/" +t +".png");
        bottomTube = new Texture("tubes/bot/bot.png");


        rand = new Random();
        posTopTube = new Vector2(x, rand.nextInt(Fluct) + TUBE_GAP + Lowest_op);
        posBotTube = new Vector2(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());

        boundsTop= new Rectangle(posTopTube.x,posTopTube.y,topTube.getWidth(),topTube.getHeight());
        boundsBot = new Rectangle (posBotTube.x,posBotTube.y,bottomTube.getWidth(),bottomTube.getHeight());

    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    public void reposition(float x){


        posTopTube.set(x, rand.nextInt(Fluct) + TUBE_GAP + Lowest_op);
        posBotTube.set(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());

    boundsTop.setPosition(posTopTube.x,posTopTube.y);
    boundsBot.setPosition(posBotTube.x,posBotTube.y);
    }
public boolean collides(Rectangle player)
{
    return player.overlaps(boundsTop) || player.overlaps(boundsBot);

}


    public void dispose()
    {
        topTube.dispose();
        bottomTube.dispose();


    }


}
