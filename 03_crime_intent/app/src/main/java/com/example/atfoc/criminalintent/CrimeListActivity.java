package com.example.atfoc.criminalintent;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CrimeListActivity extends AppCompatActivity
{
	private FragmentManager fm;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_host);

		fm = getFragmentManager();

		Fragment f = fm.findFragmentById(R.id.activity_crime_fragment_container);

		if(null == f)
		{
			f = new CrimeListFragment();

			fm.beginTransaction().add(R.id.activity_crime_fragment_container, f).commit();
		}
	}
}
