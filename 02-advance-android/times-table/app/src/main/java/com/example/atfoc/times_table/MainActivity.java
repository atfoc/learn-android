package com.example.atfoc.times_table;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
{

	private SeekBar sbNumberSelector;
	private ListView lvTimeTable;
	private ArrayList<Integer> numbers;
	private ArrayAdapter<Integer> numbersView;
	private TextView tvCurrentNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		sbNumberSelector = findViewById(R.id.numberSelector);
		lvTimeTable = findViewById(R.id.timesTable);
		tvCurrentNumber = findViewById(R.id.currentNumber);


		numbers = new ArrayList<>(20);
		numbersView = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, numbers);
		lvTimeTable.setAdapter(numbersView);

		makeTimeTable(sbNumberSelector.getProgress() + 1);

		sbNumberSelector.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
		{
			@Override
			public void onProgressChanged(SeekBar seekBar, int i, boolean b)
			{
				tvCurrentNumber.setText(Integer.toString(i+1));
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar)
			{

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar)
			{
				makeTimeTable(seekBar.getProgress()+1);
			}
		});
	}


	private void makeTimeTable(int base)
	{
		int size = numbers.size();
		for(int i = 1; i <= 20; ++i)
		{
			if(0 == size)
			{
				numbers.add(base * i);
			}
			else
			{
				numbers.set(i-1, base * i);
			}
		}
		numbersView.notifyDataSetChanged();
		Log.i("test", numbers.size() + " " + numbersView.getCount());
	}
}
