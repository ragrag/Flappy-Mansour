package com.flappymansour.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.flappymansour.game.states.GameOver;
import com.flappymansour.game.states.GameStateManager;
import com.flappymansour.game.states.MenuState;
import com.flappymansour.game.states.PlayState;

public class flappyMansour extends ApplicationAdapter{
	public static final int WIDTH =480;
		public static final int HEIGHT = 800;
		public static final String TITLE = "Flappy Mansour";
private Music music;
AdHandler handler;


	public flappyMansour(AdHandler handler)
	{

this.handler = handler;


	}



	private GameStateManager gsm;


	public SpriteBatch batch;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm= new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {


		if (gsm.states.peek() instanceof GameOver){
			handler.showAds(true);

		}

		if (gsm.states.peek() instanceof MenuState){
			handler.showAds(true);

		}
		if (gsm.states.peek() instanceof PlayState){

			handler.showAds(false);


		}


		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);

	}
	
	@Override
	public void dispose () {

		super.dispose();
		batch.dispose();
		music.dispose();

	}
}
