package com.example.atfoc.second_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

	private Button btnStartActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }
}
