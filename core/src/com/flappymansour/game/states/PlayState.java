package com.flappymansour.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.flappymansour.game.AdHandler;
import com.flappymansour.game.flappyMansour;
import com.flappymansour.game.sprites.Bird;
import com.flappymansour.game.sprites.Tube;

import java.util.Random;

/**
 * Created by Raggi on 5/26/2017.
 */

public class PlayState extends State {

    private static final int TUBE_SPACING = 123;
    private static final int TUBE_COUNT = 5;
    private static final int GROUND_Y_OFFSET = -30;


    private BitmapFont font;
    private Bird bird;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    private Array<Tube> tubes;
    private int score = 0;
    private Music sound1;


    protected PlayState(GameStateManager gsm) {
        super(gsm);

        bird = new Bird(50,200);
        cam.setToOrtho(false, flappyMansour.WIDTH/2, flappyMansour.HEIGHT/2);
        bg = new Texture("bg2.png");
        ground = new Texture("ground.png");
        font = new BitmapFont();
        font.setColor(Color.WHITE);


        sound1 = Gdx.audio.newMusic(Gdx.files.internal("sounds/6.mp3"));


        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth/2,GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth/2)+ground.getWidth(),GROUND_Y_OFFSET);
        tubes = new Array<Tube>();



        for (int i=1;i<=TUBE_COUNT;i++)
        {
            Random tr = new Random();

            tubes.add(new Tube(i* (TUBE_SPACING + Tube.TUBE_WIDTH), tr.nextInt(8)));

        }
    }





    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched())
        {
            bird.jump();
        score++;

            if (score %25 ==0) {
           sound1.play();
            }


            if (score % 9 ==0)
                bird.movement+=30;
        }


        if (Gdx.input.isKeyPressed(Input.Keys.BACK)){
            gsm.set(new MenuState(gsm));

        }

        Gdx.input.setCatchBackKey(true);

    }

    @Override
    public void update(float dt) {
    handleInput();
        updateGround();
        bird.update(dt);
        cam.position.x= bird.getPosition().x+80;
        for (int i=0;i<tubes.size;i++){
            Tube tube = tubes.get(i);
            if((cam.position.x - (cam.viewportWidth/2)) > (tube.getPosTopTube().x + tube.getTopTube().getWidth())){
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING)* TUBE_COUNT));}
            if (tube.collides(bird.getBounds()))
            {
                gsm.set(new GameOver(gsm, score));
            }



        }



        if(bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET)
            gsm.set(new GameOver(gsm, score));
        if (bird.getPosition().x > 100)

        System.out.println(score);
    cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {


        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        sb.draw(bg, cam.position.x- (cam.viewportWidth/2), 0 );
        sb.draw(bird.getTexture(), bird.getPosition().x,bird.getPosition().y);
       for (Tube tube:tubes) {
           sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
           sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
       }
        font.draw(sb, String.valueOf(score),  bird.getPosition().x,bird.getPosition().y);
        sb.draw(ground, groundPos1.x,groundPos1.y);
        sb.draw(ground, groundPos2.x,groundPos2.y);
        sb.end();

    }


    private void updateGround()
    {

        if((cam.position.x - (cam.viewportWidth/2) > groundPos1.x + ground.getWidth()))
        {
            groundPos1.add(ground.getWidth()*2,0);
        }
        if((cam.position.x - (cam.viewportWidth/2) > groundPos2.x + ground.getWidth()))
        {
            groundPos2.add(ground.getWidth()*2,0);
        }

    }



    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        for(Tube tube :tubes)
            tube.dispose();

        ground.dispose();
        font.dispose();
    sound1.dispose();
    }
}
