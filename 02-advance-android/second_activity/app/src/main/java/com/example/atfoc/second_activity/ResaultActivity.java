package com.example.atfoc.second_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ResaultActivity extends AppCompatActivity
{

	private Button btnSendMsg;
	private EditText etMsg;
	private int numbCall;

	static String TAG_MSG_RESAULT = ResaultActivity.class.getName() + ".TAG_MSG_RESAULT";
	private String TAG_ETMSG_TEXT = ResaultActivity.class.getName() + ".TAG_ETMSG_TEXT";


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_resault);

		numbCall = getIntent().getIntExtra(MainActivity.TAG_EXTRA_NUMBER_CALLED,0);

		btnSendMsg = findViewById(R.id.activity_resault_btn_send_msg);
		etMsg = findViewById(R.id.activity_resault_edit_text_msg);

		if(null != savedInstanceState)
		{
			etMsg.setText(savedInstanceState.getString(TAG_ETMSG_TEXT,""));
		}

		btnSendMsg.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				Intent i = new Intent();
				i.putExtra(TAG_MSG_RESAULT,String.valueOf(numbCall)  + etMsg.getText());
				setResult(RESULT_OK, i);
				finish();
			}
		});

	}

	@Override
	protected void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);

		outState.putString(TAG_ETMSG_TEXT, etMsg.getText().toString());
	}
}
