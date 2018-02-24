package com.example.atfoc.connect_3;

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
				winner.setImageResource(game.winner() == 0 ? R.drawable.blue_tocken : R.drawable.red_tocken);

				((Button)v.findViewById(R.id.popupBtnClose)).setOnClickListener(new View.OnClickListener()
				{
					@Override
					public void onClick(View view)
					{
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
				pw.showAtLocation(view, Gravity.CENTER, 0, 0);
			}
		}
	};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		ImageView iv = null;
		game = new Game();

        iv  = findViewById(R.id.position0);
        iv.setOnClickListener(imageViewClickListener);
    	iv.setTag(Pair.create(0,0));

		iv = findViewById(R.id.position1);
		iv.setOnClickListener(imageViewClickListener);
		iv.setTag(Pair.create(0,1));

		iv = findViewById(R.id.position2);
		iv.setOnClickListener(imageViewClickListener);
		iv.setTag(Pair.create(0,2));

		iv = findViewById(R.id.position3);
		iv.setOnClickListener(imageViewClickListener);
		iv.setTag(Pair.create(1,0));

		iv = findViewById(R.id.position4);
		iv.setOnClickListener(imageViewClickListener);
		iv.setTag(Pair.create(1,1));

		iv = findViewById(R.id.position5);
		iv.setOnClickListener(imageViewClickListener);
		iv.setTag(Pair.create(1,2));

		iv = findViewById(R.id.position6);
		iv.setOnClickListener(imageViewClickListener);
		iv.setTag(Pair.create(2,0));

		iv = findViewById(R.id.position7);
		iv.setOnClickListener(imageViewClickListener);
		iv.setTag(Pair.create(2,1));

		iv = findViewById(R.id.position8);
		iv.setOnClickListener(imageViewClickListener);
		iv.setTag(Pair.create(2,2));
	}
	private void reset()
	{
		ImageView iv = null;

		iv = findViewById(R.id.position0);
		iv.setImageResource(R.color.white);

		iv = findViewById(R.id.position1);
		iv.setImageResource(R.color.white);

		iv = findViewById(R.id.position2);
		iv.setImageResource(R.color.white);

		iv = findViewById(R.id.position3);
		iv.setImageResource(R.color.white);

		iv = findViewById(R.id.position4);
		iv.setImageResource(R.color.white);

		iv = findViewById(R.id.position5);
		iv.setImageResource(R.color.white);

		iv = findViewById(R.id.position6);
		iv.setImageResource(R.color.white);

		iv = findViewById(R.id.position7);
		iv.setImageResource(R.color.white);

		iv = findViewById(R.id.position8);
		iv.setImageResource(R.color.white);

		game.reset();
		((ImageView)findViewById(R.id.nextPlayer)).setImageResource(R.drawable.blue_tocken);
	}
}
