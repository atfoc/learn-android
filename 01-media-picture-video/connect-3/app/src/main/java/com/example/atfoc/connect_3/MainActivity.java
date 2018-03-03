package com.example.atfoc.connect_3;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Pair;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {

	private static String GAME_BUNDLE_KEY = "GAME_BUNDLE_KEY";
	private static String BTN_RESET_KEY = "BTN_RESET_KEY";

	private Game game;
	private ImageView[] ivs;
	private  Button btnReset;

	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		outState.putSerializable(GAME_BUNDLE_KEY, game);

		outState.putFloat(BTN_RESET_KEY, btnReset.getAlpha());
	}

	@SuppressWarnings("unchecked")
	private View.OnClickListener imageViewClickListener = new View.OnClickListener()
	{
		@Override
		public void onClick(View view)
		{

			Pair<Integer, Integer> p;

			p = (Pair<Integer, Integer>) view.getTag();


			int row = p.first;
			int col = p.second;

			int player = game.set(row,col);

			if(-1 == player)
			{
				return ;
			}

			int playerIcon;

			if(0 == player)
			{
				playerIcon = R.drawable.blue_tocken;
			}
			else
			{
				playerIcon = R.drawable.red_tocken;
			}

			ImageView iv = (ImageView)view;

			iv.setAlpha(0f);
			iv.setRotation(0);
			iv.setImageResource(playerIcon);
			iv.setBackgroundColor(getResources().getColor(R.color.white));
			iv.animate().alpha(1).rotation(360).setDuration(200);
			((ImageView)findViewById(R.id.nextPlayer)).setImageResource(game.playerTurn() == 0 ? R.drawable.blue_tocken : R.drawable.red_tocken);

			if(game.isEnd())
			{

				LayoutInflater li = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);

				View v = li.inflate(R.layout.popup_layout, null);

				final PopupWindow pw = new PopupWindow(v, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

				ImageView winner = v.findViewById(R.id.popupWinnerImage);

				if(-1 == game.winner())
				{
					winner.setImageResource(R.color.colorPrimaryDark);
				}
				else
				{
					winner.setImageResource(game.winner() == 0 ? R.drawable.blue_tocken : R.drawable.red_tocken);
				}

				((Button)v.findViewById(R.id.popupBtnClose)).setOnClickListener(new View.OnClickListener()
				{
					@Override
					public void onClick(View view)
					{
						((Button)findViewById(R.id.btnReset)).animate().alpha(1f).setDuration(500);
						pw.dismiss();
					}
				});

				((Button)v.findViewById(R.id.popupBtnReset)).setOnClickListener(new View.OnClickListener()
				{
					@Override
					public void onClick(View view)
					{
						reset();
						pw.dismiss();
					}
				});
				showWindow(pw, view);
			}
		}
	};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		ImageView iv = null;
		Drawable[] tmp = null;

		if(null != savedInstanceState)
		{
			game = (Game)savedInstanceState.getSerializable(GAME_BUNDLE_KEY);
		}
		else
		{
			game = new Game();
		}

		((ImageView)findViewById(R.id.nextPlayer)).setImageResource(game.getNextPlayer() == 1 ? R.drawable.red_tocken : R.drawable.blue_tocken);
		ivs = new ImageView[9];

		TypedArray position = getResources().obtainTypedArray(R.array.position_arr);

		int tmpPlayer = -1;

		for(int i = 0; i < position.length(); ++i)
		{
			iv = findViewById(position.getResourceId(i,-1));
			if(!game.isEnd())
			{
				iv.setOnClickListener(imageViewClickListener);
			}

			iv.setTag(Pair.create(i/3, i % 3));
			ivs[i] = iv;
			tmpPlayer = game.getPlayerAt(i/3, i%3);

			if(null != savedInstanceState)
			{
				if(0 == tmpPlayer)
				{
					iv.setImageResource(R.drawable.blue_tocken);
				}
				else if(1 == tmpPlayer)
				{
					iv.setImageResource(R.drawable.red_tocken);
				}

				iv.setBackgroundColor(getResources().getColor(R.color.white));
			}

		}

		btnReset = findViewById(R.id.btnReset);

		if(null != savedInstanceState)
		{
			btnReset.setAlpha(savedInstanceState.getFloat(BTN_RESET_KEY, 0));
		}

		btnReset.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				reset();
				view.animate().alpha(0f).setDuration(500);
			}
		});


	}
	private void reset()
	{

		for(ImageView iv : ivs)
		{
			iv.setImageResource(R.color.white);
			iv.setOnClickListener(imageViewClickListener);
		}

		game.reset();
		((ImageView)findViewById(R.id.nextPlayer)).setImageResource(R.drawable.blue_tocken);
	}

	private void showWindow(PopupWindow pw, View view)
	{
		for(ImageView iv : ivs)
		{
			iv.setOnClickListener(null);
		}

		pw.showAtLocation(view, Gravity.CENTER, 0, 0);

	}
}


