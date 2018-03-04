package com.example.atfoc.second_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


	static String TAG_EXTRA_NUMBER_CALLED = MainActivity.class.getName() + ".TAG_EXTRA_NUMBER_CALLED";
	private static String TAG_NUMCALLED_VALUE = MainActivity.class.getName() + ".TAG_NUMCALLED_VALUE";

	private int numCalled;
	private Button btnStartActivity;
	private Button btnStartActivityResault;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(null != savedInstanceState)
		{
			numCalled = savedInstanceState.getInt(TAG_NUMCALLED_VALUE);
		}
		else
		{
			numCalled = 0;
		}

        btnStartActivity = (Button)findViewById(R.id.activity_main_btn_start_activity);

        btnStartActivity.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
				Intent i = new Intent(getApplicationContext(), SecondActivity.class);
				startActivity(i);
            }
        });

        btnStartActivityResault = findViewById(R.id.activity_main_btn_start_activity1);

        btnStartActivityResault.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				Intent i = new Intent(getApplicationContext(), ResaultActivity.class);

				i.putExtra(TAG_EXTRA_NUMBER_CALLED, numCalled);
				++numCalled;

				startActivityForResult(i, 0);
			}
		});
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);

		if(RESULT_OK != resultCode || requestCode != 0)
		{
			return ;
		}

		String msg = data.getStringExtra(ResaultActivity.TAG_MSG_RESAULT);

		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		outState.putInt(TAG_NUMCALLED_VALUE, numCalled);
	}
}
