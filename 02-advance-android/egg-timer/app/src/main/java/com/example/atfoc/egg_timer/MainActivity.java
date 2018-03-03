package com.example.atfoc.egg_timer;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TimeUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity
{
	private SeekBar sbTime;
	private ProgressBar pbTimeLeft;
	private TextView tvTimeLeft;
	private Button btnStart;
	private CountDownTimer timer;

	private View.OnClickListener onClickBtnStart = new View.OnClickListener()
	{
		@Override
		public void onClick(View view)
		{
			btnStart.setText("stop");
			btnStart.setOnClickListener(onClickBtnStop);
			sbTime.setEnabled(false);
			long time = sbTime.getProgress();
			time = TimeUnit.MILLISECONDS.convert(time,TimeUnit.SECONDS);

			pbTimeLeft.setMax((int)time);
			pbTimeLeft.setProgress((int)time);

			setTimer(time);
		}
	};

	private View.OnClickListener onClickBtnStop = new View.OnClickListener()
	{
		@Override
		public void onClick(View view)
		{
			if(null != timer)
			{
				timer.cancel();
				timer = null;
				resetUi();
			}
		}
	};

	SeekBar.OnSeekBarChangeListener onSeekBarChangeSbTime = new SeekBar.OnSeekBarChangeListener()
	{
		public void onProgressChanged(SeekBar seekBar, int i, boolean b)
		{
			setTimeAsText(TimeUnit.MILLISECONDS.convert(i,TimeUnit.SECONDS));
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar)
		{

		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar)
		{

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		sbTime = findViewById(R.id.sbTime);
		pbTimeLeft = findViewById(R.id.pbTimeLeft);
		tvTimeLeft = findViewById(R.id.tvTimeLeft);
		btnStart = findViewById(R.id.btnStart);
		timer = null;

		btnStart.setOnClickListener(onClickBtnStart);
		sbTime.setOnSeekBarChangeListener(onSeekBarChangeSbTime);

	}

	private void setTimer(long time)
	{
		if(null == timer)
		{
			timer = new CountDownTimer(time, 900)
			{
				@Override
				public void onTick(long l)
				{
					setTimeAsText(l);
					pbTimeLeft.setProgress((int)l);
				}

				@Override
				public void onFinish()
				{

					setTimeAsText(0);
					pbTimeLeft.setProgress(0);
					new Handler().postDelayed(new Runnable()
					{
						@Override
						public void run()
						{
							resetUi();
						}
					}, 500);
				}
			};
			timer.start();
		}
	}

	private void setTimeAsText(long l)
	{
		long m = TimeUnit.MINUTES.convert(l, TimeUnit.MILLISECONDS);
		l -= TimeUnit.MILLISECONDS.convert(m, TimeUnit.MINUTES);
		long s = TimeUnit.SECONDS.convert(l, TimeUnit.MILLISECONDS);
		tvTimeLeft.setText(String.format("%d:%02d", m, s));
	}

	private void resetUi()
	{
		timer = null;
		btnStart.setText("start");
		btnStart.setOnClickListener(onClickBtnStart);
		sbTime.setEnabled(true);
		long time = sbTime.getProgress();
		time = TimeUnit.MILLISECONDS.convert(time, TimeUnit.SECONDS);
		setTimeAsText(time);
		pbTimeLeft.setMax((int)time);
		pbTimeLeft.setProgress((int)time);
	}
}

