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

	private Game game;
	private ImageView[] ivs;

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
		game = new Game();
		ivs = new ImageView[9];

		TypedArray position = getResources().obtainTypedArray(R.array.position_arr);

		for(int i = 0; i < position.length(); ++i)
		{
			iv = findViewById(position.getResourceId(i,-1));
			iv.setOnClickListener(imageViewClickListener);
			iv.setTag(Pair.create(i/3, i % 3));
			ivs[i] = iv;
		}

		((Button)findViewById(R.id.btnReset)).setOnClickListener(new View.OnClickListener()
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


