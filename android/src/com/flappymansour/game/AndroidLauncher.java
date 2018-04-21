package com.flappymansour.game;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.flappymansour.game.flappyMansour;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class AndroidLauncher extends AndroidApplication implements AdHandler{
private final int SHOW_ADS = 1;
	private final int HIDE_ADS = 0;
	private static final String TAG = "AndroidLauncher";
protected AdView adview;
protected	InterstitialAd mInterstitialAd;


	Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg){
			switch(msg.what){
				case SHOW_ADS:
					adview.setVisibility(View.VISIBLE);
					break;
				case HIDE_ADS:
					adview.setVisibility(View.GONE);
					break;

			}
		}


	};



	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		RelativeLayout layout = new RelativeLayout(this);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		View gameView = initializeForView(new flappyMansour(this),config);
		layout.addView(gameView);


		mInterstitialAd = new InterstitialAd(this);


		mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
		AdRequest adRequest2 = new AdRequest.Builder().build();
		mInterstitialAd.loadAd(adRequest2);

		mInterstitialAd.setAdListener(new AdListener() {
			public void onAdLoaded() {
				showInterstitial();
			}
		});

		adview = new AdView(this);
		adview.setAdListener(new AdListener() {
			@Override
		public void onAdLoaded() {
				int visibility = adview.getVisibility();
				adview.setVisibility(AdView.GONE);
				adview.setVisibility(visibility);
				Log.i(TAG, "Ad Loaded..");
			}
		});
		adview.setAdSize(AdSize.SMART_BANNER);
	adview.setAdUnitId("ca-app-pub-5954656219348211/5751251888");
AdRequest.Builder builder = new AdRequest.Builder();

		RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT

		);
layout.addView(adview, adParams);
		adview.loadAd(builder.build());
		setContentView(layout);
	}


	private void showInterstitial() {
		if (mInterstitialAd.isLoaded()) {
			mInterstitialAd.show();
		}
	}


	@Override
	public void showAds(boolean show) {
	handler.sendEmptyMessage(show ? SHOW_ADS : HIDE_ADS);
	}
}
