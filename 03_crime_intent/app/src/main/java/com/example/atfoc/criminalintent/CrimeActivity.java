package com.example.atfoc.criminalintent;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class CrimeActivity extends AppCompatActivity
{

	public static String sTAG_CRIME_UUDI_INTENT_ARG = CrimeActivity.class.getName() + ".TAG.CRIME.UUID.INTENT.ARG";
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
			Intent i = getIntent();
			UUID crimeId = (UUID)i.getSerializableExtra(sTAG_CRIME_UUDI_INTENT_ARG);
			f = CrimeFragment.newInstance(crimeId);
			fc.beginTransaction().add(R.id.activity_crime_fragment_container, f).commit();
		}
	}
}
