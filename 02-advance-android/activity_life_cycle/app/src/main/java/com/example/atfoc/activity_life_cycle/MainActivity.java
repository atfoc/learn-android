package com.example.atfoc.activity_life_cycle;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity
{

	private static String debugTag = MainActivity.class.getName();
	private static String btnClickMeKey= "btnClickMe";

	private Button btnClickMe;
	private CountDownTimer t;

	@Override
	protected void onStart()
	{
		super.onStart();
		Log.d(debugTag, "--------------------");
		Log.d(debugTag, "onStart()");
	}

	@Override
	protected void onStop()
	{
		super.onStop();
		Log.d(debugTag, "--------------------");
		Log.d(debugTag, "onStop()");
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		Log.d(debugTag, "--------------------");
		Log.d(debugTag, "onDestroy()");
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		Log.d(debugTag, "--------------------");
		Log.d(debugTag, "onPause()");

		t.cancel();
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		Log.d(debugTag, "--------------------");
		Log.d(debugTag, "onResume()");
		t.start();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		Log.d(debugTag, "--------------------");
		Log.d(debugTag, "onSavedInstance()");
		outState.putCharSequence(btnClickMeKey, btnClickMe.getText());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Log.d(debugTag, "--------------------");
		Log.d(debugTag, "onCreate()");
		setContentView(R.layout.activity_main);
		t = new CountDownTimer(5000, 900)
		{
			@Override
			public void onTick(long l)
			{
				Log.d(debugTag + "Timer", "tick");
			}

			@Override
			public void onFinish()
			{
				Log.d(debugTag + "Timer", "finish");
			}
		};



		btnClickMe = findViewById(R.id.btn_popup);
		if(null != savedInstanceState)
		{
			btnClickMe.setText(savedInstanceState.getCharSequence(btnClickMeKey, getString(R.string.btnClickMeText)));
		}
		btnClickMe.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				btnClickMe.setText("cao");
			}
		});

	}
}
