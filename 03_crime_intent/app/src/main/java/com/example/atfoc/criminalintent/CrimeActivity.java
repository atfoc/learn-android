package com.example.atfoc.criminalintent;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CrimeActivity extends AppCompatActivity
{

	private FragmentManager fc;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_host);

		fc = getFragmentManager();

		Fragment f = fc.findFragmentById(R.id.activity_crime_fragment_container);

		if(null == f)
		{
			f = new CrimeFragment();
			fc.beginTransaction().add(R.id.activity_crime_fragment_container, f).commit();
		}
	}
}
