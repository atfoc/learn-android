package com.example.atfoc.criminalintent;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.view.ViewPager;
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
		ViewPager mVp = new ViewPager(this);
		mVp.setId(R.id.activity_crime_page_viewer);
		setContentView(mVp);

		fc = getFragmentManager();
		mVp.setAdapter(new CrimePagerAdapter(fc, this));

		Intent i = getIntent();
		UUID crimeId = (UUID)i.getSerializableExtra(sTAG_CRIME_UUDI_INTENT_ARG);

		mVp.setCurrentItem(CrimeLab.getInstance(this).getIndexFromId(crimeId));

	}
}
