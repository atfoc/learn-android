package com.example.atfoc.criminalintent;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentStatePagerAdapter;

public class CrimePagerAdapter extends FragmentStatePagerAdapter
{
	private CrimeLab mCrimeLab;

	public CrimePagerAdapter(FragmentManager fm, Context c)
	{
		super(fm);
		mCrimeLab = CrimeLab.getInstance(c);
	}

	@Override
	public int getCount()
	{
		return mCrimeLab.getSize();
	}


	@Override
	public Fragment getItem(int position)
	{
		CrimeFragment f = CrimeFragment.newInstance(mCrimeLab.getCrime(position).getId());

		return f;
	}
}
